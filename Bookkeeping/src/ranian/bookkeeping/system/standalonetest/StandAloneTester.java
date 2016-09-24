package ranian.bookkeeping.system.standalonetest;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StandAloneTester {
	
	private static String encodePassword(String password) {
		
		MessageDigest md = null;
		String resultString = "";
		try {
			
			md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes("UTF-8"));
			byte[] resultBytes = md.digest();
			
			StringBuilder sb = new StringBuilder();
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
	
	public static void main(String[] args) {
		
		System.out.println( StandAloneTester.encodePassword("ranian") );

	}

}
