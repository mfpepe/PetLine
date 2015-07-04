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

		<p class="title">Actualizar Perfil de Usuario</p>
		<br>
		<table id="userTable" name="userTable" class=table2 >
			<tr>
				<td class=etiqueta>Nombre</td>
				<td>&nbsp;<input type="text" name="nombre" id="nombre" value="Admin"/></td>
			<tr>
			<tr>
				<td class=etiqueta>Apellido</td>
				<td>&nbsp;<input type="text" name="apellido" id="apellido" value="Admin"/></td>
			<tr>
			<tr>
				<td class=etiqueta>Correo Electronico</td>
				<td>&nbsp;<input type="text" name="email" id="email" value="admin@admin.com"/></td>
			<tr>
			<tr>
				<td class=etiqueta>Telefono</td>
				<td>&nbsp;<input type="text" name="tel1" id="tel1" value="15-6950-3645"/>
				&nbsp;<img src="<%= PetLineUtils.getURL() %>img/alta.png" onclick="agregarTelefono();" style="cursor:hand;"></td>
			<tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;<input type="text" name="tel2" id="tel2" value="15-3645-6950"/></td>
			<tr>							
		</table>
		<br>
		<input type="button" class="buttons" value="Modificar" onclick="alert('Se modifico exitosamente');">

</body>
</html>    

    