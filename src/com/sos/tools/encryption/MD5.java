package com.sos.tools.encryption;


import java.io.UnsupportedEncodingException;
import java.security.*;


public class MD5
{
	
	public static String generate(String value) 
	throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		byte[] bytesOfMessage = value.getBytes("UTF32");

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte [] thedigest = md.digest(bytesOfMessage);
		
		StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<thedigest.length;i++) {
    		String hex=Integer.toHexString(0xff & thedigest[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
		
		return hexString.toString();
	}

}
