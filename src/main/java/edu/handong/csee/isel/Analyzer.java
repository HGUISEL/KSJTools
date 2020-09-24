package edu.handong.csee.isel;

import java.util.ArrayList;

public class Analyzer {

	private int attributeNumber = 0;
	private ArrayList<String> originalFile = new ArrayList<String>();
	//private String[] attributeList;
	private ArrayList<String> originalLabelList = new ArrayList<String>();
	
	public Analyzer(String originalFilePath, String resultFilePath, String positiveLabel) {
		/*
		try {
            File file = new File(originalFilePath);

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null) {
            	if (line.startsWith("@attribute")) {
            		attributeNumber++;
            	}
            	
            	if (line.startsWith("%") || line.isBlank()) continue;
            	else { 
            		System.out.println(line);
            		originalFile.add(line);
            		attributeList = line.split(",");
            		//System.out.println(attributeList[attributeNumber-1]);
            		//originalLabelList.add(attributeList[attributeNumber-1]);
            	}
            }
        }
        catch (FileNotFoundException e) {
        	System.out.println("File not found");
        }
        catch (IOException e) {
            System.out.println(e);
        }
        */
		ReadFile readFile = new ReadFile(originalFilePath);
		originalFile = readFile.readFile(); 
		
		for (int i=0;i<originalFile.size();i++) {
			System.out.println(i+ " " + originalFile.get(i));
			
			if (originalFile.get(i).startsWith("@attribute")) {
        		attributeNumber++;
        	}
			
		}
		for (int i=0;i<originalFile.size();i++) {
			
			String[] attributeList = originalFile.get(i).split(","); //split is not work properly..
			//System.out.println(attributeList[0]); 
			originalLabelList.add(attributeList[attributeNumber-1]); // ArrayIndexOutOfBoundsException error
			
		}
		
		System.out.println(attributeNumber + " " + originalLabelList.size());
		for (int i=0;i<originalLabelList.size();i++) {
			System.out.println(i+ " " + originalLabelList.get(i));
		}
	}
	
	
}
