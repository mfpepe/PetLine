<!DOCTYPE html>
<%@page import="box.utils.BoxUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<%= BoxUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= BoxUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
	<p class="title">Error</p>
	<br>
	<p class="subtitle">No se pudo establecer una conexion con el box.</p>
</body>
</html>    