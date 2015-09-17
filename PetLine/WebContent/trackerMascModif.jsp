<%@page import="petline.valueObject.Mascota"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessMascota"%>
<%@page import="petline.valueObject.TrackerMascota"%>
<%@page import="petline.sessLayer.SessTrackerMascota"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String idTrackerMascota = request.getParameter("idTrackerMascota");
	SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
	
	TrackerMascota trackerMascota = sessTrackerMascota.obtenerTrackerMascota(Integer.parseInt(idTrackerMascota));

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="ModificacionTrackerMascota.do">
		<p class="title">Modificacion de Tracker-Mascota</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Mascota</td>
				<td>
					<select name="mascota" id="mascota" disabled>
						<option value=""></option>
<%
						int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));
						
						SessMascota sessMascota = new SessMascota();
						Collection<Mascota> mascotas = sessMascota.obtenerMascotasPorUsuario(idUsuario);
						for (Mascota mascota : mascotas) {
							out.print("<option value='" + mascota.getIdMascota() + "' " + (mascota.getIdMascota() == trackerMascota.getMascota().getIdMascota()?"selected":"") + " >" + mascota.getApodo() + "</option>");
						}						
%>
					</select>
				</td>
			<tr>
			<tr>
				<td class=etiqueta>Código Tracker</td>
				<td>&nbsp;<input type="text" name="tracker" id="tracker" disabled value="<%= trackerMascota.getTracker().getCodigo() %>"/></td>
			<tr>						
			<tr>
				<td class=etiqueta>Temperatura Minima</td>
				<td>&nbsp;<input type="text" name="tempMin" id="tempMin" value="<%= trackerMascota.getTempMinima() %>"/></td>
			<tr>
			<tr>
				<td class=etiqueta>Temperatura Maxima</td>
				<td>&nbsp;<input type="text" name="tempMax" id="tempMax" value="<%= trackerMascota.getTempMaxima() %>"/></td>
			<tr>			
		</table>
		<br>
		<input type="button" class="buttons" value="Modificar" onclick="validarModificacionTrackerMascota();">
		<input type="hidden" id="idTrackerMascota" name="idTrackerMascota"  value="<%= idTrackerMascota %>"/>
</form>
</body>
</html>    

    