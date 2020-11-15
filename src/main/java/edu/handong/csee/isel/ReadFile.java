package edu.handong.csee.isel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

	private String arffFilePath;
	private String resultFilePath;
	
	public ReadFile(String arffFileName, String resultFileName) {
		arffFilePath = arffFileName;
		resultFilePath = resultFileName;
	}
	
	public ArrayList<String> readArffFile() {
		
		ArrayList<String> lines = new ArrayList<String>();
		String thisLine="";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(arffFilePath));
			while ((thisLine = br.readLine()) != null) {
				
				if(!thisLine.startsWith("@") && !thisLine.trim().equals("") && !thisLine.startsWith("%")) {
					lines.add(thisLine);

				}
			} 
			br.close();
		} 
		catch (IOException e) {
			System.err.println("Error: " + e);
		}	
		
		return lines;
		
		
	}
	
	public ArrayList<String> readResultFile() {
		
		ArrayList<String> lines = new ArrayList<String>();
		String thisLine="";
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(resultFilePath));
			
			while ((thisLine = br.readLine()) != null) {
				lines.add(thisLine);

			}
		}
		catch (IOException e) {
			System.err.println("Error: " + e);
		}
		
		return lines;
		
	}
}
