package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessPerimetro;

public class BajaPerimetroAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idPerimetro = request.getParameter("idPerimetro");

		String target = null;
		String message = null;
		try {
			SessPerimetro sessPerimetro = new SessPerimetro();
			sessPerimetro.eliminarPerimetro(Integer.parseInt(idPerimetro));
			target = "success";
			message = "El perímetro fue eliminado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al eliminar el perímetro.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
