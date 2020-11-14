package com.MANDREWREID.Chord_builder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ActionScreen extends Screen implements Screenable {
//The PurchaseScreen class is a variety of screens that enable purchasing functions, calling methods within a VendingMachine object
	
	public ActionScreen(String header, String title, String[] body, String[] selections, String screenID ) {
		super(header, title, body, selections, screenID);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String screenBehavior() throws  SecurityException,  IllegalArgumentException,   IOException   {
		
		//Behavior changes depending on which screen is loaded.
		Scanner myScanner = new Scanner(System.in);
		
		
		if (super.getScreenID().equals("chordfinder")) {

			ChordFinder chordFinder = new ChordFinder();
			
				try {
					chordFinder.executeChordFinder();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		
		
		if (super.getScreenID().equals("22")) {
			
			
		}
		
		if (super.getScreenID().equals("23")) {
			
			myScanner.nextLine();
			
		}

		return "main";
	}

	

}// class
