<%@page import="petline.sessLayer.SessTrackerMascota"%>
<%@page import="petline.valueObject.TrackerMascota"%>
<%@page import="org.apache.axis.utils.StringUtils"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="petline.valueObject.Coordenada"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessCoordenada"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idTracker = Integer.parseInt(request.getParameter("idTracker"));
	String fecha = request.getParameter("fecha");

	SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	Calendar fechaHora = Calendar.getInstance();
	fechaHora.clear();
	fechaHora.setTime(sd.parse(fecha));
	SessCoordenada sessCoordenada = new SessCoordenada();
	Collection<Coordenada> coordenadas = sessCoordenada.obtenerCoordenadas(idTracker, fechaHora);
	
	if( !coordenadas.isEmpty() ){
		 
		SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
		TrackerMascota trackerMascota = sessTrackerMascota.obtenerTrackerMascotaPorTracker(idTracker);
%>
<html>
<head>
    <style>
        #mapa{
            width: 400px;
            height: 350px;
            float:left;
            background: green;
        }
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script>
function initialize() {
	
	

    var myOptions = {
    	zoom: 14,
<%
		Coordenada praCoordenada = coordenadas.iterator().next();
    
		Calendar fechaInicia = Calendar.getInstance();
		fechaInicia.clear();
    	fechaInicia.setTime( praCoordenada.getFechaHora().getTime() );
		
		out.print("center: new google.maps.LatLng(" + praCoordenada.getLatitud() + ", " + praCoordenada.getLongitud() + "),");
%>
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
        
    var map = new google.maps.Map(document.getElementById("mapa"), myOptions);     
  	
    var ruta = [
<%
		float totalMtsRecorridos = 0;
		String latOri = "";
		String lngOri = "";
		
		Calendar fechaFin = Calendar.getInstance();
		
		for (Iterator<Coordenada> iterator = coordenadas.iterator(); iterator.hasNext();) {
			Coordenada coordenada = iterator.next();
			out.print("new google.maps.LatLng(" + coordenada.getLatitud() + ", " + coordenada.getLongitud() + ")");
			if( iterator.hasNext() ){
				out.print(",");
			}
			else{
				fechaFin.clear();
				fechaFin.setTime( coordenada.getFechaHora().getTime() );
			}
			
			if( !StringUtils.isEmpty(latOri) && !StringUtils.isEmpty(lngOri) ){
				totalMtsRecorridos += PetLineUtils.distanciaMts(latOri, lngOri, coordenada.getLatitud(), coordenada.getLongitud());
			}

			latOri = coordenada.getLatitud();
			lngOri = coordenada.getLongitud();
			
		}
%>
	];

	var lineas = new google.maps.Polyline({
    	path: ruta,
     	map: map,
     	strokeColor: '#00C600',
     	strokeWeight: 4,
     	strokeOpacity: 0.6,
     	clickable: false
	});
}
    </script>
</head>
<body style="background-image:url('./img/fondo.png');"  onload="initialize();">
		<table class=table2>
			<tr>
				<td rowspan=5 width=200px><div id="mapa"><br></div></td>
				<td valign=top height=30px class="textoLibre">Kms Recorridos: <%= totalMtsRecorridos %> Kms</td>
			<tr>
			<tr>
				<td  valign=top height=30px class="textoLibre">Kms Faltantes para Cumplir Objetivo Diario: <%
					if( trackerMascota.getMascota().getKmDiarios()>0 ){
						if( trackerMascota.getMascota().getKmDiarios()>(totalMtsRecorridos/1000) ){
							out.print( (trackerMascota.getMascota().getKmDiarios() - (totalMtsRecorridos/1000)) + " Kms" );	
						}
						else{
							out.print("<font color=#00C600>Objetivo cumplido</font>");	
						}
					}
					else{
						out.print("<font color=red>No tiene un objetivo cargado</font>");
					}
				%></td>
			<tr>								
			<tr>
				<td  valign=top class="textoLibre">Calorias Consumidas: <%= PetLineUtils.caloriasQuemadas(trackerMascota.getMascota().getPeso(), fechaInicia, fechaFin) %> Kcal</td>
			<tr>	
		</table>			
</body>
</html>    
<%
	}
	else{
    	response.sendRedirect("empty.jsp?message=No existen datos para los valores seleccionados");
	}
%>