package ar.edu.unlam.analisis_soft.herramienta_testing.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TratamientoMetodos {

	private String pathArchivo;
	private int cantidadMetodos=0;
	private static String [] palabrasReservadas={"catch","WindowEvent"};
	private ArrayList<Metodo> metodos = new ArrayList<>();

	private static String encontrarMetodo(String linea){
		Pattern regex = Pattern.compile("^[\\t\\s]*(private|public|void)*(?!.*[^a-z]class)[0-9a-zA-Z ]*\\(([a-zA-Z ]|,|\\[|\\])*\\)\\s?[a-zA-Z ]*\\{?$");
		Matcher m=regex.matcher(linea);
		for (int i = 0; i < palabrasReservadas.length; i++) {
			if(linea.contains(palabrasReservadas[i]) )
				return null;
		}
		if(m.find()){
			return encontrarNombreMetodo(linea);
		}
		return null;
	}

	private static String encontrarNombreMetodo(String linea){
		int indice= linea.indexOf("(");
		String nombre="";
		if(linea.contains(" ("))
			linea=linea.replaceAll(" \\(", "(");
		
		while(indice>0&&linea.charAt(indice)!=' '){
			indice--;
		}
		indice++;
		while(linea.charAt(indice)!='('){
			nombre+=linea.charAt(indice);
			indice++;
		}
		return nombre;	
	}

	public ArrayList<Metodo> getNombres() {
		return metodos;
	}

	public TratamientoMetodos(String path) throws IOException {
		pathArchivo=path;
		File archivo = new File (path);
		FileReader lector = new FileReader(archivo); 
		BufferedReader buffer = new BufferedReader(lector);
		String linea= buffer.readLine();
		int nroLinea=0;
		while(linea!=null){
			String nombre=encontrarMetodo(linea);
			if(nombre!=null){
				metodos.add(new Metodo (nombre,nroLinea));
				cantidadMetodos++;
			}
			linea=buffer.readLine();
			nroLinea++;
		}
		buffer.close();
		lector.close();
		if(cantidadMetodos==0)
			return ;
		for (int i = 0; i < cantidadMetodos; i++)
			if(i+1!=cantidadMetodos){
			metodos.get(i).setCantidadLineas(metodos.get(i+1).getNumeroComienzo()-metodos.get(i).getNumeroComienzo());
			guardarDefinicionDeMetodo(metodos.get(i),metodos.get(i+1).getNumeroComienzo());
		} 
		metodos.get(cantidadMetodos-1).setCantidadLineas(nroLinea-metodos.get(cantidadMetodos-1).getNumeroComienzo()-1);
		guardarDefinicionDeMetodo(metodos.get(cantidadMetodos-1), nroLinea-1);
		calcularCantidadComentarios();
		calcularFanIn();
		calcularFanOut();

	}

	private void guardarDefinicionDeMetodo(Metodo metodo,int limite) throws IOException{
		File archivo = new File (pathArchivo);
		FileReader lector = new FileReader(archivo); 
		BufferedReader buffer = new BufferedReader(lector);
		String linea;
		int definicionlinea=0;
		String definicion[]=new String [metodo.getCantidadLineas()];
		for (int i = 0; i < metodo.getNumeroComienzo(); i++) {
			buffer.readLine();
		}
//		System.out.println("Metodo: "+metodo.getNombre());
		linea=buffer.readLine();
		definicion[definicionlinea]=linea;
		definicionlinea++;
		for(int i=metodo.getNumeroComienzo(); i<limite-1;i++){
//			System.out.println(linea);
			linea=buffer.readLine();
			linea.replaceAll("\\s", " ");
			definicion[definicionlinea]=linea;
			definicionlinea++;
		}
		metodo.setDefinicion(definicion);		
		buffer.close();
	}

	private void calcularCantidadComentarios(){
		for (Metodo metodo : metodos) {
		//	System.out.println("Metodo :"+metodo.getNombre()+", CC: "+cc.getComplejidadCiclomatica());
			metodo.setCantComentarios(ComentarioService.getInstance().contarCantidadDeComentarios(metodo));
		}
	}
	private void calcularFanIn(){
		for (Metodo metodo : metodos) {
			metodo.setFanIn(FanIn.getInstance().calcularFanIn(metodo, metodos));
		}
	}
	private void calcularFanOut(){
		for (Metodo metodo : metodos) {
			metodo.setFanOut(FanOut.getInstance().calcularFanOut(metodo, metodos));
		}
	}

}
