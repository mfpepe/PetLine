package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntTelefono;
import petline.valueObject.Telefono;

public class SessTelefono {
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<Telefono> obtenerTelefonosPorUsuario(int idUsuario) throws SQLException{
		EntTelefono entTelefono = new EntTelefono();
		return entTelefono.getTelefonosPorUsuario(idUsuario);
	}
	/**
	 * 
	 * @param idUsuario
	 * @param telefonos
	 * @throws SQLException
	 */
	public void actualizarTelefonos( int idUsuario, Collection<Telefono> telefonos ) throws SQLException{
		EntTelefono entTelefono = new EntTelefono();
		entTelefono.updateTelefonos(idUsuario, telefonos);
	}
	
}
