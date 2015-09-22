package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Notificacion;
import petline.valueObject.TipoNotificacion;
import petline.valueObject.Tracker;




public class EntNotificacion {

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

			Calendar fecha = Calendar.getInstance();
			while(rs.next()){
				Notificacion notificacion = new Notificacion();
				notificacion.setIdNotificacion(rs.getInt(1));
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
	
}
