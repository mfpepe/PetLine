package petline.valueObject;

import java.util.ArrayList;
import java.util.Collection;

public class TelefonoTipoNotificacion {

	private int idTelefonoTipoNotificacion;
	private Tracker tracker;
	private TipoNotificacion tipoNotificacion;
	private Collection<String> nroTelefonos;

	public TelefonoTipoNotificacion() {
		nroTelefonos = new ArrayList<String>();
	}
	
	public int getIdTelefonoTipoNotificacion() {
		return idTelefonoTipoNotificacion;
	}

	public void setIdTelefonoTipoNotificacion(int idTelefonoTipoNotificacion) {
		this.idTelefonoTipoNotificacion = idTelefonoTipoNotificacion;
	}

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

	public TipoNotificacion getTipoNotificacion() {
		return tipoNotificacion;
	}

	public void setTipoNotificacion(TipoNotificacion tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}

	public Collection<String> getNroTelefonos() {
		return nroTelefonos;
	}

	public void setNroTelefonos(Collection<String> nroTelefonos) {
		this.nroTelefonos = nroTelefonos;
	}

}
