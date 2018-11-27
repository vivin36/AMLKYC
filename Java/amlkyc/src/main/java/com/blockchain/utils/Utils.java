package com.blockchain.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {

	public static String ASCIIToHex(String asciiValue) {
		
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int index = 0; index < chars.length; index++) {
        	
            hex.append(Integer.toHexString((int) chars[index]));
        }

        return hex.toString() + "".join("", Collections.nCopies(32 - (hex.length()/2), "00"));
    }
	
	public static String hexToASCII(String hexValue) {
		
        StringBuilder output = new StringBuilder("");
        
        for (int index = 2; index < hexValue.length(); index += 2) {
        	
            String str = hexValue.substring(index, index + 2);
            output.append((char) Integer.parseInt(str, 16));
        }       
        return output.toString().replaceAll("\\u0000", "");
    }
	
	public static List<String> hexToASCIIElem(String rawHex) {
		
		List<String> elems = new ArrayList<>();
		
		String name = null, chunk = null;
		
		String cleanedPrefix = rawHex.substring(2);
		
		while(cleanedPrefix.length() > 0) {
			chunk = cleanedPrefix.substring(0, 64);
			cleanedPrefix = cleanedPrefix.substring(64, cleanedPrefix.length());
			name = hexToASCII("0x"+chunk);
			if(name.equalsIgnoreCase(""))
				break;
			elems.add(name);
		}
		
		return elems;
	}
}