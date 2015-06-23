function trim(myString){
	if( myString == null ){
		return "";
	}
	return myString.replace(/^\s+/g,'').replace(/\s+$/g,'');
}
