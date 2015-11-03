package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntRecordatorio;
import petline.valueObject.Recordatorio;

public class SessRecordatorio {
	/**
	 * 
	 * @param idMascota
	 * @return
	 * @throws SQLException
	 */
	public Collection<Recordatorio> obtenerRecordatorios( int idMascota ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		return entRecordatorio.getRecordatorios(idMascota);
	}
	/**
	 * 
	 * @param idRecordatorio
	 * @return
	 * @throws SQLException
	 */
	public Recordatorio obtenerRecordatorio( int idRecordatorio ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		return entRecordatorio.getRecordatorio(idRecordatorio);
	}	
	/**
	 * 
	 * @param recordatorio
	 * @throws SQLException
	 */
	public void insertarRecordatorio( Recordatorio recordatorio ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		entRecordatorio.insertRecordatorio(recordatorio);
	}	
	/**
	 * 
	 * @param recordatorio
	 * @throws SQLException
	 */
	public void actualizarRecordatorio( Recordatorio recordatorio ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		entRecordatorio.updateRecordatorio(recordatorio);
	}	
	/**
	 * 
	 * @param idRecordatorio
	 * @throws SQLException
	 */
	public void eliminarRecordatorio( int idRecordatorio ) throws SQLException{
		EntRecordatorio entRecordatorio = new EntRecordatorio();
		entRecordatorio.deleteRecordatorio(idRecordatorio);
	}		
}
