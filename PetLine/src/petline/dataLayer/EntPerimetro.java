package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Perimetro;




public class EntPerimetro {
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<Perimetro> getPerimetrosPorUsuario( int idUsuario ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Perimetro> perimetros = new ArrayList<Perimetro>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select IdPerimetro, Latitud, Longitud, Distancia, IdUsuario, Descripcion from perimetro" +
							"	where IdUsuario=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idUsuario);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				Perimetro perimetro = new Perimetro();
				
				perimetro.setIdPerimetro(rs.getInt(1));
				perimetro.setLatitud(rs.getString(2));
				perimetro.setLongitud(rs.getString(3));
				perimetro.setDistancia(rs.getInt(4));
				perimetro.setIdUsuario(rs.getInt(5));
				perimetro.setDescripcion(rs.getString(6));

				perimetros.add(perimetro);
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
		return perimetros;
	}
	/**
	 * 
	 * @param idPerimetro
	 * @return
	 * @throws SQLException
	 */
	public Perimetro getPerimetro( int idPerimetro ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Perimetro perimetro = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select IdPerimetro, Latitud, Longitud, Distancia, IdUsuario, Descripcion from perimetro" +
							"	where IdPerimetro=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idPerimetro);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				perimetro = new Perimetro();
				
				perimetro.setIdPerimetro(rs.getInt(1));
				perimetro.setLatitud(rs.getString(2));
				perimetro.setLongitud(rs.getString(3));
				perimetro.setDistancia(rs.getInt(4));
				perimetro.setIdUsuario(rs.getInt(5));
				perimetro.setDescripcion(rs.getString(6));
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
		return perimetro;
	}	
	/**
	 * 
	 * @param perimetro
	 * @throws SQLException
	 */
	public void insertPerimetro( Perimetro perimetro ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();

			query.append( 	"	insert into perimetro(IdPerimetro, Latitud, Longitud, Distancia, IdUsuario, Descripcion)" +
					"	select IFNULL(max(IdPerimetro),0)+1 , ?, ?, ?, ?, ? from perimetro" );			
			
			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, perimetro.getLatitud());
			stmt.setString(2, perimetro.getLongitud());
			stmt.setInt(3, perimetro.getDistancia());
			stmt.setInt(4, perimetro.getIdUsuario());
			stmt.setString(5, perimetro.getDescripcion());
			
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
	 * @param perimetro
	 * @throws SQLException
	 */
	public void updatePerimetro( Perimetro perimetro ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	update perimetro set Latitud = ?, Longitud = ?, Distancia = ?, Descripcion = ? " +
							"	where IdPerimetro=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, perimetro.getLatitud());
			stmt.setString(2, perimetro.getLongitud());
			stmt.setInt(3, perimetro.getDistancia());
			stmt.setString(4, perimetro.getDescripcion());
			stmt.setInt(5, perimetro.getIdPerimetro());
			
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
	 * @param IdPerimetro
	 * @throws SQLException
	 */
	public void deletePerimetro( int IdPerimetro ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	delete from perimetro where IdPerimetro = ?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, IdPerimetro);
			
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
