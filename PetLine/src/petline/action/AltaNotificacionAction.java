package petline.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import petline.sessLayer.SessTelefonoTipoNotificacion;
import petline.sessLayer.SessTipoNotificacion;
import petline.valueObject.TelefonoTipoNotificacion;
import petline.valueObject.TipoNotificacion;
import petline.valueObject.Tracker;

public class AltaNotificacionAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idTracker = request.getParameter("idTracker");

		String target = null;
		String message = null;
		try {

			Collection<TelefonoTipoNotificacion> telefonoTipoNotificaciones = new ArrayList<TelefonoTipoNotificacion>();
			
			SessTipoNotificacion sessTipoNotificacion = new SessTipoNotificacion();
			Collection<TipoNotificacion> tipoNotificaciones =  sessTipoNotificacion.obtenerTipoNotificaciones();
			
			for (TipoNotificacion tipoNotificacion : tipoNotificaciones) {
				String opc = request.getParameter("opc" + tipoNotificacion.getIdTipoNotificacion());

				TelefonoTipoNotificacion ttn = new TelefonoTipoNotificacion();
				ttn.setTracker(new Tracker());
				ttn.getTracker().setIdTracker(Integer.parseInt(idTracker));
				
				ttn.setTipoNotificacion(tipoNotificacion);
				
				if( "S".equalsIgnoreCase(opc) ){					
					String[] nroTelefonos = request.getParameterValues("tel" + tipoNotificacion.getIdTipoNotificacion());
					ttn.getNroTelefonos().addAll(Arrays.asList(nroTelefonos));
				}
				telefonoTipoNotificaciones.add(ttn);
			}
			
			SessTelefonoTipoNotificacion sessTelefonoTipoNotificacion = new SessTelefonoTipoNotificacion();
			sessTelefonoTipoNotificacion.insertarTelefonoTipoNotificacion(telefonoTipoNotificaciones);

			target = "success";
			message = "Notificaciones modificadas exitosamente";
		} catch (Exception e) {
			target = "failure";
			message = "Ocurrio un error al modificar Notificación.\n" + e.getMessage();
		}

		StringBuffer path = new StringBuffer();
		path.append(mapping.findForward(target).getPath());
		path.append("?message=" + message);
		path.append("&idTracker=" + idTracker);

		return new ActionForward(path.toString(), false);

	}
}
