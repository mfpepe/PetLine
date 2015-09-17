<%@page import="petline.valueObject.Coordenada"%>
<%@page import="petline.sessLayer.SessCoordenada"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idTracker = Integer.parseInt(request.getParameter("idTracker"));
	
	SessCoordenada sessCoordenada = new SessCoordenada();
	Coordenada coordenada = sessCoordenada.obtenerUltimaCoordenada(idTracker);
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
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=true"></script>
<script>
	var mapa;
	var marcador;
	var circle;

    function localizame() {
        var punto = new google.maps.LatLng(<%= coordenada.getLatitud() %> , <%= coordenada.getLongitud() %>);

        var config = {
            zoom:16,
            center:punto,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        //VARIABLE MAPA
        mapa = new google.maps.Map( document.getElementById("mapa"), config );    	
    	
        marcador = new google.maps.Marker({
        	/*Creamos un marcador*/
       		position: punto, /*Lo situamos en nuestro punto */
        	map: mapa /* Lo vinculamos a nuestro mapa */
       	});
    }
    
    </script>
</head>
<body style="background-image:url('./img/fondo.png');" onload="localizame();setTimeout(function(){ location.reload(); }, 60000);">
	<div id="mapa"><br></div>
</body>
</html>    

    