package ar.edu.unlam.analisis_soft.grupo2.lector;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import ar.edu.unlam.analisis_soft.grupo2.entidades.Clase;
import ar.edu.unlam.analisis_soft.grupo2.entidades.Metodo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LectorJavaParser extends LectorProyecto {

	@Override
	protected void leerArchivoJava(File archivo, List<Clase> clasesProyecto) {
		try {
			CompilationUnit compilationUnit = JavaParser.parse(archivo);
			
			Clase clase = new Clase(archivo.getName().replace(".java", ""), new ArrayList<Metodo>());
			
			VoidVisitorAdapter<Clase> visitadorMetodos = new VoidVisitorAdapter<Clase>() {
				
				@Override
				public void visit(MethodDeclaration metodo, Clase clase) {
					
					clase.getMetodos().add(
							new Metodo(
									metodo.getName(),
									clase,
									Arrays.asList(
											metodo.getBody()==null ? 
													new String[0] : metodo.getBody().toString().split("\n")
									)
							)
					);
				}

				@Override
				public void visit(ConstructorDeclaration constructor, Clase clase) {
		        	
		        	String codigoConstructor = constructor.getBlock().toString();
		        	
		        	if(!codigoConstructor.isEmpty()){
		        		
		        		clase.getMetodos().add(
								new Metodo(
										constructor.getName().concat(" (Constructor)"),
										clase,
										Arrays.asList( codigoConstructor.split("\n") )
								)
						);
		        	}
				}
				
				
			};
			
			visitadorMetodos.visit(compilationUnit, clase);
			
			clasesProyecto.add(clase);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
