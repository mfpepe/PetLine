<%@page import="org.apache.axis.utils.StringUtils"%>
<%@page import="petline.valueObject.UsuarioBox"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessUsuarioBox"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));

	SessUsuarioBox sessUsuarioBox = new SessUsuarioBox();
	Collection<UsuarioBox> usuarioBoxs = sessUsuarioBox.obtenerBoxPorUsuario(idUsuario);
	
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
		<p class="title">Box's</p>
		<p id=message class="msgExitoso"><%= (StringUtils.isEmpty(message)?"":message) %></p>
		<br>
		<a href="./boxAlta.jsp"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Box</td>
				<td>Actualizar</td>
				<td>Eliminar</td>
			<tr>
			<%
				if( usuarioBoxs.isEmpty() ){
					out.println("<tr class='tableImpar'><td colspan=6 align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
				}
				else{
					boolean esImpar = true;
					for (UsuarioBox usuarioBox : usuarioBoxs) {
						out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
						out.println("<td><a target=\"_blank\" href=\"BoxView.do?idBox=" + usuarioBox.getBox().getIdBox() + "\">" + usuarioBox.getBox().getDescripcion() + "</a></td>");
						out.println("<td><a href='./boxModif.jsp?idUsuarioBox=" + usuarioBox.getIdUsuarioBox() + "'><img src='" + PetLineUtils.getURL() + "img/upd.png'></a></td>");
						out.println("<td><a href='./boxElim.jsp?idUsuarioBox=" + usuarioBox.getIdUsuarioBox() + "'><img src='" + PetLineUtils.getURL() + "img/del.png'></a></td>");
						out.println("<tr>");
						esImpar = !esImpar;													
					}
				}
			%>			
		</table>		
</body>
</html>    

    