package metricas.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import entidades.Metodo;
import entidades.Nombrable;
import metricas.Metrica;
import metricas.ResultadoMetrica;

public class Halstead implements Metrica, Nombrable {
	
    private Integer longitud;
	private Double volumen;
    
	private Integer cantidadOperadoresUnicos = 0;
    private Integer cantidadOperadores = 0;
    private Integer cantidadOperandosUnicos = 0;
    private Integer cantidadOperandos = 0;

    private static final String operadores [] = {"if", "else", "case", "default", "for", "while", "catch", "throw",
			"+", "-", "*", "/", "==", "!=", "=", "<=", ">=", "<", ">",
			"&&", "||", "and", "or", "equal to"};

    //Set que contendra los operadores del codigo fuente
	private Set<String> setOperadores = new HashSet<String>();
	//Set que contendra los operandos del codigo fuente
	private Set<String> setOperandos = new HashSet<String>();

	public String getNombre() {
		return "Halsted";
	}
	
	public void calcular(Metodo metodo) {
		List<String> codigo = metodo.getCodigo();
    	this.longitud = 0;
    	this.volumen = 0.0;
    	    	
        for (String linea : codigo) {            
            this.buscarOperadores(linea);
            this.buscarOperandos(linea);
        }
        
        this.cantidadOperadoresUnicos = this.setOperadores.size();
        this.cantidadOperandosUnicos = this.setOperandos.size();
        
        this.longitud = this.cantidadOperadores + this.cantidadOperandos;
        this.volumen = (this.longitud * (Math.log(this.cantidadOperadoresUnicos.doubleValue() + 
        							  Math.log(this.cantidadOperandosUnicos.doubleValue())) / Math.log(2)));
    }
    
    private void buscarOperadores(String linea) {
    	for(int i = 0; i < operadores.length - 1; i++){
    		if(linea.contains(operadores[i])) {
    			this.cantidadOperadores += StringUtils.countMatches(linea, operadores[i]);
    			this.setOperadores.add(operadores[i]);
    		}
    	}
    }
    
    private void buscarOperandos(String linea) {
    	String operandos[] = linea.split("^.*(if|else|case|default|for|while|catch|throw|\\+|-|\\*|\\/"
    									 + "|={1}?|!=|={2}?|<=|>=|<{1}?|>{1}?|&&|\\|{2}?|and|or|equal to).*");
    	
    	for(int i = 0; i < operandos.length ; i++) {
    		this.cantidadOperandos += 1;
    		this.setOperandos.add(operandos[i]);
    	}
    }

	public ResultadoMetrica obtenerResultado() {
		return new ResultadoMetrica(
				this.getNombre(), 
				String.format(
						"Longitud %d Volumen %.2f n1 %d N1 %d n2 %d N2 %d", 
						this.longitud, this.volumen, this.cantidadOperadoresUnicos, this.cantidadOperadores, this.cantidadOperandosUnicos, this.cantidadOperandos
					)
				);
	}
	
	public static String[] getOperadores(){
		return operadores;
	}
}