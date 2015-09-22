package petline.sessLayer;

import java.sql.SQLException;
import java.util.Collection;

import petline.dataLayer.EntTipoNotificacion;
import petline.valueObject.TipoNotificacion;

public class SessTipoNotificacion {

	public Collection<TipoNotificacion> obtenerTipoNotificaciones() throws SQLException{
		EntTipoNotificacion entTipoNotificacion = new EntTipoNotificacion();
		return entTipoNotificacion.getTipoNotificaciones();
	}
}
