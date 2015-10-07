package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessMascota;
import petline.valueObject.Mascota;

public class BajaMascotaPerimetroAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idMascota = request.getParameter("idMascota"); 

		String target = null;
		String message = null;
		try {
			SessMascota sessMascota = new SessMascota();
			Mascota mascota = sessMascota.obtenerMascota(Integer.parseInt(idMascota));
			mascota.setIdPerimetro(0);
			
			sessMascota.actualizarMascota(mascota);
			target = "success";
			message = "Mascota Perímetro fue desasignado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al desasignar Mascota Perímetro.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
