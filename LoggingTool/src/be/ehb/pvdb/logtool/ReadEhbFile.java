package be.ehb.pvdb.logtool;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class ReadEhbFile {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss:SS");
		System.out.println(sdf.format(new Date(System.currentTimeMillis())));

		File file = new File("ehb.txt");
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;

		try {
			fis = new FileInputStream(file);

			// Here BufferedInputStream is added for fast reading.
			bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);

			// dis.available() returns 0 if the file does not have more lines.
			while (dis.available() != 0) {

				// this statement reads the line from the file and print it to
				// the console.
				try {
					@SuppressWarnings("deprecation")
					String disRead = dis.readLine();
					String deCrypt = decrypt(disRead);
					System.out.println("encrypt: " + disRead);
					System.out.println("         " + deCrypt);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			// dispose all the resources after using them.
			fis.close();
			bis.close();
			dis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String decrypt(String encryptedData) throws Exception {
		Key key = new SecretKeySpec(new byte[] { 'E', 'H', 'B', ' ', 'H', 'e', 't', ' ', 'V', 'e', 'r','h', 'a', 'a', 'l','!'}, "AES");
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.decodeBase64(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
}
