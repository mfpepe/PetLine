<%@page import="java.util.Collection"%>
<%@page import="petline.valueObject.TrackerMascota"%>
<%@page import="petline.sessLayer.SessTrackerMascota"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));

	SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
	Collection<TrackerMascota> trackerMascotas = sessTrackerMascota.obtenerTrackerMascotaPorUsuario(idUsuario);
	
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
		 dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'],
		 dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
		 dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sá'],
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
		    $( "#fecha" ).datepicker();
		    
		    document.getElementById("FRAMESEC").style.height = (screen.height-200) + "px";
		    document.getElementById("FRAMESEC").style.width = "100%";
		  });
</script>
</head>
<body style="height: 100%; padding: 0px; margin: 0px;">
		<p class="title">Recorrido</p>
		<br>
		<table class=table2>
			<tr>
				<td class=etiqueta>Mascota</td>
				<td>&nbsp;
					<select class="chosen-select" name="tracker" id="tracker">
						<option value=""></option>
						<%

						for (TrackerMascota trackerMascota : trackerMascotas) {
							out.print("<option value='" + trackerMascota.getTracker().getIdTracker() + "'>" + trackerMascota.getMascota().getApodo() + "</option>");
						}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td class=etiqueta>Fecha</td>
				<td>&nbsp;
					<input type="text" name="fecha" id="fecha" readonly maxlength=10 value="<%= (new SimpleDateFormat("dd/MM/yyyy")).format(Calendar.getInstance().getTime()) %>"/>
				</td>
			</tr>
			<tr>
				<td colspan=2 align=left>
					<input type="button" class="buttons" value="Buscar" onclick="onChangeMascotaRecorrido();">
				</td>
			</tr>
			<tr height=700px>
				<td colspan=2><iframe name="FRAMESEC" id="FRAMESEC" src="./empty.jsp?message=" frameborder="0"></iframe></td>
			</tr>			
		</table>		
</body>
</html>    

    