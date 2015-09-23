<%@page import="petline.valueObject.Raza"%>
<%@page import="petline.sessLayer.SessRaza"%>
<%@page import="petline.sessLayer.SessTamanio"%>
<%@page import="petline.valueObject.Perimetro"%>
<%@page import="petline.valueObject.Tamanio"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessPerimetro"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="AltaMascota.do">
		<p class="title">Alta de Mascotas</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Apodo</td>
				<td>&nbsp;<input type="text" name="apodo" id="apodo"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Fecha de Nacimiento (dd/mm/yyyy)</td>
				<td>&nbsp;<input type="text" name="edad" id="edad"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Peso</td>
				<td>&nbsp;<input type="text" name="peso" id="peso"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Objetivo Diario</td>
				<td>&nbsp;<input type="text" name="objetivo" id="objetivo"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Per�metro</td>
				<td>&nbsp;<% 
					SessPerimetro sessPerimetro = new SessPerimetro();
					Collection<Perimetro> perimetros = sessPerimetro.obtenerPerimetrosPorUsuario(Integer.parseInt((String) session.getAttribute("SESSION_IDUSER")));
					out.print("<select name='perimetro' id='perimetro' ><option value=''></option>");
					for( Perimetro perimetro : perimetros ){
						out.print("<option value='" + perimetro.getIdPerimetro() + "'>" + perimetro.getDescripcion() + "</option>");
					}
					out.print("</select>");
				%></td>
			</tr>
			<tr>
				<td class=etiqueta>Raza</td>
				<td>&nbsp;<% 
					SessRaza sessRaza = new SessRaza();
					Collection<Raza> razas = sessRaza.obtenerRazas();
					out.print("<select name='raza' id='raza' ><option value=''></option>");
					for( Raza raza : razas ){
						out.print("<option value='" + raza.getIdRaza() + "'>" + raza.getDescripcion() + "</option>");
					}
					out.print("</select>");
				%></td>
			</tr>						
			<tr>
				<td class=etiqueta>Tama�o</td>
				<td>&nbsp;<% 
					SessTamanio sessTamanio = new SessTamanio();
					Collection<Tamanio> tama�os = sessTamanio.obtenerTama�os();
					out.print("<select name='tamanio' id='tamanio' ><option value=''></option>");
					for( Tamanio tama�o : tama�os ){
						out.print("<option value='" + tama�o.getIdTamanio() + "'>" + tama�o.getDescripcion() + "</option>");
					}
					out.print("</select>");
				%></td>
			</tr>					
		</table>
		<br>
		<input type="button" class="buttons" value="Agregar" onclick="validarAltaMascota();">
</form>
</body>
</html>    

    