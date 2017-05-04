package ar.edu.unlam.analisis_soft.herramienta_testing.services;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by sbogado on 03/05/17.
 */
public class FanIn {
private int fanIn=0;
	public FanIn(Metodo metodo, ArrayList<Metodo> metodos) {	
		String regex="\\s*\\([a-zA-Z0-9 ,]*\\);";
//		System.out.println("Metodo: " +metodo.getNombre());
		for (int j = 0; j < metodo.getCantidadLineas(); j++){
			for (Metodo metodoAux : metodos) {
				if(!metodoAux.getNombre().equals(metodo.getNombre()))
					fanIn+=regexChecker(metodoAux.getNombre()+regex,metodo.getDefinicion()[j]);		
			}
		}
	}
	public int getfanIn() {
		return fanIn;
	}
	public static int regexChecker(String theRegex, String str2Check){
		        Pattern checkRegex = Pattern.compile(theRegex);
		        Matcher regexMatcher = checkRegex.matcher( str2Check );
		        int cant=0;
		        while ( regexMatcher.find() ){
		            if (regexMatcher.group().length() != 0){	                 
		                cant++;
	//	                System.out.println(str2Check);
		            }
		        }
		        return cant;
		    }
}