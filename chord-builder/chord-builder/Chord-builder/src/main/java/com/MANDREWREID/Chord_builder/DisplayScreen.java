package com.MANDREWREID.Chord_builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import java.lang.reflect.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DisplayScreen extends Screen implements Screenable {
//The display screen displays the inventory of the vending machine

	public DisplayScreen(String header, String title, String[] body, String[] selections, String screenID) {
		super(header, title, body, selections, screenID);

	}

	@Override
	public String screenBehavior() throws SecurityException, IllegalArgumentException {
		if (screenID.equals("chordinfo")) {
			
			
			displayChordExamples();

			System.out.println("Press Enter to Return to the previous screen.");
			
			Scanner myScanner = new Scanner(System.in);
				myScanner.nextLine();
			
			
			
			return "chordmain";
		}

		return "main";

	}// screenBehavior

	public void displayChordExamples() {
		ChordData chordData = new ChordData();

		for (Entry<String, Integer[]> chord : chordData.getChordMap().entrySet()) {
			Integer[] noteInts = chord.getValue();
			String printChord = new String();
			
			for(Integer noteInt : noteInts) {
				printChord += chordData.intNote(noteInt + 1) + " ";
			}
			
			System.out.println("C" + chord.getKey() + " { " + printChord + "}");
		}
	}

}// class
