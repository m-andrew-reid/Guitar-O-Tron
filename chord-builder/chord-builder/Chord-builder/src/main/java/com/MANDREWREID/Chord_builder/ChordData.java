package com.MANDREWREID.Chord_builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChordData {

	private static Map<Integer, String> intToNoteMap = new HashMap<Integer, String>();

	private static Map<String, Integer> noteToIntMap = new HashMap<String, Integer>();
	private static Map<String, Integer[]> chordMap = new HashMap<String, Integer[]>();

	public ChordData() {

		intToNoteMap.put(1, "C");
		intToNoteMap.put(2, "C#");
		intToNoteMap.put(3, "D");
		intToNoteMap.put(4, "D#");
		intToNoteMap.put(5, "E");
		intToNoteMap.put(6, "F");
		intToNoteMap.put(7, "F#");
		intToNoteMap.put(8, "G");
		intToNoteMap.put(9, "G#");
		intToNoteMap.put(10, "A");
		intToNoteMap.put(11, "A#");
		intToNoteMap.put(12, "B");

		noteToIntMap.put("C", 1);
		noteToIntMap.put("C#", 2);
		noteToIntMap.put("D", 3);
		noteToIntMap.put("D#", 4);
		noteToIntMap.put("E", 5);
		noteToIntMap.put("F", 6);
		noteToIntMap.put("F#", 7);
		noteToIntMap.put("G", 8);
		noteToIntMap.put("G#", 9);
		noteToIntMap.put("A", 10);
		noteToIntMap.put("A#", 11);
		noteToIntMap.put("B", 12);

		addChord("major", 0, 4, 7);
		addChord ("minor", 0, 3, 7);
		addChord("diminished", 0, 3, 7);
		addChord("major7", 0, 4, 7,11);
		addChord("minor7", 0, 3, 7,10);
		addChord("dominant7", 0, 4, 7,10);
		addChord("sus2", 0, 2, 7);
		addChord("sus4", 0, 5, 7);

	}
	
	//check to see if a string is a valid note
	public boolean checkNote(String suspectString) {
		for(Map.Entry<String, Integer> goodNote : noteToIntMap.entrySet()) {
			if(goodNote.getKey().equals(suspectString)) {
				return true;
			}
		}
	return false;
	}
	
	//check to see if a int is a valid note
		public boolean checkNote(int suspectInt) {
			for(Map.Entry<Integer, String> goodInt : intToNoteMap.entrySet()) {
				if(goodInt.getKey().equals(suspectInt)) {
					return true;
				}
			}
		return false;
		}
	
	public void addChord(String name, int rootNote,int secondNote, int thirdNote) {
		Integer[] noteArrayIntegers = {rootNote, secondNote,thirdNote};
		chordMap.put(name.toLowerCase(), noteArrayIntegers);
	}
	
	public  void  addChord(String name, int rootNote,int secondNote, int thirdNote,int fourthNote) {
		Integer[] noteArrayIntegers = {rootNote, secondNote,thirdNote,fourthNote};
		chordMap.put(name.toLowerCase(), noteArrayIntegers);
	}

	public static Map<Integer, String> getIntToNoteMap() {
		return intToNoteMap;
	}

	public static Map<String, Integer> getNoteToIntMap() {
		return noteToIntMap;
	}



	public  int noteInt(String s) {
		return noteToIntMap.get(s);
	}

	public  String intNote(int i) {
		return intToNoteMap.get(i);
	}
	
	public void printChord (String rootNote, String chordType ) {
		Integer[] chordNotes = chordMap.get(chordType.toLowerCase());
		int rootInt = noteInt(rootNote.toUpperCase());
		ArrayList<String> printNotes = new ArrayList<String>();
		for(Integer i : chordNotes) {
			if(i+rootInt <= 12) {
			printNotes.add(intNote(i+rootInt));
			} else {
				printNotes.add(intNote(i-12+rootInt));
			}
			
		}
		System.out.println(rootNote.toUpperCase() + " " + chordType + printNotes.toString());
		
	}
	
	
	


	


	public  Integer[] getChord(String chordType) {
		return chordMap.get(chordType);
		
	}
	
	public  Map<String, Integer[]> getChordMap() {
		return chordMap;
	}
	

}
