<%@page import="petline.valueObject.Notificacion"%>
<%@page import="petline.sessLayer.SessNotificacion"%>
<%@page import="petline.valueObject.TrackerMascota"%>
<%@page import="petline.sessLayer.SessTrackerMascota"%>
<%@page import="petline.sessLayer.SessAnotacion"%>
<%@page import="petline.valueObject.Anotacion"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="petline.valueObject.Recordatorio"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.valueObject.Mascota"%>
<%@page import="petline.sessLayer.SessMascota"%>
<%@page import="petline.sessLayer.SessRecordatorio"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));

	SessMascota sessMascota = new SessMascota();
	Collection<Mascota> mascotas = sessMascota.obtenerMascotasPorUsuario(idUsuario);
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body>
    <%
        String message = request.getParameter("message");
    %>    
		<p class="title">Pantalla de Inicio</p>
		<p class="subtitle">Bienvenido  <%= message %>.</p>

<%
		if(!mascotas.isEmpty()){
			out.print("<p class=\"subtitle2\" align=left>Recordatorio</p>");
			out.print("<table class=table2>");
			for(Mascota mascota : mascotas){
				out.print("<tr class=\"tableTitle\"><td colspan=3>" + mascota.getApodo() + "</td><tr>");
				SessRecordatorio sessRecordatorio = new SessRecordatorio();
				Collection<Recordatorio> recordatorios = sessRecordatorio.obtenerRecordatorios(mascota.getIdMascota());
				out.print("<tr class=\"tableTitle\"><td>Descripcion</td><td>Fecha</td><td>Frecuencia</td><tr>");
				if(!recordatorios.isEmpty()){
					boolean esImpar = true;
					SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					for (Recordatorio recordatorio : recordatorios) {
						out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
						out.println("<td>" + recordatorio.getDescripcion() + "</td>");
						out.println("<td>" + sd.format(recordatorio.getFechaHora().getTime()) + "</td>");
						out.println("<td>" + recordatorio.getFrecuencia().getDescripcion() + "</td>");
						out.println("<tr>");
						esImpar = !esImpar;													
					}
				}
				else{
					out.print("<tr class=\"tableImpar\"><td colspan=3>NO HAY DATOS A MOSTRAR</td><tr>");	
				}
			}			
			out.print("</table>");
		}

		SessAnotacion sessAnotacion = new SessAnotacion();
		Collection<Anotacion> anotaciones = sessAnotacion.obtenerAnotacionesPorUsuario(idUsuario);

		out.print("<p class=\"subtitle2\" align=left>Anotaciones</p>");
		out.print("<table class=table2><tr class=\"tableTitle\"><td>Anotación</td>");		
		if( anotaciones.isEmpty() ){
			out.println("<tr class='tableImpar'><td align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
		}
		else{
			boolean esImpar = true;
			for (Anotacion anotacion : anotaciones) {
				out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
				out.println("<td>" + anotacion.getTexto() + "</td>");
				out.println("<tr>");
				esImpar = !esImpar;													
			}
		}			
		out.print("</table>");
		
		SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
		Collection<TrackerMascota> trackerMascotas = sessTrackerMascota.obtenerTrackerMascotaPorUsuario(idUsuario);		
		
		if(!trackerMascotas.isEmpty()){
			out.print("<p class=\"subtitle2\" align=left>Notificaciones última semana</p>");
			out.print("<table class=table2>");
			for( TrackerMascota trackerMascota : trackerMascotas ){
				out.print("<tr class=\"tableTitle\"><td colspan=2>" + trackerMascota.getMascota().getApodo() + "</td><tr>");
				SessNotificacion sessNotificacion = new SessNotificacion();
				Collection<Notificacion> notificaciones = sessNotificacion.obtenerNotificacionesUltimaSemana(trackerMascota.getTracker().getIdTracker());
				out.print("<tr class=\"tableTitle\"><td>Notificación</td><td>Fecha</td><tr>");
				if(!notificaciones.isEmpty()){
					boolean esImpar = true;
					SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					for (Notificacion notificacion : notificaciones) {
						out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
						out.println("<td>" + notificacion.getTipoNotificacion().getTitulo() + "</td>");
						out.println("<td>" + sd.format(notificacion.getFechaHora().getTime()) + "</td>");
						out.println("<tr>");
						esImpar = !esImpar;													
					}
				}
				else{
					out.print("<tr class=\"tableImpar\"><td colspan=2>NO HAY DATOS A MOSTRAR</td><tr>");	
				}				
			}
			
			out.print("</table>");
		}
		
%>			
</body>
</html>    

    