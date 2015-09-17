package petline.valueObject;

import java.util.Calendar;

public class Coordenada {

	private int idCoordenada;
	private int idTracker;
	private String latitud;
	private String longitud;
	private Calendar fechaHora;

	public int getIdCoordenada() {
		return idCoordenada;
	}

	public void setIdCoordenada(int idCoordenada) {
		this.idCoordenada = idCoordenada;
	}

	public int getIdTracker() {
		return idTracker;
	}

	public void setIdTracker(int idTracker) {
		this.idTracker = idTracker;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public Calendar getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}

}
