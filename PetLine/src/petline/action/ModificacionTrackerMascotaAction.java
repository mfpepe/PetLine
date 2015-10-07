package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessTrackerMascota;
import petline.valueObject.TrackerMascota;

public class ModificacionTrackerMascotaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idTrackerMascota = request.getParameter("idTrackerMascota");
		String tempMin = request.getParameter("tempMin");
		String tempMax = request.getParameter("tempMax");
		
		String target = null;
		String message = null;
		try {
			TrackerMascota trackerMascota = new TrackerMascota();
			trackerMascota.setIdTrackerMascota(Integer.parseInt(idTrackerMascota));
			trackerMascota.setTempMinima(Integer.parseInt(tempMin));
			trackerMascota.setTempMaxima(Integer.parseInt(tempMax));
			
			SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
			sessTrackerMascota.modificarTrackerMascota(trackerMascota);
			
			target = "success";
			message = "Tracker Mascota fue modificado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al modificar Tracker Mascota.\n" + e.getMessage();
		}
		
		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
