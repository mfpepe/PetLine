<%@page import="petline.valueObject.Perimetro"%>
<%@page import="petline.sessLayer.SessPerimetro"%>
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
		<p class="title">Mascotas-Perimetros</p>
		<p id=message class="msgExitoso"><%= (StringUtils.isEmpty(message)?"":message) %></p>
		<br>
		<a href="./mascPeriAlta.jsp"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Mascota</td>
				<td>Perimetro</td>
				<td>Eliminar</td>
			<tr>
			<%
				if( mascotas.isEmpty() ){
					out.println("<tr class='tableImpar'><td colspan=3 align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
				}
				else{
					boolean tieneDatos = false;
					boolean esImpar = true;
					SessPerimetro sessPerimetro = new SessPerimetro();
					Perimetro perimetro = null;
					for (Mascota mascota : mascotas) {
						
						perimetro = sessPerimetro.obtenerPerimetro(mascota.getIdPerimetro());
						
						if( perimetro != null ){						
							out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
							out.println("<td>" + mascota.getApodo() + "</td>");
							out.println("<td>" + perimetro.getDescripcion() + "</td>");
							out.println("<td><a href='./mascPeriElim.jsp?idMascota=" + mascota.getIdMascota() + "'><img src='" + PetLineUtils.getURL() + "img/del.png'></a></td>");
							out.println("<tr>");
							esImpar = !esImpar;	
							tieneDatos = true;
						}
					}
					
					if(!tieneDatos){
						out.println("<tr class='tableImpar'><td colspan=3 align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
					}
				}
			%>						
		</table>
</body>
</html>    

    