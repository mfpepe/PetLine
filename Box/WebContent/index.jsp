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

var audio_context;
var recorder;
var webSocket;

var timeoutRecord;
var puedeGrabar = false;
var initRecord = true;

function init(){
 	try {

	    window.AudioContext = window.AudioContext || window.webkitAudioContext;

	    window.navigator.getUserMedia = ( navigator.mozGetUserMedia || navigator.getUserMedia ||
                   navigator.webkitGetUserMedia || navigator.msGetUserMedia);			    
	    
	    window.URL = window.URL || window.webkitURL;
	     
	    audio_context = new AudioContext;

	    navigator.getUserMedia({audio: true}, startUserMedia, stopUserMedia);
	    
	} catch (e) {
    	puedeGrabar = false;
     	alert('No web audio support in this browser!');
   	} 
}	
	
function stopUserMedia(){
	puedeGrabar = false;
}	
	
function startUserMedia(stream) {
	puedeGrabar = true;
	
	document.getElementById("audioRecord").src="<%= BoxUtils.getURL() %>img/record.png";
	

   	var microphone = audio_context.createMediaStreamSource(stream);
   	window.source = microphone;  // Workaround for: https://bugzilla.mozilla.org/show_bug.cgi?id=934512
   	var script_processor = audio_context.createScriptProcessor(1024, 1, 1);   	
  
   	recorder = new Recorder(microphone);
   	
   	openSocket();
}

function openSocket(){
	webSocket = new WebSocket('ws://<%= BoxUtils.getURLWS() %>/Box/websocket');
	webSocket.binaryType = "arraybuffer";
}

function recordAudio(){
	if(puedeGrabar){
		if(initRecord){
			startRecording();
			initRecord = false;
		}
		else{
			stopRecording();
			initRecord = true;
		}				
	}
}

function startRecording() {
	document.getElementById("audioRecord").src="<%= BoxUtils.getURL() %>img/stoprecord.png";
	recorder && recorder.record();
   	timeoutRecord = setTimeout(function(){ stopRecording(); }, 15000);
}

function stopRecording() {
	document.getElementById("audioRecord").src="<%= BoxUtils.getURL() %>img/record.png";
	clearTimeout(timeoutRecord);
   	recorder && recorder.stop();
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
		
  </script>
  <script src="<%= BoxUtils.getURL() %>js/recorder.js"></script>
</head>
<body style="background-image:url('./img/fondo.png');" onload="init();">
	<form method="post" name="form" id="form" action="TakePictureAction.do">
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
 					<%-- <video id="sampleMovie" width="640" height="360" controls>
						<source src="<%= BoxUtils.getURLCamera() %>" type="video/mp4" />
					</video> --%>
				<%-- 	
					<video  id="sampleMovie" width="640" height="480" controls autoplay="autoplay">
					  <source src="<%= BoxUtils.getURLCamera() %>" type='video/webm; codecs="vp8, vorbis"' />
					</video> --%>
						
  		<%-- 			<video id="sampleMovie" width="640" height="480" controls autoplay="autoplay">
						<source src="<%= BoxUtils.getURLCamera() %>"  />
					</video> 	 --%>			
				</td>
				<!-- <td valign=top height=30px><button id=start name=start class="buttons" onclick="startRecording();">record</button>&nbsp;<button id=stop name=stop class="buttons" onclick="stopRecording();" disabled>stop</button></td> -->
				<td  valign=top height=30px><img id="audioRecord" src="<%= BoxUtils.getURL() %>img/norecord.png" onclick="javascript:recordAudio();" style="cursor: pointer;"></td>
			<tr>
			<tr>
				<td  valign=top ><img src="<%= BoxUtils.getURL() %>img/picture.png" onclick="document.form.submit();" style="cursor: pointer;"></td>
			<tr>			
		</table>
	</form>
</body>
</html>
<%
	}
	else{
		response.sendRedirect("error.jsp");
	}
%>