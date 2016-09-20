package ranian.bookkeeping.system.utilities;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionUtility {

	public static String encodePassword(String password) {
		
		MessageDigest md = null;
		String resultString = "";
		try {
			
			md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes("UTF-8"));
			byte[] resultBytes = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < resultBytes.length; i++) {				
				String part = String.format("%02X", resultBytes[i]);
				sb.append(part);
			}
			resultString = sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultString;
	}
	
}
