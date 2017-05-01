package leer;

public class Leer {
	
	/*
	 * COMO FUNCIONA LA HERRAMIENTA DE TESTING:
	 *1.Se va a Archivo, Buscar Archivo, se selecciona archivo .java con la clase a analizar. El path se guarda en un jtext.
	 *2.Se selecciona el boton de Confirmar Path. Se crea un objeto tipo ClaseAnalyzer con el path del jtext.
	 *2.1 El constructor del objeto ClaseAnalyzer lee el archivo, separa los metodos del codigo, y con cada uno crea objetos tipo MetodoAnalyzer, que los coloca en un array de MetodoAnalyzer en el objeto ClaseAnalyzer.
	 *2.2 Cada constructor de los MetodoAnalyzer calcula la complejidad, lineas de codigo, comentarios y Halstead y de la primera linea de su codigo, extrae el nombre del metodo.
	 *2.3 El constructor del objeto ClaseAnalyzer recorre los elementos del Array tipo MetodoAnalyzer, y con los nombres de los metodos, calcula los fan in y fan out.
	 *3.Se muestran en la interfaz los nombres de los MetodoAnalyzer para que el usuario seleccione de que metodo quiere ver sus datos.
	 *4.Se selecciona de la lista, y con el indice se busca en el array del objeto ClaseAnalyzer el metodo correspondiente. Con sus getters se muestran los datos calculados en su constructor.
	 *5.Fin.
	 * 
	 * 
	 * 
	 * Clases:
	 * ClaseAnalyzer, que contiene los MetodoAnalyzer.
	 * MetodoAnalyzer, que tiene el codigo de cada metodo del archivo seleccionado, y donde se calcula la complejidad y las lineas de codigo.
	 * Pantalla, donde el usuario usa el programa.
	 * 
	 * Otras clases:
	 * MyTests, para probar los metodos más facil.
	 * Main, que quedó de cuando el programa no tenía interfaz.
	 * 
	 * QUE FALTA:
	 * Hacer que el Constructor de ClaseAnalyzer lea el .java, y separe los codigos de cada metodo para poder construir los metodos automaticamente.
	 * Hacer que los MetodoAnalyzer calculen los Halstead.
	 * 
	 */

}
