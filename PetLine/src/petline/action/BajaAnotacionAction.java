package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessAnotacion;

public class BajaAnotacionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idAnotacion = request.getParameter("idAnotacion");

		String target = null;
		String message = null;
		try {
			SessAnotacion sessAnotacion = new SessAnotacion();
			sessAnotacion.eliminarAnotacion(Integer.parseInt(idAnotacion));
			target = "success";
			message = "Anotación fue eliminado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al eliminar Anotación.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
