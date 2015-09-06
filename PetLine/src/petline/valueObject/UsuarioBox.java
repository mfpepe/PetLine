package petline.valueObject;

public class UsuarioBox {

	private int idUsuarioBox;
	private Usuario usuario;
	private Box box;

	public int getIdUsuarioBox() {
		return idUsuarioBox;
	}

	public void setIdUsuarioBox(int idUsuarioBox) {
		this.idUsuarioBox = idUsuarioBox;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}
}
