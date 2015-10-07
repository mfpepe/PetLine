function soloNumeros()
{
	var evt = window.event;
	var charCode = (evt.which) ? evt.which : event.keyCode
	if (charCode > 31 && (charCode < 48 || charCode > 57))
		return false;

	return true;
}

function soloLetras() {
	var e = window.event;
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) 
		return true;
    patron =/[A-ZñÑáéíóúÁÉÍÓÚa-z ]/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

function soloNumerosLetrasYPunto() {
	var evt = window.event;
	var charCode = (evt.which) ? evt.which : event.keyCode;
	
	patron =/[A-ZñÑáéíóúÁÉÍÓÚa-z ]/;
    te = String.fromCharCode(charCode);
	
	if ( patron.test(te) //LETRAS
			|| (charCode >= 48 && charCode <= 57) //NUMEROS
			|| charCode == 46 ) //PUNTO
		return true;

	return false;
}

function soloNumerosLetras() {
	var evt = window.event;
	var charCode = (evt.which) ? evt.which : event.keyCode;
	
	patron =/[A-ZñÑáéíóúÁÉÍÓÚa-z ]/;
    te = String.fromCharCode(charCode);
	
	if ( patron.test(te) //LETRAS
			|| (charCode >= 48 && charCode <= 57) //NUMEROS
			) //PUNTO
		return true;

	return false;
}

function validarMail(mail) {
	mail = mail.toLowerCase();
	if (/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/.test(mail)){
		return true;
	} else {
		return false;
	}
}

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
	var telefono = document.getElementById("telefono").value;
	var alias = document.getElementById("alias").value;
	var clave = document.getElementById("clave").value;
	var clave2 = document.getElementById("clave2").value;
	
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
	else if(!validarMail(email)){
		alert("Correo Electronico inválido.");
		return;		
	}
	
	if( trim(telefono) == "" ){
		alert("Teléfono es obligatorio.");
		return;
	}	

	if( trim(alias) == "" ){
		alert("Alias es obligatorio.");
		return;
	}		
	else if( alias.length < 5 ){
		alert("Alias debe contener mas de 4 caracteres.");
		return;		
	}
	
	if( trim(clave) == "" ){
		alert("Clave es obligatorio.");
		return;
	}	
	else if( alias.length < 7 ){
		alert("Clave debe contener mas de 6 caracteres.");
		return;		
	}
	
	if( trim(clave2) == "" ){
		alert("Repetir la clave es obligatorio.");
		return;
	}		
	
	if(trim(clave) != trim(clave2)){
		alert("Las claves no coinciden.");
		return;		
	}
	
	document.form1.submit();
}

function onChangeMascotaRecorrido(){
	
	var tracker = document.getElementById("tracker").value;
	var fecha = document.getElementById("fecha").value;
	
	if( trim(tracker) == "" || trim(fecha) == "" || !esFechaValida(fecha) ){
		document.getElementById("FRAMESEC").src="./empty.jsp";
	}
	else{
		document.getElementById("FRAMESEC").src="./recorridoCons.jsp?idTracker=" + tracker + "&fecha=" + fecha; 	
	}
}

function onChangeMascotaConsejo(){
	
	var mascota = document.getElementById("mascota").value;
	
	if( trim(mascota) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp";
	}
	else{
		document.getElementById("FRAMESEC").src="./consejos.jsp?idMascota=" + mascota; 	
	}
}

function onChangeMascotaRecordatorio(){
	
	var mascota = document.getElementById("mascota").value;
	
	if( trim(mascota) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp";
	}
	else{
		document.getElementById("FRAMESEC").src="./recordatorios.jsp?idMascota=" + mascota; 		
	}
}

function onChangeMascotaNotificacion(){
	
	var tracker = document.getElementById("tracker").value;
	
	if( trim(tracker) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp"
	}
	else{
		document.getElementById("FRAMESEC").src="./notificacion.jsp?idTracker=" + tracker;	
	}
}

function onChangeMascotaUbicacion(){
	
	var idTracker = document.getElementById("tracker").value;
	
	if( trim(idTracker) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp";
	}
	else{
		document.getElementById("FRAMESEC").src="./ubicacion.jsp?idTracker=" + idTracker;	
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
    
    $("#tel" + document.getElementById("cantTelefonos").value).mask("(+54) 9 $#####-####");
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

function esHoraValida(hora) 
{ 
	if (hora=='') {return false;} 
	if (hora.length>5) {return false;} 
	if (hora.length!=5) {return false;} 
	a=hora.charAt(0) //<=2 
	b=hora.charAt(1) //<4 
	c=hora.charAt(2) //: 
	d=hora.charAt(3) //<=5
	e=hora.charAt(4) 
	if ((a==2 && b>3) || (a>2)) {return false;} 
	if (d>5) {return false;}  
	if (c!=':') {return false;}
	if (e<0 || e>9) {return false;}
	
	return true;
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
	
	if(trim(document.getElementById("raza").value) == ""){
		alert("Raza de la Mascota es obligatorio.");
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
	
	if(trim(document.getElementById("raza").value) == ""){
		alert("Raza de la Mascota es obligatorio.");
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
	else if(!validarMail(document.getElementById("email").value)){
		alert("Correo Electronico inválido.");
		return;		
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
	else if( distancia>2000 ){
		alert("La distancia de un perimetro debe ser menor o igual a 2000.");
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
	else if( distancia>2000 ){
		alert("La distancia de un perimetro debe ser menor o igual a 2000.");
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

function validarAltaTrackerMascota(){

	if(trim(document.getElementById("mascota").value) == ""){
		alert("La Mascota es obligatoria.");
		return false;
	}

	if(trim(document.getElementById("tracker").value) == ""){
		alert("Tracker es obligatorio.");
		return false;
	}	
	
	var tempMin = document.getElementById("tempMin").value;
	if(trim(tempMin) == ""){
		alert("Temperatura Minima es obligatoria.");
		return false;	
	} else if( !esEntero(tempMin) ){
		alert("Temperatura Minima debe ser un numero entero.");
		return false;
	} 
	
	var tempMax = document.getElementById("tempMax").value;
	if(trim(tempMax) == ""){
		alert("Temperatura Maxima es obligatoria.");
		return false;	
	} else if( !esEntero(tempMax) ){
		alert("Temperatura Maxima debe ser un numero entero.");
		return false;
	} 	
	
	if( tempMin >= tempMax ){
		alert("Temperatura Maxima debe ser mayor a Temperatura Minima.");
		return false;
	}
	
	if( tempMin < -99 ){
		alert("Temperatura Minima debe ser mayor a -99.");
		return false;
	}	

	if( tempMax > 99 ){
		alert("Temperatura Maxima debe ser menor a 99.");
		return false;
	}	
	
	document.form1.submit();
	
}

function validarModificacionTrackerMascota(){
		
	var tempMin = document.getElementById("tempMin").value;
	if(trim(tempMin) == ""){
		alert("Temperatura Minima es obligatoria.");
		return false;	
	} else if( !esEntero(tempMin) ){
		alert("Temperatura Minima debe ser un numero entero.");
		return false;
	} 
	
	var tempMax = document.getElementById("tempMax").value;
	if(trim(tempMax) == ""){
		alert("Temperatura Maxima es obligatoria.");
		return false;	
	} else if( !esEntero(tempMax) ){
		alert("Temperatura Maxima debe ser un numero entero.");
		return false;
	} 	
	
	if( tempMin >= tempMax ){
		alert("Temperatura Maxima debe ser mayor a Temperatura Minima.");
		return false;
	}
	
	if( tempMin < -99 ){
		alert("Temperatura Minima debe ser mayor a -99.");
		return false;
	}	

	if( tempMax > 99 ){
		alert("Temperatura Maxima debe ser menor a 99.");
		return false;
	}	
	
	document.form1.submit();
	
}

function validarAltaAnotacion(){
	if(trim(document.getElementById("anotacion").value) == ""){
		alert("Anotación es obligatorio.");
		return false;
	}	
	
	document.form1.submit();
}

function validarModificacionAnotacion(){
	if(trim(document.getElementById("anotacion").value) == ""){
		alert("Anotación es obligatorio.");
		return false;
	}	
	
	document.form1.submit();
}

function validarAltaRecordatorio(){

	if(trim(document.getElementById("descripcion").value) == ""){
		alert("Descripcion es obligatorio.");
		return false;
	}
	
	var fechaNac = document.getElementById("fecha").value;
	
	if(trim(fechaNac) == ""){
		alert("La Fecha es obligatoria.");
		return false;
	}		
	else if( !esFechaValida(fechaNac) ){
		alert("La Fecha es inválida.");
		return false;		
	}
	
	var hora = document.getElementById("hora").value; 
	
	if(trim(hora) == ""){
		alert("Hora es obligatorio.");
		return false;
	} else if( !esHoraValida(hora) ){
		alert("Hora es inválida.");
		return false;
	} 

	if(trim(document.getElementById("frecuencia").value) == ""){
		alert("Frecuencia es obligatorio.");
		return false;
	}
	
	document.form1.submit();
}

function validarModificacionRecordatorio(){

	if(trim(document.getElementById("descripcion").value) == ""){
		alert("Descripcion es obligatorio.");
		return false;
	}
	
	var fechaNac = document.getElementById("fecha").value;
	
	if(trim(fechaNac) == ""){
		alert("La Fecha es obligatoria.");
		return false;
	}		
	else if( !esFechaValida(fechaNac) ){
		alert("La Fecha es inválida.");
		return false;		
	}
	
	var hora = document.getElementById("hora").value; 
	
	if(trim(hora) == ""){
		alert("Hora es obligatorio.");
		return false;
	} else if( !esHoraValida(hora) ){
		alert("Hora es inválida.");
		return false;
	} 

	if(trim(document.getElementById("frecuencia").value) == ""){
		alert("Frecuencia es obligatorio.");
		return false;
	}
	
	document.form1.submit();
}

function validarAltaNotificacion(){
	
	if(trim(document.getElementById("opc1").value)=="S" && document.getElementById("tel1").selectedIndex == -1){
		alert("Si desea recibir notificaciones de " + document.getElementById("tit1").innerHTML +  " seleccione al menos un Nro de Teléfono.");
		return false;
	}
	
	if(trim(document.getElementById("opc2").value)=="S" && document.getElementById("tel2").selectedIndex == -1){
		alert("Si desea recibir notificaciones de " + document.getElementById("tit2").innerHTML +  " seleccione al menos un Nro de Teléfono.");
		return false;
	}
	
	if(trim(document.getElementById("opc3").value)=="S" && document.getElementById("tel3").selectedIndex == -1){
		alert("Si desea recibir notificaciones de " + document.getElementById("tit3").innerHTML +  " seleccione al menos un Nro de Teléfono.");
		return false;
	}
	
	if(trim(document.getElementById("opc4").value)=="S" && document.getElementById("tel4").selectedIndex == -1){
		alert("Si desea recibir notificaciones de " + document.getElementById("tit4").innerHTML +  " seleccione al menos un Nro de Teléfono.");
		return false;
	}	
	
	document.form1.submit();
}