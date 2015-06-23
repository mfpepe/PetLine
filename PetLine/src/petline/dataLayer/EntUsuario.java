package petline.dataLayer;



public class EntUsuario {

	public boolean isUserValid(String user, String pass) {
		return ( "admin".equalsIgnoreCase(user) && "admin".equalsIgnoreCase(pass) );
//		TODO:		
//		Connection con = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		boolean isInvalidUser = false;
//		try {
//			con = ConnectionManager.getConnection();
//
//			StringBuffer query = new StringBuffer();
//			query.append( "select * from Usuarios where userName=? and pass=?" );
//
//			stmt = con.prepareStatement(query.toString());
//
//			stmt.setString(1, user);
//			stmt.setString(2, HashGenerator.convert(pass));
//			
//			rs = stmt.executeQuery();
//
//			isInvalidUser = rs.next();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//		    if (rs != null) {
//		        try {
//		            rs.close();
//		        } catch (SQLException e) {}
//		    }
//		    if (stmt != null) {
//		        try {
//		        	stmt.close();
//		        } catch (SQLException e) {}
//		    }
//		    if (con != null) {
//		        try {
//		            con.close();
//		        } catch (SQLException e) {}
//		    }
//		}
//		return isInvalidUser;
	}

}
