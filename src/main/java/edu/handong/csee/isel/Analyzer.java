package edu.handong.csee.isel;

import java.util.ArrayList;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Analyzer {

	private ArrayList<String> originalClassList = new ArrayList<String>();
	private ArrayList<String> resultClassList = new ArrayList<String>();
	private ArrayList<String> changedClassList = new ArrayList<String>();
	
	public Analyzer(String originalFilePath, String resultFilePath, String attributeName, String positiveLabel) throws Exception {
		String[] string1;
		String[] string2;
		DataSource source = new DataSource(originalFilePath);
		Instances instances = source.getDataSet();
		// Instance inst = instances.firstInstance(); 
		// System.out.println(inst.attribute(42)); //attribute index(parameter)에 따라 어떤 attribute인지  
		
		instances.setClassIndex(instances.numAttributes()-1);
		
		//System.out.println("class attribute: " + instances.classAttribute()); // class attribute ( ex) @attribute class {buggy,clean} )
		//System.out.println("index of class: " + instances.classIndex()); // index is start from 0
		//System.out.println("numInstances: " + instances.numInstances());  // number of all instances
		//System.out.println("index of class: " + inst.classIndex()); // class label의 index 
		//System.out.println("number of attributes: " + instances.numAttributes()); // number of all attributes
		
		ArrayList<String> lines = new ArrayList<String>(); // has list of all original class 
		ArrayList<String> resultLines = new ArrayList<String>(); // has list of all result class 
		
		ReadFile readFile = new ReadFile(originalFilePath, resultFilePath);
		lines = readFile.readArffFile();  // read arff file
		resultLines = readFile.readResultFile(); // read result file
		
		// split instance with comma "," and extract class label in originalClassList
		for (int j=0;j<lines.size();j++) {
			string1 = lines.get(j).split(",");
			originalClassList.add(string1[instances.classIndex()]); // extract class label
			//System.out.println(j+ " " +originalClassList.get(j));
		}
		
		// split result with "]" and extract result in resultClassList
		for (int j=1;j<resultLines.size();j++) {
			string2 = resultLines.get(j).split("]", 2);
			resultClassList.add(string2[1].trim()); // extract class label
		}
		/* for (int j=0;j<resultClassList.size();j++) {
			System.out.println(j+ " " + originalClassList.get(j) + " " + resultClassList.get(j));
		} */
		
		changedClassList = changeLabelName(originalClassList, positiveLabel);
		
		
	}
	
	// buggy와 같이 input으로 받는 positive를 TRUE로, 아닌 것을 FALSE로 
	public ArrayList<String> changeLabelName(ArrayList<String> originalClassList, String positiveName) {
		
		ArrayList<String> changedClassLabelList = new ArrayList<String>();
		
		for (int i=0;i<originalClassList.size();i++) {
			if (positiveName.equals(originalClassList.get(i))) {
				// System.out.println(" pos: "+ originalClassList.get(i));
				changedClassLabelList.add(i, "TRUE");
			}
			else {
				changedClassLabelList.add(i, "FALSE");
			}
		}
		/*
		for (int i=0;i<changedClassLabelList.size();i++) {
			System.out.println(" " + i + " "+ originalClassList.get(i) + " " + changedClassLabelList.get(i));
		}
		*/
		return changedClassLabelList; 
	
	}
	
	
}
