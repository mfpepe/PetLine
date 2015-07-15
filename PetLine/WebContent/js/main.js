function trim(myString){
	if( myString == null ){
		return "";
	}
	return myString.replace(/^\s+/g,'').replace(/\s+$/g,'');
}

function registrarUsuario(){
	
	var box = document.getElementById("box").value;
	var nombre = document.getElementById("nombre").value;
	var apellido = document.getElementById("apellido").value;
	var email = document.getElementById("email").value;
	
	if( trim(box) == "" ){
		alert("Código de Box es obligatorio.");
		return;
	}

	if( trim(nombre) == "" ){
		alert("Nombre es obligatorio.");
		return;
	}
	
	if( trim(apellido) == "" ){
		alert("Apellido es obligatorio.");
		return;
	}
	
	if( trim(email) == "" ){
		alert("Correo Electronico es obligatorio.");
		return;
	}	

	document.form.action = document.form.action + "?message=Usuario Registrado Correctamente.";
	document.form.submit();
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

function onChangeMascotaNotificacion(){
	
	var mascota = document.getElementById("mascota").value;
	
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

	var tableID = 'telefonos';
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var cell = row.insertCell(0);
    var element = document.createElement("input");
    element.type = "text";
    cell.appendChild(element);
}

function eliminarTelefono(){
	
	var tableID = 'telefonos';
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;

    table.deleteRow(rowCount-1);
}

function getCurrentDate(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	today = mm+'/'+dd+'/'+yyyy;
	return today;
}
