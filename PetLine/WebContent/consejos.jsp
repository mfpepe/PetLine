<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="petline.valueObject.Consejo"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessConsejo"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idMascota = Integer.parseInt(request.getParameter("idMascota"));

	SessConsejo sessConsejo = new SessConsejo();
	Collection<Consejo> consejos = sessConsejo.obtenerConsejos(idMascota);
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
<body style="background-image:url('./img/fondo.png');">
		<br>
		<table id="table" class=table2>
			<thead>
				<tr class="tableTitle">
				<td>Tip</td>
				<td>Descripción</td>
				<td>&nbsp;</td>
			</tr></thead><tbody>
			<%
				if( consejos.isEmpty() ){
					out.println("<tr class='tableImpar'><td colspan=2 align=Center>NO HAY DATOS A MOSTRAR</td></tr>");
				}
				else{
					boolean esImpar = true;
					for (Consejo consejo : consejos) {
						out.println("<tr class='" + (esImpar?"tableImpar":"tablePar") + "'>");
						out.println("<td>" + consejo.getTitulo() + "</td>");
						out.println("<td>" + consejo.getTexto() + "</td>");
						out.println("<td>");
						if(!StringUtils.isEmpty(consejo.getNombreImagen())){
							out.println("<img src='" + PetLineUtils.getURL() + "img/" + consejo.getNombreImagen() + "'>");
						}
						else{
							out.println("&nbsp;");
						}
						out.println("</td>");
						out.println("</tr>");
						esImpar = !esImpar;													
					}
				}
			%>					
			</tbody>							
		</table>		
</body>
</html>    