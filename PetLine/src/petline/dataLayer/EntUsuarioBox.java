package petline.dataLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import petline.util.ConnectionManager;
import petline.valueObject.Box;
import petline.valueObject.Usuario;
import petline.valueObject.UsuarioBox;

public class EntUsuarioBox {

	public Collection<UsuarioBox> getBoxPorUsuario( int idUsuario ) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Collection<UsuarioBox> usuarioBoxs = new ArrayList<UsuarioBox>();
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("	select ub.IdUsuarioBox, u.IdUsuario, u.Nombre, u.Apellido, u.CorreoElectronico, u.Password ,u.UserId, " +
					"		b.IdBox, b.Descripcion, b.Codigo, b.URL " +
					"		from usuariobox ub, box b, usuario u " +
					"		where ub.idusuario=? " +
					"		and ub.Idbox=b.idbox " +
					"		and ub.idusuario=u.idusuario");

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idUsuario);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				UsuarioBox usuarioBox = new UsuarioBox();
				usuarioBox.setIdUsuarioBox(rs.getInt(1));
				usuarioBox.setUsuario(new Usuario());
				usuarioBox.getUsuario().setIdUsuario(rs.getInt(2));
				usuarioBox.getUsuario().setNombre(rs.getString(3));
				usuarioBox.getUsuario().setApellido(rs.getString(4));
				usuarioBox.getUsuario().setMail(rs.getString(5));
				usuarioBox.getUsuario().setPass(rs.getString(6));
				usuarioBox.getUsuario().setUserId(rs.getString(7));
				usuarioBox.setBox(new Box());
				usuarioBox.getBox().setIdBox(rs.getInt(8));
				usuarioBox.getBox().setDescripcion(rs.getString(9));
				usuarioBox.getBox().setCodigo(rs.getString(10));
				usuarioBox.getBox().setUrl(rs.getString(11));

				usuarioBoxs.add(usuarioBox);
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
		return usuarioBoxs;
	}
	
	public void insertUsuarioBox( UsuarioBox usuarioBox ) throws SQLException{
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			con.setAutoCommit(false);
			
			stmt = con.prepareStatement("	insert into usuariobox (idusuariobox, idusuario, idbox)" +
										" select IFNULL(max(idusuariobox),0)+1, ?, ? from usuariobox");
			
			stmt.setInt(1, usuarioBox.getUsuario().getIdUsuario());
			stmt.setInt(2, usuarioBox.getBox().getIdBox());
			
			stmt.execute();
			
			stmt.clearParameters();
			
			stmt = con.prepareStatement("	update box set descripcion=? where idBox=?");

			stmt.setString(1, usuarioBox.getBox().getDescripcion());
			stmt.setInt(2, usuarioBox.getBox().getIdBox());
			
			stmt.execute();
			
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
	
	public UsuarioBox getUsuarioBox( int idUsuarioBox ) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UsuarioBox usuarioBox = null;
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append("	select ub.IdUsuarioBox, u.IdUsuario, u.Nombre, u.Apellido, u.CorreoElectronico, u.Password ,u.UserId, " +
					"		b.IdBox, b.Descripcion, b.Codigo, b.URL " +
					"		from usuariobox ub, box b, usuario u " +
					"		where ub.idUsuarioBox=? " +
					"		and ub.Idbox=b.idbox " +
					"		and ub.idusuario=u.idusuario");

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idUsuarioBox);
			
			rs = stmt.executeQuery();

			while (rs.next()) {
				usuarioBox = new UsuarioBox();
				usuarioBox.setIdUsuarioBox(rs.getInt(1));
				usuarioBox.setUsuario(new Usuario());
				usuarioBox.getUsuario().setIdUsuario(rs.getInt(2));
				usuarioBox.getUsuario().setNombre(rs.getString(3));
				usuarioBox.getUsuario().setApellido(rs.getString(4));
				usuarioBox.getUsuario().setMail(rs.getString(5));
				usuarioBox.getUsuario().setPass(rs.getString(6));
				usuarioBox.getUsuario().setUserId(rs.getString(7));
				usuarioBox.setBox(new Box());
				usuarioBox.getBox().setIdBox(rs.getInt(8));
				usuarioBox.getBox().setDescripcion(rs.getString(9));
				usuarioBox.getBox().setCodigo(rs.getString(10));
				usuarioBox.getBox().setUrl(rs.getString(11));
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
		return usuarioBox;
	}

	public void updateUsuarioBox(int idUsuarioBox, String descripcion) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	update box b, usuariobox ub set b.descripcion = ? " +
							"	where ub.idusuariobox=? " +
							"	and ub.idbox=b.idbox" );

			stmt = con.prepareStatement(query.toString());

			stmt.setString(1, descripcion);
			stmt.setInt(2, idUsuarioBox);
			
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

	public void deleteUsuarioBox(int idUsuarioBox) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
	
		try {
			con = ConnectionManager.getConnection();

			StringBuffer query = new StringBuffer();
			query.append( 	"	delete from usuariobox where idusuariobox=? ");

			stmt = con.prepareStatement(query.toString());

			stmt.setInt(1, idUsuarioBox);
			
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
