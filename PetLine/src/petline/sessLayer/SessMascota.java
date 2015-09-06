package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntMascota;
import petline.valueObject.Mascota;

public class SessMascota {

	public Collection<Mascota> obtenerMascotasPorUsuario( int idUsuario ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		return entMascota.getMascotasPorUsuario(idUsuario);
	}
	
	public Mascota obtenerMascota( int idMascota ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		return entMascota.getMascota(idMascota);
	}	
	
	public void insertarMascota( Mascota mascota ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		entMascota.insertMascota(mascota);
	}	

	public void actualizarMascota( Mascota mascota ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		entMascota.updateMascota(mascota);
	}	
	
	public void eliminarMascota( int idMascota ) throws SQLException{
		EntMascota entMascota = new EntMascota();
		entMascota.deleteMascota(idMascota);
	}	
	
}
