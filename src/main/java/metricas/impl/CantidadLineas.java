package metricas.impl;

import java.util.List;

import entidades.Metodo;
import entidades.Nombrable;
import metricas.Metrica;
import metricas.ResultadoMetrica;

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
