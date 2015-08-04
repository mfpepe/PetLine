package box.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import box.utils.TokenGenerator;

public class LoginAction extends Action{
	
	   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	     
	        String st = request.getParameter("st");
	        String target = null;
	        String message = null;
	        
	        if(!StringUtils.isEmpty(st) && TokenGenerator.validateToken(st)) {
	            target = "success";
	            message = st;
	        }
	        else {
	            target = "failure";
	        }
	        	        
	        StringBuffer path = new StringBuffer();
	        path.append(mapping.findForward(target).getPath());
	        path.append("?message=" + message);
	        
	        return new ActionForward(path.toString(), false);
	    }
}
