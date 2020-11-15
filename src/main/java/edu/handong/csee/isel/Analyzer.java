package edu.handong.csee.isel;

import java.util.ArrayList;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Analyzer {

	private ArrayList<String> originalClassList = new ArrayList<String>();
	private ArrayList<String> resultClassList = new ArrayList<String>();
	private ArrayList<String> changedClassList = new ArrayList<String>();
	private int TP=0;
	private int FP=0;
	private int TN=0;
	private int FN=0;
	
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
		}
		
		// split result with "]" and extract result in resultClassList
		for (int j=1;j<resultLines.size();j++) {
			string2 = resultLines.get(j).split("]", 2);
			resultClassList.add(string2[1].trim()); // extract class label
		}
		/* for (int j=0;j<resultClassList.size();j++) {
			System.out.println(j+ " " + originalClassList.get(j) + " " + resultClassList.get(j));
		} */
		
		setChangedClassList(changeLabelName(originalClassList, positiveLabel));
		
		/*
		for (int j=0;j<resultClassList.size();j++) {
			System.out.println(j+ " " + changedClassList.get(j) + " " + resultClassList.get(j));
		}
		*/
		
	}
	
	// input으로 받는 positive label name을 TRUE로, 아닌 것을 FALSE로 
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
	
	public void calcConfusionMatrix() {
		for (int j=0;j<resultClassList.size();j++) {
			//System.out.println(j+ " " + changedClassList.get(j) + " " + resultClassList.get(j));
			if (changedClassList.get(j).equals("TRUE")) {
				//System.out.println(" this is TRUE(clean) in original");
				if (resultClassList.get(j).equals("TRUE")) {
					TP++;
					System.out.println("Instance " + j+1 + " predicted as, buggy, (Actual class: buggy)");
				}
				else {
					FN++;
					System.out.println("Instance " + j+1 + " predicted as, clean, (Actual class: buggy)"); 
				}
			}
			else if (changedClassList.get(j).equals("FALSE")) {
				//System.out.println(" this is FALSE(buggy) in original");
				if (resultClassList.get(j).equals("TRUE")) {
					FP++;
					System.out.println("Instance " + j+1 + " predicted as, buggy, (Actual class: clean)");
				}
				else {
					TN++;
					System.out.println("Instance " + j+1 + " predicted as, clean, (Actual class: clean)");
				}
			}
		}
		System.out.println("TP: " + TP);
		System.out.println("FP: " + FP);
		System.out.println("TN: " + TN);
		System.out.println("FN: " + FN);
		
		System.out.println("total: " + (TN+FP+FN+TP));
		
	}
	
	public void printEvaluationResult() {
		double precision = (double) TP/(TP+FP);
		double recall = (double) TP/(TP+FN);
		double f1 = (double) (2*(precision*recall)) / (precision+recall);
		System.out.println("precision: " + precision);
		System.out.println("recall: " + recall);
		System.out.println("f1: " + f1);
		
	}

	public ArrayList<String> getChangedClassList() {
		return changedClassList;
	}

	public void setChangedClassList(ArrayList<String> changedClassList) {
		this.changedClassList = changedClassList;
	}
	
}
