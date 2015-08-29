package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import petline.util.ConnectionManager;
import petline.util.HashGenerator;




public class EntUsuario {

	public boolean isUserValid(String user, String pass) {
		//return ( "admin".equalsIgnoreCase(user) && "admin".equalsIgnoreCase(pass) );
	
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean isValidUser = false;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( "select * from usuario where Usuariocol=? and Password=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, user);
			stmt.setString(2, HashGenerator.convert(pass));
			
			rs = stmt.executeQuery();

			isValidUser = rs.next();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
		return isValidUser;
	}

}
