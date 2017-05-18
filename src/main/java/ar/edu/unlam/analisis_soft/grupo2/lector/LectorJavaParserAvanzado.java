package ar.edu.unlam.analisis_soft.grupo2.lector;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;

import ar.edu.unlam.analisis_soft.grupo2.entidades.Clase;
import ar.edu.unlam.analisis_soft.grupo2.entidades.Metodo;

public class LectorJavaParserAvanzado extends LectorProyecto {

	@Override
	protected void leerArchivoJava(File archivo, List<Clase> clasesProyecto) {
		
		try {
			CompilationUnit compilationUnit = JavaParser.parse(archivo);
			
	        for (TypeDeclaration tipo : compilationUnit.getTypes()) {
	        	
        		//Si encuentro una clase leo sus metodos o clases internas
        		if ( tipo instanceof ClassOrInterfaceDeclaration ){
        			
        			ClassOrInterfaceDeclaration claseOInterfaz = (ClassOrInterfaceDeclaration) tipo;
        			
        			//Me aseguro que no sea una ar.edu.unlam.analisis_soft.grupo2.interfaz (no se pueden calcular todas las ar.edu.unlam.analisis_soft.grupo2.metricas)
        			if (! claseOInterfaz.isInterface() ){
        				
	        			Clase clase = new Clase( claseOInterfaz.getName(), new ArrayList<Metodo>() );
	        			
		        		this.leerMetodosYClasesInternas(
		        				clase, 
		        				claseOInterfaz.getMembers(), 
		        				clasesProyecto
	        				);
		        		
		        		clasesProyecto.add(clase);
        			}
        		}
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void leerMetodosYClasesInternas(Clase nuevaClase, List<BodyDeclaration> miembros, List<Clase> clasesProyecto) {
		
		for (BodyDeclaration miembro : miembros ) {
        	
			//Si encuentro una clase interna proceso recursivamente
            if (miembro instanceof ClassOrInterfaceDeclaration) {
            	
            	ClassOrInterfaceDeclaration internalClassOrInterface = (ClassOrInterfaceDeclaration) miembro;
            	
            	//Me aseguro que no sea una ar.edu.unlam.analisis_soft.grupo2.interfaz (no se pueden calcular todas las ar.edu.unlam.analisis_soft.grupo2.metricas)
            	if (! internalClassOrInterface.isInterface()){
            		
            		ClassOrInterfaceDeclaration internalClass = internalClassOrInterface;
            		
	            	Clase claseInterna = new Clase( internalClass.getName(), new ArrayList<Metodo>() );
	            	
	            	this.leerMetodosYClasesInternas( 
	            			claseInterna, 
	            			internalClass.getMembers(), 
	            			clasesProyecto
	        			);
	            	
	            	clasesProyecto.add(claseInterna);
            	}
            	
            //Si encuentro un metodo lo agrego a la nueva clase
            } else if (miembro instanceof MethodDeclaration) {
            	
            	MethodDeclaration metodo = (MethodDeclaration) miembro;
            	
            	nuevaClase.getMetodos().add(
						new Metodo(
								metodo.getName(),
								nuevaClase,
								Arrays.asList(
										metodo.getBody()==null ? 
												new String[0] : metodo.getBody().toString().split("\n")
								)
						)
				);
            	
            //Si encuentro un constructor lo agrego a la nueva clase como metodo
	        } else if (miembro instanceof ConstructorDeclaration) {
	        	
	        	ConstructorDeclaration constructor = (ConstructorDeclaration) miembro;
	        	
	        	String codigoConstructor = constructor.getBlock().toString();
	        	
	        	if(codigoConstructor != null && !codigoConstructor.isEmpty()){
		        	nuevaClase.getMetodos().add(
							new Metodo(
									constructor.getName().concat(" (Constructor)"),
									nuevaClase,
									Arrays.asList( codigoConstructor.split("\n") )
							)
					);
	        	}
	        }
        }
		
	}
}