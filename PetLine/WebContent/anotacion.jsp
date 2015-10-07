<%@page import="org.apache.axis.utils.StringUtils"%>
<%@page import="petline.valueObject.Anotacion"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessAnotacion"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));

	SessAnotacion sessAnotacion = new SessAnotacion();
	Collection<Anotacion> anotaciones = sessAnotacion.obtenerAnotacionesPorUsuario(idUsuario);
	
	String message = request.getParameter("message");
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Anotación</title>
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
		<p class="title">Anotación</p>
		<p id=message class="msgExitoso"><%= (StringUtils.isEmpty(message)?"":message) %></p>
		<br>
		<a href="./anotacionAlta.jsp"><img src="<%= PetLineUtils.getURL() %>img/alta.png"></a>
		<br>
		<table id="table" class=table2>
			<thead>
				<tr class="tableTitle">
				<td>Anotación</td>
				<td>Actualizar</td>
				<td>Eliminar</td>
			</tr></thead><tbody>
			<%
				if( anotaciones.isEmpty() ){
					out.println("<tr class='tableImpar'><td colspan=3 align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
				}
				else{
					boolean esImpar = true;
					for (Anotacion anotacion : anotaciones) {
						out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
						out.println("<td>" + anotacion.getTexto() + "</td>");
						out.println("<td><a href='./anotacionModif.jsp?idAnotacion=" + anotacion.getIdAnotacion() + "'><img src='" + PetLineUtils.getURL() + "img/upd.png'></a></td>");
						out.println("<td><a href='./anotacionElim.jsp?idAnotacion=" + anotacion.getIdAnotacion() + "'><img src='" + PetLineUtils.getURL() + "img/del.png'></a></td>");
						out.println("</tr>");
						esImpar = !esImpar;													
					}
				}
			%>	
			</tbody>			
		</table>		
</body>
</html>    

    