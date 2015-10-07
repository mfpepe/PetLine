<%@page import="petline.valueObject.Frecuencia"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessFrecuencia"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="petline.valueObject.Recordatorio"%>
<%@page import="petline.sessLayer.SessRecordatorio"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String idMascota = request.getParameter("idMascota");
	String idRecordatorio = request.getParameter("idRecordatorio");
	SessRecordatorio sessRecordatorio = new SessRecordatorio();
	
	Recordatorio recordatorio = sessRecordatorio.obtenerRecordatorio(Integer.parseInt(idRecordatorio));

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
		 yearSuffix: ''
		 };
		 $.datepicker.setDefaults($.datepicker.regional['es']);  
		  
		  $(function() {
		    $( "#fecha" ).datepicker();
		  });
</script>
</head>
<body style="background-image:url('./img/fondo.png');">
<form method="post" name="form1" id="form1" action="ModificacionRecordatorio.do">
		<p class="title">Modificar Recordatorio</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Descripcion</td>
				<td>&nbsp;<input type="text" name="descripcion" id="descripcion" value="<%= recordatorio.getDescripcion() %>"/></td>
			<tr>
			<tr>
				<td class=etiqueta>Fecha</td>
				<td>&nbsp;<input type="text" name="fecha" id="fecha" value="<%= (new SimpleDateFormat("dd/MM/yyyy")).format(recordatorio.getFechaHora().getTime()) %>"  readonly maxlength=10/></td>
			<tr>
			<tr>
				<td class=etiqueta>Hora</td>
				<td>&nbsp;<input type="text" name="hora" id="hora" value="<%= (new SimpleDateFormat("HH:mm")).format(recordatorio.getFechaHora().getTime()) %>" maxlength=5 /></td>
			<tr>		
			<tr>
				<td class=etiqueta>Frecuencia</td>
				<td>&nbsp;
					<select name="frecuencia" id="frecuencia" >
						<option value=""></option>
<%
							SessFrecuencia sessFrecuencia = new SessFrecuencia();
							Collection<Frecuencia> frecuencias = sessFrecuencia.obtenerFrecuencias();

							for (Frecuencia frecuencia : frecuencias) {
								out.print("<option value='" + frecuencia.getIdFrecuencia() + "' " + (frecuencia.getIdFrecuencia() == recordatorio.getFrecuencia().getIdFrecuencia()?"selected":"") + ">" + frecuencia.getDescripcion() + "</option>");
							}
						%>
					</select>
				</td>
			<tr>				
		</table>
		<br>
		<input type="button" class="buttons" value="Modificar" onclick="validarModificacionRecordatorio();">
		&nbsp;<input type="button" class="buttons" value="Cancelar" onclick="history.back();">
		<input type="hidden" id="idRecordatorio" name="idRecordatorio"  value="<%= idRecordatorio %>"/>
		<input type="hidden" id="idMascota" name="idMascota"  value="<%= idMascota %>"/>
</form>
</body>
</html>    

    