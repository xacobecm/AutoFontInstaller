package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import model.*;

public class API {
	
	//API KEY:  https://www.googleapis.com/webfonts/v1/webfonts?key=AIzaSyBKyVoc_pHs8e4GbC3N7_y4D2L3sUr-eYg
	//TODO: Better exception handling
	public String getFontList() throws Exception {
		//Font has Variant[] variants. From the JSON we build Font objects and return them in an array to the view to draw them in the JList.
		String resString = "";
		URL url = new URL("https://www.googleapis.com/webfonts/v1/webfonts?key=AIzaSyBKyVoc_pHs8e4GbC3N7_y4D2L3sUr-eYg");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.connect();
		int resCode = con.getResponseCode();
		if(resCode == 200) {
			Scanner sc = new Scanner(url.openStream());
			
			while( sc.hasNext() ) {
				resString += sc.nextLine() + "\n";
			}
		} else {
			throw new Exception("Error getting from Google API");
		}
		
		return resString;
	}
	
}
