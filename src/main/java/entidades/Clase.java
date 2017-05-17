package entidades;

import java.util.List;

public class Clase implements Nombrable {
	
	private String nombre;
	private List<Metodo> metodos;
	
	@SuppressWarnings("unused")
	private Clase() {
	}

	public Clase(String nombre, List<Metodo> metodos) {
		super();
		this.nombre = nombre;
		this.metodos = metodos;
	}
	
	public List<Metodo> getMetodos() {
		return metodos;
	}
	public void setMetodos(List<Metodo> metodos) {
		this.metodos = metodos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
