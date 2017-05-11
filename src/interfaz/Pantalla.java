package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import logica.ClaseAnalyzer;

public class Pantalla {

	private JFrame frame;
	private JTextField tComentarios;
	private JTextField tPorcentaje;
	private JTextField tComplejidad;
	private JTextField tFanIn;
	private JTextField tFanOut;
	private JTextField tLongitud;
	private JTextField tVolumen;
	private JTextField tLineas;
	private JLabel lblNewLabel;
	private JLabel lblLneasDeCdigo;
	private JLabel lblPorcentajeDeComentarios;
	private JLabel lblComplejidadCiclomtica;
	private JLabel lblFanIn;
	private JLabel lblFanOut;
	private JLabel lblHalsteadLongitud;
	private JLabel lblHalsteadVolumen;
	private JButton btnNewButton;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel_1;
	private JLabel lblSeleccioneMtodo;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JTextField tPath;
	private JTextArea tCodigo;
	private JList<String> tMetodos;

	private ClaseAnalyzer clase;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla window = new Pantalla();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
				
		
	}

	/**
	 * Create the application.
	 */
	public Pantalla() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				cerrar();				
			}
		});
		frame.setBounds(100, 100, 538, 601);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Herramienta de Testing by Grupo 2");
		
		
		
		JLabel label1 = new JLabel("C\u00F3digo Analizado:");
		label1.setBounds(21, 300, 244, 14);
		frame.getContentPane().add(label1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(21, 325, 476, 189);
		frame.getContentPane().add(scrollPane);
		
		tCodigo = new JTextArea();
		scrollPane.setViewportView(tCodigo);
		
		tCodigo.setEditable(false);
		
		btnNewButton = new JButton("ANALIZAR");
		btnNewButton.addActionListener(new ActionListener() {
			/**
			 * Si hay path en el jtext y un metodo seleccionado en el jlist, se usa el indice del metodo seleccionado para buscar sus datos mediante getters.
			 */
			public void actionPerformed(ActionEvent e) {
							
				int index  = tMetodos.getSelectedIndex();
				
				if(index != -1){
					
					tCodigo.setText("");
					
					tLineas.setText(""+ clase.getMetodo(index).getCantLineas());
					tComentarios.setText(""+ clase.getMetodo(index).getCantLineasComentario());
					
					String aux = Double.toString(clase.getMetodo(index).getPorcentaje());
					
					if( aux.length()>5 )
						tPorcentaje.setText("" + aux.substring(0, 5) + "%");
					else
						tPorcentaje.setText("" + aux+ "%");
					
					tComplejidad.setText(""+ (clase.getMetodo(index).getComplejidad()+1));
					
					tFanIn.setText(""+ clase.getMetodo(index).getFanin());
					tFanOut.setText(""+ clase.getMetodo(index).getFanout());
					tLongitud.setText(""+ clase.getMetodo(index).getHalsteadLongitud());
					
					aux = Double.toString(clase.getMetodo(index).getHalsteadVolumen());
					
					if( aux.length()>8 )
						tVolumen.setText(""+ aux.substring(0,8));
					else
						tVolumen.setText(""+ aux);
					
					for(String linea: clase.getMetodo(index).getCodigo()){
						if(linea.length()>0){
							tCodigo.append("\n"+linea.substring(1));
							tCodigo.setCaretPosition(0);
						}
						else{
							tCodigo.append("\n"+linea);
							tCodigo.setCaretPosition(0);
						}
					}
						
				}
			}
		});
		
		btnNewButton.setBounds(21, 266, 476, 23);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 76, 176, 179);
		frame.getContentPane().add(scrollPane_1);
		
		tMetodos = new JList<String>();
		scrollPane_1.setViewportView(tMetodos);
		
		lblNewLabel_1 = new JLabel("Herramienta de Testing v1.0 by Grupo 2 | 2017");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setBounds(21, 525, 629, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblSeleccioneMtodo = new JLabel("Seleccione M\u00E9todo:");
		lblSeleccioneMtodo.setBounds(20, 61, 200, 14);
		frame.getContentPane().add(lblSeleccioneMtodo);
		
		tPath = new JTextField();
		tPath.setEditable(false);
		tPath.setText("Seleccione un Archivo desde el Men\u00FA Archivo para poder Analizar el c\u00F3digo.");
		tPath.setBounds(21, 30, 476, 20);
		frame.getContentPane().add(tPath);
		tPath.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Path de Archivo Seleccionado:");
		lblNewLabel_2.setBounds(21, 11, 216, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel = new JLabel("Lineas de C\u00F3digo Totales");
		lblNewLabel.setBounds(224, 64, 155, 14);
		frame.getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		tLineas = new JTextField();
		tLineas.setBounds(411, 61, 86, 20);
		frame.getContentPane().add(tLineas);
		tLineas.setEditable(false);
		tLineas.setColumns(10);
		
		lblLneasDeCdigo = new JLabel("L\u00EDneas de Comentario");
		lblLneasDeCdigo.setBounds(224, 89, 155, 14);
		frame.getContentPane().add(lblLneasDeCdigo);
		lblLneasDeCdigo.setHorizontalAlignment(SwingConstants.LEFT);
		
		tComentarios = new JTextField();
		tComentarios.setBounds(411, 86, 86, 20);
		frame.getContentPane().add(tComentarios);
		tComentarios.setEditable(false);
		tComentarios.setColumns(10);
		
		lblPorcentajeDeComentarios = new JLabel("Porcentaje de Comentarios");
		lblPorcentajeDeComentarios.setBounds(224, 114, 155, 14);
		frame.getContentPane().add(lblPorcentajeDeComentarios);
		lblPorcentajeDeComentarios.setHorizontalAlignment(SwingConstants.LEFT);
		
		tPorcentaje = new JTextField();
		tPorcentaje.setBounds(411, 111, 86, 20);
		frame.getContentPane().add(tPorcentaje);
		tPorcentaje.setEditable(false);
		tPorcentaje.setColumns(10);
		
		lblComplejidadCiclomtica = new JLabel("Complejidad Ciclom\u00E1tica");
		lblComplejidadCiclomtica.setBounds(224, 139, 155, 14);
		frame.getContentPane().add(lblComplejidadCiclomtica);
		lblComplejidadCiclomtica.setHorizontalAlignment(SwingConstants.LEFT);
		
		tComplejidad = new JTextField();
		tComplejidad.setBounds(411, 136, 86, 20);
		frame.getContentPane().add(tComplejidad);
		tComplejidad.setEditable(false);
		tComplejidad.setColumns(10);
		
		lblFanIn = new JLabel("Fan In     ");
		lblFanIn.setBounds(224, 164, 112, 14);
		frame.getContentPane().add(lblFanIn);
		lblFanIn.setHorizontalAlignment(SwingConstants.LEFT);
		
		tFanIn = new JTextField();
		tFanIn.setBounds(411, 161, 86, 20);
		frame.getContentPane().add(tFanIn);
		tFanIn.setEditable(false);
		tFanIn.setColumns(10);
		
		lblFanOut = new JLabel("Fan Out ");
		lblFanOut.setBounds(224, 189, 111, 14);
		frame.getContentPane().add(lblFanOut);
		lblFanOut.setHorizontalAlignment(SwingConstants.LEFT);
		
		tFanOut = new JTextField();
		tFanOut.setBounds(411, 186, 86, 20);
		frame.getContentPane().add(tFanOut);
		tFanOut.setEditable(false);
		tFanOut.setColumns(10);
		
		lblHalsteadLongitud = new JLabel("Halstead Longitud");
		lblHalsteadLongitud.setBounds(224, 214, 139, 14);
		frame.getContentPane().add(lblHalsteadLongitud);
		lblHalsteadLongitud.setHorizontalAlignment(SwingConstants.LEFT);
		
		tLongitud = new JTextField();
		tLongitud.setBounds(411, 211, 86, 20);
		frame.getContentPane().add(tLongitud);
		tLongitud.setEditable(false);
		tLongitud.setColumns(10);
		
		lblHalsteadVolumen = new JLabel("Halstead Volumen");
		lblHalsteadVolumen.setBounds(224, 239, 139, 14);
		frame.getContentPane().add(lblHalsteadVolumen);
		lblHalsteadVolumen.setHorizontalAlignment(SwingConstants.LEFT);
		
		tVolumen = new JTextField();
		tVolumen.setBounds(411, 236, 86, 20);
		frame.getContentPane().add(tVolumen);
		tVolumen.setEditable(false);
		tVolumen.setColumns(10);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Buscar Archivo");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(frame);

				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					
					if( selectedFile.getAbsolutePath().contains(".java") ){
						
						tPath.setText(selectedFile.getAbsolutePath());
						clase = new ClaseAnalyzer(tPath.getText());
						tMetodos.setListData(clase.obtenerNombreMetodos());
						
					}else{
						tPath.setText("El archivo seleccionado debe tener extensi�n .java");
					}	
				}
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);		
		mntmNewMenuItem_1 = new JMenuItem("Salir");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cerrar();
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);		
	}
	
	private void cerrar(){
		int confirmed = JOptionPane.showConfirmDialog(null, "�Seguro desea Salir?", "Advertencia:", JOptionPane.YES_NO_OPTION);

	    if (confirmed == JOptionPane.YES_OPTION)
		      frame.dispose();
	}
}
