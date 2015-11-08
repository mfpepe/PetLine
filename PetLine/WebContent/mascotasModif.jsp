<%@page import="petline.valueObject.Raza"%>
<%@page import="petline.sessLayer.SessRaza"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="petline.sessLayer.SessPerimetro"%>
<%@page import="petline.valueObject.Perimetro"%>
<%@page import="petline.valueObject.Tamanio"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessTamanio"%>
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
		 dayNames: ['Domingo', 'Lunes', 'Martes', 'Mi�rcoles', 'Jueves', 'Viernes', 'S�bado'],
		 dayNamesShort: ['Dom','Lun','Mar','Mi�','Juv','Vie','S�b'],
		 dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S�'],
		 weekHeader: 'Sm',
		 dateFormat: 'dd/mm/yy',
		 firstDay: 1,
		 isRTL: false,
		 showMonthAfterYear: false,
		 yearSuffix: '',
         changeMonth: true,
         changeYear: true,
         showButtonPanel: true
		 };
		 $.datepicker.setDefaults($.datepicker.regional['es']);  
		  
		  $(function() {
		    $( "#edad" ).datepicker();
		  });
</script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="ModificacionMascota.do">
		<p class="title">Actualizar Mascota</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Apodo</td>
				<td>&nbsp;<input type="text" name="apodo" id="apodo" onkeypress="return soloLetras();" maxlength=50 value="<%= mascota.getApodo() %>"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Fecha de Nacimiento</td>
				<td>&nbsp;<input type="text" name="edad" id="edad" readonly maxlength=10 value="<%= (new SimpleDateFormat("dd/MM/yyyy")).format(mascota.getFechaNacimiento().getTime()) %>"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Peso (Kg)</td>
				<td>&nbsp;<input type="text" name="peso" id="peso" value="<%= mascota.getPeso() %>"/></td>
			</tr>
			<tr>
				<td class=etiqueta>Objetivo Diario (Kms)</td>
				<td>&nbsp;<input type="text" name="objetivo" id="objetivo" value="<%= mascota.getKmDiarios() %>"/></td>
			</tr>			
			<tr>
				<td class=etiqueta>Per�metro</td>
				<td>&nbsp;<% 
					SessPerimetro sessPerimetro = new SessPerimetro();
					Collection<Perimetro> perimetros = sessPerimetro.obtenerPerimetrosPorUsuario(Integer.parseInt((String) session.getAttribute("SESSION_IDUSER")));
					out.print("<select name='perimetro' id='perimetro' ><option value=''></option>");
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
					out.print("<select name='raza' id='raza' ><option value=''></option>");
					for( Raza raza : razas ){
						out.print("<option " + (raza.getIdRaza() == mascota.getIdRaza()?"selected":"") + " value='" + raza.getIdRaza() + "'>" + raza.getDescripcion() + "</option>");
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
						out.print("<option " + (tama�o.getIdTamanio() == mascota.getIdTama�o()?"selected":"") + " value='" + tama�o.getIdTamanio() + "'>" + tama�o.getDescripcion() + "</option>");
					}
					out.print("</select>");
				%></td>
			</tr>		
		</table>
		<br>
		<input type="button" class="buttons" value="Modificar" onclick="validarModificacionMascota()">
		&nbsp;<input type="button" class="buttons" value="Cancelar" onclick="history.back();">
		<input type="hidden" id="idMascota" name="idMascota"  value="<%= mascota.getIdMascota() %>"/>
</form>
</body>
</html>    

    