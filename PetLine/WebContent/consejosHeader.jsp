<%@page import="petline.valueObject.Mascota"%>
<%@page import="java.util.Collection"%>
<%@page import="petline.sessLayer.SessMascota"%>
<%@page import="petline.util.PetLineUtils"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int idUsuario = Integer.parseInt((String) session.getAttribute("SESSION_IDUSER"));

	SessMascota sessMascota = new SessMascota();
	Collection<Mascota> mascotas = sessMascota.obtenerMascotasPorUsuario(idUsuario);
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Box's</title>
<link rel="stylesheet" type="text/css" href="<%= PetLineUtils.getURL() %>css/PetLine.css">
<script type="text/javascript" src="<%= PetLineUtils.getURL() %>js/main.js" ></script>
</head>
<body  style="height: 100%; padding: 0px; margin: 0px;" >
		<p class="title">Consejos</p>
		<br>
		<table class=table2>
			<tr>
				<td class=etiqueta>Mascota</td>
				<td>&nbsp;
					<select name="mascota" id="mascota" onchange="onChangeMascotaConsejo();" >
						<option value=""></option>
						<%
							for (Mascota mascota : mascotas) {
								out.print("<option value='" + mascota.getIdMascota() + "'>" + mascota.getApodo() + "</option>");
							}
						%>
					</select>
				</td>
			</tr>
			<tr height=700px>
				<td colspan=2><iframe width="100%" frameborder="0" height="100%" name="FRAMESEC" id="FRAMESEC" src="./empty.jsp?message="></iframe></td>
			</tr>			
		</table>		
</body>
</html>    

    