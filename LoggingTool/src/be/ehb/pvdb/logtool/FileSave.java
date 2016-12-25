package be.ehb.pvdb.logtool;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class FileSave implements Listener {
	private Message message;
	@SuppressWarnings("unused")
	private Subject messageData;
	
	public FileSave(Subject messageData) {
		this.messageData = messageData;
		messageData.registerListener(this);
	}
	
	public void doSendMsg(Message message) {
		this.message = message;
		display();
	}
	public void display() {
		System.out.println("filesave: " + message.toString());
		try {
			FileWriter fw = new FileWriter("ehb.txt",true);
			BufferedWriter bw = new BufferedWriter(fw);
			MessageJsonEncoder messageJsonEncoder = new MessageJsonEncoder();
			String messageJsonEncoded = null;
			try {
				messageJsonEncoded = messageJsonEncoder.encode(message);
			}
			//catch (EncodeException e) {
			catch (Exception e) {
				e.printStackTrace();
			}
			bw.write(encrypt(messageJsonEncoded));
			bw.newLine();
			bw.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("error"+e.getMessage());
		}
	}
	public static String encrypt(String Data) throws Exception {
		Key key = new SecretKeySpec(new byte[] { 'E', 'H', 'B', ' ', 'H', 'e', 't', ' ', 'V', 'e', 'r','h', 'a', 'a', 'l','!'}, "AES");
		Cipher c = Cipher.getInstance("AES");
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.encodeBase64String(encVal);
		return encryptedValue;
	}

//	public static String decrypt(String encryptedData) throws Exception {
//		Key key = new SecretKeySpec(new byte[] { 'E', 'H', 'B', ' ', 'H', 'e', 't', ' ', 'V', 'e', 'r','h', 'a', 'a', 'l','!'}, "AES");
//		Cipher c = Cipher.getInstance("AES");
//		c.init(Cipher.DECRYPT_MODE, key);
//		byte[] decordedValue = Base64.decodeBase64(encryptedData);
//		byte[] decValue = c.doFinal(decordedValue);
//		String decryptedValue = new String(decValue);
//		return decryptedValue;
//	}
}
