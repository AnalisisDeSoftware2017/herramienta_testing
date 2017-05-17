package metricas;

import entidades.Metodo;

public interface Metrica {

	public void calcular(Metodo metodo);
	public ResultadoMetrica obtenerResultado();
	
}
