package algoritmo;

import java.util.ArrayList;

public class Hamming {
	
	ArrayList<ArrayList<String>> matrixHammed = new ArrayList<>();
	public int bitDetected = 0;
	
	public Hamming() {}
	
	public ArrayList<ArrayList<String>> hammingMatrix(String rowToAnalize, String parityType){
		fillFirstRow(rowToAnalize);
		fillParity(parityType);
		fullHammedFill();
		return matrixHammed;
	}
	
	public void fillFirstRow(String binary) {
		ArrayList<String> noParity = new ArrayList<>();
		noParity.add("");
		noParity.add("");
		noParity.add(Character.toString(binary.charAt(0)));
		noParity.add("");
		noParity.add(Character.toString(binary.charAt(1)));
		noParity.add(Character.toString(binary.charAt(2)));
		noParity.add(Character.toString(binary.charAt(3)));
		noParity.add("");
		noParity.add(Character.toString(binary.charAt(4)));
		noParity.add(Character.toString(binary.charAt(5)));
		noParity.add(Character.toString(binary.charAt(6)));
		noParity.add(Character.toString(binary.charAt(7)));
		noParity.add(Character.toString(binary.charAt(8)));
		noParity.add(Character.toString(binary.charAt(9)));
		noParity.add(Character.toString(binary.charAt(10)));
		noParity.add("");
		noParity.add(Character.toString(binary.charAt(11)));
		matrixHammed.add(noParity);
	}
	
	public void fillParity(String parityType) {
		ArrayList<String> newRow;
		int k, parToAnalyze;
		
		for(int i = 0; i < 5; i++) {
			newRow = new ArrayList<>();
			parToAnalyze = (int)Math.pow(2, i);
			k = parToAnalyze - 1;
			
			for(int m = 0; m < k; m++) {
				newRow.add("");
			}
			
			while(k < matrixHammed.get(0).size()) {
				
				//Analyze
				for(int j = 0; j < parToAnalyze; j++) {
					if(j+k >= matrixHammed.get(0).size()) {
						k = matrixHammed.get(0).size();
						break;
					}
					if(matrixHammed.get(0).get(j+k) == "") {
						newRow.add("0");
					}else {
						newRow.add(matrixHammed.get(0).get(j+k));
					}
				}
				k+=parToAnalyze;
				//Jumps
				for(int l = 0; l < parToAnalyze; l++) {
					if(l+k >= matrixHammed.get(0).size()) {
						k = matrixHammed.get(0).size();
						break;
					}
					newRow.add("");
				}
				
				k += parToAnalyze;
			}
			newRow.set(parToAnalyze-1, checkParity(newRow, parityType));
			matrixHammed.add(newRow);
		}
	}
	
	public String checkParity(ArrayList<String> row, String parityType) {
		int ones = 0;
		
		for(int i = 0; i < row.size(); i++) {
			if(row.get(i).equals("1")) {
				ones++;
			}
		}
		
		if( (ones % 2 == 0 && parityType == "Par") || (ones % 2 != 0 && parityType == "Impar") ) {
			return "0";
		}else {
			return "1";
		}
	}
	
	public void fullHammedFill() {
		ArrayList<String> full = new ArrayList<>();
		String value;
		
		for(int i = 0; i < matrixHammed.get(0).size(); i++) {
			value = "";
			int rowCheck = 0;
			while(value.equals("")) {
				value = matrixHammed.get(rowCheck).get(i);
				rowCheck++;
			}
			full.add(value);
		}
		
		matrixHammed.add(full);
	}
	
	public void fillParityCheck(String parityType) {
		ArrayList<String> newRow;
		int k, parToAnalyze;
		
		for(int i = 0; i < 5; i++) {
			newRow = new ArrayList<>();
			parToAnalyze = (int)Math.pow(2, i);
			k = parToAnalyze - 1;
			
			for(int m = 0; m < k; m++) {
				newRow.add("");
			}
			
			while(k < matrixHammed.get(0).size()) {
				
				//Analyze
				for(int j = 0; j < parToAnalyze; j++) {
					if(j+k >= matrixHammed.get(0).size()) {
						k = matrixHammed.get(0).size();
						break;
					}
					if(matrixHammed.get(0).get(j+k) == "") {
						newRow.add("0");
					}else {
						newRow.add(matrixHammed.get(0).get(j+k));
					}
				}
				k+=parToAnalyze;
				//Jumps
				for(int l = 0; l < parToAnalyze; l++) {
					if(l+k >= matrixHammed.get(0).size()) {
						k = matrixHammed.get(0).size();
						break;
					}
					newRow.add("");
				}
				
				k += parToAnalyze;
			}
			String correctParity = getCorrectParity(newRow, parityType);
			
			if(!newRow.get(getFirstNonNull(newRow)).equals(correctParity)) {
				newRow.add("Error");
				bitDetected += parToAnalyze;
			}else {
				newRow.add("Correcto");
			}
			
			newRow.add(correctParity);
			matrixHammed.add(newRow);
		}
	}
	
	public String getCodedChain(ArrayList<String> sepChain) {
		String completeChain = "";
		for(int i =0; i < sepChain.size(); i++) {
			completeChain += sepChain.get(i);
		}
		
		return completeChain;
	}
	
	public ArrayList<ArrayList<String>> verifyCode(String code, String parityType){
		fillFirstRowCheck(code);
		fillParityCheck(parityType);
		return matrixHammed;
	}
	
	public void fillFirstRowCheck(String code) {
		ArrayList<String> incomming = new ArrayList<>();
		for(int i = 0; i < code.length(); i++) {
			incomming.add(Character.toString(code.charAt(i)));
		}
		matrixHammed.add(incomming);
	}
	
	public String getCorrectParity(ArrayList<String> check, String parityType) {
		ArrayList<String> copy = new ArrayList<>();
		for(int i = 0; i < check.size(); i++) {
			copy.add(check.get(i));
		}
		copy.set(getFirstNonNull(copy), "");
		return checkParity(copy, parityType);
	}
	
	public int getFirstNonNull(ArrayList<String> toCheck) {
		int count = 0;
		while(toCheck.get(count).equals("")) {
			count++;
		}
		return count;
	}
}
