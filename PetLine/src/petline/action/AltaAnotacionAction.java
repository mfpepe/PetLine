package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessAnotacion;
import petline.valueObject.Anotacion;

public class AltaAnotacionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String texto = request.getParameter("anotacion");

		String target = null;
		String message = null;

		try {
			Anotacion anotacion = new Anotacion();
			anotacion.setTexto(texto);
			anotacion.setIdUsuario(Integer.parseInt((String) request.getSession().getAttribute("SESSION_IDUSER")));
			
			SessAnotacion sessAnotacion = new SessAnotacion();
			sessAnotacion.insertarAnotacion(anotacion);
			
			target = "success";
			message = "Anotación fue dada de alta exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al dar de alta Anotación.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
