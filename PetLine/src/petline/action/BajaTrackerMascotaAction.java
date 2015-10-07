package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessTrackerMascota;

public class BajaTrackerMascotaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idTrackerMascota = request.getParameter("idTrackerMascota");
		
		String target = null;
		String message = null;
		try {
			SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
			sessTrackerMascota.eliminarTrackerMascota(Integer.parseInt(idTrackerMascota));
			
			target = "success";
			message = "Tracker Mascota fue eliminado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al eliminar Tracker Mascota.\n" + e.getMessage();
		}
		
		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
