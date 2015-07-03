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
		<p class="title">Trackers-Mascotas</p>
		<br>
		<a href="./trackerMascAlta.jsp"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Tracker</td>
				<td>Mascota</td>
				<td>Temperatura Minima</td>
				<td>Temperatura Maxima</td>
				<td>Actualizar</td>
				<td>Eliminar</td>
			<tr>
			<tr class="tableImpar">
				<td>Tracker 1</td>
				<td>Donato</td>
				<td>5º</td>
				<td>30º</td>
				<td><a href="./trackerMascModif.jsp?tempMin=5º&tempMax=30º"><img src="<%= PetLineUtils.getURL() %>img/upd.png"></a></td>
				<td><a href="./trackerMascElim.jsp?tempMin=5º&tempMax=30º"><img src="<%= PetLineUtils.getURL() %>img/del.png"></a></td>
			<tr>			
		</table>
</body>
</html>    

    