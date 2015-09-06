package petline.util;

import java.util.Calendar;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PetLineUtils {

	public static String getURL() {
		String url = "";
		try {
			Configuration config = new PropertiesConfiguration("petline/config.properties");
			url = config.getString("URL");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}

		return url;
	}
	
    public static Integer calcularEdad(Calendar fechaNacimiento){
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if(mes<0 || (mes==0 && dia<0)){
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return año;
    }	
	
}
