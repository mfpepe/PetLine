package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Anotacion;




public class EntAnotacion {
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<Anotacion> getAnotacionesPorUsuario( int idUsuario ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Anotacion> anotaciones = new ArrayList<Anotacion>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select Id, Texto, IdUsuario from anotacion where IdUsuario=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idUsuario);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				Anotacion anotacion = new Anotacion();
				
				anotacion.setIdAnotacion(rs.getInt(1));
				anotacion.setTexto(rs.getString(2));
				anotacion.setIdUsuario(rs.getInt(3));

				anotaciones.add(anotacion);
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
		return anotaciones;
	}
	/**
	 * 
	 * @param idAnotacion
	 * @return
	 * @throws SQLException
	 */
	public Anotacion getAnotacion( int idAnotacion ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Anotacion anotacion = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select Id, Texto, IdUsuario from anotacion where Id=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idAnotacion);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				anotacion = new Anotacion();
				
				anotacion.setIdAnotacion(rs.getInt(1));
				anotacion.setTexto(rs.getString(2));
				anotacion.setIdUsuario(rs.getInt(3));
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
		return anotacion;
	}	
	/**
	 * 
	 * @param anotacion
	 * @throws SQLException
	 */
	public void insertAnotacion( Anotacion anotacion ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();

			query.append( 	"	insert into anotacion(Id, Texto, IdUsuario)" +
					"	select IFNULL(max(Id),0)+1 , ?, ? from anotacion" );			
			
			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, anotacion.getTexto());
			stmt.setInt(2, anotacion.getIdUsuario());
			
			stmt.execute();
			
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
	/**
	 * 
	 * @param anotacion
	 * @throws SQLException
	 */
	public void updateAnotacion( Anotacion anotacion ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	update anotacion set Texto = ?, IdUsuario = ? " +
							"	where Id=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, anotacion.getTexto());
			stmt.setInt(2, anotacion.getIdUsuario());
			stmt.setInt(3, anotacion.getIdAnotacion());
			
			stmt.execute();
			
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
	/**
	 * 
	 * @param IdAnotacion
	 * @throws SQLException
	 */
	public void deleteAnotacion( int IdAnotacion ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	delete from anotacion where Id = ?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, IdAnotacion);
			
			stmt.execute();
			
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
