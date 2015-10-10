package petline.util;

import java.util.Calendar;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PetLineUtils {

	public static String getURL() {
		String url = "";
		try {
			Configuration config = new PropertiesConfiguration(
					"petline/config.properties");
			url = config.getString("URL");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return url;
	}

	public static Integer calcularEdad(Calendar fechaNacimiento) {
		// Se crea un objeto con la fecha actual
		Calendar fechaActual = Calendar.getInstance();
		// Se restan la fecha actual y la fecha de nacimiento
		int año = fechaActual.get(Calendar.YEAR)
				- fechaNacimiento.get(Calendar.YEAR);
		int mes = fechaActual.get(Calendar.MONTH)
				- fechaNacimiento.get(Calendar.MONTH);
		int dia = fechaActual.get(Calendar.DATE)
				- fechaNacimiento.get(Calendar.DATE);
		// Se ajusta el año dependiendo el mes y el día
		if (mes < 0 || (mes == 0 && dia < 0)) {
			año--;
		}
		// Regresa la edad en base a la fecha de nacimiento
		return año;
	}

	public static float distanciaMts(String latOri, String lngOri,
			String latDes, String lngDes) {

		float lat1 = Float.parseFloat(latOri);
		float lng1 = Float.parseFloat(lngOri);
		float lat2 = Float.parseFloat(latDes);
		float lng2 = Float.parseFloat(lngDes);

		double earthRadius = 6371; // kilometers
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		float dist = (float) (earthRadius * c);

		return dist*1000;
	}

	public static double caloriasQuemadas(double peso, Calendar fechaInicio, Calendar fechaFin) {
		//http://www.realfitness.es/calculadoras/aprende-utilizar-tablas-met-calcular-calorias-quemadas/

		double diff = fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis();

		double diffHours = diff / (60 * 60 * 1000);				
				
		//return (((3*0.0175*peso)*60)*diffHours); // Kcal/hs
		return (((3*0.1225*peso)*60)*diffHours); // Kcal/hs

	}
	
    public static long difenciaFechasHoras(Calendar cal1, Calendar cal2)
    {
        // conseguir la representacion de la fecha en milisegundos
        long milis1 = cal1.getTimeInMillis();
        long milis2 = cal2.getTimeInMillis();

        // calcular la diferencia en milisengundos
        long diff = milis2 - milis1;

        // calcular la diferencia en segundos
     //   long diffSeconds = diff / 1000;

        // calcular la diferencia en minutos
       // long diffMinutes = diff / (60 * 1000);

        // calcular la diferencia en horas
        long diffHours = diff / (60 * 60 * 1000);

        // calcular la diferencia en dias
        //long diffDays = diff / (24 * 60 * 60 * 1000);

        return diffHours;
    }	
	
	public static boolean isValidPhoneNumber( String nroTelefono ){

		try {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		    PhoneNumber numberProto = phoneUtil.parse(nroTelefono, "AR");
			
		    String nroTelFormateado = phoneUtil.format(numberProto, PhoneNumberFormat.NATIONAL); 
		    
			return nroTelFormateado.indexOf(" ") != -1 && (nroTelFormateado.substring(nroTelFormateado.indexOf(" ")+1).split("-").length == 2 || nroTelFormateado.substring(nroTelFormateado.indexOf(" ")+1).split("-").length == 3);
		    
		} catch (NumberParseException e) {
			return false;
		}				
		
	}	
	
	public static String getNumericPhone(String nroTelefono) {
		try {
			Integer.parseInt(nroTelefono);
		} catch (NumberFormatException nfe) {
			String newNroTelefono = "";
			for (int i = 0; i < nroTelefono.length(); i++) {
				if (Character.isDigit(nroTelefono.charAt(i))) {
					newNroTelefono += nroTelefono.charAt(i);
				}
			}
			nroTelefono = newNroTelefono;
		}

		return nroTelefono;
	}	
	
	public static String getNumericPhoneNumber( String nroTelefono ) throws Exception{
		if( isValidPhoneNumber(nroTelefono) ){
			try {
				PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			    PhoneNumber numberProto = phoneUtil.parse(nroTelefono, "AR");
				
			    String nroTelFormateado = phoneUtil.format(numberProto, PhoneNumberFormat.NATIONAL); 
			    
			    nroTelFormateado = nroTelFormateado.replaceAll(" ", "").replaceAll("-", "");
			    if( nroTelFormateado.startsWith("0") ){
			    	nroTelFormateado = nroTelFormateado.substring(1);
			    }
				return nroTelFormateado;
			} catch (NumberParseException e) {
				throw new Exception("El Nro de Teléfono no es valido. " + e.getMessage());
			}		
		}
		else{
			throw new Exception("El Nro de Teléfono no es valido.");
		}
	}		
	
	public static String getNumericPhoneNumberWithoutAreaCode( String nroTelefono ) throws Exception{
		if( isValidPhoneNumber(nroTelefono) ){
			try {
				PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			    PhoneNumber numberProto = phoneUtil.parse(nroTelefono, "AR");
				
			    String nroTelFormateado = phoneUtil.format(numberProto, PhoneNumberFormat.NATIONAL); 

				nroTelFormateado = nroTelFormateado.substring(nroTelFormateado.indexOf(" ")+1).replaceAll("-", "");
				if(nroTelFormateado.startsWith("15")){
					nroTelFormateado = "11" + nroTelFormateado.substring(2);
				}
				return nroTelFormateado;
			} catch (NumberParseException e) {
				throw new Exception("El Nro de Teléfono no es valido. " + e.getMessage());
			}		
		}
		else{
			throw new Exception("El Nro de Teléfono no es valido.");
		}
	}	
	
	public static String getAreaCodeFromPhoneNumber( String nroTelefono ) throws Exception{
		if( isValidPhoneNumber(nroTelefono) ){
			try {
				PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			    PhoneNumber numberProto = phoneUtil.parse(nroTelefono, "AR");
				
			    String nroTelFormateado = phoneUtil.format(numberProto, PhoneNumberFormat.NATIONAL); 
			    
			    return nroTelFormateado.substring(0, nroTelFormateado.indexOf(" "));
			    
			} catch (NumberParseException e) {
				throw new Exception("El Nro de Teléfono no es valido. " + e.getMessage());
			}		
		}
		else{
			throw new Exception("El Nro de Teléfono no es valido.");
		}
	}		
	
	public static String getPhoneNumberForMask( String nroTelefono ) throws Exception{
		if( isValidPhoneNumber(nroTelefono) ){
			try {
				PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			    PhoneNumber numberProto = phoneUtil.parse(nroTelefono, "AR");
				
			    String nroTelFormateado = phoneUtil.format(numberProto, PhoneNumberFormat.NATIONAL); 
			    
			    StringBuffer nTel = new StringBuffer();
			    nTel.append("(+54) ");
			    String[] particionadoTel = nroTelFormateado.substring(nroTelFormateado.indexOf(" ")+1).split("-");
			    
			    int codArea = Integer.parseInt(nroTelFormateado.substring(0, nroTelFormateado.indexOf(" ")));
			    
			    if( particionadoTel.length == 3 ){
			    	nTel.append("9 ");
			    	nTel.append(codArea);
			    	nTel.append(particionadoTel[1]);
			    	nTel.append("-");
			    	nTel.append(particionadoTel[2]);
			    }
			    else{
			    	nTel.append(codArea);
			    	nTel.append(particionadoTel[0]);
			    	nTel.append("-");
			    	nTel.append(particionadoTel[1]);			    	
			    }

				return nTel.toString();
			} catch (NumberParseException e) {
				throw new Exception("El Nro de Teléfono no es valido. " + e.getMessage());
			}		
		}
		else{
			throw new Exception("El Nro de Teléfono no es valido.");
		}
	}	    
    
}
