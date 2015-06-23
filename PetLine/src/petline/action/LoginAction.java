package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.form.LoginForm;
import petline.sessLayer.SessUsuario;

public class LoginAction extends Action{
	
	   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	     
	        String target = null;
	        LoginForm loginForm = (LoginForm)form; 
	        String message = "";
	        
	        SessUsuario sessUsuario = new SessUsuario();
	        
	        if(sessUsuario.esUsuarioValido(loginForm.getUserName(), loginForm.getPassword())) {
	            target = "success";
	            message = loginForm.getUserName();
	            request.getSession().setAttribute("SESSION_USER", loginForm.getUserName());
	        }
	        else {
	            target = "failure";
	            message = "Usuario o Password invalidos.";
	        }
	        	        
	        StringBuffer path = new StringBuffer();
	        path.append(mapping.findForward(target).getPath());
	        path.append("?message=" + message);
	        
	        return new ActionForward(path.toString(), false);

	    }
}
