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
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/jquery-ui.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery.js"></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery-ui.js"></script>
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/jquery.mask.js"></script>
<script type="text/javascript">
$.datepicker.regional['es'] = {
		 closeText: 'Cerrar',
		 prevText: '<Ant',
		 nextText: 'Sig>',
		 currentText: 'Hoy',
		 monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
		 monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
		 dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
		 dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
		 dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
		 weekHeader: 'Sm',
		 dateFormat: 'dd/mm/yy',
		 firstDay: 1,
		 isRTL: false,
		 showMonthAfterYear: false,
		 yearSuffix: ''
		 };
		 $.datepicker.setDefaults($.datepicker.regional['es']);  
		  
		  $(function() {
		    $( "#edad" ).datepicker();
		  });
</script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="AltaMascota.do">
		<p class="title">Alta de Mascotas</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Apodo</td>
				<td>&nbsp;<input type="text" name="apodo" id="apodo" onkeypress="return soloLetras();" maxlength=50/></td>
			</tr>
			<tr>
				<td class=etiqueta>Fecha de Nacimiento</td>
				<td>&nbsp;<input type="text" name="edad" id="edad" readonly maxlength=10/></td>
			</tr>
			<tr>
				<td class=etiqueta>Peso (Kg)</td>
				<td>&nbsp;<input type="text" name="peso" id="peso" onkeypress="return soloNumeros();" maxlength=3/></td>
			</tr>
			<tr>
				<td class=etiqueta>Objetivo Diario (Kms)</td>
				<td>&nbsp;<input type="text" name="objetivo" id="objetivo" onkeypress="return soloNumeros();" maxlength=2/></td>
			</tr>
			<tr>
				<td class=etiqueta>Perímetro</td>
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
				<td class=etiqueta>Tamaño</td>
				<td>&nbsp;<% 
					SessTamanio sessTamanio = new SessTamanio();
					Collection<Tamanio> tamaños = sessTamanio.obtenerTamaños();
					out.print("<select name='tamanio' id='tamanio' ><option value=''></option>");
					for( Tamanio tamaño : tamaños ){
						out.print("<option value='" + tamaño.getIdTamanio() + "'>" + tamaño.getDescripcion() + "</option>");
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

    