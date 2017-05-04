package ar.edu.unlam.analisis_soft.herramienta_testing.services;
/**
 * Created by sbogado on 03/05/17.
 */
public class Metodo {
	private String nombre;
	private String definicion[];
	private int numeroComienzo;
	private int cantidadLineas;
	private int cantComentarios;
	private int fanIn;
	private int fanOut;

	public double getPorcentajeComentarios() {
		return Math.floor(cantComentarios/(double)cantidadLineas*100);
	}
	public int getFanOut() {
		return fanOut;
	}
	public void setFanOut(int fanOut) {
		this.fanOut = fanOut;
	}
	public int getFanIn() {
		return fanIn;
	}
	public void setFanIn(int fanIn) {
		this.fanIn = fanIn;
	}

	public void setCantComentarios(int cantComentarios) {
		this.cantComentarios = cantComentarios;
	}
	public int getCantComentarios() {
		return cantComentarios;
	}

	public Metodo(String nombre, int numeroComienzo) {
		this.nombre = nombre;
		this.numeroComienzo = numeroComienzo;
	}
	public String getNombre() {
		return nombre;
	}
	public int getCantidadLineas() {
		return cantidadLineas;
	}
	public void setCantidadLineas(int cantidadLineas) {
		this.cantidadLineas = cantidadLineas;
	}
	public String[] getDefinicion() {
		return definicion;
	}
	public int getNumeroComienzo() {
		return numeroComienzo;
	}
	public void setDefinicion(String definicion[]) {
		this.definicion = definicion;
	}
	
}
