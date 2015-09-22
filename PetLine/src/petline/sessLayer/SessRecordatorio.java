package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntRecordatorio;
import petline.valueObject.Recordatorio;

public class SessRecordatorio {

	public Collection<Recordatorio> obtenerRecordatorios( int idMascota ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		return entRecordatorio.getRecordatorios(idMascota);
	}
	
	public Recordatorio obtenerRecordatorio( int idRecordatorio ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		return entRecordatorio.getRecordatorio(idRecordatorio);
	}	
	
	public void insertarRecordatorio( Recordatorio recordatorio ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		entRecordatorio.insertRecordatorio(recordatorio);
	}	

	public void actualizarRecordatorio( Recordatorio recordatorio ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		entRecordatorio.updateRecordatorio(recordatorio);
	}	
	
	public void eliminarRecordatorio( int idRecordatorio ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		entRecordatorio.deleteRecordatorio(idRecordatorio);
	}		
}
