<%@page import="org.apache.axis.utils.StringUtils"%>
<%@page import="petline.valueObject.Telefono"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessTelefono"%>
<%@page import="petline.valueObject.Usuario"%>
<%@page import="petline.sessLayer.SessUsuario"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));
	SessUsuario sessUsuario = new SessUsuario();
	Usuario usuario = sessUsuario.obtenerUsuarioPorId(idUsuario);
	
	SessTelefono sessTelefono = new SessTelefono();
	
	Collection<Telefono> telefonos = sessTelefono.obtenerTelefonosPorUsuario(idUsuario);
	
	String message = request.getParameter("message");
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');" onLoad="setTimeout(function(){ document.getElementById('message').innerHTML=''; }, 3000);">
<form method="post" name="form1" id="form1" action="ModificacionUsuario.do">
		<p class="title">Actualizar Perfil de Usuario</p>
		<p id=message class="msgExitoso"><%= (StringUtils.isEmpty(message)?"":message) %></p>
		<br>
		<table id="userTable" name="userTable" class=table2 >
			<tr>
				<td class=etiqueta>Nombre</td>
				<td width=150px>&nbsp;<input type="text" name="nombre" id="nombre" value="<%= usuario.getNombre() %>"/></td>
				<td>&nbsp;</td>
			<tr>
			<tr>
				<td class=etiqueta>Apellido</td>
				<td>&nbsp;<input type="text" name="apellido" id="apellido" value="<%= usuario.getApellido() %>"/></td>
				<td>&nbsp;</td>
			<tr>
			<tr>
				<td class=etiqueta>Correo Electronico</td>
				<td>&nbsp;<input type="text" name="email" id="email" value="<%= usuario.getMail() %>"/></td>
				<td>&nbsp;</td>
			<tr>
			<tr>
				<td class=etiqueta>Telefono</td>
				<td valign=top>
				<table id="telefonos" name="telefonos" class="table2">
					<%
						int cont = 0;
						for(Telefono telefono : telefonos){
							cont++;
							out.print("<tr><td height=25px>&nbsp;<input type='tex' name='tel" + cont + "' id='tel" + cont + "' value='" + telefono.getNroTelefono() + "'/></td></tr>");
						}
					%>
				</table>
				</td>		
				<td>
					<img width=25px height=25px src="<%= PetLineUtils.getURL() %>img/alta.png" onclick="agregarTelefono();" style="cursor:hand;">
				    <img width=25px height=25px src="<%= PetLineUtils.getURL() %>img/del.png" onclick="eliminarTelefono();" style="cursor:hand;">
				</td>		
			</tr>				
		</table>
		<br>
		<input type="button" class="buttons" value="Modificar" onclick="validarModificacionUsuario();">
		<input type="hidden" id="cantTelefonos" name="cantTelefonos" value="<%= cont %>">
</form>
</body>
</html>    

    