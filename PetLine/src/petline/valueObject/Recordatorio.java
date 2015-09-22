package petline.valueObject;

import java.util.Calendar;

public class Recordatorio {

	private int idRecordatorio;
	private String descripcion;
	private Calendar fechaHora;
	private Frecuencia frecuencia;
	private int idMascota;

	public int getIdRecordatorio() {
		return idRecordatorio;
	}

	public void setIdRecordatorio(int idRecordatorio) {
		this.idRecordatorio = idRecordatorio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Calendar getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Frecuencia getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Frecuencia frecuencia) {
		this.frecuencia = frecuencia;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

}
