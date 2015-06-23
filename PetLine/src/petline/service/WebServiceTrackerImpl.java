package petline.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://service/", portName = "WebServiceTrackerImplPort", serviceName = "WebServiceTrackerImplService", endpointInterface = "service.WebServiceTracker")
public class WebServiceTrackerImpl implements WebServiceTracker {

	@Override
	@WebMethod(operationName = "trackerService", action = "urn:TrackerService")
	public String trackerService(String idTracker, String latitud, String longitud, String temperatura) throws Exception {
		// TODO Auto-generated method stub
		return "CUMBIA!!!";
	}


}