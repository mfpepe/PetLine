<%@page import="org.apache.axis.utils.StringUtils"%>
<%@page import="petline.valueObject.Mascota"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessMascota"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));

	SessMascota sessMascota = new SessMascota();
	Collection<Mascota> mascotas = sessMascota.obtenerMascotasPorUsuario(idUsuario);
	
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
		<p class="title">Mascotas</p>
		<p id=message class="msgExitoso"><%= (StringUtils.isEmpty(message)?"":message) %></p>
		<br>
		<a href="./mascotasAlta.jsp"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Apodo</td>
				<td>Edad</td>
				<td>Peso</td>
				<td>Objetivo Diario</td>
				<td>Actualizar</td>
				<td>Eliminar</td>
			<tr>
			<%
				if( mascotas.isEmpty() ){
					out.println("<tr class='tableImpar'><td colspan=6 align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
				}
				else{
					boolean esImpar = true;
					for (Mascota mascota : mascotas) {
						out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
						out.println("<td>" + mascota.getApodo() + "</td>");
						out.println("<td>" + PetLineUtils.calcularEdad(mascota.getFechaNacimiento()) + " Años</td>");
						out.println("<td>" + mascota.getPeso() + " Kg</td>");
						out.println("<td>" + (mascota.getKmDiarios()!=0?mascota.getKmDiarios() + " Kms":"") + "</td>");
						out.println("<td><a href='./mascotasModif.jsp?idMascota=" + mascota.getIdMascota() + "'><img src='" + PetLineUtils.getURL() + "img/upd.png'></a></td>");
						out.println("<td><a href='./mascotasElim.jsp?idMascota=" + mascota.getIdMascota() + "'><img src='" + PetLineUtils.getURL() + "img/del.png'></a></td>");
						out.println("<tr>");
						esImpar = !esImpar;													
					}
				}
			%>
		</table>
</body>
</html>    

    