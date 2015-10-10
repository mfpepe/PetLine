package petline.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessMascota;
import petline.valueObject.Mascota;

public class ModificacionMascotaAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idMascota = request.getParameter("idMascota"); 
		String apodo = request.getParameter("apodo");
		String edad = request.getParameter("edad");
		String peso = request.getParameter("peso");
		String objetivo = request.getParameter("objetivo");
		String perimetro = request.getParameter("perimetro");
		String tamanio = request.getParameter("tamanio");
		String raza = request.getParameter("raza");

		Mascota mascota = new Mascota();
		mascota.setIdMascota(Integer.parseInt(idMascota));
		mascota.setApodo(apodo);

		Calendar fechaNac = Calendar.getInstance();
		fechaNac.clear();
		fechaNac.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(edad));
		mascota.setFechaNacimiento(fechaNac);

		mascota.setPeso(Double.parseDouble(peso.replaceAll(",", ".")));
		mascota.setKmDiarios((StringUtils.isEmpty(objetivo)) ? 0.0 : Double.parseDouble(objetivo.replaceAll(",", ".")));
		mascota.setIdUsuario(Integer.parseInt((String) request.getSession().getAttribute("SESSION_IDUSER")));

		mascota.setIdPerimetro((StringUtils.isEmpty(perimetro)) ? 0 : Integer.parseInt(perimetro));
		mascota.setIdTamaño(Integer.parseInt(tamanio));
		mascota.setIdRaza(Integer.parseInt(raza));

		String target = null;
		String message = null;
		try {
			SessMascota sessMascota = new SessMascota();
			sessMascota.actualizarMascota(mascota);
			target = "success";
			message = "Las mascota fue modificada exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al modificar la mascota.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
