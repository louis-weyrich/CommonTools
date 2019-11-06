package com.sos.tools.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashingUtil {

	private static MessageDigest SHA512 = null;
	private static MessageDigest SHA256 = null;
	private static final String HEXES = "0123456789ABCDEF";

	private static HashingUtil instance = null;

	private HashingUtil() {
		try {
			SHA256 = MessageDigest.getInstance("SHA-256");
			SHA512 = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			// TODO: log exception
		}
	}

	private static synchronized HashingUtil getInstance() {
		if (instance == null) {
			instance = new HashingUtil();
		}
		return instance;
	}

	public static String getHashSHA512(String input) {
		return getInstance().getHash(input, SHA512);
	}

	public static String getHashSHA256(String input) {
		return getInstance().getHash(input, SHA256);
	}
	
	public static String getHashSHA512(File input) {
		return getInstance().getHash(input, SHA512);
	}

	public static String getHashSHA256(File input) {
		return getInstance().getHash(input, SHA256);
	}
	
	public static String getHashSHA512(byte[] input) {
		return getInstance().getHash(input, SHA512);
	}

	public static String getHashSHA256(byte[] input) {
		return getInstance().getHash(input, SHA256);
	}	

	private static String getHex(byte[] raw) {
		if (raw == null) {
			return null;
		}
		final StringBuilder hex = new StringBuilder(2 * raw.length);
		for (final byte b : raw) {
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(
					HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}

	private String getHash(String str, MessageDigest digester) {
		String hexString = "";
		try {
			if (str == null || str.length() == 0) {
				throw new IllegalArgumentException(
						"String to encript cannot be null or zero length");
			}

			// Start calculations
			if (digester != null) {
				digester.update(str.getBytes());
				byte[] hash = digester.digest();
				hexString = getHex(hash);
			} else {
				throw new IllegalArgumentException(
						"MessageDigest cannot be null");
			}
		} catch (IllegalArgumentException e) {
			// TODO: log exception
		}
		return hexString;
	}

	private String getHash(File file, MessageDigest digester) {
		String hexString = "";
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int numRead;
			do {
				numRead = fis.read(buffer);
				if (numRead > 0) {
					digester.update(buffer, 0, numRead);
				}
			} while (numRead != -1);
			byte[] hash = digester.digest();
			hexString = getHex(hash);
		} catch (FileNotFoundException e) {
			// TODO: log exception
		} catch (IOException e) {
			// TODO: log exception
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					fis = null;
				}
			}
		}
		return hexString;
	}
	
	private String getHash(byte[] bytes, MessageDigest digester) {
		String hexString = "";
		try {			
			digester.update(bytes);
			byte[] hash = digester.digest();
			hexString = getHex(hash);
			
		} catch (Exception e) {
			// TODO: log exception
		}
		return hexString;
	}

}
