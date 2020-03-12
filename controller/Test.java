package controller;

public class Test {

	public static void main(String[] args) throws Exception {
		API api = new API();
		
		String res = api.getFontList();
		System.out.println(res);
	}
	
}
