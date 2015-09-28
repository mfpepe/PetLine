<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="petline.service.WebServiceTrackerImpl"%>
<%

	String codigoTracker = request.getParameter("codigoTracker");
	String latitud = request.getParameter("latitud");
	String longitud = request.getParameter("longitud");
	String temperatura = request.getParameter("temperatura");
	String bajaBateria = request.getParameter("bajaBateria");

	if( !StringUtils.isEmpty(codigoTracker) && !StringUtils.isEmpty(latitud) && !StringUtils.isEmpty(longitud) 
			&& !StringUtils.isEmpty(temperatura) && !StringUtils.isEmpty(bajaBateria) ){
		
		WebServiceTrackerImpl webServiceTrackerImpl = new WebServiceTrackerImpl();
		String rpta = webServiceTrackerImpl.trackerService(codigoTracker, latitud, longitud, temperatura, bajaBateria); 
		out.print("@@" + (StringUtils.isEmpty(rpta)?"NOMSG":rpta) + "@@");
	}
	else{
		out.print("@@NOMSG@@");
	}
%>
