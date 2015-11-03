package petline.sessLayer;

import java.sql.SQLException;

import petline.dataLayer.EntUsuario;
import petline.valueObject.Usuario;

public class SessUsuario {
	/**
	 * 
	 * @param user
	 * @param pass
	 * @return
	 */
	public boolean esUsuarioValido( String user, String pass ){
		EntUsuario entUsuario = new EntUsuario();
		return entUsuario.isUserValid(user, pass);
	}
	/**
	 * 
	 * @param user
	 * @param pass
	 * @return
	 */
	public Usuario obtenerUsuario( String user, String pass ){
		EntUsuario entUsuario = new EntUsuario();
		return entUsuario.getUsuario(user, pass);
	}	
	/**
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario obtenerUsuarioPorId( int idUsuario ){
		EntUsuario entUsuario = new EntUsuario();
		return entUsuario.getUsuarioPorId(idUsuario);
	}	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public Usuario obtenerUsuarioPorAlias( String userId ){
		EntUsuario entUsuario = new EntUsuario();
		return entUsuario.getUsuarioPorAlias(userId);
	}		
	/**
	 * 
	 * @param usuario
	 * @throws SQLException
	 */
	public void actualizarUsuario( Usuario usuario ) throws SQLException{
		EntUsuario entUsuario = new EntUsuario();
		entUsuario.updateUsuario(usuario);
	}		
	/**
	 * 
	 * @param usuario
	 * @param telefono
	 * @param idBox
	 * @throws SQLException
	 */
	public void registrarUsuario( Usuario usuario, String telefono, int idBox ) throws SQLException{
		EntUsuario entUsuario = new EntUsuario();
		entUsuario.registerUsuario(usuario, telefono, idBox);
	}	
	
}
