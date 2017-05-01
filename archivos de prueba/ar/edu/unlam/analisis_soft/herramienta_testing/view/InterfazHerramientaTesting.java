package ar.edu.unlam.analisis_soft.herramienta_testing.view;

import ar.edu.unlam.analisis_soft.herramienta_testing.services.Metodo;
import ar.edu.unlam.analisis_soft.herramienta_testing.services.TratamientoMetodos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class InterfazHerramientaTesting extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPaneTesting;
	private JTextField textPath;
	private TratamientoMetodos tMetodos;
	private JFileChooser selector = new JFileChooser();
	private JList <String> listNombres = new JList<String>();
	//private JLabel lblCC;
	private JLabel lblCCInforme;
	private JLabel lblCantidadDeLineas;
	private JLabel lblCantidadComentarios;
	private JLabel lblCantidadComentariosInforme;
	private JLabel lblCantidadLineasInforme;
	private JTextArea textAreaDefinicionMetodo;
	private JPanel panel;
	private JFileChooser selectorArchivo;
	private JScrollPane scrollPaneTextArea;
	private final JButton btnExaminar;
	private String path=null;
	private final JButton btnCalcular;
	private JLabel lblFanInInforme;
	private JLabel lblFanOutInforme;
	private String direccionUsuario = System.getProperty("user.home");
	private JLabel lblPorcentajeDeComentarios;
	//private JLabel lblVolumen;
	//private JLabel lblVolumenInforme= new JLabel("");
	private JSeparator separator_1;
	//private JLabel lblLongitud;
	//private JLabel lblLongitudInforme;
	//private JLabel lblN1;
	//private JLabel lblN1Infome;
//	private JLabel lblN2;
	//private JLabel lblN2Informe;
	//private JLabel lbl_n1;
	//private JLabel lbl_n2;
	//private JLabel lbl_n1Informe;
	//private JLabel lbl_n2Informe;
	private JLabel lblInformePorcentajeComentarios = new JLabel("");
	//private JLabel lblMtodoDeMc;
	public InterfazHerramientaTesting() {
		setResizable(false);
		selector.setDialogTitle("Seleccionar c�digo fuente");
		setTitle("Herramienta Testing");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 300, 884, 92);
		contentPaneTesting = new JPanel();
		contentPaneTesting.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneTesting);
		contentPaneTesting.setLayout(null);
		textPath = new JTextField();
		textPath.setBounds(26, 11, 671, 20);
		contentPaneTesting.add(textPath);
		textPath.setColumns(10);
		btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});;
		btnCalcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(path==null){
					JOptionPane.showMessageDialog(null, "No ha ingresado una ruta de archivo");
					return ;
				}
				try{
					setBounds(250, 100, 861, 600);
					tMetodos= new TratamientoMetodos(path);
					cargarJList(tMetodos.getNombres());
					listNombres.setSelectedIndex(0);
					
				}
				catch(IOException exc){
					JOptionPane.showMessageDialog(null, "Ruta inv�lida");
					return ;
				}
			}
		});
		btnCalcular.setEnabled(false);
		btnCalcular.setBounds(343, 36, 145, 23);
		contentPaneTesting.add(btnCalcular);
		//lblCC = new JLabel("Complejidad Ciclomatica:");
		//lblCC.setBounds(10, 87, 165, 14);
		lblCantidadComentarios= new JLabel("Cantidad de Comentarios:");
		lblCantidadComentarios.setBounds(10, 220, 150, 14);
		lblCantidadComentariosInforme = new JLabel("");
		lblCantidadComentariosInforme.setBounds(170, 220, 43, 14);
		contentPaneTesting.add(lblCantidadComentarios);
		contentPaneTesting.add(lblCantidadComentariosInforme);
		//contentPaneTesting.add(lblCC);
		lblCCInforme = new JLabel("");
		lblCCInforme.setBounds(159, 87, 38, 14);
		contentPaneTesting.add(lblCCInforme);
		panel = new JPanel();
		panel.setBounds(454, 70, 381, 213);
		contentPaneTesting.add(panel);
		panel.setLayout(null);
		listNombres.setBounds(0, 0, 381, 213);
		panel.add(listNombres);
		/**lblLongitud = new JLabel("Longitud:");
		lblLongitud.setBounds(222, 325, 75, 14);
		contentPaneTesting.add(lblLongitud);
		
		lblLongitudInforme = new JLabel("");
		lblLongitudInforme.setBounds(289, 325, 89, 14);
		contentPaneTesting.add(lblLongitudInforme);*/
		
		/**lblN1 = new JLabel("Ocurrencia de operadores:");
		lblN1.setBounds(0, 300, 159, 14);
		contentPaneTesting.add(lblN1);*/
		
		/**lblN1Infome = new JLabel("");
		lblN1Infome.setBounds(159, 300, 70, 14);
		contentPaneTesting.add(lblN1Infome);*/
		/**
		lblN2 = new JLabel("Ocurrencia de operandos:");
		lblN2.setBounds(222, 300, 156, 14);
		contentPaneTesting.add(lblN2);*/
		
		/**lblN2Informe = new JLabel("");
		lblN2Informe.setBounds(388, 300, 51, 14);
		contentPaneTesting.add(lblN2Informe);*/
		
		/**lbl_n1 = new JLabel("Operadores \u00FAnicos: ");
		lbl_n1.setBounds(450, 300, 124, 14);
		contentPaneTesting.add(lbl_n1);*/
		
		/**lbl_n2 = new JLabel("Operandos \u00FAnicos:");
		lbl_n2.setBounds(683, 300, 113, 14);
		contentPaneTesting.add(lbl_n2);
		
		lbl_n1Informe = new JLabel("");
		lbl_n1Informe.setBounds(584, 300, 62, 14);
		contentPaneTesting.add(lbl_n1Informe);*/
		
		/**lbl_n2Informe = new JLabel("");
		lbl_n2Informe.setBounds(806, 300, 62, 14);
		contentPaneTesting.add(lbl_n2Informe);*/
		listNombres.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String valorSeleccionado=listNombres.getSelectedValue();
				textAreaDefinicionMetodo.setText("");
				for (Metodo metodo : tMetodos.getNombres()) {
					if(valorSeleccionado != null && metodo != null &&valorSeleccionado.equals(metodo.getNombre())){
						for (int i = 0; i < metodo.getDefinicion().length; i++) {
							textAreaDefinicionMetodo.setText(textAreaDefinicionMetodo.getText()+"\n"+metodo.getDefinicion()[i]);
						}

						lblCantidadLineasInforme.setText(""+metodo.getCantidadLineas());
						lblCantidadComentariosInforme.setText(""+metodo.getCantComentarios());
						lblFanInInforme.setText(""+metodo.getFanIn());
						lblFanOutInforme.setText(""+metodo.getFanOut());
						textAreaDefinicionMetodo.setCaretPosition(0);
						lblInformePorcentajeComentarios.setText(""+metodo.getPorcentajeComentarios());
						break;
					}
				}
					
			}
		});
		lblCantidadDeLineas = new JLabel("Cantidad de l\u00EDneas:");
		lblCantidadDeLineas.setBounds(201, 87, 145, 14);
		contentPaneTesting.add(lblCantidadDeLineas);
		lblCantidadLineasInforme = new JLabel("");
		lblCantidadLineasInforme.setBounds(320, 87, 124, 14);
		contentPaneTesting.add(lblCantidadLineasInforme);
		btnExaminar = new JButton("Examinar");
		btnExaminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectorArchivo = new JFileChooser(direccionUsuario+"/Desktop");
				selectorArchivo.setDialogTitle("Seleccione el c�digo fuente a evaluar.");
				selectorArchivo.setBounds(100,100,HEIGHT, WIDTH);
				int opcion = selectorArchivo.showOpenDialog(btnExaminar);
				 if (opcion == JFileChooser.APPROVE_OPTION){ 
					 path = selectorArchivo.getSelectedFile().getPath();
					 if(!aceptarArchivo(".java",path)){
						 JOptionPane.showMessageDialog(null, "El formato del archivo no es valido. Por favor seleccione un archivo .java");
						 return;
					 }
					 textPath.setText(path);
                     btnCalcular.setEnabled(true);
				 }
			}
		});
		btnExaminar.setBounds(712, 10, 89, 23);
		contentPaneTesting.add(btnExaminar);
		JLabel lblFanin = new JLabel("Fan In:");
		lblFanin.setBounds(10, 151, 80, 14);
		contentPaneTesting.add(lblFanin);
		lblFanInInforme = new JLabel("");
		lblFanInInforme.setBounds(66, 151, 38, 14);
		contentPaneTesting.add(lblFanInInforme);
		JLabel lblFanOut = new JLabel("Fan Out:");
		lblFanOut.setBounds(213, 151, 70, 14);
		contentPaneTesting.add(lblFanOut);
		lblFanOutInforme = new JLabel("");
		lblFanOutInforme.setBounds(276, 151, 70, 14);
		contentPaneTesting.add(lblFanOutInforme);
		textAreaDefinicionMetodo = new JTextArea();
		textAreaDefinicionMetodo.setBackground(Color.BLACK);
		textAreaDefinicionMetodo.setForeground(Color.WHITE);
		textAreaDefinicionMetodo.setEnabled(false);
		textAreaDefinicionMetodo.setBounds(10, 309, 838, 170);
		scrollPaneTextArea = new JScrollPane(textAreaDefinicionMetodo);
		scrollPaneTextArea.setBounds(24, 350, 811, 227);
		scrollPaneTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
		contentPaneTesting.add(scrollPaneTextArea);
		scrollPaneTextArea.setEnabled(false);
		scrollPaneTextArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		lblPorcentajeDeComentarios = new JLabel("Porcentaje de Comentarios:");
		lblPorcentajeDeComentarios.setBounds(213, 220, 176, 14);
		contentPaneTesting.add(lblPorcentajeDeComentarios);
		lblInformePorcentajeComentarios.setBounds(382, 220, 75, 14);
		contentPaneTesting.add(lblInformePorcentajeComentarios);
		/**lblVolumen = new JLabel("Volumen:");
		lblVolumen.setBounds(517, 325, 73, 14);
		contentPaneTesting.add(lblVolumen);
		lblVolumenInforme.setBounds(584, 325, 57, 14);
		contentPaneTesting.add(lblVolumenInforme);*/
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(0, 254, 444, 14);
		contentPaneTesting.add(separator3);
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 189, 444, 45);
		contentPaneTesting.add(separator);
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 112, 444, 45);
		contentPaneTesting.add(separator_1);
		
		//JLabel lblMetodoDeHalstead = new JLabel("M\u00C9TODO DE HALSTEAD");
		/**lblMetodoDeHalstead.setForeground(Color.BLUE);
		lblMetodoDeHalstead.setBounds(302, 269, 149, 14);
		contentPaneTesting.add(lblMetodoDeHalstead);*/
		
		/**lblMtodoDeMc = new JLabel("M\u00C9TODO DE MC CABE");
		lblMtodoDeMc.setForeground(Color.BLUE);
		lblMtodoDeMc.setBounds(10, 70, 149, 14);
		contentPaneTesting.add(lblMtodoDeMc);*/
		cargarJList(new ArrayList<Metodo>());
	}
	public boolean aceptarArchivo(String extension, String nombre) {
        return nombre.toLowerCase().endsWith(extension);
    }
	private void cargarJList(ArrayList<Metodo> lista){
		DefaultListModel<String> modelo=new DefaultListModel<String>();;
		for (Metodo metodo: lista) {
			modelo.addElement(metodo.getNombre());
		}
		listNombres.setModel(modelo);
	}
}
