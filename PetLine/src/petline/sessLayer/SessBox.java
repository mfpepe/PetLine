package petline.sessLayer;

import java.sql.SQLException;

import petline.dataLayer.EntBox;
import petline.valueObject.Box;

public class SessBox {

	public Box obtenerBox(int idBox) throws SQLException{
		EntBox entBox = new EntBox();
		return entBox.getBox(idBox);
	}

	public Box obtenerBoxPorCodigo(String codigo) throws SQLException{
		EntBox entBox = new EntBox();
		return entBox.getBoxPorCodigo(codigo);
	}
	
}
