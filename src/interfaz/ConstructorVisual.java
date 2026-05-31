package interfaz;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;
import java.awt.Insets;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

public class ConstructorVisual {
	
	
	JFrame frame;
	JTextField nombreEquipoNuevo;
	JSpinner reqMinimoEquipo;
	JButton crearEquipo, detallesEmpleado, detallesEquipo;
	JComboBox<String> empleadosDisponibles;
	JTextArea informacionSolicitada;
	
	JList<String> listaEquiposCreados;
	
	public ConstructorVisual() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Equipo Perfecto");
		frame.setBounds(100, 100, 787, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ArrayList<String> asd = new ArrayList<>();
		asd.add("hola");asd.add("pene");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");
		DefaultListModel<String> nombre = new DefaultListModel<>();
		for(String s : asd) {
			nombre.addElement(s);
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		JPanel panelDetalles = new JPanel();
		panelDetalles.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDetalles.setBounds(10, 0, 516, 290);
		frame.getContentPane().add(panelDetalles);
		panelDetalles.setLayout(null);
		
		listaEquiposCreados = new JList<>(nombre);
		listaEquiposCreados.setBounds(10, 11, 178, 219);
		panelDetalles.add(listaEquiposCreados);
		
		detallesEquipo = new JButton("Mostrar detalles del equipo");
		detallesEquipo.setBounds(10, 241, 178, 45);
		panelDetalles.add(detallesEquipo);
		
		
		empleadosDisponibles = new JComboBox();
		empleadosDisponibles.setBounds(256, 11, 126, 31);
		panelDetalles.add(empleadosDisponibles);
		
		detallesEmpleado = new JButton("Mostrar Detalles");
		detallesEmpleado.setBounds(256, 53, 126, 20);
		panelDetalles.add(detallesEmpleado);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel panelCreacion = new JPanel();
		panelCreacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCreacion.setBounds(595, 11, 166, 124);
		frame.getContentPane().add(panelCreacion);
		panelCreacion.setLayout(null);
		
	
		crearEquipo = new JButton("Crear Equipo");
		crearEquipo.setBounds(61, 82, 95, 23);
		panelCreacion.add(crearEquipo);
		
		
		// 12 x 5 = 60, maximo de desempeño que puede tener un equipo
		reqMinimoEquipo = new JSpinner();
		reqMinimoEquipo.setBounds(109, 42, 47, 26);
		reqMinimoEquipo.setValue(12);
		panelCreacion.add(reqMinimoEquipo);
		
		
		nombreEquipoNuevo = new JTextField();
		nombreEquipoNuevo.setBounds(70, 11, 86, 20);
		panelCreacion.add(nombreEquipoNuevo);
		nombreEquipoNuevo.setColumns(10);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		informacionSolicitada = new JTextArea();
		informacionSolicitada.setBounds(10, 324, 516, 232);
		frame.getContentPane().add(informacionSolicitada);
		
	}
}
