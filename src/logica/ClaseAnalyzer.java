package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ClaseAnalyzer {
	private ArrayList<MetodoAnalyzer> metodos = new ArrayList<MetodoAnalyzer>();
	private String nombreClase;
	private FileReader fr;
	private BufferedReader br;
	
	public String getNombre(){
		return this.nombreClase;
	}
	
	public String[] obtenerNombreMetodos(){
		
		String[]aux=new String[metodos.size()];
		for(int i=0; i<metodos.size(); i++){
			aux[i]=metodos.get(i).getNombre();
		}		
		return aux;
	}
	
	public MetodoAnalyzer getMetodo(int index){
		return this.metodos.get(index);
	}
	
	/**
	 * Extrae lo que esta entre "class" y "{"
	 */
	private String obtenerNombreClase(String linea){
				
		int indexStart = linea.indexOf("class ") + 6;
		int indexEnd = linea.indexOf("{");
		String aux = linea.substring(indexStart, indexEnd);
		int indexAux= aux.indexOf(" ");
		
		if(indexAux != -1 && indexAux < indexEnd)
			this.nombreClase = aux.substring(0, indexAux);
		else
			this.nombreClase = aux;
		
		return this.nombreClase;
	}	
	
	/**
	 * Recibe dirección de .java, y lo parsea por metodos. Una vez finalizado, llama al metodo resolver().
	 */	
	public ClaseAnalyzer(String dir){
		
		try{
			
			fr=new FileReader(dir);
			br=new BufferedReader(fr);
			
			int contMetodo=0;
			
			String linea;
			
			while( (linea=br.readLine()) !=null && !linea.contains("class")){ 
			}
			if( linea != null && linea.contains("class")){
				obtenerNombreClase(linea);
			}
			while( (linea=br.readLine()) !=null && !linea.contains("{")){ 	
			}
			if(linea!= null && linea.contains("{")){
				ArrayList<String> aux = new ArrayList<String>();
				aux.add(linea);
				this.metodos.add(new MetodoAnalyzer(aux));
			}
			
			while( (linea=br.readLine()) != null){
				
				while( linea !=null && !linea.contains("public") && !linea.contains("private") && !linea.contains("protected")){ 
					this.metodos.get(contMetodo).agregarLineaCodigo(linea);
					linea=br.readLine();
				}
				if( linea != null && ( linea.contains("public") || linea.contains("private") || linea.contains("protected"))){ 
					contMetodo++;
					ArrayList<String> aux = new ArrayList<String>();
					aux.add(linea);
					this.metodos.add(new MetodoAnalyzer(aux));				
				}								
			}
			
			this.resolver();			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fr!=null){
					fr.close();
				}
			}catch(Exception e2){
				e2.printStackTrace();
			}
		}				
	}	
		
	/**
	 * Recorrer todos los objetos Metodo de la clase y calcular los FanIn, FanOut, y Halstead.
	 */
	public void resolver(){
		
		for (int i=0; i < this.metodos.size(); i++){
			
			this.metodos.get(i).calcularComplejidadLineasComentario();
			this.metodos.get(i).calcularHalstead();
			
			for(int j=0; j < this.metodos.size(); j++){

				if(i!=j){
					if(this.metodos.get(j).hayCoincidenciasTotal(this.metodos.get(i).getNombre()) ){						
					 
					this.metodos.get(i).addFanIn();
					this.metodos.get(j).addFanOut();

					}
				}
			}
		}		
	}

}
