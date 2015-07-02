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
		<p class="title">Mascotas</p>
		<br>
		<a href="./mascotasAlta.jsp"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Apodo</td>
				<td>Edad</td>
				<td>Peso</td>
				<td>Objetivo Diario</td>
				<td>Actualizar</td>
				<td>Eliminar</td>
			<tr>
			<tr class="tableImpar">
				<td>Donato</td>
				<td>4 Años</td>
				<td>5 Kg</td>
				<td>2 Km</td>
				<td><a href="./mascotasModif.jsp?apodo=Donato&edad=4&peso=5&objetivo=2"><img src="<%= PetLineUtils.getURL() %>img/upd.png"></a></td>
				<td><a href="./mascotasElim.jsp?apodo=Donato&edad=4&peso=5&objetivo=2"><img src="<%= PetLineUtils.getURL() %>img/del.png"></a></td>
			<tr>			
		</table>
</body>
</html>    

    