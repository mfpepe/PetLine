package petline.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessTelefono;
import petline.sessLayer.SessUsuario;
import petline.valueObject.Usuario;

public class ModificacionUsuarioAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nombre = request.getParameter("nombre"); 
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		int cantTelefonos = Integer.parseInt(request.getParameter("cantTelefonos"));
		
		Collection<String> telefonos = new ArrayList<String>();
		for (int i = 1; i <= cantTelefonos; i++) {
			telefonos.add(request.getParameter("tel" + i));
		}
		
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(Integer.parseInt((String) request.getSession().getAttribute("SESSION_IDUSER")));
		usuario.setApellido(apellido);
		usuario.setNombre(nombre);
		usuario.setMail(email);
		
		
		String target = null;
		String message = null;
		try {
			SessUsuario sessUsuario = new SessUsuario();
			sessUsuario.actualizarUsuario(usuario);
			
			SessTelefono sessTelefono = new SessTelefono();
			sessTelefono.actualizarTelefonos( usuario.getIdUsuario(), telefonos );
			
			target = "success";
			message = "El usuario fue modificado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al modificar el usuario.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}