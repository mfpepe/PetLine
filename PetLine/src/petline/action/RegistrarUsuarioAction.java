package petline.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessBox;
import petline.sessLayer.SessUsuario;
import petline.util.HashGenerator;
import petline.util.PetLineUtils;
import petline.valueObject.Box;
import petline.valueObject.Usuario;

public class RegistrarUsuarioAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String codigoBox = request.getParameter("box");
		String nombre = request.getParameter("nombre"); 
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String telefono = request.getParameter("telefono");
		String alias = request.getParameter("alias");
		String clave = request.getParameter("clave");
	
		
		String target = null;
		String message = null;
		if( PetLineUtils.isValidPhoneNumber(telefono) ){
			SessBox sessBox = new SessBox();
			
			Box box = sessBox.obtenerBoxValidoPorCodigo(codigoBox);

			if(box != null){
				
				SessUsuario sessUsuario = new SessUsuario();
				Usuario user = sessUsuario.obtenerUsuarioPorAlias(alias);
				
				if(user == null){
					try {
						Usuario usuario = new Usuario();
						usuario.setApellido(apellido);
						usuario.setNombre(nombre);
						usuario.setMail(email);
						usuario.setPass(HashGenerator.convert(clave));
						usuario.setUserId(alias);
						
						sessUsuario.registrarUsuario(usuario, PetLineUtils.getNumericPhoneNumberWithoutAreaCode(telefono), box.getIdBox());
						
						target = "success";
						message = "El usuario fue registrado exitosamente";
					} catch (Exception e) {
						target = "failure";
						message = "Ocurrio un error al registrar el usuario.\n" + e.getMessage();
					}	
				}
				else{
					target = "failure";
					message = "Alias existente.";	
				}
			
				
			}
			else{
				target = "failure";
				message = "El codigo del box es incorrecto.";			
			}			
		}
		else{
			target = "failure";
			message = "Teléfono inválido.";			
		}


		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);

		return new ActionForward(path.toString(), false);

	}
}
