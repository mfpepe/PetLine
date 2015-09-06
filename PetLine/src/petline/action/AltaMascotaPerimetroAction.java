package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessMascota;
import petline.valueObject.Mascota;

public class AltaMascotaPerimetroAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idMascota = request.getParameter("mascota"); 
		String perimetro = request.getParameter("perimetro");

		String target = null;
		String message = null;
		try {
			SessMascota sessMascota = new SessMascota();
			Mascota mascota = sessMascota.obtenerMascota(Integer.parseInt(idMascota));
			mascota.setIdPerimetro(Integer.parseInt(perimetro));
			
			sessMascota.actualizarMascota(mascota);
			target = "success";
			message = "El per�metro fue asignado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al asignar el per�metro.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
