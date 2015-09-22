package petline.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessRecordatorio;
import petline.valueObject.Frecuencia;
import petline.valueObject.Recordatorio;

public class ModificacionRecordatorioAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idRecordatorio = request.getParameter("idRecordatorio");
		String idMascota = request.getParameter("idMascota");
		String descripcion = request.getParameter("descripcion");
		String fecha = request.getParameter("fecha");
		String hora = request.getParameter("hora");
		String frecuencia = request.getParameter("frecuencia");

		Recordatorio recordatorio = new Recordatorio();
		recordatorio.setIdRecordatorio(Integer.parseInt(idRecordatorio));
		recordatorio.setDescripcion(descripcion);

		Calendar fec = Calendar.getInstance();
		fec.clear();
		fec.setTime(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(fecha + " " + hora));
		recordatorio.setFechaHora(fec);

		recordatorio.setFrecuencia(new Frecuencia());
		recordatorio.getFrecuencia().setIdFrecuencia(Integer.parseInt(frecuencia));
		recordatorio.setIdMascota(Integer.parseInt(idMascota));

		String target = null;
		String message = null;
		try {
			SessRecordatorio sessRecordatorio = new SessRecordatorio();
			sessRecordatorio.actualizarRecordatorio(recordatorio);
			target = "success";
			message = "Recordatorio fue modificado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al modificar Recordatorio.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
