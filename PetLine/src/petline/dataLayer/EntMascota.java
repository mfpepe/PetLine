package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Mascota;




public class EntMascota {
	/**
	 * 
	 * @param idUsuario
	 * @return
	 * @throws SQLException
	 */
	public Collection<Mascota> getMascotasPorUsuario( int idUsuario ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Mascota> mascotas = new ArrayList<Mascota>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select IdMascota, Apodo, Fecha_nac, Peso, KmDiarios, IdTamaño, IdUsuario, IdPerimetro, IdRaza" +
							"	from mascota" +
							"	where IdUsuario=?" +
							"	order by 1" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idUsuario);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				Mascota mascota = new Mascota();
				mascota.setIdMascota(rs.getInt(1));
				mascota.setApodo(rs.getString(2));
				Calendar fechaNac = Calendar.getInstance();
				fechaNac.clear();
				fechaNac.setTime(rs.getDate(3));
				mascota.setFechaNacimiento(fechaNac);
				mascota.setPeso(rs.getDouble(4));
				mascota.setKmDiarios(rs.getDouble(5));
				mascota.setIdTamaño(rs.getInt(6));
				mascota.setIdUsuario(rs.getInt(7));
				mascota.setIdPerimetro(rs.getInt(8));
				mascota.setIdRaza(rs.getInt(9));
				
				mascotas.add(mascota);
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
		return mascotas;
	}
	/**
	 * 
	 * @param idMascota
	 * @return
	 * @throws SQLException
	 */
	public Mascota getMascota( int idMascota ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Mascota mascota = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select IdMascota, Apodo, Fecha_nac, Peso, KmDiarios, IdTamaño, IdUsuario, IdPerimetro, IdRaza" +
							"	from mascota" +
							"	where IdMascota=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idMascota);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				mascota = new Mascota();
				mascota.setIdMascota(rs.getInt(1));
				mascota.setApodo(rs.getString(2));
				Calendar fechaNac = Calendar.getInstance();
				fechaNac.clear();
				fechaNac.setTime(rs.getDate(3));
				mascota.setFechaNacimiento(fechaNac);
				mascota.setPeso(rs.getDouble(4));
				mascota.setKmDiarios(rs.getDouble(5));
				mascota.setIdTamaño(rs.getInt(6));
				mascota.setIdUsuario(rs.getInt(7));
				mascota.setIdPerimetro(rs.getInt(8));
				mascota.setIdRaza(rs.getInt(9));
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
		return mascota;
	}	
	/**
	 * 
	 * @param mascota
	 * @throws SQLException
	 */
	public void insertMascota( Mascota mascota ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();

			query.append( 	"	insert into mascota(IdMascota, Apodo, Fecha_nac, Peso, KmDiarios, IdTamaño, IdUsuario, IdPerimetro, IdRaza)" +
							"	select IFNULL(max(IdMascota),0)+1 , ?, ?, ?, ?, ?, ?, ?, ? from mascota" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, mascota.getApodo());
			stmt.setDate(2, new java.sql.Date(mascota.getFechaNacimiento().getTimeInMillis()));
			stmt.setDouble(3, mascota.getPeso());
			stmt.setDouble(4, mascota.getKmDiarios());
			stmt.setInt(5, mascota.getIdTamaño());
			stmt.setInt(6, mascota.getIdUsuario());
			if(mascota.getIdPerimetro() == 0){
				stmt.setNull(7, Types.INTEGER);
			}
			else{
				stmt.setInt(7, mascota.getIdPerimetro());	
			}
			stmt.setInt(8, mascota.getIdRaza());
			
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
	 * @param mascota
	 * @throws SQLException
	 */
	public void updateMascota( Mascota mascota ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	update mascota set Apodo = ?, Fecha_nac = ?, Peso = ?, KmDiarios = ?, IdTamaño = ?, IdUsuario = ?, IdPerimetro = ?, IdRaza = ?" +
							"	where IdMascota = ?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, mascota.getApodo());
			stmt.setDate(2, new java.sql.Date(mascota.getFechaNacimiento().getTimeInMillis()));
			stmt.setDouble(3, mascota.getPeso());
			stmt.setDouble(4, mascota.getKmDiarios());
			stmt.setInt(5, mascota.getIdTamaño());
			stmt.setInt(6, mascota.getIdUsuario());
			if(mascota.getIdPerimetro() == 0){
				stmt.setNull(7, Types.INTEGER);
			}
			else{
				stmt.setInt(7, mascota.getIdPerimetro());	
			}
			stmt.setInt(8, mascota.getIdRaza());
			stmt.setInt(9, mascota.getIdMascota());
			
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
	 * @param IdMascota
	 * @throws SQLException
	 */
	public void deleteMascota( int IdMascota ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	delete from mascota where IdMascota = ?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, IdMascota);
			
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
