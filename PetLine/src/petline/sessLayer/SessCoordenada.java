package petline.sessLayer;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;

import petline.dataLayer.EntCoordenada;
import petline.valueObject.Coordenada;

public class SessCoordenada {

	public Collection<Coordenada> obtenerCoordenadas( int idTracker, Calendar fecha ) throws SQLException{
		EntCoordenada entCoordenada = new EntCoordenada();
		return entCoordenada.getCoordenadas(idTracker, fecha);
	}

	public Coordenada obtenerUltimaCoordenada( int idTracker ) throws SQLException{
		EntCoordenada entCoordenada = new EntCoordenada();
		return entCoordenada.getUltimaCoordenada(idTracker);
	}
	
	public void insertarCoordenada( Coordenada coordenada ) throws SQLException{
		EntCoordenada entCoordenada = new EntCoordenada();
		entCoordenada.insertCoordenada(coordenada);
	}
}
