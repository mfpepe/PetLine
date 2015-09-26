package petline.util;

import java.util.Calendar;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

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

		return dist;
	}

	public static double caloriasQuemadas(int peso, Calendar fechaInicio, Calendar fechaFin) {
		//http://www.realfitness.es/calculadoras/aprende-utilizar-tablas-met-calcular-calorias-quemadas/

		long diff = fechaFin.getTimeInMillis() - fechaInicio.getTimeInMillis();

		long diffHours = diff / (60 * 60 * 1000);				
				
		return (((3*0.0175*peso)*60)*diffHours); // Kcal/hs

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
	
}
