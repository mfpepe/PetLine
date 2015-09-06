package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntTelefono;
import petline.valueObject.Telefono;

public class SessTelefono {

	public Collection<Telefono> obtenerTelefonosPorUsuario(int idUsuario) throws SQLException{
		EntTelefono entTelefono = new EntTelefono();
		return entTelefono.getTelefonosPorUsuario(idUsuario);
	}

	public void actualizarTelefonos( int idUsuario, Collection<String> telefonos ) throws SQLException{
		EntTelefono entTelefono = new EntTelefono();
		entTelefono.updateTelefonos(idUsuario, telefonos);
	}
	
}
