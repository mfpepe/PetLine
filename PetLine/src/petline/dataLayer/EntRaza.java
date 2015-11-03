package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Raza;




public class EntRaza {
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Collection<Raza> getRazas() throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Raza> razas = new ArrayList<Raza>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select idRaza, Descripcion from raza" );

			stmt = con.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();

			while(rs.next()){
				Raza raza = new Raza();
				raza.setIdRaza(rs.getInt(1));
				raza.setDescripcion(rs.getString(2));
				razas.add(raza);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;			
		} finally {
		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException e) {}
		    }
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
		return razas;
	}
	
}
