package petline.dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Tamanio;

public class EntTamanio {

	public Collection<Tamanio> getTamaños() throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Tamanio> tamaños = new ArrayList<Tamanio>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("	select IdTamaño, Descripcion from tamaño");

			stmt = con.prepareStatement(query.toString());

			rs = stmt.executeQuery();

			while (rs.next()) {
				Tamanio tamaño = new Tamanio();
				tamaño.setIdTamanio(rs.getInt(1));
				tamaño.setDescripcion(rs.getString(2));

				tamaños.add(tamaño);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return tamaños;
	}
}
