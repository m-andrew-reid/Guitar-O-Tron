package com.MANDREWREID.Chord_builder;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;



public class ScreenManager {
	ArrayList<Screenable> screens = new ArrayList<Screenable>();

	public ScreenManager() throws FileNotFoundException {
		initializeScreens();
	}

	/*
	 * 001 Main Screen 010 Chords 011 ChordFinder
	 * 
	 * 012 Display Chord information 020 Tunings 021 Show Tunings 022 Create Custom
	 * Tuning
	 * 
	 * 
	 */
	/////// METHODS********************

	

/////******LOAD SCREEN		
	public void loadScreen(String screenID) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		 
		// match a screen with the ID passed to the method
		for (Screenable screen : screens) {
			if (screen.getScreenID().equals(screenID)) {

				// print out the selected screen and move into its functionality, returning an
				// eventual selection
				String selection = screen.printScreen();// 
																			
				if(selection == null) {
					System.out.println("Invalid Selection. Press Enter to proceed.");
					Scanner myScanner = new Scanner(System.in);
					loadScreen(screenID);
				}
				// load the selected screen and start the process over
				String nextScreen = selection;
				
				loadScreen(nextScreen);
				
				

			}
		}
		// if user enters something weird, return to the main screen
		
		

	}

	////// ******INITIALIZE SCREENS
	public void initializeScreens() throws FileNotFoundException {

		File screenInfo = new File("screeninfo.txt");

		Scanner myScanner = new Scanner(screenInfo);
		if (screenInfo.exists()) {

			while (myScanner.hasNextLine()) {

				String testString = myScanner.nextLine();
				String[] testArray = testString.split("\\|");

				if (testArray[5].equals( "screen")) {
					screens.add(new Screen(testArray[1], testArray[2], testArray[3].split(";"), testArray[4].split(";"),
							testArray[0]));

				}

				if (testArray[5].equals( "display")){
					screens.add(new DisplayScreen(testArray[1], testArray[2], testArray[3].split(";"), testArray[4].split(";"),
							testArray[0]));

				}
				
				if (testArray[5].equals( "action")) {
					screens.add(new ActionScreen(testArray[1], testArray[2], testArray[3].split(";"), testArray[4].split(";"),
							testArray[0]));

				}
			}

		}
		myScanner.close();
	}
}// class
