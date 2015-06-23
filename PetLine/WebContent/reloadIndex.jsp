<%@page import="org.apache.axis.utils.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String msg = request.getParameter("message");
%>
<HEAD>  
<script type="text/javascript">  
function breakout(){  
	//if (window.top != window.self){  
 		window.top.location="<%= request.getRequestURL().toString().substring(0, request.getRequestURL().toString().lastIndexOf("/")) + (StringUtils.isEmpty(msg)?"":"?message=" + msg) %>";  
 	//}  
}  
</script>  
</HEAD>  
<BODY onLoad="javascript:breakout()">          
</body>  
</html>  