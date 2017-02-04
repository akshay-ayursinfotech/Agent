package com.ayursinfotech.agent.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.DatatypeConverter;

public final class CustomEncrypt {
	private CustomEncrypt() {
	}

	public static String encrypt(String str) {
		byte[] message;
		try {
			message = str.getBytes("UTF-8");
			String encoded = DatatypeConverter.printBase64Binary(message);
			return encoded;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String decrypt(String str) {

		byte[] decoded = DatatypeConverter.parseBase64Binary(str);
		String decodedStr = new String(decoded, StandardCharsets.UTF_8);
		return decodedStr;
	}

	/*
	 * public static void main(String args[]) { System.out.println("aaa"); }
	 */

}
