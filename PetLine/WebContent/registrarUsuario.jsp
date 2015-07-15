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
<form method="post" name="form" id="form" action="./index.jsp">
		<p class="title">Registrar Usuario</p>
		<br>
		<table id="userTable" name="userTable" class=table2 >
			<tr>
				<td class=etiqueta>Código de Box</td>
				<td width=150px>&nbsp;<input type="text" name="box" id="box" value=""/></td>
				<td>&nbsp;</td>
			<tr>		
			<tr>
				<td class=etiqueta>Nombre</td>
				<td width=150px>&nbsp;<input type="text" name="nombre" id="nombre" value=""/></td>
				<td>&nbsp;</td>
			<tr>
			<tr>
				<td class=etiqueta>Apellido</td>
				<td>&nbsp;<input type="text" name="apellido" id="apellido" value=""/></td>
				<td>&nbsp;</td>
			<tr>
			<tr>
				<td class=etiqueta>Correo Electronico</td>
				<td>&nbsp;<input type="text" name="email" id="email" value=""/></td>
				<td>&nbsp;</td>
			<tr>			
		</table>
		<br>
		<input type="button" class="buttons" value="Registrar" onclick="registrarUsuario();">
</form>
</body>
</html>    

    