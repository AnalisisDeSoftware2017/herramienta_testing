package metricas.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ayuda.Cadenas;
import entidades.Metodo;
import entidades.Nombrable;
import metricas.Metrica;
import metricas.ResultadoMetrica;

public class ComplejidadCiclomatica  implements Metrica, Nombrable {
	
	private Integer complejidad;
	
	public String getNombre() {
		return "Complejidad ciclomática";
	}

	public void calcular(Metodo metodo) {
		List<String> codigo = metodo.getCodigo();
		this.complejidad = 0;
		
		for(String linea : codigo){
			
			linea = Cadenas.normalizar(linea);
			
			complejidad += StringUtils.countMatches(linea, "if (")
						 + StringUtils.countMatches(linea, "while (")
						 + StringUtils.countMatches(linea, "for (")
						 + StringUtils.countMatches(linea, " && ")
						 + StringUtils.countMatches(linea, " || ")
						 + StringUtils.countMatches(linea, " ? ")
						 + StringUtils.countMatches(linea, "case ")
						 + StringUtils.countMatches(linea, "catch (");
		}
		
		this.complejidad += 1;
	}
	
	public ResultadoMetrica obtenerResultado() {
		return new ResultadoMetrica( this.getNombre(), this.complejidad.toString() );
	}

}
