package com.MANDREWREID.Chord_builder;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TuningSet {
	private  Map<String, Tuning> tunings = new HashMap<String, Tuning>();
	
	public TuningSet() {
//		tunings.put("Standard", new Tuning("E", "B", "G", "D", "A", "E"));
//		tunings.put("Drop-D", new Tuning("E", "B", "G", "D", "A", "D"));
//		tunings.put("Baritone-B", new Tuning("B", "F#", "D", "A", "E", "B"));
//		tunings.put("Baritone-A", new Tuning("A", "E", "C", "G", "D", "A"));
		try {
		this.tunings =	generateTunings();
		} catch (FileNotFoundException e) {
			System.out.println("Tuning File Not Found.");
			e.printStackTrace();
		}
	}

	private Map<String, Tuning> generateTunings() throws FileNotFoundException{
		Map<String, Tuning> newTunings = new HashMap<String, Tuning>();
		
		File tuningInfo = new File("tuninginfo");

		Scanner myScanner = new Scanner(tuningInfo);
		if (tuningInfo.exists()) {

			while (myScanner.hasNextLine()) {
				
				String testString = myScanner.nextLine();
				String[] testArray = testString.split("\\|");
				String[] tuningArray = testArray[1].split(";");
				Tuning newTuning = new Tuning(tuningArray);
				newTunings.put(testArray[0],newTuning);
				
			}

		}
		myScanner.close();
	
		
		
		
		return newTunings;
	}
	
	public  Map<String, Tuning> getTunings() {
		return tunings;
	}

	public  void newTuning(String tuningName, Tuning tuning) {
		tunings.put(tuningName,tuning);
	}
	
	










}
