package petline.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import petline.sessLayer.SessCoordenada;
import petline.sessLayer.SessNotificacion;
import petline.sessLayer.SessPerimetro;
import petline.sessLayer.SessTelefonoTipoNotificacion;
import petline.sessLayer.SessTracker;
import petline.sessLayer.SessTrackerMascota;
import petline.util.PetLineUtils;
import petline.valueObject.Coordenada;
import petline.valueObject.Notificacion;
import petline.valueObject.Perimetro;
import petline.valueObject.TelefonoTipoNotificacion;
import petline.valueObject.TipoNotificacion;
import petline.valueObject.TipoNotificacionConst;
import petline.valueObject.Tracker;
import petline.valueObject.TrackerMascota;

@WebService(targetNamespace = "http://service/", portName = "WebServiceTrackerImplPort", serviceName = "WebServiceTrackerImplService", endpointInterface = "service.WebServiceTracker")
public class WebServiceTrackerImpl implements WebServiceTracker {

	private static final long MAXIMA_ESPERA_ENTRE_NOTIFICACIONES = 3;
	
	@Override
	@WebMethod(operationName = "trackerService", action = "urn:TrackerService")
	public String trackerService(String codigoTracker, String latitud, String longitud, String temperatura, String bajaBateria) throws Exception {
		
		SessTracker sessTracker = new SessTracker();
		Tracker tracker = sessTracker.obtenerTrackerPorCodigo(codigoTracker);
		
		StringBuffer rpta = new StringBuffer();
		
		if( tracker != null ){

			//GUARDA COORDENADAS
			_insertarCoordenadas(tracker.getIdTracker(), latitud, longitud);
			
			//ACTUALIZO TEMPERATURA ACTUAL
			_actualizarTemperaturaActual(tracker.getIdTracker(), temperatura);
			
			SessNotificacion sessNotificacion = new SessNotificacion();
			HashMap<Integer, Calendar> ultimasNotificaciones = sessNotificacion.obtenerUltimasNotificacionesRealizadas(tracker.getIdTracker());
			
			Calendar fechaCall = Calendar.getInstance();
			
			if( !ultimasNotificaciones.containsKey(new Integer(TipoNotificacionConst.TIPO_NOTIFICACION_BATERIA)) 
					|| PetLineUtils.difenciaFechasHoras( ultimasNotificaciones.get(new Integer(TipoNotificacionConst.TIPO_NOTIFICACION_BATERIA)) , fechaCall) >= MAXIMA_ESPERA_ENTRE_NOTIFICACIONES){
				//BATERIA BAJA
				rpta.append(_verificarBateriaBaja(tracker.getIdTracker(), bajaBateria));				
			}
				

			if( !ultimasNotificaciones.containsKey(new Integer(TipoNotificacionConst.TIPO_NOTIFICACION_OBJETIVO)) 
					|| PetLineUtils.difenciaFechasHoras( ultimasNotificaciones.get(new Integer(TipoNotificacionConst.TIPO_NOTIFICACION_OBJETIVO)) , fechaCall) >= MAXIMA_ESPERA_ENTRE_NOTIFICACIONES){
				//OBJETIVO DIARIO
				String objetivoDiario = _verificarObjetivoDiario(tracker.getIdTracker());
				if(!StringUtils.isEmpty(objetivoDiario) && !StringUtils.isEmpty(rpta.toString())){
					rpta.append("|");
				}
				rpta.append(objetivoDiario);
			}

			if( !ultimasNotificaciones.containsKey(new Integer(TipoNotificacionConst.TIPO_NOTIFICACION_PERIMETRO)) 
					|| PetLineUtils.difenciaFechasHoras( ultimasNotificaciones.get(new Integer(TipoNotificacionConst.TIPO_NOTIFICACION_PERIMETRO)) , fechaCall) >= MAXIMA_ESPERA_ENTRE_NOTIFICACIONES){
				//PERIMETRO
				String perimetro = _verificarPerimetro(tracker.getIdTracker(), latitud, longitud);
				if(!StringUtils.isEmpty(perimetro) && !StringUtils.isEmpty(rpta.toString())){
					rpta.append("|");
				}
				rpta.append(perimetro);
			}

			if( !ultimasNotificaciones.containsKey(new Integer(TipoNotificacionConst.TIPO_NOTIFICACION_TEMPERATURA)) 
					|| PetLineUtils.difenciaFechasHoras( ultimasNotificaciones.get(new Integer(TipoNotificacionConst.TIPO_NOTIFICACION_TEMPERATURA)) , fechaCall) >= MAXIMA_ESPERA_ENTRE_NOTIFICACIONES){
				//TEMPERATURA
				String temp = _verificarTemperatura(tracker.getIdTracker(), temperatura);
				if(!StringUtils.isEmpty(temp) && !StringUtils.isEmpty(rpta.toString())){
					rpta.append("|");
				}
				rpta.append(temp);		
			}
			
			if(!StringUtils.isEmpty(rpta.toString())){
				SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
				TrackerMascota trackerMascota = sessTrackerMascota.obtenerTrackerMascotaPorTracker(tracker.getIdTracker());
				rpta.insert(0, trackerMascota.getMascota().getApodo() + "@");
			}
			
		}
		
		return rpta.toString();
	}

	private void _actualizarTemperaturaActual(int idTracker, String temperatura) throws SQLException {
		SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
		TrackerMascota trackerMascota = sessTrackerMascota.obtenerTrackerMascotaPorTracker(idTracker);
		sessTrackerMascota.modificarTemperaturaActualTrackerMascota(trackerMascota.getIdTrackerMascota(), Integer.parseInt(temperatura));
	}

	private void _insertarCoordenadas(int idTracker, String latitud, String longitud) throws SQLException {
		Coordenada coordenada = new Coordenada();
		coordenada.setFechaHora(Calendar.getInstance());
		coordenada.setIdTracker(idTracker);
		coordenada.setLatitud(latitud);
		coordenada.setLongitud(longitud);
		SessCoordenada sessCoordenada = new SessCoordenada();
		sessCoordenada.insertarCoordenada(coordenada);
	}

	private static String _verificarBateriaBaja(int idTracker, String bajaBateria){
		try {
			StringBuffer rpta = new StringBuffer();
			SessTelefonoTipoNotificacion sessTelefonoTipoNotificacion = new SessTelefonoTipoNotificacion();
			TelefonoTipoNotificacion ttn = sessTelefonoTipoNotificacion.obtenerTelefonoTipoNotificacion(TipoNotificacionConst.TIPO_NOTIFICACION_BATERIA, idTracker);
			if( ttn == null ){
				TipoNotificacion tn = new TipoNotificacion();
				tn.setIdTipoNotificacion(TipoNotificacionConst.TIPO_NOTIFICACION_BATERIA);
				ttn = new TelefonoTipoNotificacion();
				ttn.setTipoNotificacion(tn);
			}
			if( "S".equalsIgnoreCase(bajaBateria)  ){
				Notificacion notificacion = new Notificacion();
				notificacion.setFechaHora(Calendar.getInstance());
				notificacion.setTipoNotificacion(ttn.getTipoNotificacion());
				notificacion.setTracker(new Tracker());
				notificacion.getTracker().setIdTracker(idTracker);
				
				SessNotificacion sessNotificacion = new SessNotificacion();
				sessNotificacion.insertarNotificacion(notificacion);
				
				if(!ttn.getNroTelefonos().isEmpty()){
					rpta.append(StringUtils.join(ttn.getNroTelefonos().iterator(), ","));
					rpta.append(":");
					rpta.append(ttn.getTipoNotificacion().getIdTipoNotificacion());					
				}
			
			}
			return rpta.toString();			
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	private static String _verificarObjetivoDiario(int idTracker){
		try {
			StringBuffer rpta = new StringBuffer();
			
			SessTelefonoTipoNotificacion sessTelefonoTipoNotificacion = new SessTelefonoTipoNotificacion();
			TelefonoTipoNotificacion ttn = sessTelefonoTipoNotificacion.obtenerTelefonoTipoNotificacion(TipoNotificacionConst.TIPO_NOTIFICACION_OBJETIVO, idTracker);
			if( ttn == null ){
				TipoNotificacion tn = new TipoNotificacion();
				tn.setIdTipoNotificacion(TipoNotificacionConst.TIPO_NOTIFICACION_OBJETIVO);
				ttn = new TelefonoTipoNotificacion();
				ttn.setTipoNotificacion(tn);
			}
			
			Calendar fHoy = Calendar.getInstance();
			SessCoordenada sessCoordenada = new SessCoordenada();
			Collection<Coordenada> coordenadas = sessCoordenada.obtenerCoordenadas(idTracker, fHoy);
			
			String latOri = "";
			String lngOri = "";
			float totalMtsRecorridos = 0;
			for(Coordenada coordenada : coordenadas){
				if( !StringUtils.isEmpty(latOri) && !StringUtils.isEmpty(lngOri) ){
					totalMtsRecorridos += PetLineUtils.distanciaMts(latOri, lngOri, coordenada.getLatitud(), coordenada.getLongitud());
				}

				latOri = coordenada.getLatitud();
				lngOri = coordenada.getLongitud();				
			}

			SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
			TrackerMascota trackerMascota = sessTrackerMascota.obtenerTrackerMascotaPorTracker(idTracker);
			
			if( trackerMascota.getMascota().getKmDiarios() > 0.0 && (totalMtsRecorridos/1000) > trackerMascota.getMascota().getKmDiarios() ){
				
				Notificacion notificacion = new Notificacion();
				notificacion.setFechaHora(Calendar.getInstance());
				notificacion.setTipoNotificacion(ttn.getTipoNotificacion());
				notificacion.setTracker(trackerMascota.getTracker());
				
				SessNotificacion sessNotificacion = new SessNotificacion();
				sessNotificacion.insertarNotificacion(notificacion);				
				
				if( !ttn.getNroTelefonos().isEmpty() ){						
					rpta.append(StringUtils.join(ttn.getNroTelefonos().iterator(), ","));
					rpta.append(":");
					rpta.append(ttn.getTipoNotificacion().getIdTipoNotificacion());
				}
			}
			
			return rpta.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}	
	
	private static String _verificarPerimetro(int idTracker, String latitud, String longitud){
		try {
			StringBuffer rpta = new StringBuffer();
			
			SessTelefonoTipoNotificacion sessTelefonoTipoNotificacion = new SessTelefonoTipoNotificacion();
			TelefonoTipoNotificacion ttn = sessTelefonoTipoNotificacion.obtenerTelefonoTipoNotificacion(TipoNotificacionConst.TIPO_NOTIFICACION_PERIMETRO, idTracker);
			if( ttn == null ){
				TipoNotificacion tn = new TipoNotificacion();
				tn.setIdTipoNotificacion(TipoNotificacionConst.TIPO_NOTIFICACION_PERIMETRO);
				ttn = new TelefonoTipoNotificacion();
				ttn.setTipoNotificacion(tn);
			}
			
			SessPerimetro sessPerimetro = new SessPerimetro();
			SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
			TrackerMascota trackerMascota = sessTrackerMascota.obtenerTrackerMascotaPorTracker(idTracker);
			
			Perimetro perimetro = sessPerimetro.obtenerPerimetro(trackerMascota.getMascota().getIdPerimetro());
			
			if( perimetro != null ){
				float distanciaMts = PetLineUtils.distanciaMts(perimetro.getLatitud(), perimetro.getLongitud(), latitud, longitud);
				if( distanciaMts > perimetro.getDistancia() ){
					
					Notificacion notificacion = new Notificacion();
					notificacion.setFechaHora(Calendar.getInstance());
					notificacion.setTipoNotificacion(ttn.getTipoNotificacion());
					notificacion.setTracker(trackerMascota.getTracker());
					
					SessNotificacion sessNotificacion = new SessNotificacion();
					sessNotificacion.insertarNotificacion(notificacion);	
		
					if( !ttn.getNroTelefonos().isEmpty() ){
						rpta.append(StringUtils.join(ttn.getNroTelefonos().iterator(), ","));
						rpta.append(":");
						rpta.append(ttn.getTipoNotificacion().getIdTipoNotificacion());
					}
				}
			}
		
			return rpta.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}	
	
	private static String _verificarTemperatura(int idTracker, String temperatura){
		try {
			StringBuffer rpta = new StringBuffer();
			
			SessTelefonoTipoNotificacion sessTelefonoTipoNotificacion = new SessTelefonoTipoNotificacion();
			TelefonoTipoNotificacion ttn = sessTelefonoTipoNotificacion.obtenerTelefonoTipoNotificacion(TipoNotificacionConst.TIPO_NOTIFICACION_TEMPERATURA, idTracker);
			if( ttn == null ){
				TipoNotificacion tn = new TipoNotificacion();
				tn.setIdTipoNotificacion(TipoNotificacionConst.TIPO_NOTIFICACION_TEMPERATURA);
				ttn = new TelefonoTipoNotificacion();
				ttn.setTipoNotificacion(tn);
			}
			
			SessTrackerMascota sessTrackerMascota = new SessTrackerMascota();
			TrackerMascota trackerMascota = sessTrackerMascota.obtenerTrackerMascotaPorTracker(idTracker);

			if( Float.parseFloat(temperatura) < trackerMascota.getTempMinima() || Float.parseFloat(temperatura) > trackerMascota.getTempMaxima() ){
				
				Notificacion notificacion = new Notificacion();
				notificacion.setFechaHora(Calendar.getInstance());
				notificacion.setTipoNotificacion(ttn.getTipoNotificacion());
				notificacion.setTracker(trackerMascota.getTracker());
				
				SessNotificacion sessNotificacion = new SessNotificacion();
				sessNotificacion.insertarNotificacion(notificacion);					
				
				if( !ttn.getNroTelefonos().isEmpty() ){					
					rpta.append(StringUtils.join(ttn.getNroTelefonos().iterator(), ","));
					rpta.append(":");
					rpta.append(ttn.getTipoNotificacion().getIdTipoNotificacion());
				}
			}
			
			return rpta.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}		
}