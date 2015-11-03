package petline.sessLayer;

import java.sql.SQLException;

import petline.dataLayer.EntBox;
import petline.valueObject.Box;

public class SessBox {

	/**
	 * 
	 * @param idBox
	 * @return
	 * @throws SQLException
	 */
	public Box obtenerBox(int idBox) throws SQLException{
		EntBox entBox = new EntBox();
		return entBox.getBox(idBox);
	}

	/**
	 * 
	 * @param codigo
	 * @return
	 * @throws SQLException
	 */
	public Box obtenerBoxPorCodigo(String codigo) throws SQLException{
		EntBox entBox = new EntBox();
		return entBox.getBoxPorCodigo(codigo);
	}
	
	/**
	 * 
	 * @param codigo
	 * @return
	 * @throws SQLException
	 */
	public Box obtenerBoxValidoPorCodigo(String codigo) throws SQLException{
		EntBox entBox = new EntBox();
		return entBox.getBoxValidoPorCodigo(codigo);
	}	
	
}
