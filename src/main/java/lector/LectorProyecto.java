package lector;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import entidades.Clase;

public abstract class LectorProyecto {

	private List<Clase> clases;
	
	public LectorProyecto(){
	}
	
	public List<Clase> leerProyecto(File proyecto){
		this.clases = new ArrayList<Clase>();
		this.leer(proyecto);
		return this.clases;
	}

	private void leer(File archivo) {
		
		if(archivo.isDirectory()){
			for ( File archivoDirectorio : archivo.listFiles()){
				this.leer(archivoDirectorio);
			}
		} else if (archivo.getName().endsWith(".java")) {
			leerArchivoJava(archivo, this.clases);
		}
	}

	protected abstract void leerArchivoJava(File archivo, List<Clase> clasesProyecto);
	
}
