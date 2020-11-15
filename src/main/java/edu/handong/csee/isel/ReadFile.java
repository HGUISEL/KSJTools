package edu.handong.csee.isel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

	private String filePath;
	
	public ReadFile(String fileName) {
		filePath = fileName;
	}
	
	public ArrayList<String> readFile() {
		

		ArrayList<String> lines = new ArrayList<String>();
		String thisLine="";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((thisLine = br.readLine()) != null) {
				
				if(!thisLine.startsWith("@") && !thisLine.trim().equals("") && !thisLine.startsWith("%")) {
					lines.add(thisLine);
					//System.out.println(i + " " + lines.get(i++));
				}
			} 
			br.close();
		} 
		catch (IOException e) {
			System.err.println("Error: " + e);
		}	
		
		return lines;
		
		
	}
}
