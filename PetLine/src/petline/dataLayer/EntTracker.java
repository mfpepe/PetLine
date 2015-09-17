package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import petline.util.ConnectionManager;
import petline.valueObject.Tracker;




public class EntTracker {

	public Tracker getTrackerPorCodigo( String codigo ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Tracker tracker = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select IdTracker, Codigo, Descripcion from tracker where Codigo=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, codigo);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				tracker = new Tracker();
				tracker.setIdTracker(rs.getInt(1));
				tracker.setCodigo(rs.getString(2));
				tracker.setDescripcion(rs.getString(3));
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
		return tracker;
	}		
}
