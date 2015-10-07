package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessRecordatorio;

public class BajaRecordatorioAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idRecordatorio = request.getParameter("idRecordatorio");

		String target = null;
		String message = null;
		try {
			SessRecordatorio sessRecordatorio = new SessRecordatorio();
			sessRecordatorio.eliminarRecordatorio(Integer.parseInt(idRecordatorio));
			target = "success";
			message = "Recordatorio fue eliminado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al eliminar Recordatorio.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
