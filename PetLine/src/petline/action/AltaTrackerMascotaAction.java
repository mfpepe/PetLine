package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessMascota;
import petline.sessLayer.SessTracker;
import petline.sessLayer.SessTrackerMascota;
import petline.valueObject.Mascota;
import petline.valueObject.Tracker;
import petline.valueObject.TrackerMascota;

public class AltaTrackerMascotaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idMascota = request.getParameter("mascota"); 
		String codTracker = request.getParameter("tracker");
		String tempMin = request.getParameter("tempMin");
		String tempMax = request.getParameter("tempMax");

		SessTracker sessTracker = new SessTracker();

		Tracker tracker = sessTracker.obtenerTrackerPorCodigo(codTracker);

		String target = null;
		String message = null;
		if(tracker != null){
			try {
				SessMascota sessMascota = new SessMascota();
				Mascota mascota = sessMascota.obtenerMascota(Integer.parseInt(idMascota));
				
				TrackerMascota trackerMascota = new TrackerMascota();
				trackerMascota.setTempMinima(Integer.parseInt(tempMin));
				trackerMascota.setTempMaxima(Integer.parseInt(tempMax));
				trackerMascota.setTracker(tracker);
				trackerMascota.setMascota(mascota);
				
				SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
				sessTrackerMascota.insertarTrackerMascota(trackerMascota);
				
				target = "success";
				message = "El tracker para la mascota fue dado de alta exitosamente";
			} catch (Exception e) {
				target = "failure";
				message = "Ocurrio un error al dar de alta el tracker para la mascota.\n" + e.getMessage();
			}
		}
		else{
			target = "failure";
			message = "El codigo del tracker es incorrecto.";			
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
