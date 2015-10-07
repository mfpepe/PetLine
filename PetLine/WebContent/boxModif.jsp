<%@page import="petline.valueObject.UsuarioBox"%>
<%@page import="petline.sessLayer.SessUsuarioBox"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String idUsuarioBox = request.getParameter("idUsuarioBox");
	SessUsuarioBox sessUsuarioBox = new SessUsuarioBox();
	
	UsuarioBox usuarioBox = sessUsuarioBox.obtenerUsuarioBox(Integer.parseInt(idUsuarioBox));

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="ModificacionBox.do">
		<p class="title">Actualizar Box</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Descripción</td>
				<td>&nbsp;<input type="text" name="descripcion" id="descripcion" value="<%= usuarioBox.getBox().getDescripcion() %>"/></td>
			<tr>			
		</table>
		<br>
		<input type="button" class="buttons" value="Modificar" onclick="validarModificacionBox();">
		&nbsp;<input type="button" class="buttons" value="Cancelar" onclick="history.back();">
		<input type="hidden" id="idUsuarioBox" name="idUsuarioBox"  value="<%= idUsuarioBox %>"/>
</form>
</body>
</html>    

    