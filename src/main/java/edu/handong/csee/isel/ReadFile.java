package edu.handong.csee.isel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

	private String filePath;
	
	public ReadFile(String fileName) {
		filePath = fileName;
	}
	
	public ArrayList<String> readFile() {
		
		ArrayList<String> originalFile = new ArrayList<String>();
		
		try {
            File file = new File(filePath);

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null) {
            	
            	if (line.startsWith("%") || line.isBlank()) continue;
            	else { 
            		// System.out.println(line);
            		originalFile.add(line);
            	}
            }
        }
        catch (FileNotFoundException e) {
        	System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println(e);
        }
		
		return originalFile;
		
	}
}
