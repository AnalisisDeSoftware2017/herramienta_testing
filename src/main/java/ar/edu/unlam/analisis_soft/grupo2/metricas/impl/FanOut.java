package ar.edu.unlam.analisis_soft.grupo2.metricas.impl;

import java.util.List;

import ar.edu.unlam.analisis_soft.grupo2.ayuda.Cadenas;
import ar.edu.unlam.analisis_soft.grupo2.entidades.Clase;
import ar.edu.unlam.analisis_soft.grupo2.entidades.Metodo;
import ar.edu.unlam.analisis_soft.grupo2.entidades.Nombrable;
import ar.edu.unlam.analisis_soft.grupo2.metricas.Metrica;
import ar.edu.unlam.analisis_soft.grupo2.metricas.ResultadoMetrica;

public class FanOut implements Metrica, Nombrable {

	private List<Clase> proyecto;
	private Integer fanOut;
	
	public FanOut (List<Clase> proyecto){
		this.proyecto = proyecto;
	}
	
	public String getNombre() {
		return "Fan Out";
	}

	public void calcular(Metodo metodo) {
		this.fanOut = 0;
		for(Clase claseProyecto : this.proyecto){
			for(Metodo metodoClaseProyecto : claseProyecto.getMetodos()){
				this.fanOut += Cadenas.cantidadOcurrenciasMetodo(metodoClaseProyecto.getNombre(), metodo.getCodigo());
			}
		}
	}

	public ResultadoMetrica obtenerResultado() {
		return new ResultadoMetrica( this.getNombre(), this.fanOut.toString() );
	}

}
