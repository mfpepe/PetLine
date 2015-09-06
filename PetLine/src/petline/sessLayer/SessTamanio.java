package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntTamanio;
import petline.valueObject.Tamanio;

public class SessTamanio {

	public Collection<Tamanio> obtenerTamaños() throws SQLException{
		EntTamanio entTamanio = new EntTamanio();
		return entTamanio.getTamaños();
	}

}
