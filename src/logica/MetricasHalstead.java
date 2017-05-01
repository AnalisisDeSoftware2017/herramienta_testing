package logica;

import java.util.HashSet;
import java.util.List;
import java.util.Set; 


public class MetricasHalstead{ 

	private Integer longitudHalstead; 
	private Double volumenHalstead; 

	private Integer cantidadOperadoresUnicos = 0; 
	private Integer cantidadOperadores = 0; 
	private Integer cantidadOperandosUnicos = 0; 
	private Integer cantidadOperandos = 0; 
	private String operadores [] = {"if", "else", "case", "default", "for", "while", "catch", "throw","+", "-", "*", "/", "==", "!=", "=", "<=", ">=", "<", ">","&&", "||", "and", "or", "equal to"}; 

	/**
	 * Set que contendrá los operadores del código fuente 
	 */ 
	Set<String> setOperadores = new HashSet<String>(); 

	/**
	 * Set que contendrá los operandos del código fuente 
	 */ 
	Set<String> setOperandos = new HashSet<String>(); 

	
	public Integer getLongitudHalstead() {
		return longitudHalstead;
	}

	public Double getVolumenHalstead() {
		return volumenHalstead;
	}

	void buscarOperadores(String linea) { 
		for(int i = 0; i < this.operadores.length - 1; i++) 
			if(linea.contains(this.operadores[i])) { 
				this.cantidadOperadores += 1; 
				this.setOperadores.add(this.operadores[i]); 
			} 
	} 

	void buscarOperandos(String linea) { 
		String operandos[] = linea.split("^.*(if|else|case|default|for|while|catch|throw|\\+|-|\\*|\\/" + "|={1}?|!=|={2}?|<=|>=|<{1}?|>{1}?|&&|\\|{2}?|and|or|equal to).*"); 
		for(int i = 0; i < operandos.length ; i++){ 
			
			this.cantidadOperandos += 1; 
			this.setOperandos.add(operandos[i]); 
		} 
	} 
	
	public void procesar(List<String> lineasArchivo) { 

		this.longitudHalstead = 0; 
		this.volumenHalstead = 0.0; 

		for (String linea : lineasArchivo) { 
			buscarOperadores(linea); 
			buscarOperandos(linea); 
		} 

		this.cantidadOperadoresUnicos = this.setOperadores.size(); 
		this.cantidadOperandosUnicos = this.setOperandos.size(); 

		this.longitudHalstead = this.cantidadOperadores + this.cantidadOperandos; 
		this.volumenHalstead = this.longitudHalstead * (Math.log(this.cantidadOperadoresUnicos + this.cantidadOperandosUnicos)/Math.log(2));
	} 



}