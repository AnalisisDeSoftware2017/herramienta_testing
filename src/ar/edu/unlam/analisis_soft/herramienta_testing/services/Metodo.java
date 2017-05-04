package ar.edu.unlam.analisis_soft.herramienta_testing.services;
/**
 * Created by sbogado on 03/05/17.
 */
public class Metodo {
	private String nombre;
	private String definicion[];
	private Integer numeroComienzo;
	private Integer cantidadLineas;
	private Integer cantComentarios;
	private Integer fanIn;
	private Integer fanOut;

	public double getPorcentajeComentarios() {
		return Math.floor(cantComentarios/(double)cantidadLineas*100);
	}
	public Integer getFanOut() {
		return fanOut;
	}
	public void setFanOut(Integer fanOut) {
		this.fanOut = fanOut;
	}
	public Integer getFanIn() {
		return fanIn;
	}
	public void setFanIn(Integer fanIn) {
		this.fanIn = fanIn;
	}

	public void setCantComentarios(Integer cantComentarios) {
		this.cantComentarios = cantComentarios;
	}
	public Integer getCantComentarios() {
		return cantComentarios;
	}

	public Metodo(String nombre, Integer numeroComienzo) {
		this.nombre = nombre;
		this.numeroComienzo = numeroComienzo;
	}
	public String getNombre() {
		return nombre;
	}
	public Integer getCantidadLineas() {
		return cantidadLineas;
	}
	public void setCantidadLineas(Integer cantidadLineas) {
		this.cantidadLineas = cantidadLineas;
	}
	public String[] getDefinicion() {
		return definicion;
	}
	public Integer getNumeroComienzo() {
		return numeroComienzo;
	}
	public void setDefinicion(String definicion[]) {
		this.definicion = definicion;
	}
	
}
