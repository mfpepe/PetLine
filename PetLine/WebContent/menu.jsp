<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height:100%">
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
         <td height="90px" width="210px" class="menufondo" align=center>
             <img name="logo" id="log" alt="PetLine" src="<%= PetLineUtils.getURL() %>img/petLineLogo.png">
         </td>
         <td colspan=2 rowspan="13">
             <iframe width="100%" frameborder="0" height="100%" name="FRAMEGRAL" id="FRAMEGRAL" src="./inicio.jsp?message=<%= message %>"></iframe>
         </td>
     </tr>
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./mascotas.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonMascotas.png"></a>
         </td>
     </tr>    
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./usuarioModif.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonUsuario.png"></a>
         </td>
     </tr>   
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./box.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonBox.png"></a>
         </td>
     </tr>
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./perimetro.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonPerimetro.png"></a>
         </td>
     </tr>
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./mascPeri.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonMascPeri.png"></a>
         </td>
     </tr>    
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./recorridoHeader.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonRecorrido.png"></a>
         </td>
     </tr>     
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./trackerMasc.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonTrackMasc.png"></a>
         </td>
     </tr>
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./consejosHeader.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonConsejos.png"></a>
         </td>
     </tr>
	 <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./notificacionHeader.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonNotificacion.png"></a>
         </td>
     </tr>     
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./recordatoriosHeader.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonRecordatorio.png"></a>
         </td>
     </tr>
     <tr>
         <td height="30px" class="menufondo" align=center>
             <a style="cursor:hand;" class="menufondo" target="FRAMEGRAL" href="./anotacion.jsp"><img src="<%= PetLineUtils.getURL() %>img/botonAnotacion.png"></a>
         </td>
     </tr>
     <tr>
     	<td class="menufondo"><br></td>
    </tr>
	<tr>
		<td height=30px class="menufondo">        
			<a target='FRAMEGRAL' href="inicio.jsp?message=<%= message %>"><img src="<%= PetLineUtils.getURL() %>img/menubutton.png"></a>
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

    