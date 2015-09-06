<%@page import="petline.valueObject.Perimetro"%>
<%@page import="petline.sessLayer.SessPerimetro"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String idPerimetro = request.getParameter("idPerimetro");
	SessPerimetro sessPerimetro = new SessPerimetro();
	
	Perimetro perimetro = sessPerimetro.obtenerPerimetro(Integer.parseInt(idPerimetro));


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
        if (navigator.geolocation) { /* Si el navegador tiene geolocalizacion */
            navigator.geolocation.getCurrentPosition(coordenadas, errores);
        }else{
            alert('Oops! Tu navegador no soporta geolocalización. Bájate Chrome, que es gratis!');
        }
    }
    
    function coordenadas(position) {
        latitud = document.getElementById("latitud").value;
        longitud = document.getElementById("longitud").value
        
        cargarMapa();
    }
    
    function errores(err) {
        /*Controlamos los posibles errores */
        if (err.code == 0) {
          alert("Oops! Algo ha salido mal");
        }
        if (err.code == 1) {
          alert("Oops! No has aceptado compartir tu posición");
        }
        if (err.code == 2) {
          alert("Oops! No se puede obtener la posición actual");
        }
        if (err.code == 3) {
          alert("Oops! Hemos superado el tiempo de espera");
        }
    }
     
    function cargarMapa() {
        var punto = new google.maps.LatLng(latitud,longitud);

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
        
        setearMarcador();
        
        google.maps.event.addListener(mapa, "click", function(event){
    	    var coordenadas = event.latLng.toString();
    	    
    	    coordenadas = coordenadas.replace("(", "");
    	    coordenadas = coordenadas.replace(")", "");
    	    
    	    //coordenadas por separado
    	    var lista = coordenadas.split(",");
    	    
    	    //alert("Las coordenada X es"+ lista[0]);
    	    //alert("Las coordenada Y es"+ lista[1]);
    	
    	    document.getElementById("latitud").value = lista[0];
    		document.getElementById("longitud").value = lista[1];
    		
        	setearMarcador();
         });        
    }        
    
	function setearMarcador(){
	    //variable para dirección, punto o coordenada
	    var direccion = new google.maps.LatLng(document.getElementById("latitud").value, document.getElementById("longitud").value);
	                
	    marcador.setMap(null);
	    if(circle != null)
	    	circle.setMap(null);
	    
	    //variable para marcador
	    marcador = new google.maps.Marker({
	        position:direccion,//la posición del nuevo marcador
	        map: mapa, //en que mapa se ubicará el marcador
	        animation:google.maps.Animation.DROP,//como aparecerá el marcador
	        draggable:false//no permitir el arrastre del marcador
	    });
	    //ubicar el marcador en el mapa
	    marcador.setMap(mapa)            
	    
	    var radio = document.getElementById("distancia").value;
	    if(radio > 0 ){
	        // Add circle overlay and bind to marker
	        circle = new google.maps.Circle({
	          map: mapa,
	          radius: parseInt(radio),
	          fillColor: '#AA0000'
	        });
	        circle.bindTo('center', marcador, 'position');            
	    	
	    }
	}    
    
    </script>
</head>
<body style="background-image:url('./img/fondo.png');"  onload="localizame();">
<form method="post" name="form1" id="form1" action="ModificacionPerimetro.do">
		<p class="title">Actualizar Perímetro</p>
		<br>
		<table class=table2 >
			<tr>
				<td class=etiqueta>Descripción</td>
				<td>&nbsp;<input type="text" name="descripcion" id="descripcion" value="<%= perimetro.getDescripcion() %>"/></td>
			<tr>
			<tr>
				<td class=etiqueta>Distancia</td>
				<td>&nbsp;<input type="text" name="distancia" id="distancia" value="<%= perimetro.getDistancia() %>"  OnBlur="setearMarcador();"/></td>
			<tr>
		</table>
		<div id="mapa"><br></div>
		<br>
		<input type="button" class="buttons" value="Modificar" onclick="validarModificacionPerimetro();">
		<input type="hidden" id="latitud" name="latitud" value="<%= perimetro.getLatitud() %>"/>
		<input type="hidden" id="longitud" name="longitud" value="<%= perimetro.getLongitud() %>"/>
		<input type="hidden" id="idPerimetro" name="idPerimetro"  value="<%= perimetro.getIdPerimetro() %>"/>
</form>
</body>
</html>    

    