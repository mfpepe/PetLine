function trim(myString){
	if( myString == null ){
		return "";
	}
	return myString.replace(/^\s+/g,'').replace(/\s+$/g,'');
}

function onChangeMascotaRecorrido(){
	
	var mascota = document.getElementById("mascota").value;
	
	if( trim(mascota) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp"
	}
	else{
		document.getElementById("FRAMESEC").src="./recorridoCons.jsp"	
	}
}

function onChangeMascotaConsejo(){
	
	var mascota = document.getElementById("mascota").value;
	
	if( trim(mascota) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp"
	}
	else{
		document.getElementById("FRAMESEC").src="./consejos.jsp"	
	}
}

function onChangeMascotaRecordatorio(){
	
	var mascota = document.getElementById("mascota").value;
	
	if( trim(mascota) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp"
	}
	else{
		document.getElementById("FRAMESEC").src="./recordatorios.jsp"	
	}
}

function onChangeTrackerNotificacion(){
	
	var mascota = document.getElementById("tracker").value;
	
	if( trim(mascota) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp"
	}
	else{
		document.getElementById("FRAMESEC").src="./notificacion.jsp"	
	}
}