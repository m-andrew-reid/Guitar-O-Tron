package com.MANDREWREID.Chord_builder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Screenable {
	
public String screenBehavior() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException; 
		

	
	public String getHeader() ;

	public String getTitle() ;

	public String[] getBody() ;


	public String[] getSelections() ;


	public String getScreenID() ;

	public  void printHeader(String header);
	
	public  void printTitle(String header) ;
	public  void printBody(String[] body);
	
	public  void printSelections(String[] selections);

	



	public String printScreen() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException;
	

}

