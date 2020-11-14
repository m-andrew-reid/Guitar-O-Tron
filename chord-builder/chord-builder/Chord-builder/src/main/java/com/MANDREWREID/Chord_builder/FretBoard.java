package com.MANDREWREID.Chord_builder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FretBoard {
	
	private ArrayList<ArrayList<Integer>> fretBoardArrayList = new ArrayList<ArrayList<Integer>>();
	private ChordData chordData = new ChordData();
	
	
		public FretBoard(Tuning tuning) {
			
			for(String openNote : tuning.getOpenNotes()) {
			this.fretBoardArrayList.add(fillString(openNote));
			
		}
}



		public ArrayList<Integer> fillString(String openNote){
			ArrayList<Integer> buildString = new ArrayList<Integer>();
			int noteToInt = ChordData.getNoteToIntMap().get(openNote);
			
			for(int i = 0; i<16;i++) {
				if(noteToInt <=12) {
					buildString.add(noteToInt);
					noteToInt += 1;
				} else {
					noteToInt -= 12;
					buildString.add(noteToInt);
					noteToInt += 1;
					
				}
				
				
			}
			return buildString;
			
		}
		
		public void printTunedString(int stringNumber) {
			
			ArrayList<String> guitarStringPrint = new ArrayList<String>();
			ArrayList<Integer> myString = fretBoardArrayList.get(stringNumber);
			for(int i : myString) {
				guitarStringPrint.add(chordData.intNote(i));
			}
			System.out.println(guitarStringPrint.toString());
		}



		public ArrayList<ArrayList<Integer>> getFretBoardArrayList() {
			return fretBoardArrayList;
		}

		public int getNumberOfGuitarStrings() {
			return fretBoardArrayList.size();
		}
		
}
