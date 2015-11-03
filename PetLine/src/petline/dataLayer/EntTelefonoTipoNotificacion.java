package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.TelefonoTipoNotificacion;
import petline.valueObject.TipoNotificacion;
import petline.valueObject.Tracker;




public class EntTelefonoTipoNotificacion {
	/**
	 * 
	 * @param idTipoNotificacion
	 * @param idTracker
	 * @return
	 * @throws SQLException
	 */
	public TelefonoTipoNotificacion getTelefonoTipoNotificacion(int idTipoNotificacion, int idTracker) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TelefonoTipoNotificacion ttn = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select ttn.IdTelTipoNotificacion, " + 
							"	ttn.NroTelefono, " +
							"	t.IdTracker, t.Codigo, t.Descripcion, " +
							"	tn.IdTipoDeNotificacion, tn.Titulo, tn.Mensaje " +
							"	from teltiponotificacion ttn inner join tracker t on t.IdTracker=ttn.IdTracker " +
							"	inner join tipodenotificacion tn on tn.IdTipoDeNotificacion=ttn.IdTipoNot " +
							"	where ttn.IdTracker=? " +
							"	and ttn.IdTipoNot=?" );

			stmt = con.prepareStatement(query.toString());
			
			stmt.setInt(1, idTracker);
			stmt.setInt(2, idTipoNotificacion);
			
			rs = stmt.executeQuery();

			if( rs.next() ){
				ttn = new TelefonoTipoNotificacion();
				ttn.setIdTelefonoTipoNotificacion(rs.getInt(1));
				ttn.setTracker(new Tracker());
				ttn.getTracker().setIdTracker(rs.getInt(3));
				ttn.getTracker().setCodigo(rs.getString(4));
				ttn.getTracker().setDescripcion(rs.getString(5));
				ttn.setTipoNotificacion(new TipoNotificacion());
				ttn.getTipoNotificacion().setIdTipoNotificacion(rs.getInt(6));
				ttn.getTipoNotificacion().setTitulo(rs.getString(7));
				ttn.getTipoNotificacion().setMensaje(rs.getString(8));
				ttn.getNroTelefonos().add(rs.getString(2));
				
				while(rs.next()){
					ttn.getNroTelefonos().add(rs.getString(2));
				}
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
		return ttn;
	}
	/**
	 * 
	 * @param telefonoTipoNotificaciones
	 * @throws SQLException
	 */
	public void insertTelefonoTipoNotificacion(Collection<TelefonoTipoNotificacion> telefonoTipoNotificaciones) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			con.setAutoCommit(false);
			
			for (TelefonoTipoNotificacion telefonoTipoNotificacion : telefonoTipoNotificaciones) {
			
				stmt = con.prepareStatement("	delete from teltiponotificacion where IdTracker=? and IdTipoNot=? ");
				
				stmt.setInt(1, telefonoTipoNotificacion.getTracker().getIdTracker());
				stmt.setInt(2, telefonoTipoNotificacion.getTipoNotificacion().getIdTipoNotificacion());
				
				stmt.execute();
				
				stmt.clearParameters();
				
				for (String telefono : telefonoTipoNotificacion.getNroTelefonos()) {
					stmt = con.prepareStatement("	insert into teltiponotificacion (IdTelTipoNotificacion, IdTracker, NroTelefono, IdTipoNot)"
							+ "	select IFNULL(max(IdTelTipoNotificacion),0)+1 , ?, ?, ? from teltiponotificacion");

					stmt.setInt(1, telefonoTipoNotificacion.getTracker().getIdTracker());
					stmt.setString(2, telefono);
					stmt.setInt(3, telefonoTipoNotificacion.getTipoNotificacion().getIdTipoNotificacion());
					
					stmt.execute();
					stmt.clearParameters();
				}				
				
			}
			
			con.commit();
			
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
