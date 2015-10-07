<%@page import="petline.valueObject.Anotacion"%>
<%@page import="petline.sessLayer.SessAnotacion"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String idAnotacion = request.getParameter("idAnotacion");
	SessAnotacion sessAnotacion = new SessAnotacion();
	
	Anotacion anotacion = sessAnotacion.obtenerAnotacion(Integer.parseInt(idAnotacion));
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="BajaAnotacion.do">
		<p class="title">Eliminar Anotación</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Anotación</td>
				<td>&nbsp;<input type="text" name="anotacion" id="anotacion" value="<%= anotacion.getTexto() %>" disabled/></td>
			<tr>
		</table>
		<br>
		<input type="button" class="buttons" value="Eliminar" onclick="document.form1.submit()">
		&nbsp;<input type="button" class="buttons" value="Cancelar" onclick="history.back();">
		<input type="hidden" id="idAnotacion" name="idAnotacion"  value="<%= anotacion.getIdAnotacion() %>"/>
</form>
</body>
</html>    

    