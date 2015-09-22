<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.axis.utils.StringUtils"%>
<%@page import="petline.valueObject.Recordatorio"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessRecordatorio"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idMascota = Integer.parseInt(request.getParameter("idMascota"));

	SessRecordatorio sessRecordatorio = new SessRecordatorio();
	Collection<Recordatorio> recordatorios = sessRecordatorio.obtenerRecordatorios(idMascota);
	
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
		<p id=message class="msgExitoso"><%= (StringUtils.isEmpty(message)?"":message) %></p>
		<a href="./recordatoriosAlta.jsp?idMascota=<%= idMascota %>"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Descripcion</td>
				<td>Fecha Hora</td>
				<td>Frecuencia</td>
				<td>Actualizar</td>
				<td>Eliminar</td>
			<tr>
			<%
				if( recordatorios.isEmpty() ){
					out.println("<tr class='tableImpar'><td colspan=6 align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
				}
				else{
					boolean esImpar = true;
					SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					for (Recordatorio recordatorio : recordatorios) {
						out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
						out.println("<td>" + recordatorio.getDescripcion() + "</td>");
						out.println("<td>" + sd.format(recordatorio.getFechaHora().getTime()) + "</td>");
						out.println("<td>" + recordatorio.getFrecuencia().getDescripcion() + "</td>");
						out.println("<td><a href='./recordatoriosModif.jsp?idMascota=" + idMascota + "&idRecordatorio=" + recordatorio.getIdRecordatorio() + "'><img src='" + PetLineUtils.getURL() + "img/upd.png'></a></td>");
						out.println("<td><a href='./recordatoriosElim.jsp?idMascota=" + idMascota + "&idRecordatorio=" + recordatorio.getIdRecordatorio() + "'><img src='" + PetLineUtils.getURL() + "img/del.png'></a></td>");
						out.println("<tr>");
						esImpar = !esImpar;													
					}
				}
			%>			
		</table>		
</body>
</html>    

    