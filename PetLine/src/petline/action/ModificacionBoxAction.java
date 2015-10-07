package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessUsuarioBox;

public class ModificacionBoxAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idUsuarioBox = request.getParameter("idUsuarioBox");
		String descripcion = request.getParameter("descripcion");
		String target = null;
		String message = null;
		try {
			SessUsuarioBox sessUsuarioBox = new SessUsuarioBox();
			sessUsuarioBox.modificarUsuarioBox(Integer.parseInt(idUsuarioBox), descripcion);
			
			target = "success";
			message = "El box fue modificado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al modificar el box.\n" + e.getMessage();
		}
		
		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
