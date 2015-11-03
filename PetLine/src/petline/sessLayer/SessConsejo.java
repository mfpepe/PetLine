package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntConsejo;
import petline.valueObject.Consejo;

public class SessConsejo {
	/**
	 * 
	 * @param idMascota
	 * @return
	 * @throws SQLException
	 */
	public Collection<Consejo> obtenerConsejos( int idMascota ) throws SQLException{
		EntConsejo entConsejo = new EntConsejo();
		return entConsejo.getConsejos(idMascota);
	}
}
