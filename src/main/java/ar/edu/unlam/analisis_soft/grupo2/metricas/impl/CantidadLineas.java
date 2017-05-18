package ar.edu.unlam.analisis_soft.grupo2.metricas.impl;

import java.util.List;

import ar.edu.unlam.analisis_soft.grupo2.entidades.Metodo;
import ar.edu.unlam.analisis_soft.grupo2.entidades.Nombrable;
import ar.edu.unlam.analisis_soft.grupo2.metricas.Metrica;
import ar.edu.unlam.analisis_soft.grupo2.metricas.ResultadoMetrica;

public class CantidadLineas implements Metrica, Nombrable {
	
	private Integer cantidadLineas;
	
	public String getNombre() {
		return "Cantidad de lineas";
	}

	public void calcular(Metodo metodo) {
		List<String> codigo = metodo.getCodigo();
		this.cantidadLineas = codigo.size();
	}

	public ResultadoMetrica obtenerResultado() {
		return new ResultadoMetrica( this.getNombre(), this.cantidadLineas.toString() );
	}
}
