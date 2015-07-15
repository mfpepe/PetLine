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
				<td>
					<select name="tel1" id="tel1" multiple>
						<option value="15-6950-3645" selected>15-6950-3645</option>
						<option value="15-3645-6950" selected>15-3645-6950</option>
					</select>
				</td>
			<tr>
			<tr class="tablePar">
				<td>Variaciones de Temperatura</td>
				<td>
					<select name="opc1" id="opc1" >
						<option value="S">Si</option>
						<option value="N" selected>No</option>
					</select>
				</td>
				<td>
					<select name="tel2" id="tel2" multiple>
						<option value="15-6950-3645">15-6950-3645</option>
						<option value="15-3645-6950">15-3645-6950</option>
					</select>
				</td>
			<tr>
			<tr class="tableImpar">
				<td>Objetivos Cumplidos</td>
				<td>
					<select name="opc1" id="opc1" >
					
						<option value="S">Si</option>
						<option value="N" selected>No</option>
					</select>
				</td>
				<td>
					<select name="tel3" id="tel3" multiple>
						<option value="15-6950-3645">15-6950-3645</option>
						<option value="15-3645-6950">15-3645-6950</option>
					</select>
				</td>
			<tr>	
			<tr class="tablePar">
				<td>Bateria Baja</td>
				<td>
					<select name="opc4" id="opc4" disabled>
						<option value="S" selected>Si</option>
						<option value="N">No</option>
					</select>
				</td>
				<td>
					<select name="tel4" id="tel4" multiple>
						<option value="15-6950-3645" selected>15-6950-3645</option>
						<option value="15-3645-6950">15-3645-6950</option>
					</select>
				</td>
			<tr>									
		</table>	
		<br>
		<input type="button" class="buttons" value="Guardar" onclick="alert('Notificaciones modificadas exitosamente.');">
</body>
</html>    