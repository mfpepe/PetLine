<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bienvenido</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body style="height: 100%; padding: 0px; margin: 0px;">
    <%
        String message = request.getParameter("message");
    %>    
 	<table width="100%" height="99%" cellspacing="0" cellpadding="0">
     <tr>
         <td height="90px" width="210px" class="menufondo">
             <img name="logo" id="log" alt="PetLine" src="<%= PetLineUtils.getURL() %>img/petLineLogo.png">
         </td>
         <td colspan=2 rowspan="3">
             <iframe width="100%" frameborder="0" height="100%" name="FRAMEGRAL" id="FRAMEGRAL" src="./empty.jsp?message=<%= message %>"></iframe>
         </td>
     </tr>
     <tr>
         <td height="30px" class="menufondo">
             <a style="cursor:hand;" class="menufondo"><img src="<%= PetLineUtils.getURL() %>img/botonConsulta.png"></a>
         </td>
     </tr>
     <tr>
     	<td class="menufondo"><br></td>
    </tr>
	<tr>
		<td height=30px class="menufondo">        
			<a target='FRAMEGRAL' href="empty.jsp?message=<%= message %>"><img src="<%= PetLineUtils.getURL() %>img/menubutton.png"></a>
			<a href=""><img src="<%= PetLineUtils.getURL() %>img/back.png" onClick="history.back();" ></a>
		</td>
		<td align=right valign=middle class="menufondo">
			<p>Usuario: <%= message.toUpperCase() %></p>
		</td>		
		<td align=right valign=middle class="menufondo" width="35px">
			<a target='_top' href="logout.do"><img src="<%= PetLineUtils.getURL() %>img/logout.png"></a>
		</td>
	</tr>
	<tr>		
		<td height=10px colspan=3 class="version">        
			<p>PetLine: 0.0.1</p>
		</td>
	</tr>			
     </table>		
</body>
</html>    

    