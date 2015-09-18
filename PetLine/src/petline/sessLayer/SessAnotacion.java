package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntAnotacion;
import petline.valueObject.Anotacion;

public class SessAnotacion {

	public Collection<Anotacion> obtenerAnotacionesPorUsuario(int idUsuario) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		return entAnotacion.getAnotacionesPorUsuario(idUsuario);
	}
	
	public Anotacion obtenerAnotacion(int idAnotacion) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		return entAnotacion.getAnotacion(idAnotacion);
	}	
	
	public void insertarAnotacion( Anotacion anotacion ) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		entAnotacion.insertAnotacion(anotacion);
	}	

	public void actualizarAnotacion( Anotacion anotacion ) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		entAnotacion.updateAnotacion(anotacion);
	}	
	
	public void eliminarAnotacion( int idAnotacion ) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		entAnotacion.deleteAnotacion(idAnotacion);
	}	
	
}
