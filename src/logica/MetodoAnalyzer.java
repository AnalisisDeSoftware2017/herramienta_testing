package logica;

import java.util.ArrayList;
import java.util.List;

public class MetodoAnalyzer {
	
	private int fanin=0; 
	private int fanout=0; 
	private int complejidad=0;
	private int lineas=0;
	private int lineasComentario=0;
	private ArrayList<String> codigo = new ArrayList <String>();
	private String nombreMetodo;
	private Integer longitud; 
	private Double volumen; 
	private List<String> lineasValidas; 
	
	public MetodoAnalyzer(ArrayList <String> cod){	
		
		this.codigo=cod;	
		this.nombreMetodo=obtenerNombreMetodo(this.codigo.get(0));		
		this.lineasValidas = new ArrayList<String>();
	}
	
	public void agregarLineaCodigo(String linea){
		this.codigo.add(linea);
	}
		
	public String obtenerNombreMetodo(String texto){
		
		int indexParentesis = texto.indexOf("(");
		int indexAux=indexParentesis;
		int indexFin=0, indexInicio=0;
		
		
		indexAux--;
		if ( indexAux > 0 && texto.substring(indexAux).startsWith(" ") ){
			
			while( indexAux > 0 && texto.substring(indexAux).startsWith(" ") )
				indexAux--;
			if(indexAux > 0)
				indexFin = indexAux+1;
			while( indexAux > 0 && !texto.substring(indexAux).startsWith(" ") )
				indexAux--;
			if( indexAux > 0)
				indexInicio = indexAux;
			
			return texto.substring(indexInicio+1, indexFin);
			
		}else{
			
			indexFin = indexAux+1;
			while( indexAux > 0 && !texto.substring(indexAux).startsWith(" ") )
				indexAux--;
			if( indexAux > 0)
				indexInicio = indexAux;
			
			return texto.substring(indexInicio+1, indexFin);
		}
	}


	public double getPorcentaje(){
		return (double)lineasComentario*100/(lineas+lineasComentario);
	}
	
	public String getNombre(){
		return this.nombreMetodo;
	}
	
	public ArrayList<String> getCodigo(){
		return this.codigo;
	}
	
	public int getCantLineas(){
		return lineas;
	}
	
	public int getCantLineasComentario(){
		return lineasComentario;
	}
	
	public int getFanin(){
		return fanin;
	}
	
	public int getFanout(){
		return fanout;
	}
	
	public int getComplejidad(){
		return complejidad;
	}
	
	public void addFanIn(){
		this.fanin++;
	}
	
	public void addFanOut(){
		this.fanout++;
	}
	
	/**
	 * Recibe un String (nombre de otro metodo por ejemplo) y en cada linea se fija si aparece. (Para los fan)
	 */
	public boolean hayCoincidenciasTotal(String busq){

		for(int i=1; i<this.codigo.size(); i++){
			
			String code=this.codigo.get(i);		
			
			if(code.contains(busq))
				return true;			
		}
		return false;
	}
	
	
	/**
	 * Cuenta las veces que sale "busq" en "text". Se usa para calcularComplejidad.
	 */	
	public int contarCoinc(String busq, String text){
		
		int index = text.indexOf(busq);
		int count = 0;
		while (index != -1) {
		    count++;
		    text = text.substring(index + 1);
		    index = text.indexOf(busq);
		}	
		return count;
	}
	
	
	/**
	 * CUENTA MAL SI TENGO UN METODO QUE TERMINE CON WHILE( o IF( o FOR( o si tengo mas de un if o while en una linea.
	 */
	public int calcularComplejidad(String linea){
		int cantWIFC=0; 
		int cantCond=0; 
		
		if((cantWIFC=contarCoinc("while(", linea))!=0 || (cantWIFC=contarCoinc("if(", linea))!=0 || (cantWIFC=contarCoinc("for(", linea))!=0 || (cantWIFC=contarCoinc("case ", linea))!=0){
			cantCond+=contarCoinc("&&", linea);
			cantCond+=contarCoinc("||", linea);			
		}
		if(cantCond>0)
			return cantCond+1;
		return cantWIFC;		
	}
	
	
	/**
	 * Del Codigo que ya esta en un Array<String>, calcula la cantidad de lineas validas, cantidad de comentario y la complejidad.
	 */	
	public void calcularComplejidadLineasComentario(){
		
		for(int i=0; i<this.codigo.size();i++){
			
			String linea = this.codigo.get(i);
			
			if(esLineaValida(linea)){
				
				if(!esComentario(linea)){
					this.lineas++;
					this.complejidad+=calcularComplejidad(linea);
					lineasValidas.add(linea);
				}
				else{
					this.lineasComentario++;
				}
			}						
		}	
	}
	
	public void calcularHalstead(){
		MetricasHalstead halstead = new MetricasHalstead();
		
		halstead.procesar(lineasValidas);
		this.longitud = halstead.getLongitudHalstead();
		this.volumen = halstead.getVolumenHalstead();
	}
	
	public boolean esComentario(String linea){
		
		int i=0;
		while(i<linea.length() && (linea.substring(i).startsWith(" ") || linea.substring(i).startsWith("	")))
			i++;
		
		if(i<linea.length())
			if(linea.substring(i).startsWith("*") || linea.substring(i).startsWith("//") || linea.substring(i).startsWith("/*") || linea.substring(i).startsWith("/**"))
				return true;		
		return false;
	}
	
	private boolean esLineaValida(String linea){
		
		for(int index = 1; index < linea.length(); index++){
			if( !linea.substring(index, index + 1).contains(" ") )
				return true;
		}	
		return false;
	}

	public Integer getHalsteadLongitud() {
		return longitud;
	}

	public Double getHalsteadVolumen() {
		return volumen;
	}

}
