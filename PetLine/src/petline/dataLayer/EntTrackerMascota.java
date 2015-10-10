package petline.dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Mascota;
import petline.valueObject.Tracker;
import petline.valueObject.TrackerMascota;

public class EntTrackerMascota {

	public Collection<TrackerMascota> getTrackerMascotaPorUsuario( int idUsuario ) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<TrackerMascota> trackerMascotas = new ArrayList<TrackerMascota>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("	select tm.IdTrackerMascota, tm.TempActual, tm.TempMinima, tm.TempMaxima, " +
						 "	m.IdMascota, m.Apodo, m.Fecha_nac, m.Peso, m.KmDiarios, m.IdTamaño, m.IdUsuario, m.IdPerimetro, " +
						"	t.IdTracker, t.Codigo, t.Descripcion " +
						"	from trackermascota tm " +
						"	inner join mascota m on m.IdMascota=tm.idmascota " +
						"	inner join tracker t on t.IdTracker=tm.idtracker " +
						"	where m.idusuario=? ");

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idUsuario);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				TrackerMascota trackerMascota = new TrackerMascota();
				trackerMascota.setIdTrackerMascota(rs.getInt(1));
				trackerMascota.setTempActual(rs.getInt(2));
				trackerMascota.setTempMinima(rs.getInt(3));
				trackerMascota.setTempMaxima(rs.getInt(4));
				trackerMascota.setMascota(new Mascota());
				trackerMascota.getMascota().setIdMascota(rs.getInt(5));
				trackerMascota.getMascota().setApodo(rs.getString(6));
				Calendar fechaNac = Calendar.getInstance();
				fechaNac.clear();
				fechaNac.setTime(rs.getDate(7));
				trackerMascota.getMascota().setFechaNacimiento(fechaNac);
				trackerMascota.getMascota().setPeso(rs.getDouble(8));
				trackerMascota.getMascota().setKmDiarios(rs.getDouble(9));
				trackerMascota.getMascota().setIdTamaño(rs.getInt(10));
				trackerMascota.getMascota().setIdUsuario(rs.getInt(11));
				trackerMascota.getMascota().setIdPerimetro(rs.getInt(12));
				
				trackerMascota.setTracker(new Tracker());
				trackerMascota.getTracker().setIdTracker(rs.getInt(13));
				trackerMascota.getTracker().setCodigo(rs.getString(14));
				trackerMascota.getTracker().setDescripcion(rs.getString(15));
				
				trackerMascotas.add(trackerMascota);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return trackerMascotas;
	}
	
	public TrackerMascota getTrackerMascotaPorMascota( int idMascota ) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TrackerMascota trackerMascota = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("	select tm.IdTrackerMascota, tm.TempActual, tm.TempMinima, tm.TempMaxima, " +
						 "	m.IdMascota, m.Apodo, m.Fecha_nac, m.Peso, m.KmDiarios, m.IdTamaño, m.IdUsuario, m.IdPerimetro, " +
						"	t.IdTracker, t.Codigo, t.Descripcion " +
						"	from trackermascota tm " +
						"	inner join mascota m on m.IdMascota=tm.idmascota " +
						"	inner join tracker t on t.IdTracker=tm.idtracker " +
						"	where m.IdMascota=? ");

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idMascota);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				trackerMascota = new TrackerMascota();
				trackerMascota.setIdTrackerMascota(rs.getInt(1));
				trackerMascota.setTempActual(rs.getInt(2));
				trackerMascota.setTempMinima(rs.getInt(3));
				trackerMascota.setTempMaxima(rs.getInt(4));
				trackerMascota.setMascota(new Mascota());
				trackerMascota.getMascota().setIdMascota(rs.getInt(5));
				trackerMascota.getMascota().setApodo(rs.getString(6));
				Calendar fechaNac = Calendar.getInstance();
				fechaNac.clear();
				fechaNac.setTime(rs.getDate(7));
				trackerMascota.getMascota().setFechaNacimiento(fechaNac);
				trackerMascota.getMascota().setPeso(rs.getDouble(8));
				trackerMascota.getMascota().setKmDiarios(rs.getDouble(9));
				trackerMascota.getMascota().setIdTamaño(rs.getInt(10));
				trackerMascota.getMascota().setIdUsuario(rs.getInt(11));
				trackerMascota.getMascota().setIdPerimetro(rs.getInt(12));
				
				trackerMascota.setTracker(new Tracker());
				trackerMascota.getTracker().setIdTracker(rs.getInt(13));
				trackerMascota.getTracker().setCodigo(rs.getString(14));
				trackerMascota.getTracker().setDescripcion(rs.getString(15));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return trackerMascota;
	}
	
	public TrackerMascota getTrackerMascotaPorTracker( int idTracker ) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TrackerMascota trackerMascota = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("	select tm.IdTrackerMascota, tm.TempActual, tm.TempMinima, tm.TempMaxima, " +
						 "	m.IdMascota, m.Apodo, m.Fecha_nac, m.Peso, m.KmDiarios, m.IdTamaño, m.IdUsuario, m.IdPerimetro, " +
						"	t.IdTracker, t.Codigo, t.Descripcion " +
						"	from trackermascota tm " +
						"	inner join mascota m on m.IdMascota=tm.idmascota " +
						"	inner join tracker t on t.IdTracker=tm.idtracker " +
						"	where t.IdTracker=? ");

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idTracker);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				trackerMascota = new TrackerMascota();
				trackerMascota.setIdTrackerMascota(rs.getInt(1));
				trackerMascota.setTempActual(rs.getInt(2));
				trackerMascota.setTempMinima(rs.getInt(3));
				trackerMascota.setTempMaxima(rs.getInt(4));
				trackerMascota.setMascota(new Mascota());
				trackerMascota.getMascota().setIdMascota(rs.getInt(5));
				trackerMascota.getMascota().setApodo(rs.getString(6));
				Calendar fechaNac = Calendar.getInstance();
				fechaNac.clear();
				fechaNac.setTime(rs.getDate(7));
				trackerMascota.getMascota().setFechaNacimiento(fechaNac);
				trackerMascota.getMascota().setPeso(rs.getDouble(8));
				trackerMascota.getMascota().setKmDiarios(rs.getDouble(9));
				trackerMascota.getMascota().setIdTamaño(rs.getInt(10));
				trackerMascota.getMascota().setIdUsuario(rs.getInt(11));
				trackerMascota.getMascota().setIdPerimetro(rs.getInt(12));
				
				trackerMascota.setTracker(new Tracker());
				trackerMascota.getTracker().setIdTracker(rs.getInt(13));
				trackerMascota.getTracker().setCodigo(rs.getString(14));
				trackerMascota.getTracker().setDescripcion(rs.getString(15));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return trackerMascota;
	}	
	
	public TrackerMascota getTrackerMascota( int idTrackerMascota ) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		TrackerMascota trackerMascota = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("	select tm.IdTrackerMascota, tm.TempActual, tm.TempMinima, tm.TempMaxima, " +
						 "	m.IdMascota, m.Apodo, m.Fecha_nac, m.Peso, m.KmDiarios, m.IdTamaño, m.IdUsuario, m.IdPerimetro, " +
						"	t.IdTracker, t.Codigo, t.Descripcion " +
						"	from trackermascota tm " +
						"	inner join mascota m on m.IdMascota=tm.idmascota " +
						"	inner join tracker t on t.IdTracker=tm.idtracker " +
						"	where tm.IdTrackerMascota=? ");

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idTrackerMascota);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				trackerMascota = new TrackerMascota();
				trackerMascota.setIdTrackerMascota(rs.getInt(1));
				trackerMascota.setTempActual(rs.getInt(2));
				trackerMascota.setTempMinima(rs.getInt(3));
				trackerMascota.setTempMaxima(rs.getInt(4));
				trackerMascota.setMascota(new Mascota());
				trackerMascota.getMascota().setIdMascota(rs.getInt(5));
				trackerMascota.getMascota().setApodo(rs.getString(6));
				Calendar fechaNac = Calendar.getInstance();
				fechaNac.clear();
				fechaNac.setTime(rs.getDate(7));
				trackerMascota.getMascota().setFechaNacimiento(fechaNac);
				trackerMascota.getMascota().setPeso(rs.getDouble(8));
				trackerMascota.getMascota().setKmDiarios(rs.getDouble(9));
				trackerMascota.getMascota().setIdTamaño(rs.getInt(10));
				trackerMascota.getMascota().setIdUsuario(rs.getInt(11));
				trackerMascota.getMascota().setIdPerimetro(rs.getInt(12));
				
				trackerMascota.setTracker(new Tracker());
				trackerMascota.getTracker().setIdTracker(rs.getInt(13));
				trackerMascota.getTracker().setCodigo(rs.getString(14));
				trackerMascota.getTracker().setDescripcion(rs.getString(15));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return trackerMascota;
	}	
	
	public void insertTrackerMascota( TrackerMascota trackerMascota ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();
			
			stmt = con.prepareStatement("	insert into trackermascota (IdTrackerMascota, TempMinima, TempMaxima, IdMascota, IdTracker)" +
										" select IFNULL(max(IdTrackerMascota),0)+1, ?, ?, ?, ? from trackermascota");
			
			stmt.setInt(1, trackerMascota.getTempMinima());
			stmt.setInt(2, trackerMascota.getTempMaxima());
			stmt.setInt(3, trackerMascota.getMascota().getIdMascota());
			stmt.setInt(4, trackerMascota.getTracker().getIdTracker());
			
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
	
	public void updateTrackerMascota( TrackerMascota trackerMascota ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();
			
			stmt = con.prepareStatement("	update trackermascota set TempMinima = ?, TempMaxima = ?, TempActual = NULL " +
										" where IdTrackerMascota = ?");
			
			stmt.setInt(1, trackerMascota.getTempMinima());
			stmt.setInt(2, trackerMascota.getTempMaxima());
			stmt.setInt(3, trackerMascota.getIdTrackerMascota());
			
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
	
	public void deleteTrackerMascota( int idTrackerMascota ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();
			
			stmt = con.prepareStatement("	delete from trackermascota where IdTrackerMascota = ?");
			
			stmt.setInt(1, idTrackerMascota);
			
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
	
	public void updateTemperaturaActualTrackerMascota( int IdTrackerMascota, int tempActual ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();
			
			stmt = con.prepareStatement("	update trackermascota set TempActual = ? " +
										" where IdTrackerMascota = ?");
			
			stmt.setInt(1, tempActual);
			stmt.setInt(2, IdTrackerMascota);
			
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
