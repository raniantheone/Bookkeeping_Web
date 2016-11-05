package ranian.bookkeeping.system.standalonetest;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
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
		
		// System.out.println( StandAloneTester.encodePassword("ranian") );
		
		try {
			Class<?> clazz = Class.forName("ranian.bookkeeping.system.standalonetest.TestObjectA");
			
			// 1 str arg constructor
			// Constructor<?> ctor = clazz.getConstructor(String.class);
			// Object testObjectA = ctor.newInstance("Ranian test");
			
			// 1 str arg and int constructor
			// Constructor<?> ctor = clazz.getConstructor(String.class, Integer.class);
			// Object testObjectA = ctor.newInstance("Ranian test", 10);
			
			Constructor<?> ctor = clazz.getConstructor();
			Object testObjectA = ctor.newInstance();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
