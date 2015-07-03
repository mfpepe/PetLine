<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anotación</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
		<p class="title">Anotación</p>
		<br>
		<a href="./anotacionAlta.jsp"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Descripcion</td>
				<td>Actualizar</td>
				<td>Eliminar</td>
			<tr>
			<tr class="tableImpar">
				<td>El Nro de el veterinario es 44445555</td>
				<td><a href="./anotacionModif.jsp?descripcion=El Nro de el veterinario es 44445555"><img src="<%= PetLineUtils.getURL() %>img/upd.png"></a></td>
				<td><a href="./anotacionElim.jsp?descripcion=El Nro de el veterinario es 44445555"><img src="<%= PetLineUtils.getURL() %>img/del.png"></a></td>
			<tr>			
		</table>		
</body>
</html>    

    