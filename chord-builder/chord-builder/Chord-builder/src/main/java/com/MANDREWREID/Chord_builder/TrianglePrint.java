package com.MANDREWREID.Chord_builder;

public class TrianglePrint {
	public static void main(String[] args) {
		String oldString = "Hey fellow warriors";
		

		String[] words = oldString.split(" ");
		int wordCount = words.length;
		String[] newWords = new String[wordCount];
		String word;
		String returnString = "";

		for (int i = 0; i < wordCount; i++) {
			word = words[i];
			String newString = "";
			if (word.length() >= 5) {

				for (int j = word.length() - 1; j >= 0; j--) {
					newString = newString + word.charAt(j);

				}

				if (i != wordCount-1) {
					newWords[i] = newString + " ";
				}
				if (i == wordCount-1) {
					newWords[i] = newString;
				}

			} else {

				if (i != wordCount - 1) {
					newWords[i] = word + " ";
				} else {
					newWords[i] = word;
				}
			}

		}
		
		for(String string: newWords) {
			returnString = returnString + string;
		}
		return returnString;

	}
}
