package petline.dataLayer;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Consejo;




public class EntConsejo {

	public Collection<Consejo> getConsejos(int idMascota) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<Consejo> consejos = new ArrayList<Consejo>();
		try {
			con = ConnectionManager.getConnection();
				//TODO revisar query para que contemple todos los filtros
			StringBuffer query = new StringBuffer();
			query.append( 	"	select c.IdConsejo, c.Titulo, c.Texto, c.EdadDesde, c.EdadHasta, c.PesoDesde, c.PesoHasta, c.IdTamaño, c.IdRaza " + 
							"	from consejo c " +
							"	inner join mascota m on m.IdRaza=m.IdRaza " +
							"	where m.idmascota=?" );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idMascota);
			
			rs = stmt.executeQuery();

			while(rs.next()){
				Consejo consejo = new Consejo();
				consejo.setIdConsejo(rs.getInt(1));
				consejo.setTitulo(rs.getString(2));
				consejo.setTexto(rs.getString(3));
				consejo.setEdadDesde(rs.getInt(4));
				consejo.setEdadHasta(rs.getInt(5));
				consejo.setPesoDesde(rs.getInt(6));
				consejo.setPesoHasta(rs.getInt(7));
				consejo.setIdTamaño(rs.getInt(8));
				consejo.setIdRaza(rs.getInt(9));
				
				consejos.add(consejo);
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
		return consejos;
	}
	
}
