package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessBox;
import petline.util.TokenGenerator;
import petline.valueObject.Box;

public class BoxViewAction extends Action{
	
	   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		   	int idBox = Integer.parseInt(request.getParameter("idBox"));
		   
		   	SessBox sessBox = new SessBox();
		   	Box box = sessBox.obtenerBox(idBox);
		   	
	        StringBuffer path = new StringBuffer();
	        path.append(box.getUrl() + "Box");
	        path.append("?st=" + TokenGenerator.generoToken());
	        
	        response.sendRedirect(path.toString());
	        
	        return null;

	    }
}
