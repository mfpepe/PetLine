<%@page import="petline.valueObject.TrackerMascota"%>
<%@page import="petline.sessLayer.SessTrackerMascota"%>
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
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="AltaTrackerMascota.do">
		<p class="title">Asignación de Tracker-Mascota</p>
		<br>
		<table class=table2 >	
			<tr>
				<td class=etiqueta>Mascota</td>
				<td>&nbsp;
					<select name="mascota" id="mascota" >
						<option value=""></option>
						<%
						SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
						TrackerMascota trackerMascota = null;
						for (Mascota mascota : mascotas) {
							trackerMascota = sessTrackerMascota.obtenerTrackerMascotaPorMascota(mascota.getIdMascota());
							
							if( trackerMascota == null ){
								out.print("<option value='" + mascota.getIdMascota() + "'>" + mascota.getApodo() + "</option>");
							}
						}
						%>
					</select>
				</td>
			<tr>
			<tr>
				<td class=etiqueta>Código de Tracker</td>
				<td>&nbsp;<input type="text" name="tracker" id="tracker"  onkeypress="return soloNumerosLetras();"/></td>
			<tr>				
			<tr>
				<td class=etiqueta>Temperatura Minima</td>
				<td>&nbsp;<input type="text" name="tempMin" id="tempMin" maxlength=3/></td>
			<tr>
			<tr>
				<td class=etiqueta>Temperatura Maxima</td>
				<td>&nbsp;<input type="text" name="tempMax" id="tempMax" maxlength=3/></td>
			<tr>			
		</table>
		<br>
		<input type="button" class="buttons" value="Agregar" onclick="validarAltaTrackerMascota();">
		&nbsp;<input type="button" class="buttons" value="Cancelar" onclick="history.back();">
</form>
</body>
</html>    

    