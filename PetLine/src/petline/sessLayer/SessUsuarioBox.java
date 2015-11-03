package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntUsuarioBox;
import petline.valueObject.UsuarioBox;

public class SessUsuarioBox {
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<UsuarioBox> obtenerBoxPorUsuario( int idUsuario ) throws SQLException{
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		return entUsuarioBox.getBoxPorUsuario(idUsuario);
	}	
	/**
	 * 
	 * @param usuarioBox
	 * @throws SQLException
	 */
	public void insertarUsuarioBox( UsuarioBox usuarioBox ) throws SQLException{
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		entUsuarioBox.insertUsuarioBox(usuarioBox);
	}	 
	/**
	 * 
	 * @param idUsuarioBox
	 * @return
	 * @throws SQLException
	 */
	public UsuarioBox obtenerUsuarioBox( int idUsuarioBox ) throws SQLException{
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		return entUsuarioBox.getUsuarioBox(idUsuarioBox);
	}
	/**
	 * 
	 * @param idUsuarioBox
	 * @param descripcion
	 * @throws SQLException
	 */
	public void modificarUsuarioBox(int idUsuarioBox, String descripcion) throws SQLException {
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		entUsuarioBox.updateUsuarioBox(idUsuarioBox, descripcion);
	}
	/**
	 * 
	 * @param idUsuarioBox
	 * @throws SQLException
	 */
	public void eliminarUsuarioBox(int idUsuarioBox) throws SQLException {
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		entUsuarioBox.deleteUsuarioBox(idUsuarioBox);
	}		
	
}
