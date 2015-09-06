package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessPerimetro;
import petline.valueObject.Perimetro;

public class AltaPerimetroAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String descripcion = request.getParameter("descripcion");
		String distancia = request.getParameter("distancia");
		String latitud = request.getParameter("latitud");
		String longitud = request.getParameter("longitud");

		String target = null;
		String message = null;

		try {
			Perimetro perimetro = new Perimetro();
			perimetro.setDescripcion(descripcion);
			perimetro.setDistancia(Integer.parseInt(distancia));
			perimetro.setLatitud(latitud);
			perimetro.setLongitud(longitud);
			perimetro.setIdUsuario(Integer.parseInt((String) request.getSession().getAttribute("SESSION_IDUSER")));
			
			SessPerimetro sessPerimetro = new SessPerimetro();
			sessPerimetro.insertarPerimetro(perimetro);
			
			target = "success";
			message = "El perímetro fue dado de alta exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al dar de alta el perímetro.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
