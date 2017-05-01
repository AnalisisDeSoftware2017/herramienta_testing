package ar.edu.unlam.analisis_soft.herramienta_testing.services;

/**
 * Created by sbogado on 03/05/17.
 */
public class ComentarioService {

	private static ComentarioService instance;


	private ComentarioService(){

	}

	public static ComentarioService getInstance(){
		if(instance == null){
			instance = new ComentarioService();
		}
		return instance;
	}

	public Integer contarCantidadDeComentarios(Metodo metodo) {
		Integer cantComentarios = 0;
		for (int j = 0; j < metodo.getCantidadLineas(); j++){
			cantComentarios+=RegexCheckerService.regexChecker("//",metodo.getDefinicion()[j]);
			cantComentarios+=RegexCheckerService.regexChecker("\\*/",metodo.getDefinicion()[j]);
		}
		return cantComentarios;
	}

}