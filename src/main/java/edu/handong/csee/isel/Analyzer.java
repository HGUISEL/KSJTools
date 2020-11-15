package edu.handong.csee.isel;

import java.util.ArrayList;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Analyzer {

	private ArrayList<String> originalClassList = new ArrayList<String>();
	
	public Analyzer(String originalFilePath, String resultFilePath, String attributeName, String positiveLabel) throws Exception {
		String[] string;
		DataSource source = new DataSource(originalFilePath);
		Instances instances = source.getDataSet();
		// Instance inst = instances.firstInstance(); 
		// System.out.println(inst.attribute(42)); //attribute index(parameter)에 따라 어떤 attribute인지  
		
		instances.setClassIndex(instances.numAttributes()-1);
		
		System.out.println("class attribute: " + instances.classAttribute()); // class attribute ( ex) @attribute class {buggy,clean} )
		System.out.println("index of class: " + instances.classIndex()); // index is start from 0
		System.out.println("numInstances: " + instances.numInstances());  // number of all instances
		//System.out.println("index of class: " + inst.classIndex()); // class label의 index 
		System.out.println("number of attributes: " + instances.numAttributes()); // number of all attributes
		
		ArrayList<String> lines = new ArrayList<String>();
		
		ReadFile readFile = new ReadFile(originalFilePath);
		lines = readFile.readFile();
		
		for (int j=0;j<lines.size();j++) {
			//System.out.println(lines.get(j));
			string = lines.get(j).split(",");
			//System.out.println(string[inst.classIndex()]);
			originalClassList.add(string[instances.classIndex()]);
		}
		for (int j=0;j<originalClassList.size();j++) {
			System.out.println(j+ " " + originalClassList.get(j));
		}

		
	}
	
	
}
