package ar.edu.unlam.analisis_soft.grupo2.metricas;

import ar.edu.unlam.analisis_soft.grupo2.entidades.Nombrable;

public class ResultadoMetrica implements Nombrable {

	private String nombre;
	private String resultado;
	
	public ResultadoMetrica(String nombreMetrica, String resultado) {
		this.nombre = nombreMetrica;
		this.resultado = resultado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
}
