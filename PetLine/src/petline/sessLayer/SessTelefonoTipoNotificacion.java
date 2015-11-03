package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntTelefonoTipoNotificacion;
import petline.valueObject.TelefonoTipoNotificacion;

public class SessTelefonoTipoNotificacion {
	/**
	 * 
	 * @param idTipoNotificacion
	 * @param idTracker
	 * @return
	 * @throws SQLException
	 */
	public TelefonoTipoNotificacion obtenerTelefonoTipoNotificacion(int idTipoNotificacion, int idTracker) throws SQLException{
		EntTelefonoTipoNotificacion entTelefonoTipoNotificacion = new EntTelefonoTipoNotificacion();
		return entTelefonoTipoNotificacion.getTelefonoTipoNotificacion(idTipoNotificacion, idTracker);
	}
	/**
	 * 
	 * @param telefonoTipoNotificaciones
	 * @throws SQLException
	 */
	public void insertarTelefonoTipoNotificacion(Collection<TelefonoTipoNotificacion> telefonoTipoNotificaciones) throws SQLException{
		EntTelefonoTipoNotificacion entTelefonoTipoNotificacion = new EntTelefonoTipoNotificacion();
		entTelefonoTipoNotificacion.insertTelefonoTipoNotificacion(telefonoTipoNotificaciones);
	}
}
