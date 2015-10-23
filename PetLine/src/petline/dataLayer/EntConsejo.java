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

			StringBuffer query = new StringBuffer();
			query.append( 	
							" select c.IdConsejo, c.Titulo, c.Texto, c.EdadDesde, c.EdadHasta, c.PesoDesde, c.PesoHasta, c.IdTamaño, c.IdRaza, c.NombreImagen " +
							" from consejo c " +
							" inner join mascota m on m.IdRaza=m.IdRaza " +
							" where m.idmascota=? " +
							" and m.IdRaza = c.IdRaza " +
							" and m.IdTamaño = c.IdTamaño " +
							" and m.Peso>c.PesoDesde " +
							" and m.Peso<=c.PesoHasta " +
							" union " +
							" select c.IdConsejo, c.Titulo, c.Texto, c.EdadDesde, c.EdadHasta, c.PesoDesde, c.PesoHasta, c.IdTamaño, c.IdRaza, c.NombreImagen " +
							" from consejo c " +
							" inner join mascota m on m.IdRaza=m.IdRaza " +
							" where m.idmascota=? " +
							" and m.IdRaza = c.IdRaza " +
							" and c.IdTamaño is null " +
							" and m.Peso>c.PesoDesde " +
							" and m.Peso<=c.PesoHasta " +
							" union " +
							" select c.IdConsejo, c.Titulo, c.Texto, c.EdadDesde, c.EdadHasta, c.PesoDesde, c.PesoHasta, c.IdTamaño, c.IdRaza, c.NombreImagen " +
							" from consejo c " +
							" inner join mascota m on m.IdRaza=m.IdRaza " +
							" where m.idmascota=? " +
							" and m.IdRaza = c.IdRaza " +
							" and m.IdTamaño = c.IdTamaño " +
							" and YEAR(CURDATE())-YEAR(m.fecha_nac) + IF(DATE_FORMAT(CURDATE(),'%m-%d') > DATE_FORMAT(m.fecha_nac,'%m-%d'), 0, -1)>c.EdadDesde " +
							" and YEAR(CURDATE())-YEAR(m.fecha_nac) + IF(DATE_FORMAT(CURDATE(),'%m-%d') > DATE_FORMAT(m.fecha_nac,'%m-%d'), 0, -1)<=c.EdadHasta " +
							" union " +
							" select c.IdConsejo, c.Titulo, c.Texto, c.EdadDesde, c.EdadHasta, c.PesoDesde, c.PesoHasta, c.IdTamaño, c.IdRaza, c.NombreImagen " +
							" from consejo c " +
							" inner join mascota m on m.IdRaza=m.IdRaza " +
							" where m.idmascota=? " +
							" and m.IdRaza = c.IdRaza " +
							" and c.IdTamaño is null " +
							" and YEAR(CURDATE())-YEAR(m.fecha_nac) + IF(DATE_FORMAT(CURDATE(),'%m-%d') > DATE_FORMAT(m.fecha_nac,'%m-%d'), 0, -1)>c.EdadDesde " +
							" and YEAR(CURDATE())-YEAR(m.fecha_nac) + IF(DATE_FORMAT(CURDATE(),'%m-%d') > DATE_FORMAT(m.fecha_nac,'%m-%d'), 0, -1)<=c.EdadHasta " +
							" union " +
							" select c.IdConsejo, c.Titulo, c.Texto, c.EdadDesde, c.EdadHasta, c.PesoDesde, c.PesoHasta, c.IdTamaño, c.IdRaza, c.NombreImagen " +
							" from consejo c " +
							" inner join mascota m on m.IdRaza=m.IdRaza " +
							" where m.idmascota=? " +
							" and m.IdRaza = c.IdRaza " +
							" and c.IdTamaño is null " +
							" and c.EdadDesde is null " +
							" and c.EdadHasta is null " +
							" and c.PesoDesde is null " +
							" and c.PesoHasta is null " +
							" union " +
							" select c.IdConsejo, c.Titulo, c.Texto, c.EdadDesde, c.EdadHasta, c.PesoDesde, c.PesoHasta, c.IdTamaño, c.IdRaza, c.NombreImagen " +
							" from consejo c " +
							" inner join mascota m on m.IdRaza=m.IdRaza " +
							" where m.idmascota=? " +
							" and c.IdRaza is null " +
							" and c.IdTamaño is null " +
							" and c.EdadDesde is null " +
							" and c.EdadHasta is null " +
							" and c.PesoDesde is null " +
							" and c.PesoHasta is null " );

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idMascota);
			stmt.setInt(2, idMascota);
			stmt.setInt(3, idMascota);
			stmt.setInt(4, idMascota);
			stmt.setInt(5, idMascota);
			stmt.setInt(6, idMascota);
			
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
				consejo.setNombreImagen(rs.getString(10));
				
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
