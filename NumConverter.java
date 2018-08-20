package algoritmo;

import java.util.HashMap;
import java.util.Map;

public class NumConverter {
	Map<String,String> hexInBin = new HashMap<String, String>();
	Map<String,String> decimalBin = new HashMap<String, String>();
	public NumConverter() {
		hexInBin.put("0", "0000");
		hexInBin.put("1", "0001");
		hexInBin.put("2", "0010");
		hexInBin.put("3", "0011");
		hexInBin.put("4", "0100");
		hexInBin.put("5", "0101");
		hexInBin.put("6", "0110");
		hexInBin.put("7", "0111");
		hexInBin.put("8", "1000");
		hexInBin.put("9", "1001");
		hexInBin.put("A", "1010");
		hexInBin.put("B", "1011");
		hexInBin.put("C", "1100");
		hexInBin.put("D", "1101");
		hexInBin.put("E", "1110");
		hexInBin.put("F", "1111");
		decimalBin.put("0", "0000");
		decimalBin.put("1", "0001");
		decimalBin.put("2", "0010");
		decimalBin.put("3", "0011");
		decimalBin.put("4", "0100");
		decimalBin.put("5", "0101");
		decimalBin.put("6", "0110");
		decimalBin.put("7", "0111");
		decimalBin.put("8", "1000");
		decimalBin.put("9", "1001");
	}
	
	public String covertFromHexToBin(String hexNumber) {
		String binToReturn = "";
		hexNumber = hexNumber.toUpperCase();
		
		if(hexNumber.length() != 3) {
			return "Su número no es de tres dígitos";
		}
		for(int i = 0; i < hexNumber.length(); i++) {
			if(hexInBin.get(Character.toString(hexNumber.charAt(i))) == null) {
				return "Error su número no es hexadecimal";
			}
			binToReturn += hexInBin.get(Character.toString(hexNumber.charAt(i)));
		}
		return binToReturn;
		
	}
	
	public int anyToDecimal(int base, String number) {
		int convertedNumber = 0;
		int position = 0;
		
		if(base == 16) {
			number = new NumConverter().covertFromHexToBin(number);
			base = 2;
		}
		
		for(int i = number.length() - 1; i >= 0; i--) {
			if(Character.getNumericValue(number.charAt(i)) != 0 && Character.getNumericValue(number.charAt(i)) != 1) {
				return -1;
			}
			convertedNumber += Character.getNumericValue(number.charAt(i))*Math.pow(base, position);
			position++;
		}
		
		return convertedNumber;
	}
	public String decimalToBCD(String decimal) {
		String BCDstr = "";
		
		for(int i = 0; i < decimal.length(); i++) {
			BCDstr += decimalBin.get(Character.toString(decimal.charAt(i)));
		}
		return BCDstr;
		
	}
}
