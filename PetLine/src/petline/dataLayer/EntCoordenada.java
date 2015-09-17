package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Coordenada;




public class EntCoordenada {

	public Collection<Coordenada> getCoordenadas(int idTracker, Calendar fecha) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Coordenada> coordenadas = new ArrayList<Coordenada>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select IdCoordenada, IdTracker, Latitud, Longitud, Fecha from coordenada " +
							"	where IdTracker=? " +
							"	and DATE_FORMAT(Fecha, '%Y%m%d')=? " +
							"	order by Fecha" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idTracker);
			stmt.setString(2, (new SimpleDateFormat("yyyyMMdd")).format(fecha.getTime()));
			
			rs = stmt.executeQuery();

			
			while(rs.next()){
				Coordenada coordenada = new Coordenada();
				coordenada.setIdCoordenada(rs.getInt(1));
				coordenada.setIdTracker(rs.getInt(2));
				coordenada.setLatitud(rs.getString(3));
				coordenada.setLongitud(rs.getString(4));
				Calendar fechaHora = Calendar.getInstance();
				fechaHora.clear();
				fechaHora.setTime(rs.getTimestamp(5));
				coordenada.setFechaHora(fechaHora);
				
				coordenadas.add(coordenada);
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
		return coordenadas;
	}
	
	public Coordenada getUltimaCoordenada(int idTracker) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Coordenada coordenada = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	select IdCoordenada, IdTracker, Latitud, Longitud, Fecha  " +
							"	from coordenada c " +
							"	where IdTracker=? " +
							"	and fecha = ( select max(Fecha) from coordenada where IdTracker=c.IdTracker ) " +
							"	and IdCoordenada = ( select max(IdCoordenada) from coordenada where IdTracker=c.IdTracker )" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idTracker);
			
			rs = stmt.executeQuery();

			
			while(rs.next()){
				coordenada = new Coordenada();
				coordenada.setIdCoordenada(rs.getInt(1));
				coordenada.setIdTracker(rs.getInt(2));
				coordenada.setLatitud(rs.getString(3));
				coordenada.setLongitud(rs.getString(4));
				Calendar fechaHora = Calendar.getInstance();
				fechaHora.clear();
				fechaHora.setTime(rs.getTimestamp(5));
				coordenada.setFechaHora(fechaHora);
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
		return coordenada;
	}	
	
}
