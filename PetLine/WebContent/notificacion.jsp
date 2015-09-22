<%@page import="org.apache.axis.utils.StringUtils"%>
<%@page import="petline.valueObject.TelefonoTipoNotificacion"%>
<%@page import="petline.sessLayer.SessTelefonoTipoNotificacion"%>
<%@page import="petline.valueObject.Telefono"%>
<%@page import="petline.sessLayer.SessTelefono"%>
<%@page import="petline.valueObject.TipoNotificacion"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessTipoNotificacion"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String idTracker = request.getParameter("idTracker");

	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));
	
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
<form method="post" name="form1" id="form1" action="AltaNotificacion.do">
		<p id=message class="msgExitoso"><%= (StringUtils.isEmpty(message)?"":message) %></p>
		<br>
		<table class=table2>
			<tr class="tableTitle">
				<td>Notificación</td>
				<td>Activa</td>
				<td>Telefono</td>
			<tr>
			<%
				SessTipoNotificacion sessTipoNotificacion = new SessTipoNotificacion();
				Collection<TipoNotificacion> tipoNotificaciones =  sessTipoNotificacion.obtenerTipoNotificaciones();
			
				if( tipoNotificaciones.isEmpty() ){
					out.println("<tr class='tableImpar'><td colspan=3 align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
				}
				else{
					
					SessTelefono sessTelefono = new SessTelefono();
					Collection<Telefono> telefonos = sessTelefono.obtenerTelefonosPorUsuario(idUsuario);
					
					SessTelefonoTipoNotificacion sessTelefonoTipoNotificacion = new SessTelefonoTipoNotificacion();
					
					boolean esImpar = true;
					for (TipoNotificacion tn : tipoNotificaciones) {
						
						TelefonoTipoNotificacion ttn = sessTelefonoTipoNotificacion.obtenerTelefonoTipoNotificacion(tn.getIdTipoNotificacion(), Integer.parseInt(idTracker));
						
						out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
						out.println("<td id='tit" + tn.getIdTipoNotificacion() + "'>" + tn.getTitulo() + "</td>");
						
						out.println("<td><select name='opc" + tn.getIdTipoNotificacion() + "' id='opc" + tn.getIdTipoNotificacion() + "' >");
						out.println("<option value='S' " + (ttn==null?"":"selected") + ">Si</option>");
						out.println("<option value='N' " + (ttn==null?"selected":"") + ">No</option>");
						out.println("</select></td>");
						
						out.println("<td><select name='tel" + tn.getIdTipoNotificacion() + "' id='tel" + tn.getIdTipoNotificacion() + "' multiple>");
						for( Telefono telefono : telefonos ){
							out.println("<option value='" + telefono.getNroTelefono() + "' " + ( ttn != null && ttn.getNroTelefonos().contains(telefono.getNroTelefono())?"selected":"" ) + ">" + telefono.getNroTelefono() + "</option>");	
						}
						out.println("</select></td>");
						out.println("<tr>");
						esImpar = !esImpar;													
					}
				}
			%>							
		</table>	
		<br>
		<input type="button" class="buttons" value="Guardar" onclick="validarAltaNotificacion();">
		<input type="hidden" id="idTracker" name="idTracker"  value="<%= idTracker %>"/>
</form>
</body>
</html>    