package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessPerimetro;
import petline.valueObject.Perimetro;

public class ModificacionPerimetroAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idPerimetro = request.getParameter("idPerimetro"); 
		String descripcion = request.getParameter("descripcion");
		String distancia = request.getParameter("distancia");
		String latitud = request.getParameter("latitud");
		String longitud = request.getParameter("longitud");

		Perimetro perimetro = new Perimetro();
		perimetro.setIdPerimetro(Integer.parseInt(idPerimetro));
		perimetro.setDescripcion(descripcion);
		perimetro.setDistancia(Integer.parseInt(distancia));
		perimetro.setLatitud(latitud);
		perimetro.setLongitud(longitud);

		String target = null;
		String message = null;
		try {
			SessPerimetro sessPerimetro = new SessPerimetro();
			sessPerimetro.actualizarPerimetro(perimetro);
			target = "success";
			message = "El perímetro fue modificado exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al modificar el perímetro.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
