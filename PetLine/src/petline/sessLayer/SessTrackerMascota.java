package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntTrackerMascota;
import petline.valueObject.TrackerMascota;

public class SessTrackerMascota {

	public Collection<TrackerMascota> obtenerTrackerMascotaPorUsuario( int idUsuario ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		return entTrackerMascota.getTrackerMascotaPorUsuario(idUsuario);
	}	
	
	public TrackerMascota obtenerTrackerMascotaPorMascota( int idMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		return entTrackerMascota.getTrackerMascotaPorMascota(idMascota);
	}	

	public TrackerMascota obtenerTrackerMascotaPorTracker( int idTracker ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		return entTrackerMascota.getTrackerMascotaPorTracker(idTracker);
	}		
	
	public TrackerMascota obtenerTrackerMascota( int idTrackerMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		return entTrackerMascota.getTrackerMascota(idTrackerMascota);
	}	
	
	public void insertarTrackerMascota( TrackerMascota trackerMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		entTrackerMascota.insertTrackerMascota(trackerMascota);
	}	
	
	public void modificarTrackerMascota( TrackerMascota trackerMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		entTrackerMascota.updateTrackerMascota(trackerMascota);
	}		
	
	public void eliminarTrackerMascota( int idTrackerMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		entTrackerMascota.deleteTrackerMascota(idTrackerMascota);
	}	
}
