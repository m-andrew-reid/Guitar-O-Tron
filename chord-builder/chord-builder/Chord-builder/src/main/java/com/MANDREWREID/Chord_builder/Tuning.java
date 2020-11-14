package com.MANDREWREID.Chord_builder;

import java.util.ArrayList;

public class Tuning {
	
	
private ArrayList<String>openNotes = new ArrayList<String>();

	
	
	public Tuning(String one, String two, String three, String four, String five, String six) {
		this.openNotes.add(one);
		this.openNotes.add(two);
		this.openNotes.add(three);
		this.openNotes.add(four);
		this.openNotes.add(five);
		this.openNotes.add(six);
		
		}
	
	public Tuning (String[] notes) {
		for(int i = 0;i < notes.length; i ++) {
			this.openNotes.add(notes[i]);
		}
	}
	
	

	public ArrayList<String> getOpenNotes() {
		return openNotes;
	}

	public void setOpenNotes(ArrayList<String> openNotes) {
		this.openNotes = openNotes;
	}
	
	
	
	
	
}
