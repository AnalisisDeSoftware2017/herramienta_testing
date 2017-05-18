package ar.edu.unlam.analisis_soft.grupo2.metricas;

import ar.edu.unlam.analisis_soft.grupo2.entidades.Metodo;

public interface Metrica {

	public void calcular(Metodo metodo);
	public ResultadoMetrica obtenerResultado();
	
}
