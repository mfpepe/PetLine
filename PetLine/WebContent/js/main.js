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

	document.getElementById("cantTelefonos").value++;
	
	var tableID = 'telefonos';
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);

    var cell = row.insertCell(0);
    var element = document.createElement("input");
    element.type = "text";
    element.id = "tel" + document.getElementById("cantTelefonos").value;
    element.name = "tel" + document.getElementById("cantTelefonos").value;
    cell.appendChild(element);
}

function eliminarTelefono(){
	
	document.getElementById("cantTelefonos").value--;
	
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

function esFechaValida(fecha){
	var dtCh= "/";
	var minYear=1900;
	var maxYear=2100;
	function isInteger(s){
		var i;
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (((c < "0") || (c > "9"))) return false;
		}
		return true;
	}
	function stripCharsInBag(s, bag){
		var i;
		var returnString = "";
		for (i = 0; i < s.length; i++){
			var c = s.charAt(i);
			if (bag.indexOf(c) == -1) returnString += c;
		}
		return returnString;
	}
	function daysInFebruary (year){
		return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
	}
	function DaysArray(n) {
		for (var i = 1; i <= n; i++) {
			this[i] = 31
			if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
			if (i==2) {this[i] = 29}
		}
		return this
	}
	function isDate(dtStr){
		var daysInMonth = DaysArray(12)
		var pos1=dtStr.indexOf(dtCh)
		var pos2=dtStr.indexOf(dtCh,pos1+1)
		var strDay=dtStr.substring(0,pos1)
		var strMonth=dtStr.substring(pos1+1,pos2)
		var strYear=dtStr.substring(pos2+1)
		strYr=strYear
		if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
		if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
		for (var i = 1; i <= 3; i++) {
			if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
		}
		month=parseInt(strMonth)
		day=parseInt(strDay)
		year=parseInt(strYr)
		if (pos1==-1 || pos2==-1){
			return false
		}
		if (strMonth.length<1 || month<1 || month>12){
			return false
		}
		if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
			return false
		}
		if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
			return false
		}
		if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
			return false
		}
		return true
	}
	if(isDate(fecha)){
		return true;
	}else{
		return false;
	}
}

function esEntero(numero){
    if (isNaN(numero)){
        return false;
    }
    else{
        if (numero % 1 == 0) {
        	return true;//alert ("Es un numero entero");
        }
        else{
        	return false;//alert ("Es un numero decimal");
        }
    }
}

function validarAltaMascota(){

	if(trim(document.getElementById("apodo").value) == ""){
		alert("El Apodo de la Mascota es obligatorio.");
		return false;
	}
	
	var fechaNac = document.getElementById("edad").value;
	
	if(trim(fechaNac) == ""){
		alert("La Fecha de Nacimiento de la Mascota es obligatoria.");
		return false;
	}		
	else if( !esFechaValida(fechaNac) ){
		alert("La Fecha de Nacimiento es inválida.");
		return false;		
	}
	
	var peso = document.getElementById("peso").value; 
	
	if(trim(peso) == ""){
		alert("El Peso de la Mascota es obligatorio.");
		return false;
	} else if( !esEntero(peso) ){
		alert("El Peso de la Mascota debe ser un numero entero.");
		return false;
	} 

	var objetivo = document.getElementById("objetivo").value; 
	if(trim(peso) != "" && !esEntero(peso)){
		alert("El Objetivo diario de la Mascota debe ser un numero entero.");
		return false;	
	}
	
	if(trim(document.getElementById("tamanio").value) == ""){
		alert("El Tamaño de la Mascota es obligatorio.");
		return false;
	}
		
	
	document.form1.submit();
}

function validarModificacionMascota(){

	if(trim(document.getElementById("apodo").value) == ""){
		alert("El Apodo de la Mascota es obligatorio.");
		return false;
	}
	
	var fechaNac = document.getElementById("edad").value;
	
	if(trim(fechaNac) == ""){
		alert("La Fecha de Nacimiento de la Mascota es obligatoria.");
		return false;
	}		
	else if( !esFechaValida(fechaNac) ){
		alert("La Fecha de Nacimiento es inválida.");
		return false;		
	}
	
	var peso = document.getElementById("peso").value; 
	
	if(trim(peso) == ""){
		alert("El Peso de la Mascota es obligatorio.");
		return false;
	} else if( !esEntero(peso) ){
		alert("El Peso de la Mascota debe ser un numero entero.");
		return false;
	} 

	var objetivo = document.getElementById("objetivo").value; 
	if(trim(peso) != "" && !esEntero(peso)){
		alert("El Objetivo diario de la Mascota debe ser un numero entero.");
		return false;	
	}
	
	if(trim(document.getElementById("tamanio").value) == ""){
		alert("El Tamaño de la Mascota es obligatorio.");
		return false;
	}
		
	
	document.form1.submit();
}

function validarModificacionUsuario(){

	if(trim(document.getElementById("nombre").value) == ""){
		alert("El Nombre es obligatorio.");
		return false;
	}
	
	if(trim(document.getElementById("apellido").value) == ""){
		alert("El Apellido es obligatorio.");
		return false;
	}	
	
	if(trim(document.getElementById("email").value) == ""){
		alert("El Correo Electronico es obligatorio.");
		return false;
	}		
	
	var cantTelefonos = document.getElementById("cantTelefonos").value;
	if( cantTelefonos == 0 ){
		alert("Debe informar al menos un telefono.");
		return false;
	}
	
	for (i = 1; i <= cantTelefonos; i++) { 
		if( trim(document.getElementById("tel" + i).value) == "" ){
			alert("El telefono es obligatorio.");
			return false;
		}
	}
	
	document.form1.submit();
	
}

function validarAltaBox(){

	if(trim(document.getElementById("codigo").value) == ""){
		alert("El Código es obligatorio.");
		return false;
	}
	
	if(trim(document.getElementById("descripcion").value) == ""){
		alert("La Descripción es obligatoria.");
		return false;
	}	
		
	document.form1.submit();
	
}

function validarModificacionBox(){

	if(trim(document.getElementById("descripcion").value) == ""){
		alert("La Descripción es obligatoria.");
		return false;
	}	
		
	document.form1.submit();
	
}

function validarAltaPerimetro(){

	if(trim(document.getElementById("descripcion").value) == ""){
		alert("La Descripción es obligatoria.");
		return false;
	}	
	
	var distancia = document.getElementById("distancia").value;
	if(trim(distancia) == ""){
		alert("La Distancia es obligatoria.");
		return false;	
	} else if( !esEntero(distancia) ){
		alert("La distancia de un perimetro debe ser un numero entero.");
		return false;
	} 
	else if( distancia<10 ){
		alert("La distancia de un perimetro debe ser mayor o igual a 10.");
		return false;
	} 
	document.form1.submit();
	
}

function validarModificacionPerimetro(){

	if(trim(document.getElementById("descripcion").value) == ""){
		alert("La Descripción es obligatoria.");
		return false;
	}	
	
	var distancia = document.getElementById("distancia").value;
	if(trim(distancia) == ""){
		alert("La Distancia es obligatoria.");
		return false;	
	} else if( !esEntero(distancia) ){
		alert("La distancia de un perimetro debe ser un numero entero.");
		return false;
	} 
	else if( distancia<10 ){
		alert("La distancia de un perimetro debe ser mayor o igual a 10.");
		return false;
	} 
	document.form1.submit();
	
}

function validarAltaMascotaPerimetro(){

	if(trim(document.getElementById("mascota").value) == ""){
		alert("La Mascota es obligatoria.");
		return false;
	}		
	
	if(trim(document.getElementById("perimetro").value) == ""){
		alert("El perímetro es obligatorio.");
		return false;
	}	
	
	document.form1.submit();
	
}
