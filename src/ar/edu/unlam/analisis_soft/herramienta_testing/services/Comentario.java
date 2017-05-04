package ar.edu.unlam.analisis_soft.herramienta_testing.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Created by sbogado on 03/05/17.
 */
public class Comentario {
private int cantComentarios=0;
public Comentario(Metodo metodo) {		
		for (int j = 0; j < metodo.getCantidadLineas(); j++){
			cantComentarios+=regexChecker("//",metodo.getDefinicion()[j]);
			cantComentarios+=regexChecker("\\*/",metodo.getDefinicion()[j]);
		}
	}
	public int getCantComentarios() {
		return cantComentarios;
	}
	public static int regexChecker(String theRegex, String str2Check){
		        Pattern checkRegex = Pattern.compile(theRegex);
		        Matcher regexMatcher = checkRegex.matcher( str2Check );
		        int cant=0;
		        while ( regexMatcher.find() ){
		            if (regexMatcher.group().length() != 0){
		                cant++;
		            }
		        }
		        return cant;
	}

}