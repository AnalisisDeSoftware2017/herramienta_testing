package metricas.impl;

import java.util.List;

import ayuda.Cadenas;
import entidades.Clase;
import entidades.Metodo;
import entidades.Nombrable;
import metricas.Metrica;
import metricas.ResultadoMetrica;

public class FanIn implements Metrica, Nombrable {

	private List<Clase> proyecto;
	private Integer fanIn;
	
	public FanIn (List<Clase> proyecto){
		this.proyecto = proyecto;
	}
	
	public String getNombre() {
		return "Fan In";
	}

	public void calcular(Metodo metodo) {
		this.fanIn = 0;
		for(Clase claseProyecto : this.proyecto){
			for(Metodo metodoClaseProyecto : claseProyecto.getMetodos()){
				this.fanIn += Cadenas.cantidadOcurrenciasMetodo(metodo.getNombre(), metodoClaseProyecto.getCodigo());
			}
		}
	}

	public ResultadoMetrica obtenerResultado() {
		return new ResultadoMetrica( this.getNombre(), this.fanIn.toString() );
	}

}
