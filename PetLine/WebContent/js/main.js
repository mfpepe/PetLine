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

function onChangeMascotaUbicacion(){
	
	var mascota = document.getElementById("mascota").value;
	
	if( trim(mascota) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp"
	}
	else{
		document.getElementById("FRAMESEC").src="./ubicacion.jsp"	
	}
}

function agregarTelefono(){

	var tableID = 'userTable';
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var cell1 = row.insertCell(0);
    
    var cellText = document.createTextNode("");
    cell1.appendChild(cellText);
    
    var cell2 = row.insertCell(1);
    var element2 = document.createElement("input");
    element2.type = "text";
    cell2.appendChild(element2);
}