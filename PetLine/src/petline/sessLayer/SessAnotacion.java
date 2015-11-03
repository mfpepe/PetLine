package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntAnotacion;
import petline.valueObject.Anotacion;

public class SessAnotacion {

	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<Anotacion> obtenerAnotacionesPorUsuario(int idUsuario) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		return entAnotacion.getAnotacionesPorUsuario(idUsuario);
	}
	
	/**
	 * 
	 * @param idAnotacion
	 * @return
	 * @throws SQLException
	 */
	public Anotacion obtenerAnotacion(int idAnotacion) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		return entAnotacion.getAnotacion(idAnotacion);
	}	
	
	/**
	 * 
	 * @param anotacion
	 * @throws SQLException
	 */
	public void insertarAnotacion( Anotacion anotacion ) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		entAnotacion.insertAnotacion(anotacion);
	}	

	/**
	 * 
	 * @param anotacion
	 * @throws SQLException
	 */
	public void actualizarAnotacion( Anotacion anotacion ) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		entAnotacion.updateAnotacion(anotacion);
	}	
	
	/**
	 * 
	 * @param idAnotacion
	 * @throws SQLException
	 */
	public void eliminarAnotacion( int idAnotacion ) throws SQLException{
		EntAnotacion entAnotacion = new EntAnotacion();
		entAnotacion.deleteAnotacion(idAnotacion);
	}	
	
}
