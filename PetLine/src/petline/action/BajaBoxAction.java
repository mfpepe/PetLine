package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessUsuarioBox;

public class BajaBoxAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idUsuarioBox = request.getParameter("idUsuarioBox");
		
		String target = null;
		String message = null;
		try {
			SessUsuarioBox sessUsuarioBox = new SessUsuarioBox();
			sessUsuarioBox.eliminarUsuarioBox(Integer.parseInt(idUsuarioBox));
			
			target = "success";
			message = "El box fue eliminado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al dar de alta la mascota.\n" + e.getMessage();
		}
		
		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
