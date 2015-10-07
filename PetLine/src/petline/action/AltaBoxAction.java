package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessBox;
import petline.sessLayer.SessUsuarioBox;
import petline.valueObject.Box;
import petline.valueObject.Usuario;
import petline.valueObject.UsuarioBox;

public class AltaBoxAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String codigo = request.getParameter("codigo");
		String descripcion = request.getParameter("descripcion");

		SessBox sessBox = new SessBox();

		Box box = sessBox.obtenerBoxPorCodigo(codigo);

		String target = null;
		String message = null;
		if(box != null){
			try {
				box.setDescripcion(descripcion);
				UsuarioBox usuarioBox = new UsuarioBox();
				usuarioBox.setBox(box);
				usuarioBox.setUsuario(new Usuario());
				usuarioBox.getUsuario().setIdUsuario(Integer.parseInt((String) request.getSession().getAttribute("SESSION_IDUSER")));
				
				SessUsuarioBox sessUsuarioBox = new SessUsuarioBox();
				sessUsuarioBox.insertarUsuarioBox(usuarioBox);
				
				target = "success";
				message = "El box fue dado de alta exitosamente";
			} catch (Exception e) {
				target = "failure";
				message = "Ocurrio un error al dar de alta el box.\n" + e.getMessage();
			}
		}
		else{
			target = "failure";
			message = "El codigo del box es incorrecto.";			
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
