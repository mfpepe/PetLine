<!DOCTYPE html>
<%@page import="box.utils.TokenGenerator"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	String message = request.getParameter("message");

	if( !StringUtils.isEmpty(message) && TokenGenerator.validateToken(message) ){
%>


<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Box</title>
</head>
<body>

  <h1>Box</h1>

  <button id=start name=start onclick="startRecording();">record</button>
  <button id=stop name=stop onclick="stopRecording();" disabled>stop</button>
  
  <h2>Recordings</h2>
  <ul id="recordingslist"></ul>
  
  <h2>Log</h2>
  <pre id="log"></pre>

  <script>
  
  var timeoutRecord;
  
  function __log(e, data) {
    log.innerHTML += "\n" + e + " " + (data || '');
  }

  var audio_context;
  var recorder;

  function startUserMedia(stream) {
    var input = audio_context.createMediaStreamSource(stream);
    __log('Media stream created.');

    // Uncomment if you want the audio to feedback directly
    //input.connect(audio_context.destination);
    //__log('Input connected to audio context destination.');
    
    recorder = new Recorder(input);
    __log('Recorder initialised.');
  }

  function startRecording() {
    recorder && recorder.record();
    document.getElementById("start").disabled = true;
    document.getElementById("start").nextElementSibling.disabled = false;
    timeoutRecord = setTimeout(function(){ stopRecording(); }, 15000);
    __log('Recording...');
  }

  function stopRecording() {
	clearTimeout(timeoutRecord);
    recorder && recorder.stop();
    document.getElementById("stop").disabled = true;
    document.getElementById("stop").previousElementSibling.disabled = false;
    __log('Stopped recording.');
    
    // create WAV download link using audio data blob
    createDownloadLink();
    
    recorder.clear();
  }

  function createDownloadLink() {
    recorder && recorder.exportWAV(function(blob) {

      webSocket.send("start");
      
      webSocket.send(blob);
	  
      webSocket.send("stop");
      
    });
  }

  window.onload = function init() {

    try {
      // webkit shim
      window.AudioContext = window.AudioContext || window.webkitAudioContext;
      navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia;
      window.URL = window.URL || window.webkitURL;
      
      audio_context = new AudioContext;
      __log('Audio context set up.');
      __log('navigator.getUserMedia ' + (navigator.getUserMedia ? 'available.' : 'not present!'));
    } catch (e) {
      document.getElementById("start").disabled = true;
      alert('No web audio support in this browser!');
    }
    
    navigator.getUserMedia({audio: true}, startUserMedia, function(e) {
      __log('No live audio input: ' + e);
      document.getElementById("start").disabled = true;
    });
    
    openSocket();
  };
  
///////////////////////////
	var webSocket;

	function openSocket(){
		
		webSocket = new WebSocket('ws://localhost:8080/WS/websocket');
		webSocket.binaryType = "arraybuffer";
		
		webSocket.onerror = function(event) {
			onErrorWS(event);
		};

		webSocket.onopen = function(event) {
			onOpenWS(event);
		};

		webSocket.onmessage = function(event) {
			onMessageWS(event);
		};
	}

	function onMessageWS(event) {
		//var json = JSON.parse(event.data);
		__log( '<br />Received server response!' + '<br />Message: ' + event.data);
	}

	function onOpenWS(event) {
		__log('Connection established');
	}

	function onErrorWS(event) {
		__log('Error');
	}  
  </script>

  <script src="./js/recorder.js"></script>
</body>
</html>
<%
	}
	else{
		response.sendRedirect("error.jsp");
	}
%>