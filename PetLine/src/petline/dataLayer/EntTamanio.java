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

	public Collection<Tamanio> getTama�os() throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Tamanio> tama�os = new ArrayList<Tamanio>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("	select IdTama�o, Descripcion from tama�o");

			stmt = con.prepareStatement(query.toString());

			rs = stmt.executeQuery();

			while (rs.next()) {
				Tamanio tama�o = new Tamanio();
				tama�o.setIdTamanio(rs.getInt(1));
				tama�o.setDescripcion(rs.getString(2));

				tama�os.add(tama�o);
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
		return tama�os;
	}
}
