package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntTrackerMascota;
import petline.valueObject.TrackerMascota;

public class SessTrackerMascota {
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<TrackerMascota> obtenerTrackerMascotaPorUsuario( int idUsuario ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		return entTrackerMascota.getTrackerMascotaPorUsuario(idUsuario);
	}	
	/**
	 * 
	 * @param idMascota
	 * @return
	 * @throws SQLException
	 */
	public TrackerMascota obtenerTrackerMascotaPorMascota( int idMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		return entTrackerMascota.getTrackerMascotaPorMascota(idMascota);
	}	
	/**
	 * 
	 * @param idTracker
	 * @return
	 * @throws SQLException
	 */
	public TrackerMascota obtenerTrackerMascotaPorTracker( int idTracker ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		return entTrackerMascota.getTrackerMascotaPorTracker(idTracker);
	}		
	/**
	 * 
	 * @param idTrackerMascota
	 * @return
	 * @throws SQLException
	 */
	public TrackerMascota obtenerTrackerMascota( int idTrackerMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		return entTrackerMascota.getTrackerMascota(idTrackerMascota);
	}	
	/**
	 * 
	 * @param trackerMascota
	 * @throws SQLException
	 */
	public void insertarTrackerMascota( TrackerMascota trackerMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		entTrackerMascota.insertTrackerMascota(trackerMascota);
	}	
	/**
	 * 
	 * @param trackerMascota
	 * @throws SQLException
	 */
	public void modificarTrackerMascota( TrackerMascota trackerMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		entTrackerMascota.updateTrackerMascota(trackerMascota);
	}		
	/**
	 * 
	 * @param idTrackerMascota
	 * @param tempActual
	 * @throws SQLException
	 */
	public void modificarTemperaturaActualTrackerMascota( int idTrackerMascota, int tempActual ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		entTrackerMascota.updateTemperaturaActualTrackerMascota(idTrackerMascota, tempActual);
	}		
	/**
	 * 
	 * @param idTrackerMascota
	 * @throws SQLException
	 */
	public void eliminarTrackerMascota( int idTrackerMascota ) throws SQLException{
		EntTrackerMascota entTrackerMascota = new EntTrackerMascota();
		entTrackerMascota.deleteTrackerMascota(idTrackerMascota);
	}	
}
