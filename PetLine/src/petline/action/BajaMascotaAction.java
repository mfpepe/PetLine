package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessMascota;

public class BajaMascotaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idMascota = request.getParameter("idMascota");

		String target = null;
		String message = null;
		try {
			SessMascota sessMascota = new SessMascota();
			sessMascota.eliminarMascota(Integer.parseInt(idMascota));
			target = "success";
			message = "Las mascota fue eliminada exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al eliminar la mascota.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
