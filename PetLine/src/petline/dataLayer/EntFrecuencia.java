package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Frecuencia;




public class EntFrecuencia {

	public Collection<Frecuencia> getFrecuencias() throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Frecuencia> frecuencias = new ArrayList<Frecuencia>();
		try {
			con = ConnectionManager.getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append( 	"	select IdFrecuencia, Descripcion from frecuencia " );

			stmt = con.prepareStatement(query.toString());

			rs = stmt.executeQuery();

			while(rs.next()){
				Frecuencia frecuencia = new Frecuencia();
				frecuencia.setIdFrecuencia(rs.getInt(1));
				frecuencia.setDescripcion(rs.getString(2));
				
				frecuencias.add(frecuencia);
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
		return frecuencias;
	}
	
}
