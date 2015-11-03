package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.TipoNotificacion;




public class EntTipoNotificacion {
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Collection<TipoNotificacion> getTipoNotificaciones() throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<TipoNotificacion> tipoNotificaciones = new ArrayList<TipoNotificacion>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select IdTipoDeNotificacion, Titulo, Mensaje from tipodenotificacion" );

			stmt = con.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();

			while(rs.next()){
				TipoNotificacion tn = new TipoNotificacion();
				tn.setIdTipoNotificacion(rs.getInt(1));
				tn.setTitulo(rs.getString(2));
				tn.setMensaje(rs.getString(3));
				
				tipoNotificaciones.add(tn);
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
		return tipoNotificaciones;
	}
	
}
