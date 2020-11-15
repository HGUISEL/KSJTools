package edu.handong.csee.isel;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {

	String originalFilePath;
	String resultFilePath;
	String attributeName;
	String posLabelName;
	boolean help = false;
	
	public static void main (String[] args) throws Exception {
        
		Main runner = new Main();
		runner.runner(args);
		
    }
	
	void runner (String[] args) throws Exception {
		
		Options options = createOptions();
		
		if (parseOptions(options, args)) {
			
			if (help ) {
				printHelp(options);
			}
			
			Analyzer analyze = new Analyzer(originalFilePath, resultFilePath, attributeName, posLabelName);

			
		}
	
		
		
	}
	
	Options createOptions() {
		
		Options options = new Options();
		
		options.addOption(Option.builder("f").longOpt("arffFile")
				.desc("recieves the original arff file path that has a defect classifier label")
				.hasArg()
				.argName("original arff file")
				.required()
				.build());
		
		options.addOption(Option.builder("r").longOpt("resultFile")
				.desc("recieves the text file that contains result from spectral classifier with R")
				.hasArg()
				.argName("result file")
				.required()
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Help")
				.build());
		
		options.addOption(Option.builder("l").longOpt("label")
				.desc("label (Class attrubite) name")
				.hasArg()
				.argName("attribute name")
				.required()
				.build());
		
		options.addOption(Option.builder("p").longOpt("poslabel")
				.desc("String value of bug label")
				.hasArg()
				.argName("positive label value")
				.required()
				.build());
		
		return options;
		
	}
	
	boolean parseOptions(Options options, String[] args) {
		
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cmd = parser.parse(options, args);
			originalFilePath = cmd.getOptionValue("f");
			resultFilePath = cmd.getOptionValue("r");
			posLabelName = cmd.getOptionValue("p");
			attributeName = cmd.getOptionValue("l");
			help = cmd.hasOption("h");
			
		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		
		return true;
	}

	private void printHelp(Options options) {
		
		HelpFormatter formatter = new HelpFormatter();
		String header = "This is tool for compare the defect result of spectral classifier with the original arff file, so that we can get a defect prediction performance of spectral classifier.";
		String footer = "Please check help page for input arguments.";
		formatter.printHelp("./KSJtools", header, options, footer, true);
		
	}
}
