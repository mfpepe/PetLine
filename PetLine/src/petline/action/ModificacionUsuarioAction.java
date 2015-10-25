package petline.action;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessTelefono;
import petline.sessLayer.SessUsuario;
import petline.util.PetLineUtils;
import petline.valueObject.Telefono;
import petline.valueObject.Usuario;

public class ModificacionUsuarioAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nombre = request.getParameter("nombre"); 
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");

		String target = null;
		String message = null;		
		
		Collection<Telefono> telefonos = new ArrayList<Telefono>();
		
		String[] tels = request.getParameterValues("tel");
		String[] descs = request.getParameterValues("desc");
		
		Telefono tel = null;
		for (int i = 1; i <= tels.length; i++) {
			String telefono = tels[i-1];
			String descripcion = descs[i-1];
			if( PetLineUtils.isValidPhoneNumber(telefono) && PetLineUtils.getNumericPhoneNumberWithoutAreaCode(telefono).length() == 10 ){
				tel = new Telefono();
				tel.setNroTelefono(PetLineUtils.getNumericPhoneNumberWithoutAreaCode(telefono));
				tel.setDescripcion(descripcion);
				telefonos.add(tel);
			}
			else{
				target = "failure";
				message = "Teléfono inválido: " + telefono;
				break;
			}
		}
		
		if( StringUtils.isEmpty(target) ){
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(Integer.parseInt((String) request.getSession().getAttribute("SESSION_IDUSER")));
			usuario.setApellido(apellido);
			usuario.setNombre(nombre);
			usuario.setMail(email);

			try {
				SessUsuario sessUsuario = new SessUsuario();
				sessUsuario.actualizarUsuario(usuario);
				
				SessTelefono sessTelefono = new SessTelefono();
				sessTelefono.actualizarTelefonos( usuario.getIdUsuario(), telefonos );
				
				target = "success";
				message = "El usuario fue modificado exitosamente";
			} catch (Exception e) {
				target = "failure";
				message = "Ocurrio un error al modificar el usuario.\n" + e.getMessage();
			}			
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
