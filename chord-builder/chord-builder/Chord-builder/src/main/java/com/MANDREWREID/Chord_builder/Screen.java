package com.MANDREWREID.Chord_builder;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;

public class Screen implements Screenable {

	protected String header;
	protected String title;
	protected String[] body;
	protected String[] selections;
	protected String screenID;

	protected DecimalFormat moneyFormat = new DecimalFormat("'$'0.00");

	public Screen(String header, String title, String[] body, String[] selections, String screenID) {

		this.header = header;
		this.body = body;
		this.title = title;
		this.selections = selections;
		this.screenID = screenID;

	}

	public String screenBehavior() throws IllegalArgumentException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {

		String response;
		int responseInt;
		String returnString;
		Scanner myScanner = new Scanner(System.in);

		response = myScanner.nextLine();
		
		responseInt = Integer.parseInt(response);
		
		
		if (responseInt < 1) {
			System.out.println("invalid response");
			
			screenBehavior();
		}
		
		returnString = selections[responseInt -1];
		
		return returnString;

	}

	public String printScreen() throws IllegalArgumentException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {

		printHeader(header);

		printTitle(title);

		System.out.println();
		printBody(body);

		// Perform the behavior of the screen
		return screenBehavior();

	}

	public String getHeader() {
		return header;
	}

	public String getTitle() {
		return title;
	}

	public String[] getBody() {
		return body;
	}

	public String[] getSelections() {
		return selections;
	}

	public String getScreenID() {
		return screenID;
	}

	public void printHeader(String header) {
		System.out.println(header);
		System.out.println("****************");
		System.out.println();
	}

	public void printTitle(String header) {
		System.out.println(title);
	}

	public void printBody(String[] body) {
		for (String string : body) {
			System.out.println(string);
		}
	}

	public void printSelections(String[] selections) {
		for (String string : selections) {
			System.out.println(string);
		}
	}

}// class
