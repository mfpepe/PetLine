package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;

import petline.util.ConnectionManager;
import petline.valueObject.Notificacion;
import petline.valueObject.TipoNotificacion;
import petline.valueObject.Tracker;




public class EntNotificacion {
	/**
	 * 
	 * @param idTracker
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer, Calendar> getUltimasNotificacionesRealizadas( int idTracker ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HashMap<Integer, Calendar> ultimasNotificaciones = new HashMap<Integer, Calendar>();
		try {
			con = ConnectionManager.getConnection();
				
			StringBuffer query = new StringBuffer();
			query.append( 	"	select n.IdTipoNot, max(n.FechaHora) from notificacion n " +
							"	where n.IdTracker=? " + 
							"	group by n.IdTipoNot " );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idTracker);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				Calendar fecha = Calendar.getInstance();
				fecha.clear();
				fecha.setTimeInMillis(rs.getTimestamp(2).getTime());
				
				ultimasNotificaciones.put(new Integer(rs.getInt(1)), fecha);
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
		return ultimasNotificaciones;
	}	
	/**
	 * 
	 * @param idTracker
	 * @return
	 * @throws SQLException
	 */
	public Collection<Notificacion> getNotificacionesUltimaSemana( int idTracker ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Notificacion> notificaciones = new ArrayList<Notificacion>();
		try {
			con = ConnectionManager.getConnection();
				
			StringBuffer query = new StringBuffer();
			query.append( 	"	select n.idNotificacion, n.fechahora, " +
							"	tn.IdTipoDeNotificacion, tn.Titulo, tn.Mensaje, " +
							"	t.IdTracker, t.Codigo, t.Descripcion " +
							"	from notificacion n inner join tipodenotificacion tn on tn.IdTipoDeNotificacion=n.IdTipoNot " +
							"	inner join Tracker t on t.IdTracker=n.IdTracker " +
							"	where n.fechaHora>=CURDATE()-7 " +
							"	and n.idtracker=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idTracker);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				Notificacion notificacion = new Notificacion();
				notificacion.setIdNotificacion(rs.getInt(1));
				Calendar fecha = Calendar.getInstance();
				fecha.clear();
				fecha.setTimeInMillis(rs.getTimestamp(2).getTime());
				notificacion.setFechaHora(fecha);
				notificacion.setTipoNotificacion(new TipoNotificacion());
				notificacion.getTipoNotificacion().setIdTipoNotificacion(rs.getInt(3));
				notificacion.getTipoNotificacion().setTitulo(rs.getString(4));
				notificacion.getTipoNotificacion().setMensaje(rs.getString(5));
				notificacion.setTracker(new Tracker());
				notificacion.getTracker().setIdTracker(rs.getInt(6));
				notificacion.getTracker().setCodigo(rs.getString(7));
				notificacion.getTracker().setDescripcion(rs.getString(8));
				
				notificaciones.add(notificacion);
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
		return notificaciones;
	}
	/**
	 * 
	 * @param notificacion
	 * @throws SQLException
	 */
	public void insertNotificacion( Notificacion notificacion ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();

			query.append( 	"	insert into notificacion(IdNotificacion, IdTipoNot, FechaHora, IdTracker)" +
							"	select IFNULL(max(IdNotificacion),0)+1 , ?, ?, ? from notificacion" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, notificacion.getTipoNotificacion().getIdTipoNotificacion());
			stmt.setTimestamp(2, new Timestamp(notificacion.getFechaHora().getTimeInMillis()) );
			stmt.setInt(3, notificacion.getTracker().getIdTracker());
			
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
