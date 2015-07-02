<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
		<p class="title">Box View Casa</p>
		<br>
		<table class=table2>
			<tr>
				<td rowspan=3 width=300px><img src="<%= PetLineUtils.getURL() %>img/boxView.jpg"></td>
				<td valign=top height=30px><input type="button" class="buttons" value="Enviar Sonido" onclick="alert('Se envio el sonido exitosamente');"></td>
			<tr>
			<tr>
				<td  valign=top ><input type="button" class="buttons" value="Tomar Foto" onclick="alert('Se tomo la fortografia exitosamente');"></td>
			<tr>			
		</table>		
</body>
</html>    

    