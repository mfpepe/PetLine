package petline.sessLayer;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;

import petline.dataLayer.EntCoordenada;
import petline.valueObject.Coordenada;

public class SessCoordenada {
	/**
	 * 
	 * @param idTracker
	 * @param fecha
	 * @return
	 * @throws SQLException
	 */
	public Collection<Coordenada> obtenerCoordenadas( int idTracker, Calendar fecha ) throws SQLException{
		EntCoordenada entCoordenada = new EntCoordenada();
		return entCoordenada.getCoordenadas(idTracker, fecha);
	}
	/**
	 * 
	 * @param idTracker
	 * @return
	 * @throws SQLException
	 */
	public Coordenada obtenerUltimaCoordenada( int idTracker ) throws SQLException{
		EntCoordenada entCoordenada = new EntCoordenada();
		return entCoordenada.getUltimaCoordenada(idTracker);
	}
	/**
	 * 
	 * @param coordenada
	 * @throws SQLException
	 */
	public void insertarCoordenada( Coordenada coordenada ) throws SQLException{
		EntCoordenada entCoordenada = new EntCoordenada();
		entCoordenada.insertCoordenada(coordenada);
	}
}
