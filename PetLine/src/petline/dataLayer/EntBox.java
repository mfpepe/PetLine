package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import petline.util.ConnectionManager;
import petline.valueObject.Box;




public class EntBox {
	/**
	 * 
	 * @param idBox
	 * @return
	 * @throws SQLException
	 */
	public Box getBox( int idBox ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Box box = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select b.IdBox, b.Descripcion, b.Codigo, b.URL from box b where idBox=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idBox);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				box = new Box();
				box.setIdBox(rs.getInt(1));
				box.setDescripcion(rs.getString(2));
				box.setCodigo(rs.getString(3));
				box.setUrl(rs.getString(4));
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
		return box;
	}	
	/**
	 * 
	 * @param codigo
	 * @return
	 * @throws SQLException
	 */
	public Box getBoxPorCodigo( String codigo ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Box box = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select b.IdBox, b.Descripcion, b.Codigo, b.URL from box b where b.Codigo=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, codigo);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				box = new Box();
				box.setIdBox(rs.getInt(1));
				box.setDescripcion(rs.getString(2));
				box.setCodigo(rs.getString(3));
				box.setUrl(rs.getString(4));
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
		return box;
	}	
	/**
	 * 
	 * @param codigo
	 * @return
	 * @throws SQLException
	 */
	public Box getBoxValidoPorCodigo( String codigo ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Box box = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select b.IdBox, b.Descripcion, b.Codigo, b.URL from box b inner join usuariobox ub ON ub.IdBox!=b.idbox where b.Codigo=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, codigo);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				box = new Box();
				box.setIdBox(rs.getInt(1));
				box.setDescripcion(rs.getString(2));
				box.setCodigo(rs.getString(3));
				box.setUrl(rs.getString(4));
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
		return box;
	}		
}
