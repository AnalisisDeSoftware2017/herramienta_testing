package principal;


import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import entidades.Clase;
import entidades.Metodo;
import interfaz.Consola;
import interfaz.GUI;
import lector.LectorJavaParserAvanzado;
import metricas.Metrica;
import metricas.ResultadoMetrica;
import metricas.impl.CantidadComentarios;
import metricas.impl.CantidadLineas;
import metricas.impl.ComplejidadCiclomatica;
import metricas.impl.FanIn;
import metricas.impl.FanOut;
import metricas.impl.Halstead;

public class HerramientaTesting {

	private List<Clase> proyecto;
	
	public static void main(final String[] args) {
		
		if(args.length==0){
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GUI frame = new GUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else {
			try {
				//Si se pasa un argumento se supone que es el path a analizar 
				//y se ejecuta en interfaz de consola.
				File proyecto = new File(args[0]);
				HerramientaTesting herramienta = new HerramientaTesting(proyecto);
				new Consola(herramienta).ejecutar();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public HerramientaTesting (File rutaProyecto){
		this.proyecto = new LectorJavaParserAvanzado().leerProyecto(rutaProyecto);
	}
	
	public List<ResultadoMetrica> calcularMetricas(Metodo metodo){
		List<ResultadoMetrica> resultados = new ArrayList<ResultadoMetrica>();
		
		List<Metrica> metricas = new ArrayList<Metrica>();
		metricas.add(new ComplejidadCiclomatica());
		metricas.add(new CantidadLineas());
		metricas.add(new CantidadComentarios());
		metricas.add(new Halstead());		
		metricas.add(new FanIn(this.proyecto));
		metricas.add(new FanOut(this.proyecto));
		
		for(Metrica metrica : metricas){
			metrica.calcular(metodo);
			resultados.add(metrica.obtenerResultado());
		}
		return resultados;
	}

	public List<Clase> getProyecto() {
		return proyecto;
	}
	
	public static String[] getOperadoresConsiderados(){
		return Halstead.getOperadores();
	}
	
}
