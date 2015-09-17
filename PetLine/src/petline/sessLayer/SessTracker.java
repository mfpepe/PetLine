package petline.sessLayer;

import java.sql.SQLException;

import petline.dataLayer.EntTracker;
import petline.valueObject.Tracker;

public class SessTracker {

	public Tracker obtenerTrackerPorCodigo(String codigo) throws SQLException{
		EntTracker entTracker = new EntTracker();
		return entTracker.getTrackerPorCodigo(codigo);
	}
	
}
