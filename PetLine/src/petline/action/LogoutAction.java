package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LogoutAction extends Action{
	
	   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	     
	        String target = "success";
			HttpSession session = request.getSession();
			session.removeAttribute("SESSION_USER");
			session.removeAttribute("SESSION_IDUSER");
				
	        StringBuffer path = new StringBuffer();
	        path.append(mapping.findForward(target).getPath());
	        path.append("?message=Sesión cerrada exitosamente.");
	        
	        return new ActionForward(path.toString(), false);			
	    }
}
