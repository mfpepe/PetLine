package petline.util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class TokenGenerator {

	public TokenGenerator() {
	}

	private static String generateToken() {

		try {
			Random random = new Random(Calendar.getInstance().getTimeInMillis());
			Thread.sleep(1L);
			String token[] = new String[14];
			List<Integer> tokenPos = new ArrayList<Integer>();
			int numero = random.nextInt(99);
			String t1 = String.valueOf(numero < 10 ? ((Object) ((new StringBuilder("0")).append(numero).toString())) : ((Object) (Integer.valueOf(numero))));
			numero = random.nextInt(99);
			String t2 = String.valueOf(numero < 10 ? ((Object) ((new StringBuilder("0")).append(numero).toString())) : ((Object) (Integer.valueOf(numero))));
			numero = random.nextInt(99);
			String t3 = String.valueOf(numero < 10 ? ((Object) ((new StringBuilder("0")).append(numero).toString())) : ((Object) (Integer.valueOf(numero))));
			numero = random.nextInt(99);
			String t4 = String.valueOf(numero < 10 ? ((Object) ((new StringBuilder("0")).append(numero).toString())) : ((Object) (Integer.valueOf(numero))));
			numero = random.nextInt(99);
			String t5 = String.valueOf(numero < 10 ? ((Object) ((new StringBuilder("0")).append(numero).toString())) : ((Object) (Integer.valueOf(numero))));
			
			for (int i = 1; i <= 6;) {
				int pos = random.nextInt(9);
				if (!tokenPos.contains(Integer.valueOf(pos))) {
					tokenPos.add(Integer.valueOf(pos));
					i++;
				}
			}

			int iValue = 1;
			String tokenEnd1 = "";
			String tokenEnd2 = "";
			String tokenEnd3 = "";
			String tokenEnd4 = "";
			String tokenEnd5 = "";
			String tokenEnd6 = "";
			for (Iterator<Integer> iterator = tokenPos.iterator(); iterator.hasNext();) {
				Integer integer = iterator.next();
				String value = "";
				switch (iValue) {
				case 1: // '\001'
					value = "06";
					tokenEnd1 = integer.toString();
					break;

				case 2: // '\002'
					value = "07";
					tokenEnd2 = integer.toString();
					break;

				case 3: // '\003'
					value = "03";
					tokenEnd3 = integer.toString();
					break;

				case 4: // '\004'
					value = "00";
					tokenEnd4 = integer.toString();
					break;

				case 5: // '\005'
					value = "05";
					tokenEnd5 = integer.toString();
					break;

				case 6: // '\006'
					value = "02";
					tokenEnd6 = integer.toString();
					break;
				}
				token[integer.intValue()] = value;
				iValue++;
			}

			iValue = 1;
			for (int iRandom = 0; iRandom <= 9; iRandom++)
				if (StringUtils.isEmpty(token[iRandom])) {
					String value = "";
					switch (iValue) {
					case 1: // '\001'
						value = (new StringBuilder("0")).append(t1).toString();
						break;

					case 2: // '\002'
						value = (new StringBuilder("0")).append(t2).toString();
						break;

					case 3: // '\003'
						value = (new StringBuilder("0")).append(t3).toString();
						break;

					case 4: // '\004'
						value = (new StringBuilder("0")).append(t4).toString();
						break;
					}
					if (value.length() >= 3)
						value = value.substring(1, 3);
					token[iRandom] = value;
					iValue++;
				}

			String valueEnd = (new StringBuilder("0")).append(t5).toString();
			if (valueEnd.length() >= 3)
				valueEnd = valueEnd.substring(1, 3);
			token[10] = valueEnd;
			token[11] = (new StringBuilder(String.valueOf(tokenEnd1))).append(tokenEnd2).toString();
			token[12] = (new StringBuilder(String.valueOf(tokenEnd3))).append(tokenEnd4).toString();
			token[13] = (new StringBuilder(String.valueOf(tokenEnd5))).append(tokenEnd6).toString();
			String end = "";
			String as[];
			int k = (as = token).length;
			for (int j = 0; j < k; j++) {
				String str = as[j];
				end = (new StringBuilder(String.valueOf(end))).append(str).toString();
			}

			return end;
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String args[]) throws InterruptedException {
		String token = generoToken();
		System.out.println(token);
		System.out.println("es Valido: " + validateToken(token));
	}

	public static String generoToken() {
		Calendar cal = Calendar.getInstance();
		long fechaActual = cal.getTimeInMillis();
		long fechaEncriptada = (fechaActual + 1001L) * 1001L * (long) cal.get(7);
		String tokenFecha = StringUtils.reverse((new StringBuilder(String.valueOf(fechaEncriptada))).toString());
		String token = (new StringBuilder(String.valueOf(cal.get(7)))).append(tokenFecha).append(generateToken()).toString();
		int bloque = token.length() / 5;
		StringBuffer sb = new StringBuffer("");
		int startIndex = 0;
		for (; bloque <= token.length(); bloque += token.length() / 5) {
			sb.append(token.substring(startIndex, bloque)).append("-");
			startIndex = bloque;
		}

		token = sb.toString().endsWith("-") ? sb.toString().substring(0, sb.toString().length() - 1) : sb.toString();
		return token;
	}

	public static boolean validateToken(String securityToken) {
		return estaVigenteElToken(securityToken) && esValidoElBASSToken(securityToken);
	}

	private static boolean estaVigenteElToken(String token) {
		Calendar cal = Calendar.getInstance();
		final int BASS_LENGTH = 28;

		token = token.replaceAll("-", ""); // Quito los guiones
		int dayOfWeek = Integer.parseInt(token.charAt(0) + ""); // Obtengo el dia de la semana en que fue generado el token
		String date = token.substring(1, token.length() - BASS_LENGTH);
		long cc = Long.parseLong(StringUtils.reverse(date));

		cc = cc / dayOfWeek;
		cc = (cc / 1001) - 1001;

		long diff = cal.getTimeInMillis() - cc;
		diff = diff < 0 ? diff * -1 : diff * 1;

		return (diff <= (1000 * 60 * 60)); //TIEMPO EN MILISEGUNDOS QUE DURA LA VALIDEZ DEL TOKEN
	}

	private static boolean esValidoElBASSToken(String token) {
		final int BASS_LENGTH = 28;
		final int BASS_ORDERKEY_LENGTH = 6;
		final int BLOQUES = 11;
		final String KEY = "060703000502";

		token = token.replaceAll("-", "");

		int startIndexBT = token.length() - BASS_LENGTH; // Aca empieza el token completo de BASS con el order Key
		int endIndexBT = token.length() - BASS_ORDERKEY_LENGTH; // Fin de Token de BASS sin el order Key
		String bassToken = token.substring(startIndexBT, endIndexBT); // Token de BASS
		char[] keys = token.substring(endIndexBT).toCharArray(); // Orden de la clave de BASS dentro del Token
		int bloque = (bassToken.length() / BLOQUES);
		String[] bloques = new String[BLOQUES];
		int startIndex = 0, i = 0;

		while (i < bloques.length) {
			bloques[i++] = bassToken.substring(startIndex, bloque);
			startIndex = bloque;
			bloque += (bassToken.length() / BLOQUES);
		}

		StringBuffer sb = new StringBuffer();
		for (i = 0; i < keys.length; i++) {
			sb.append(bloques[Integer.parseInt(keys[i] + "")]);
		}

		return sb.toString().equalsIgnoreCase(KEY);
	}
}
