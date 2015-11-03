package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntPerimetro;
import petline.valueObject.Perimetro;

public class SessPerimetro {
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<Perimetro> obtenerPerimetrosPorUsuario(int idUsuario) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		return entPerimetro.getPerimetrosPorUsuario(idUsuario);
	}
	/**
	 * 
	 * @param idPerimetro
	 * @return
	 * @throws SQLException
	 */
	public Perimetro obtenerPerimetro(int idPerimetro) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		return entPerimetro.getPerimetro(idPerimetro);
	}	
	/**
	 * 
	 * @param perimetro
	 * @throws SQLException
	 */
	public void insertarPerimetro( Perimetro perimetro ) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		entPerimetro.insertPerimetro(perimetro);
	}	
	/**
	 * 
	 * @param perimetro
	 * @throws SQLException
	 */
	public void actualizarPerimetro( Perimetro perimetro ) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		entPerimetro.updatePerimetro(perimetro);
	}	
	/**
	 * 
	 * @param idPerimetro
	 * @throws SQLException
	 */
	public void eliminarPerimetro( int idPerimetro ) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		entPerimetro.deletePerimetro(idPerimetro);
	}	
	
}
