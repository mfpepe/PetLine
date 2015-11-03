package petline.dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Telefono;

public class EntTelefono {
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<Telefono> getTelefonosPorUsuario(int idUsuario) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Telefono> telefonos = new ArrayList<Telefono>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("	select Nro, IdUsuario, Descripcion from telefono where IdUsuario=?");

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idUsuario);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				Telefono telefono = new Telefono();
				telefono.setNroTelefono(rs.getString(1));
				telefono.setIdUsuario(rs.getInt(2));
				telefono.setDescripcion(rs.getString(3));
				
				telefonos.add(telefono);
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
		return telefonos;
	}
	/**
	 * 
	 * @param idUsuario
	 * @param telefonos
	 * @throws SQLException
	 */
	public void updateTelefonos( int idUsuario, Collection<Telefono> telefonos ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			con.setAutoCommit(false);
			
			stmt = con.prepareStatement("	delete from telefono where IdUsuario=?");
			
			stmt.setInt(1, idUsuario);
			
			stmt.execute();
			
			stmt.clearParameters();
			
			for (Telefono telefono : telefonos) {
				stmt = con.prepareStatement("	insert into telefono (Nro, IdUsuario, Descripcion) values ('" + telefono.getNroTelefono() + "', " + idUsuario + ",'" + telefono.getDescripcion() + "')");
				stmt.execute();
			}

			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;			
		} finally {
		    if (stmt != null) {
		        try {
		        	stmt.close();
		        } catch (SQLException e) {}
		    }
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) {}
		    }
		}
	}		
}
