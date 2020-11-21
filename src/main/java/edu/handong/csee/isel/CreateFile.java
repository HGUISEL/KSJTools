package edu.handong.csee.isel;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class CreateFile {
	
	private String originalFile;
	private String fileName;
	
	public CreateFile(String originalFilePath) throws Exception {
		
		this.originalFile = originalFilePath;
		DataSource source = new DataSource(originalFilePath);
		Instances instances = source.getDataSet();
		
		instances.setClassIndex(instances.numAttributes()-1);
		
		int classId=0;
		classId = instances.classIndex()+1;
		String classIds = Integer.toString(classId);
		//System.out.print(classId + " " + classIds);
		
		String[] options = new String[2];
        options[0] = "-R"; 
        options[1] = classIds; // "43"; //
        
		Remove remove = new Remove();                         // new instance of filter
        remove.setOptions(options);                           // set options
        remove.setInputFormat(instances); 
        
		Instances newData2 = Filter.useFilter(instances, remove);   // apply filter
        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData2);
        
        fileName = createFileName();
        
        saver.setFile(new File(fileName));  //"/Users/sujin/Desktop/ISEL/test5-cm1.arff"));
        saver.writeBatch();
	}
	
	public String createFileName() {
		
		String file = null;
		String[] fileName;
		
		fileName = originalFile.split(".arff", 2);
		file = fileName[0].concat("-0.arff");
		//System.out.println("filename: " + file);
		
		return file;
	}
}
