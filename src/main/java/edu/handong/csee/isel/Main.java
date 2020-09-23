package edu.handong.csee.isel;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {

	public static void main (String[] args) {
        
		Main  runner = new Main();
		runner.runner(args);
		
		/* read file 
		 * try {
            File file = new File("/Users/sujin/Desktop/ISEL/DPstudy/datasets/promise/cm1-0.arff");

            FileReader filereader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (FileNotFoundException e) {

        }
        catch (IOException e) {
            System.out.println(e);
        }*/
    }
	
	void runner (String[] args) {
		
		Options options = createOptions();
		
		
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
		
		options.addOption(Option.builder("p").longOpt("poslabel")
				.desc("String value of bug label")
				.hasArg()
				.argName("positive label name")
				.required()
				.build());
		
		return options;
		
	}
	
}
