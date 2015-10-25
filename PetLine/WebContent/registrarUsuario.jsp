<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/jquery-ui.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery.js"></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery-ui.js"></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery.mask.js"></script>
<script type="text/javascript">
$(function() {
	$("#telefono").mask("(+54) 9 $#####-####");
});
</script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="RegistrarUsuario.do">
		<p class="title">Registrar Usuario</p>
		<br>
		<table id="userTable" class=table2 >
			<tr>
				<td class=etiqueta>Código de Box</td>
				<td width=150px>&nbsp;<input type="text" name="box" id="box" value="" onkeypress="return soloNumerosLetras();" /></td>
				<td>&nbsp;</td>
			</tr>		
			<tr>
				<td class=etiqueta>Nombre</td>
				<td width=150px>&nbsp;<input type="text" name="nombre" id="nombre" value="" maxlength=50 onkeypress="return soloLetras();"/></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class=etiqueta>Apellido</td>
				<td>&nbsp;<input type="text" name="apellido" id="apellido" value="" maxlength=50 onkeypress="return soloLetras();"/></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class=etiqueta>Correo Electronico</td>
				<td>&nbsp;<input type="text" name="email" id="email" value=""/></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class=etiqueta>Teléfono</td>
				<td>&nbsp;<input type="text" name="telefono" id="telefono" value=""/></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class=etiqueta>Usuario</td>
				<td>&nbsp;<input type="text" name="alias" id="alias" value="" onkeypress="return soloLetras();" maxlength=10/></td>
				<td>&nbsp;</td>
			</tr>			
			<tr>
				<td class=etiqueta>Clave</td>
				<td>&nbsp;<input type="password" name="clave" id="clave" value="" onkeypress="return soloNumerosLetras();" maxlength=10/></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class=etiqueta>Repita Clave</td>
				<td>&nbsp;<input type="password" name="clave2" id="clave2" value="" onkeypress="return soloNumerosLetras();" maxlength=10/></td>
				<td>&nbsp;</td>
			</tr>												
		</table>
		<br>
		<input type="button" class="buttons" value="Registrar" onclick="registrarUsuario();">
</form>
</body>
</html>    

    