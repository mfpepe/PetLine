package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessUsuarioBox;

public class BajaBoxAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idUsuarioBox = request.getParameter("idUsuarioBox");
		int idUsuario = Integer.parseInt((String) request.getSession().getAttribute("SESSION_IDUSER"));
		
		String target = null;
		String message = null;
		try {
			SessUsuarioBox sessUsuarioBox = new SessUsuarioBox();
			
			if(sessUsuarioBox.obtenerBoxPorUsuario(idUsuario).size() > 1){
				sessUsuarioBox.eliminarUsuarioBox(Integer.parseInt(idUsuarioBox));
				
				target = "success";
				message = "El box fue eliminado exitosamente";				
			}
			else{
				target = "failure";
				message = "No puede eliminar, debe tener al menos un box asociado.";				
			}

		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al eliminar el box.\n" + e.getMessage();
		}
		
		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
