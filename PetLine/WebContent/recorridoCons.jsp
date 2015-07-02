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
		<table class=table2>
			<tr>
				<td rowspan=3 width=300px><img src="<%= PetLineUtils.getURL() %>img/recorrido.png"></td>
				<td valign=top height=30px class="textoLibre">Kms Recorridos: 1,5km</td>
			<tr>
			<tr>
				<td  valign=top class="textoLibre">Calorias Consumidas: 500 cal</td>
			<tr>			
		</table>			
</body>
</html>    

    