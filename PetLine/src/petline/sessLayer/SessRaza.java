package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntRaza;
import petline.valueObject.Raza;

public class SessRaza {

	public Collection<Raza> obtenerRazas() throws SQLException{
		EntRaza entRaza = new EntRaza();
		return entRaza.getRazas();
	}
	
}
