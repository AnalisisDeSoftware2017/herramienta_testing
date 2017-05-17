package entidades;

import java.util.List;

public class Metodo implements Nombrable {

	private String nombre;
	private Clase clase;
	private List<String> codigo;

	@SuppressWarnings("unused")
	private Metodo(){
	}
	
	public Metodo(String nombre, Clase clase, List<String> codigo) {
		super();
		this.nombre = nombre;
		this.clase = clase;
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	public List<String> getCodigo() {
		return codigo;
	}
	public void setCodigo(List<String> codigo) {
		this.codigo = codigo;
	}
	
}
