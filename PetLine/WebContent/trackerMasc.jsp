<%@page import="org.apache.axis.utils.StringUtils"%>
<%@page import="petline.valueObject.TrackerMascota"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessTrackerMascota"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));

	SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
	Collection<TrackerMascota> trackerMascotas = sessTrackerMascota.obtenerTrackerMascotaPorUsuario(idUsuario);
	
	String message = request.getParameter("message");
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/jquery.dataTables.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery.js"></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery-ui.js"></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery.mask.js"></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery.dataTables.nightly.js"></script>
<script type="text/javascript" >
$(document).ready(function(){
	$('#table').dataTable({
		"bPaginate": false,
		"aaSorting": [],
		"bJQueryUI": true,
		"oLanguage": {
			"sZeroRecords": "No se han encontrado datos",
			"sInfo": "",
			"sInfoEmpty": "",
			"sInfoFiltered": "(filtrado sobre un total de _MAX_ registros)",
			"sSearch": "Filtrar"
		}
	});
});
</script>
</head>
<body style="background-image:url('./img/fondo.png');" onLoad="setTimeout(function(){ document.getElementById('message').innerHTML=''; }, 3000);">
		<p class="title">Trackers-Mascotas</p>
		<p id=message class="msgExitoso"><%= (StringUtils.isEmpty(message)?"":message) %></p>
		<br>
		<a href="./trackerMascAlta.jsp"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table id="table" class=table2>
			<thead>
				<tr class="tableTitle">
				<td>Mascota</td>
				<td>Código Tracker</td>
				<td>Temperatura Mínima</td>
				<td>Temperatura Máxima</td>
				<td>Temperatura Actual</td>
				<td>Actualizar</td>
				<td>Eliminar</td>
			</tr></thead><tbody>
	<%
				if( trackerMascotas.isEmpty() ){
					out.println("<tr class='tableImpar'><td colspan=7 align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
				}
				else{
					boolean esImpar = true;

					for (TrackerMascota trackerMascota : trackerMascotas) {										
						out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
						out.println("<td>" + trackerMascota.getMascota().getApodo() + "</td>");
						out.println("<td>" + trackerMascota.getTracker().getCodigo() + "</td>");
						out.println("<td>" + trackerMascota.getTempMinima() + "</td>");
						out.println("<td>" + trackerMascota.getTempMaxima() + "</td>");
						out.println("<td>" + trackerMascota.getTempActual() + "</td>");
						out.println("<td><a href='./trackerMascModif.jsp?idTrackerMascota=" + trackerMascota.getIdTrackerMascota() + "'><img src='" + PetLineUtils.getURL() + "img/upd.png'></a></td>");
						out.println("<td><a href='./trackerMascElim.jsp?idTrackerMascota=" + trackerMascota.getIdTrackerMascota() + "'><img src='" + PetLineUtils.getURL() + "img/del.png'></a></td>");						
						out.println("</tr>");
						esImpar = !esImpar;
					}
				}
			%>				
			</tbody>
		</table>
</body>
</html>    

    