package petline.sessLayer;

import java.sql.SQLException;

import petline.dataLayer.EntUsuario;
import petline.valueObject.Usuario;

public class SessUsuario {

	public boolean esUsuarioValido( String user, String pass ){
		EntUsuario entUsuario = new EntUsuario();
		return entUsuario.isUserValid(user, pass);
	}
	
	public Usuario obtenerUsuario( String user, String pass ){
		EntUsuario entUsuario = new EntUsuario();
		return entUsuario.getUsuario(user, pass);
	}	
	
	public Usuario obtenerUsuarioPorId( int idUsuario ){
		EntUsuario entUsuario = new EntUsuario();
		return entUsuario.getUsuarioPorId(idUsuario);
	}	
	
	public Usuario obtenerUsuarioPorAlias( String userId ){
		EntUsuario entUsuario = new EntUsuario();
		return entUsuario.getUsuarioPorAlias(userId);
	}		
	
	public void actualizarUsuario( Usuario usuario ) throws SQLException{
		EntUsuario entUsuario = new EntUsuario();
		entUsuario.updateUsuario(usuario);
	}		
	
	public void registrarUsuario( Usuario usuario, String telefono, int idBox ) throws SQLException{
		EntUsuario entUsuario = new EntUsuario();
		entUsuario.registerUsuario(usuario, telefono, idBox);
	}	
	
}
