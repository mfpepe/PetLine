package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntPerimetro;
import petline.valueObject.Perimetro;

public class SessPerimetro {

	public Collection<Perimetro> obtenerPerimetrosPorUsuario(int idUsuario) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		return entPerimetro.getPerimetrosPorUsuario(idUsuario);
	}
	
	public Perimetro obtenerPerimetro(int idPerimetro) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		return entPerimetro.getPerimetro(idPerimetro);
	}	
	
	public void insertarPerimetro( Perimetro perimetro ) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		entPerimetro.insertPerimetro(perimetro);
	}	

	public void actualizarPerimetro( Perimetro perimetro ) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		entPerimetro.updatePerimetro(perimetro);
	}	
	
	public void eliminarPerimetro( int idPerimetro ) throws SQLException{
		EntPerimetro entPerimetro = new EntPerimetro();
		entPerimetro.deletePerimetro(idPerimetro);
	}	
	
}
