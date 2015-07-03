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
		<a href="./recordatoriosAlta.jsp"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Descripcion</td>
				<td>Fecha</td>
				<td>Hora</td>
				<td>Actualizar</td>
				<td>Eliminar</td>
			<tr>
			<tr class="tableImpar">
				<td>Darle las Vacunas</td>
				<td>20/07/2015</td>
				<td>19:00</td>
				<td><a href="./recordatoriosModif.jsp?descripcion=Darle las Vacunas&fecha=20/07/2015&hora=19:00"><img src="<%= PetLineUtils.getURL() %>img/upd.png"></a></td>
				<td><a href="./recordatoriosElim.jsp?descripcion=Darle las Vacunas&fecha=20/07/2015&hora=19:00"><img src="<%= PetLineUtils.getURL() %>img/del.png"></a></td>
			<tr>			
		</table>		
</body>
</html>    

    