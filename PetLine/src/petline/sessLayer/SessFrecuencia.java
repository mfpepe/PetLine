package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntFrecuencia;
import petline.valueObject.Frecuencia;

public class SessFrecuencia {

	public Collection<Frecuencia> obtenerFrecuencias() throws SQLException{
		EntFrecuencia entFrecuencia = new EntFrecuencia();
		return entFrecuencia.getFrecuencias();
	}
}
