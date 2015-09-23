<%@page import="petline.valueObject.Raza"%>
<%@page import="petline.sessLayer.SessRaza"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="petline.valueObject.Tamanio"%>
<%@page import="petline.sessLayer.SessTamanio"%>
<%@page import="petline.valueObject.Perimetro"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessPerimetro"%>
<%@page import="petline.valueObject.Mascota"%>
<%@page import="petline.sessLayer.SessMascota"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String idMascota = request.getParameter("idMascota");
	SessMascota sessMascota = new SessMascota();
	
	Mascota mascota = sessMascota.obtenerMascota(Integer.parseInt(idMascota));


%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="BajaMascota.do">
		<p class="title">Eliminar Mascota</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Apodo</td>
				<td>&nbsp;<input type="text" name="apodo" id="apodo" disabled value="<%= mascota.getApodo() %>"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Edad</td>
				<td>&nbsp;<input type="text" name="edad" id="edad" disabled value="<%= (new SimpleDateFormat("dd/MM/yyyy")).format(mascota.getFechaNacimiento().getTime()) %>"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Peso</td>
				<td>&nbsp;<input type="text" name="peso" id="peso" disabled value="<%= mascota.getPeso() %> Kgs"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Objetivo Diario</td>
				<td>&nbsp;<input type="text" name="objetivo" id="objetivo" disabled value="<%= mascota.getKmDiarios() %> Kms"/></td>
			</tr>			
			<tr>
				<td class=etiqueta>Perímetro</td>
				<td>&nbsp;<% 
					SessPerimetro sessPerimetro = new SessPerimetro();
					Collection<Perimetro> perimetros = sessPerimetro.obtenerPerimetrosPorUsuario(Integer.parseInt((String) session.getAttribute("SESSION_IDUSER")));
					out.print("<select disabled name='perimetro' id='perimetro' ><option value=''></option>");
					for( Perimetro perimetro : perimetros ){
						out.print("<option " + (perimetro.getIdPerimetro() == mascota.getIdPerimetro()?"selected":"") + " value='" + perimetro.getIdPerimetro() + "'>" + perimetro.getDescripcion() + "</option>");
					}
					out.print("</select>");
				%></td>
			</tr>	
			<tr>
				<td class=etiqueta>Raza</td>
				<td>&nbsp;<% 
					SessRaza sessRaza = new SessRaza();
					Collection<Raza> razas = sessRaza.obtenerRazas();
					out.print("<select name='raza' id='raza' disabled><option value=''></option>");
					for( Raza raza : razas ){
						out.print("<option " + (raza.getIdRaza() == mascota.getIdRaza()?"selected":"") + " value='" + raza.getIdRaza() + "'>" + raza.getDescripcion() + "</option>");
					}
					out.print("</select>");
				%></td>
			</tr>				
			<tr>
				<td class=etiqueta>Tamaño</td>
				<td>&nbsp;<% 
					SessTamanio sessTamanio = new SessTamanio();
					Collection<Tamanio> tamaños = sessTamanio.obtenerTamaños();
					out.print("<select disabled name='tamanio' id='tamanio' ><option value=''></option>");
					for( Tamanio tamaño : tamaños ){
						out.print("<option " + (tamaño.getIdTamanio() == mascota.getIdTamaño()?"selected":"") + " value='" + tamaño.getIdTamanio() + "'>" + tamaño.getDescripcion() + "</option>");
					}
					out.print("</select>");
				%></td>
			</tr>				
		</table>
		<br>
		<input type="button" class="buttons" value="Eliminar" onclick="document.form1.submit()">
		<input type="hidden" id="idMascota" name="idMascota"  value="<%= mascota.getIdMascota() %>"/>
</form>
</body>
</html>    

    