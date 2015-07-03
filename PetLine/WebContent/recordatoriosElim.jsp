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
<form method="post" name="form1" id="form1" action="./recordatorios.jsp">
		<p class="title">Eliminar Recordatorio</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Descripcion</td>
				<td>&nbsp;<input type="text" name="descripcion" id="descripcion" value="<%= request.getParameter("descripcion") %>" disabled/></td>
			<tr>
			<tr>
				<td class=etiqueta>Fecha</td>
				<td>&nbsp;<input type="text" name="fecha" id="fecha" value="<%= request.getParameter("fecha") %>" disabled/></td>
			<tr>
			<tr>
				<td class=etiqueta>Hora</td>
				<td>&nbsp;<input type="text" name="hora" id="hora" value="<%= request.getParameter("hora") %>" disabled/></td>
			<tr>			
		</table>
		<br>
		<input type="button" class="buttons" value="Eliminar" onclick="document.form1.submit()">
</form>
</body>
</html>    

    