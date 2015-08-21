package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.util.TokenGenerator;

public class BoxViewAction extends Action{
	
	   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

	        StringBuffer path = new StringBuffer();
	        path.append(mapping.findForward("success").getPath());
	        path.append("?st=" + TokenGenerator.generoToken());
	        
	        response.sendRedirect(path.toString());
	        
	        return null;

	    }
}
