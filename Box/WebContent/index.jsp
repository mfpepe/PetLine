<!DOCTYPE html>
<%@page import="box.utils.BoxUtils"%>
<%@page import="box.utils.TokenGenerator"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%
	String message = request.getParameter("message");

	if( !StringUtils.isEmpty(message) && TokenGenerator.validateToken(message) ){
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Box's</title>
	<link rel="stylesheet" type="text/css" href="<%= BoxUtils.getURL() %>css/PetLine.css">
	<script>
		var timeoutRecord;
		 
		function __log(e, data) {
			//log.innerHTML += "\n" + e + " " + (data || '');
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
		  
		var webSocket;
		
		function openSocket(){
			webSocket = new WebSocket('ws://<%= BoxUtils.getURLWS() %>/Box/websocket');
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
  <script src="<%= BoxUtils.getURL() %>js/recorder.js"></script>
</head>
<body style="background-image:url('./img/fondo.png');">
		<p class="title">Box View</p>
		<br>
		<table class=table2>
			<tr>
				<td rowspan=3 width=640px>
					<OBJECT ID="MediaPlayer" WIDTH="640" HEIGHT="480" TYPE="application/x-oleobject">
						<param name="movie" value="<%= BoxUtils.getURLCamera() %>">
						<PARAM name="autostart" VALUE="true">
						<PARAM name="ShowControls" VALUE="true">
						<param name="ShowStatusBar" value="true">
						<PARAM name="ShowDisplay" VALUE="true">
						<EMBED TYPE="application/x-mplayer2" src="<%= BoxUtils.getURLCamera() %>" NAME="MediaPlayer"
								WIDTH="640" HEIGHT="480" ShowControls="1" ShowStatusBar="1" ShowDisplay="1" autostart="1"> 
						</EMBED>
					</OBJECT>				
				</td>
				<td valign=top height=30px><button id=start name=start class="buttons" onclick="startRecording();">record</button>&nbsp;<button id=stop name=stop class="buttons" onclick="stopRecording();" disabled>stop</button></td>
			<tr>
			<tr>
				<td  valign=top ><input type="button" class="buttons" value="Tomar Foto" onclick="alert('Se tomo la fortografia exitosamente');"></td>
			<tr>			
		</table>
</body>
</html>
<%
	}
	else{
		response.sendRedirect("error.jsp");
	}
%>