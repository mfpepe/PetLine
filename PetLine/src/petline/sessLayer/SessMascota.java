package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntMascota;
import petline.valueObject.Mascota;

public class SessMascota {
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<Mascota> obtenerMascotasPorUsuario( int idUsuario ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		return entMascota.getMascotasPorUsuario(idUsuario);
	}
	/**
	 * 
	 * @param idMascota
	 * @return
	 * @throws SQLException
	 */
	public Mascota obtenerMascota( int idMascota ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		return entMascota.getMascota(idMascota);
	}	
	/**
	 * 
	 * @param mascota
	 * @throws SQLException
	 */
	public void insertarMascota( Mascota mascota ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		entMascota.insertMascota(mascota);
	}	
	/**
	 * 
	 * @param mascota
	 * @throws SQLException
	 */
	public void actualizarMascota( Mascota mascota ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		entMascota.updateMascota(mascota);
	}	
	/**
	 * 
	 * @param idMascota
	 * @throws SQLException
	 */
	public void eliminarMascota( int idMascota ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		entMascota.deleteMascota(idMascota);
	}	
	
}
