package petline.valueObject;

import java.util.Calendar;

public class Mascota {

	private int idMascota;
	private String apodo;
	private Calendar fechaNacimiento;
	private int peso;
	private int kmDiarios;
	private int idTamaño;
	private int idUsuario;
	private int idPerimetro;

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public Calendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Calendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public int getKmDiarios() {
		return kmDiarios;
	}

	public void setKmDiarios(int kmDiarios) {
		this.kmDiarios = kmDiarios;
	}

	public int getIdTamaño() {
		return idTamaño;
	}

	public void setIdTamaño(int idTamaño) {
		this.idTamaño = idTamaño;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdPerimetro() {
		return idPerimetro;
	}

	public void setIdPerimetro(int idPerimetro) {
		this.idPerimetro = idPerimetro;
	}

}
