package controller;

import java.util.ArrayList;

import model.Font;

public class Test {

	public static void main(String[] args) throws Exception {
		API api = new API();
		
		ArrayList<Font> fonts = api.getFontList();
		for(int i = 0; i < fonts.size(); i++) {
			System.out.println(fonts.get(i).getName());
			for(int j = 0; j < fonts.get(i).getVariants().size(); j++) {
				System.out.println("--- " + fonts.get(i).getVariants().get(j).getName() + ": " + fonts.get(i).getVariants().get(j).getUrl());
			}
		}
	}
	
}
