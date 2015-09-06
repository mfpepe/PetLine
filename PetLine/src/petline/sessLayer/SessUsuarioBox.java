package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntUsuarioBox;
import petline.valueObject.UsuarioBox;

public class SessUsuarioBox {

	public Collection<UsuarioBox> obtenerBoxPorUsuario( int idUsuario ) throws SQLException{
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		return entUsuarioBox.getBoxPorUsuario(idUsuario);
	}	
	
	public void insertarUsuarioBox( UsuarioBox usuarioBox ) throws SQLException{
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		entUsuarioBox.insertUsuarioBox(usuarioBox);
	}	 
	
	public UsuarioBox obtenerUsuarioBox( int idUsuarioBox ) throws SQLException{
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		return entUsuarioBox.getUsuarioBox(idUsuarioBox);
	}

	public void modificarUsuarioBox(int idUsuarioBox, String descripcion) throws SQLException {
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		entUsuarioBox.updateUsuarioBox(idUsuarioBox, descripcion);
	}

	public void eliminarUsuarioBox(int idUsuarioBox) throws SQLException {
		EntUsuarioBox entUsuarioBox = new EntUsuarioBox();
		entUsuarioBox.deleteUsuarioBox(idUsuarioBox);
	}		
	
}
