package ar.edu.unlam.analisis_soft.herramienta_testing.services;

import java.util.ArrayList;
/**
 * Created by sbogado on 03/05/17.
 */
public class FanIn {
	private static FanIn instance;

	private FanIn(){
	}

	public static FanIn getInstance() {
		if(instance ==null){
			instance  = new FanIn();
		}
		return instance;
	}

	public Integer calcularFanIn(Metodo metodo, ArrayList<Metodo> metodos) {
		Integer fanIn=0;
		String regex="\\s*\\([a-zA-Z0-9 ,]*\\);";
		for (int j = 0; j < metodo.getCantidadLineas(); j++){
			for (Metodo metodoAux : metodos) {
				if(!metodoAux.getNombre().equals(metodo.getNombre()))
					fanIn+=RegexCheckerService.regexChecker(metodoAux.getNombre()+regex,metodo.getDefinicion()[j]);
			}
		}
		return fanIn;
	}

}