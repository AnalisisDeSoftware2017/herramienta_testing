package interfaz;

import java.util.List;
import java.util.Scanner;

import entidades.Clase;
import entidades.Metodo;
import entidades.Nombrable;
import metricas.ResultadoMetrica;
import principal.HerramientaTesting;

public class Consola {
	
	private static Scanner teclado = new Scanner(System.in);
	private HerramientaTesting herramienta;
	
	public Consola(HerramientaTesting herramienta) {
		this.herramienta = herramienta;
	}

	public void ejecutar(){
		Boolean continuar = true;
		
		while (continuar){
			
			List<Clase> clasesProyecto = herramienta.getProyecto();
			
			this.enumerar(clasesProyecto);
			
			Clase claseElegida = clasesProyecto.get(Integer.valueOf(teclado.nextLine()));
			
			List<Metodo> metodosClaseElegida = claseElegida.getMetodos();
			this.enumerar(metodosClaseElegida);
			
			Metodo metodoElegido = metodosClaseElegida.get(Integer.valueOf(teclado.nextLine()));
			
			List<ResultadoMetrica> resultados = herramienta.calcularMetricas(metodoElegido);
			for(ResultadoMetrica resultado : resultados){
				System.out.println(
						String.format("%s - %s", 
								resultado.getNombre(),
								resultado.getResultado()
								)
						);
			}
			
			System.out.println("\nContinuar? Y/N: ");
			continuar = teclado.nextLine().toLowerCase().equals("y");
		}
		teclado.close();
	}

	private void enumerar(List<? extends Nombrable> nombrables) {
		System.out.println("\nOpciones: ");
		for(int indice = 0; indice<nombrables.size(); indice++){
			System.out.println(
					String.format(
							"%d - %s", 
							indice, nombrables.get(indice).getNombre()
					)
				);
		}
		System.out.println("\nIngrese numero de opcion: ");
	}
}
