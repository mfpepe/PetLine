function trim(myString){
	if( myString == null ){
		return "";
	}
	return myString.replace(/^\s+/g,'').replace(/\s+$/g,'');
}

function onChangeMascota(){
	
	var mascota = document.getElementById("mascota").value;
	
	if( trim(mascota) == "" ){
		document.getElementById("FRAMESEC").src="./empty.jsp"
	}
	else{
		document.getElementById("FRAMESEC").src="./recorridoCons.jsp"	
	}
}