package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessAnotacion;
import petline.valueObject.Anotacion;

public class ModificacionAnotacionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idAnotacion = request.getParameter("idAnotacion"); 
		String texto = request.getParameter("anotacion");
		
		Anotacion anotacion = new Anotacion();
		anotacion.setIdAnotacion(Integer.parseInt(idAnotacion));
		anotacion.setTexto(texto);
		anotacion.setIdUsuario(Integer.parseInt((String) request.getSession().getAttribute("SESSION_IDUSER")));

		String target = null;
		String message = null;
		try {
			SessAnotacion sessAnotacion = new SessAnotacion();
			sessAnotacion.actualizarAnotacion(anotacion);
			target = "success";
			message = "Anotación fue modificada exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al modificar Anotación.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
