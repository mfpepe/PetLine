package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Frecuencia;
import petline.valueObject.Recordatorio;




public class EntRecordatorio {

	public Collection<Recordatorio> getRecordatorios(int idMascota) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Recordatorio> recordatorios = new ArrayList<Recordatorio>();
		try {
			con = ConnectionManager.getConnection();
			
			StringBuffer query = new StringBuffer();
			query.append( 	"	select r.IdRecordatorio, r.Descripcion, r.FechaHora, r.IdFrecuencia, f.Descripcion, r.IdMascota " + 
							"	from recordatorio r inner join frecuencia f on f.IdFrecuencia=r.IdFrecuencia " +
							"	where r.IdMascota=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idMascota);
			
			rs = stmt.executeQuery();

			Calendar fecha = Calendar.getInstance();
			while(rs.next()){
				Recordatorio recordatorio = new Recordatorio();
				recordatorio.setIdRecordatorio(rs.getInt(1));
				recordatorio.setDescripcion(rs.getString(2));
				fecha.clear();
				fecha.setTimeInMillis(rs.getTimestamp(3).getTime());
				recordatorio.setFechaHora(fecha);
				recordatorio.setFrecuencia(new Frecuencia());
				recordatorio.getFrecuencia().setIdFrecuencia(rs.getInt(4));
				recordatorio.getFrecuencia().setDescripcion(rs.getString(5));
				recordatorio.setIdMascota(rs.getInt(6));
				
				recordatorios.add(recordatorio);
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
		return recordatorios;
	}
	
	public Recordatorio getRecordatorio( int idRecordatorio ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Recordatorio recordatorio = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select r.IdRecordatorio, r.Descripcion, r.FechaHora, r.IdFrecuencia, f.Descripcion, r.IdMascota " + 
							"	from recordatorio r inner join frecuencia f on f.IdFrecuencia=r.IdFrecuencia " +
							"	where r.IdRecordatorio=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idRecordatorio);
			
			rs = stmt.executeQuery();

			Calendar fecha = Calendar.getInstance();
			while(rs.next()){
				recordatorio = new Recordatorio();
				recordatorio.setIdRecordatorio(rs.getInt(1));
				recordatorio.setDescripcion(rs.getString(2));
				fecha.clear();
				fecha.setTimeInMillis(rs.getTimestamp(3).getTime());
				recordatorio.setFechaHora(fecha);
				recordatorio.setFrecuencia(new Frecuencia());
				recordatorio.getFrecuencia().setIdFrecuencia(rs.getInt(4));
				recordatorio.getFrecuencia().setDescripcion(rs.getString(5));
				recordatorio.setIdMascota(rs.getInt(6));
				
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
		return recordatorio;
	}	
	
	public void insertRecordatorio( Recordatorio recordatorio ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();

			query.append( 	"	insert into recordatorio(IdRecordatorio, Descripcion, FechaHora, IdFrecuencia, IdMascota)" +
							"	select IFNULL(max(IdRecordatorio),0)+1 , ?, ?, ?, ? from recordatorio" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, recordatorio.getDescripcion());
			stmt.setTimestamp(2, new Timestamp(recordatorio.getFechaHora().getTimeInMillis()));
			stmt.setInt(3, recordatorio.getFrecuencia().getIdFrecuencia());
			stmt.setInt(4, recordatorio.getIdMascota());
			
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
	
	public void updateRecordatorio( Recordatorio recordatorio ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	update recordatorio set Descripcion=?, FechaHora=?, IdFrecuencia=?, IdMascota=?" +
							"	where IdRecordatorio = ?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, recordatorio.getDescripcion());
			stmt.setTimestamp(2, new Timestamp(recordatorio.getFechaHora().getTimeInMillis()));
			stmt.setInt(3, recordatorio.getFrecuencia().getIdFrecuencia());
			stmt.setInt(4, recordatorio.getIdMascota());
			stmt.setInt(5, recordatorio.getIdRecordatorio());
			
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
	
	public void deleteRecordatorio( int idRecordatorio ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	delete from recordatorio where IdRecordatorio = ?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idRecordatorio);
			
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
