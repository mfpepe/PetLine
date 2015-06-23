package petline.sessLayer;

import petline.dataLayer.EntUsuario;

public class SessUsuario {

	public boolean esUsuarioValido( String user, String pass ){
		EntUsuario entUsuario = new EntUsuario();
		return entUsuario.isUserValid(user, pass);
	}
	
}
