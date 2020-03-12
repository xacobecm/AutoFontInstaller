package model;

import java.util.ArrayList;

public class Font {

	private String name;
	private String category;		
	ArrayList<Variant> variants = new ArrayList<Variant>();
	
	
	public Font(String name, String category) {
		super();
		this.name = name;
		this.category = category;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public ArrayList<Variant> getVariants() {
		return variants;
	}
	public void setVariants(ArrayList<Variant> variants) {
		this.variants = variants;
	}


	@Override
	public String toString() {
		return name;
	}
	
	
}
