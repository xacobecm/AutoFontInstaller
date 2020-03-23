package controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import org.json.*;

import model.*;

public class API {
	
	//TODO: Better exception handling
	public ArrayList<Font> getFontList() throws Exception {
		//Font has Variant[] variants. From the JSON we build Font objects and return them in an array to the view to draw them in the JList.
		String resString = "";
		ArrayList<Font> fonts = new ArrayList<>();
		URL url = new URL("https://www.googleapis.com/webfonts/v1/webfonts?key=AIzaSyBKyVoc_pHs8e4GbC3N7_y4D2L3sUr-eYg");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.connect();
		int resCode = con.getResponseCode();
		
		if(resCode == 200) {
			Scanner sc = new Scanner(url.openStream());
			
			//Writing each JSON line to a String
			while( sc.hasNext() ) {
				resString += sc.nextLine();
			}
			
			//Parsing JSON
			JSONObject resJSON = new JSONObject(resString);
			JSONArray fontJSONArray = resJSON.getJSONArray("items");
			for( int i = 0; i < fontJSONArray.length(); i++ ) {
				JSONObject fontJSON = fontJSONArray.getJSONObject(i);
				Font font = new Font(fontJSON.getString("family"), fontJSON.getString("category"));
				JSONObject variantJSON = fontJSON.getJSONObject("files");
				Iterator<String> it = variantJSON.keys();
				
				while ( it.hasNext() ) {
					String key = it.next();
					String value = variantJSON.getString(key);
					Variant variant = new Variant(key, value);
					font.getVariants().add(variant);
				}
				
				fonts.add(font);
			}
			
			sc.close();
			return fonts;
		} else {
			throw new Exception("Error getting from Google API");
		}
	}
	
	public boolean installFont(Variant v) {
		try {
			String[] cmd = { "/bin/sh", "-c", "cd $HOME/.local/share/fonts; wget " + v.getUrl() + "; fc-cache -f -v" };
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}
