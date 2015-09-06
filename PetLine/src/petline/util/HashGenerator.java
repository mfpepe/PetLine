package petline.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
	/**
	 * Convierte la cadena a una cadena sha
	 * 
	 * @param message
	 *            mensaje original
	 * @return cadena sha
	 */
	public static String convert(String message) {
		byte[] data = message.getBytes();
		StringBuffer buffer = new StringBuffer();

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(data);
			byte[] sha = md.digest();

			for (int i = 0; i < sha.length; ++i) {
				int num = sha[i] & 0xff;
				buffer.append(Integer.toHexString(num / 16));
				buffer.append(Integer.toHexString(num % 16));
			}

			return buffer.toString();

		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(HashGenerator.convert("mpepe"));
	}
}
