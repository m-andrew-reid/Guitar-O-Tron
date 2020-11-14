package com.MANDREWREID.Chord_builder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

public class ChordFinder {

	ChordData chordData = new ChordData();
	TuningSet tuningSet = new TuningSet();
	String chordType = new String();
	String rootNote = new String();
	int fret;
	int stretch;
	boolean inversionAllowed;

	static FretBoard fretBoard;

	public ChordFinder() {
	}

	public ChordFinder(String tuning) {
		// TODO ask user for tuning
		fretBoard = new FretBoard(tuningSet.getTunings().get(tuning));

	}

	// ***********EXECUTE CHORD FINDER
	// TODO add custom tuning
	public void executeChordFinder() throws IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// heading
		System.out.println("Chord Finder Tool");
		System.out.println("*****************");
		System.out.println();

		// tuning selection
		fretBoard = selectTuning();

		// if fretboard generation fails, try again
		if (fretBoard == null) {
			System.out.println("There was a problem creating the fretboard.");
			fretBoard = selectTuning();
		}

		chordType = selectChordType();
		rootNote = selectRootNote();
		fret = selectFret();
		stretch = selectStretch();
		inversionAllowed = selectInversion();

		findChord(rootNote, chordType, fret, stretch, inversionAllowed);
		System.out.println();
		System.out.println();

		Scanner myScanner = new Scanner(System.in);
		System.out.println("Find another chord? (Y)es or (N)o to return to main menu.");
		String response = myScanner.nextLine();
		if (response.toUpperCase().equals("Y")) {
			extraChordFinder();

		}
		System.out.println("Returning to main menu.");
	}

	// ****EXTRA CHORDFINDER
	// Use this method for subsequent calls of chordfinder
	public String extraChordFinder() {
		chordType = selectChordType();
		rootNote = selectRootNote();
		fret = selectFret();
		stretch = selectStretch();
		inversionAllowed = selectInversion();

		findChord(rootNote, chordType, fret, stretch, inversionAllowed);
		System.out.println();
		System.out.println();

		Scanner myScanner = new Scanner(System.in);
		System.out.println("Find another chord? (Y)es or (N)o to return to main menu.");
		String response = myScanner.nextLine();
		if (response.toUpperCase().equals("Y")) {
			return extraChordFinder();

		}
		return "main";

	}

	// *******SELECT STRETCH

	public boolean selectInversion() {
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Allow Inversions (y)es or (n)o?");
		System.out.println("Allowing inversions may create a chord that does not have the root note");
		System.out.println("as the lowest note.");
		String input = myScanner.nextLine().toUpperCase();
		if (input.equals("Y")) {
			
			return true;
		}

		if (input.equals("N")) {
			
			return false;
		} else {

			
		return	selectInversion();
		}
		
	}

	public int selectStretch() {
		int input;
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Enter how many frets you are willing to stretch to make a chord. (0-5)");
		input = myScanner.nextInt();
		if (input < 0 || input > 5) {
			System.out.println("Invalid distance. Please enter a valid distance.");
			selectFret();
		}
		
		return input;
	}

	// *******SELECT FRET
	public int selectFret() {
		int input;
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Enter the fret you wish to start from (0-16):");
		input = myScanner.nextInt();
		if (input < 0 || input > 16) {
			System.out.println("Invalid fret. Please enter a valid fret.");
			
			selectFret();
		}
		
		return input;
	}

	// *********SELECT ROOT NOTE
	public String selectRootNote() {
		Scanner myScanner = new Scanner(System.in);
		String rootNote;
		System.out.println("Type in the name of the chord's root note.");
		System.out.println("Sharps only. Examples:  C  C# c c#");
		rootNote = myScanner.nextLine().toUpperCase();

		if (!chordData.checkNote(rootNote)) {
			System.out.println("Not a valid note.");
			
			selectRootNote();
		}
		
		return rootNote;
	}

	// ******SELECT CHORD TYPE
	public String selectChordType() {
		int input;
		int selection = 1;
		int selectionCount = chordData.getChordMap().size();
		ArrayList<String> chordTypes = new ArrayList<String>();
		Scanner myScanner = new Scanner(System.in);
		System.out.println("Select Chord type");
		for (Map.Entry<String, Integer[]> chordType : chordData.getChordMap().entrySet()) {
			System.out.println(selection + ") " + chordType.getKey());
			chordTypes.add(chordType.getKey());
			selection += 1;
		}
		try {
		 input = Integer.parseInt(myScanner.nextLine());
		
		}catch (Exception e) {
			System.out.println("Not a valid selection. Select a chord type.");
			return selectChordType();
		}

		if (input > selectionCount) {
			System.out.println("Not a valid selection. Select a chord type.");
			
			return selectChordType();
		}
		
		return chordTypes.get(input - 1);
	}

	/// ************SELECT TUNING
	// create the Fretboard by having the user select a tuning
	public FretBoard selectTuning() throws IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println("Select a tuning.");
		Scanner myScanner = new Scanner(System.in);
		ArrayList<String> tuningNames = new ArrayList<String>();
		int selection = 1;
		int selectionCount = tuningSet.getTunings().size();
		System.out.println(selectionCount + "selections");
		int input = 0;

		for (Map.Entry<String, Tuning> tuning : tuningSet.getTunings().entrySet()) {
			System.out.println(selection + ") " + tuning.getKey());
			tuningNames.add(tuning.getKey());
			selection += 1;
		}
		System.out.println(selection + ") Return to previous screen.");
		// get selection from user
		input = myScanner.nextInt();

		// If the user enters a number higher than there are selections, return to the
		// previous screen
		if (input > selectionCount) {
			ScreenManager screenManager = new ScreenManager();
			screenManager.loadScreen("chordmain");

		}

		fretBoard = new FretBoard(tuningSet.getTunings().get(tuningNames.get(input - 1)));

		return fretBoard;
	}

	// ************FIND CHORD
	// *****Find and display the chord that has been built
	public void findChord(String rootNote, String chordType, int fretPosition, int maxStretch, boolean inversionAllowed) {

		ArrayList<Integer> fretMarks = new ArrayList<Integer>();
		ArrayList<Integer> chordNotesInts = generateChord(chordData, tuningSet, fretBoard, rootNote, chordType);
		
		
		for (ArrayList<Integer> guitarString : fretBoard.getFretBoardArrayList()) {
			int currentFret = -1;
			
			for (int i = fretPosition; i <= fretPosition + maxStretch; i++) {

				for (Integer noteInteger : chordNotesInts) {

					// set the open string first. it will remain open if no other
					// notes are found
					if (noteInteger.equals(guitarString.get(0)) && currentFret == -1) {
						currentFret = 0;
						
						
						
					}

					if (noteInteger.equals(guitarString.get(i))) {
						currentFret = i;

					}

				}

			}
			fretMarks.add(currentFret);
		}
		
		if(inversionAllowed == false) {
		fretMarks =	checkInversion(fretMarks);
			 }
		
		System.out.println(fretMarks.toString());

		// display the created fingering
		displayFingering(fretMarks, fretBoard);
	}

	
	//******* CHECK FOR INVERSION
	public ArrayList<Integer>checkInversion(ArrayList<Integer> fretMarks){
		boolean rootMatched = false;
		int root = chordData.noteInt(rootNote);
		
		for (int i = fretMarks.size() - 1 ;i >=0 ; i-- ) {
			
			if(rootMatched == false) {
				
				
				ArrayList<Integer> currentString = fretBoard.getFretBoardArrayList().get(i);
				int fretPosition = fretMarks.get(i);
				int fretNote = currentString.get(fretPosition);
			
				
				if(fretNote == root) {
					rootMatched = true;
					
					
				} else if (currentString.get(0) == root) {
					fretMarks.set(i, 0);
					rootMatched = true;
					
				} else {
					
					
					fretMarks.set(i, -1);
				}
			}
		}
		
		
		
		return fretMarks;
	}
	
	
	
	
	// ********GENERATE CHORD

	public ArrayList<Integer> generateChord(ChordData chordData, TuningSet tuningSet, FretBoard fretBoard,
			String rootNote, String chordType) {

		Integer[] chordSemiIntegers = chordData.getChordMap().get(chordType);
		int rootInt = chordData.noteInt(rootNote.toUpperCase());
		ArrayList<Integer> chordInts = new ArrayList<Integer>();
		ArrayList<String> chordNotes = new ArrayList<String>();

		for (Integer i : chordSemiIntegers) {
			if (i + rootInt <= 12) {
				chordInts.add(i + rootInt);
				chordNotes.add(chordData.intNote(i + rootInt));
			} else {
				chordInts.add(i - 12 + rootInt);
				chordNotes.add(chordData.intNote(i - 12 + rootInt));
			}

		}
		System.out.println(rootNote + chordType + " chord notes " + chordNotes.toString());
		return chordInts;

	}

	// *******DISPLAY FINGERING
	public void displayFingering(ArrayList<Integer> fretMarks, FretBoard fretBoard) {

		ChordData chordData = new ChordData();
		printFretNumbers(fretBoard);

		System.out.println();
		for (int i = 0; i < fretBoard.getNumberOfGuitarStrings(); i++) {
			ArrayList<Integer> guitarString = fretBoard.getFretBoardArrayList().get(i);
			String stringRootNote = chordData.intNote(guitarString.get(0));
			if (stringRootNote.length() == 1) {
				System.out.print(stringRootNote + " ]");
			}

			if (stringRootNote.length() == 2) {
				System.out.print(stringRootNote + "]");
			}

			for (int j = 0; j < guitarString.size(); j++) {
				String note = chordData.intNote(guitarString.get(j));

				// handle muted strings
				if (j == 0 && fretMarks.get(i) == -1) {
					if (note.length() > 1) {
						System.out.print("X" + "  |");
						continue;
					} else {
						System.out.print("X" + "   |");
						continue;
					}
				}

				if (fretMarks.get(i) == j) {
					// open strings
					if (j == 0) {
						if (note.length() > 1) {
							System.out.print(note + " O}");
						} else {
							System.out.print(note + "  O}");
						}
					}
					// fretted strings
					if (j != 0) {
						if (note.length() > 1) {
							System.out.print(note + " +|");
						} else {
							System.out.print(note + "  +|");
						}
					}

				} else
				// unused portion of string
				if (j == 0) {
					System.out.print("---{}");

				} else {
					System.out.print("----|");
				}

			}

			System.out.println();

		}
		printFretNumbers(fretBoard);
	}

	public void printFretNumbers(FretBoard fretBoard) {
		System.out.print("}]");
		for (Integer i = 0; i < fretBoard.getFretBoardArrayList().get(0).size(); i++) {
			if (i.toString().length() > 1) {
				System.out.print("   " + i);
			} else {
				System.out.print("    " + i);
			}
		}
	}

}// end ChordFinder
