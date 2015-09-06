<%@page import="petline.sessLayer.SessPerimetro"%>
<%@page import="petline.valueObject.Perimetro"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.valueObject.Mascota"%>
<%@page import="petline.sessLayer.SessMascota"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));
	int idMascota = Integer.parseInt(request.getParameter("idMascota"));

	SessMascota sessMascota = new SessMascota();
	Mascota mascota = sessMascota.obtenerMascota(idMascota);
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="BajaMascotaPerimetro.do">
		<p class="title">Eliminar Asignación de Perimetro-Mascota</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Mascota</td>
				<td>&nbsp;
					<select name="mascota" id="mascota" disabled>
						<option value=""></option>
						<option value="<%= mascota.getIdMascota() %>" selected><%= mascota.getApodo() %></option>
					</select>
				</td>
			<tr>
			<tr>
				<td class=etiqueta>Perimetro</td>
				<td>&nbsp;
					<select name="perimetro" id="perimetro" disabled>
						<option value=""></option>
						<%
							SessPerimetro sessPerimetro = new SessPerimetro();
							Collection<Perimetro> perimetros = sessPerimetro.obtenerPerimetrosPorUsuario(idUsuario);
							for(Perimetro per : perimetros){
								out.print("<option " + (per.getIdPerimetro()==mascota.getIdPerimetro()?"selected":"") + " value='" + per.getIdPerimetro() + "'>" + per.getDescripcion() + "</option>");
							}
						%>
					</select>
				</td>
			<tr>
		</table>
		<br>
		<input type="button" class="buttons" value="Eliminar" onclick="document.form1.submit()">
		<input type="hidden" id="idMascota" name="idMascota"  value="<%= mascota.getIdMascota() %>"/>
</form>
</body>
</html>    

    