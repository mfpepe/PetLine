package petline.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "WebServiceTracker", targetNamespace = "http://service/")
public interface WebServiceTracker{
	@WebMethod(operationName = "trackerService", action = "urn:TrackerService") 
	public String trackerService (String codigoTracker, String latitud, String longitud, String temperatura, String bajaBateria) throws Exception;
}