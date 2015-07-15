<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
    <%
        String message = request.getParameter("message");
    %>    
		<p class="title">Pantalla de Inicio</p>
		<p class="subtitle">Bienvenido  <%= message %>.</p>

		<p class="subtitle2" align=left>Recordatorio</p>
		<table class=table2>
			<tr class="tableTitle">
				<td colspan=4>Donato</td>
			<tr>		
			<tr class="tableTitle">
				<td>Descripcion</td>
				<td>Fecha</td>
				<td>Hora</td>
				<td>Frecuencia</td>
			<tr>
			<tr class="tableImpar">
				<td>Darle las Vacunas</td>
				<td>20/07/2015</td>
				<td>19:00</td>
				<td>Semanal</td>
			<tr>			
		</table>
		<br>
		<p class="subtitle2" align=left>Anotaciones</p>
		<table class=table2>
			<tr class="tableTitle">
				<td>Descripcion</td>
			<tr>
			<tr class="tableImpar">
				<td>El Nro de el veterinario es 44445555</td>
			<tr>			
		</table>
		<br>
		<p class="subtitle2" align=left>Notificaciones</p>
		<table class=table2>
			<tr class="tableTitle">
				<td colspan=4>Donato</td>
			<tr>		
			<tr class="tableTitle">
				<td>Notificación</td>
				<td>Fecha</td>
				<td>Hora</td>
			<tr>
			<tr class="tableImpar">
				<td>Alejamiento del perimetro</td>
				<td>10/07/2015</td>
				<td>19:00</td>
			<tr>			
		</table>				
</body>
</html>    

    