package petline.valueObject;


public class TipoNotificacion {

	private int idTipoNotificacion;
	private String titulo;
	private String mensaje;

	public int getIdTipoNotificacion() {
		return idTipoNotificacion;
	}

	public void setIdTipoNotificacion(int idTipoNotificacion) {
		this.idTipoNotificacion = idTipoNotificacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
