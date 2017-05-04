package ar.edu.unlam.analisis_soft.herramienta_testing.services;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by sbogado on 03/05/17.
 */
public class FanOut {
private int fanOut=0;
	public FanOut(Metodo metodo,ArrayList<Metodo> metodos) {
		String nuevo;
		for (Metodo metodoAux : metodos) {
			for (int j = 0; j < metodoAux.getCantidadLineas(); j++){
				nuevo=BorrarComillas(metodoAux.getDefinicion()[j], "\"");
				fanOut+=regexChecker("[^a-z]"+metodo.getNombre()+"\\s*\\(",nuevo);	
			}
		}
		fanOut--;	
	}
	public int getfanOut() {
		return fanOut;
	}
	public static int regexChecker(String theRegex, String str2Check){
	   Pattern checkRegex = Pattern.compile(theRegex);
	   Matcher regexMatcher = checkRegex.matcher( str2Check );
	   int cant=0;
	        while(regexMatcher.find()){
	            if (regexMatcher.group().length() != 0){
	                cant++;
		            }
		        }
		        return cant;	
		    }
	private static String BorrarComillas(String linea, String condicion){
		int pos1=0;
		int pos2=0;
		int cant=0;
		String linea2=linea;
		String linea3=linea;
		  while (linea.indexOf(condicion) > -1) {
			  cant++;
			  if(cant==1){
		        	pos1=linea.indexOf(condicion);
		        }
		        else
		        	pos2=linea.indexOf(condicion)+pos1+2;
		        linea = linea.substring(linea.indexOf(condicion)+condicion.length(),linea.length());	
		  }
		if(pos1!=0){
			linea=linea2.substring(0,pos1);
			linea3=linea3.substring(pos2,linea3.length());
			String resultado = linea+linea3;
			return resultado;
		}
		else
		return linea;
	}
}