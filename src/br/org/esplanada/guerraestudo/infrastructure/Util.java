package br.org.esplanada.guerraestudo.infrastructure;


public class Util {
	public static String getCompletedString(String str, int maxWidth, char ch){
		if (str == null)
			str = "";
		for(int i = str.length(); i < maxWidth; i++)
			str += ch;
		return str;
	}
}
