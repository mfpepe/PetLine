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
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Notificación</td>
				<td>Activa</td>
				<td>Telefono</td>
			<tr>
			<tr class="tableImpar">
				<td>Alejamiento del perimetro</td>
				<td>
					<select name="opc1" id="opc1" >
						<option value="S" selected>Si</option>
						<option value="N">No</option>
					</select>
				</td>
				<td><input type="text" value="15-6950-3645"/></td>
			<tr>
			<tr class="tablePar">
				<td>Variaciones de Temperatura</td>
				<td>
					<select name="opc1" id="opc1" >
						<option value="S">Si</option>
						<option value="N" selected>No</option>
					</select>
				</td>
				<td><input type="text" value=""/></td>
			<tr>
			<tr class="tableImpar">
				<td>Bateria Baja del Tracker</td>
				<td>
					<select name="opc1" id="opc1" >
						<option value="S">Si</option>
						<option value="N" selected>No</option>
					</select>
				</td>
				<td><input type="text" value=""/></td>
			<tr>
			<tr class="tablePar">
				<td>Objetivos Cumplidos</td>
				<td>
					<select name="opc1" id="opc1" >
						<option value="S">Si</option>
						<option value="N" selected>No</option>
					</select>
				</td>
				<td><input type="text" value=""/></td>
			<tr>
			<tr class="tableImpar">
				<td>Recordatorios</td>
				<td>
					<select name="opc1" id="opc1" >
						<option value="S">Si</option>
						<option value="N" selected>No</option>
					</select>
				</td>
				<td><input type="text" value=""/></td>
			<tr>									
		</table>	
		<br>
		<input type="button" class="buttons" value="Guardar" onclick="alert('Notificaciones modificadas exitosamente.');">
</body>
</html>    