package interfaz;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JSpinner;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class InterfazPrincipal {

	private JFrame frame;
	private JTextField nombreEquipoNuevo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPrincipal window = new InterfazPrincipal();
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
	public InterfazPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 787, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton visualizarDisponibles = new JButton("Crear Equipo");
		visualizarDisponibles.setBounds(588, 318, 133, 45);
		frame.getContentPane().add(visualizarDisponibles);
		
		JList listaEmpleadosDisponibles = new JList();
		listaEmpleadosDisponibles.setBounds(10, 48, 155, 76);
		frame.getContentPane().add(listaEmpleadosDisponibles);
		
		
		
		JSpinner requerimientoMinimoEquipo = new JSpinner();
		requerimientoMinimoEquipo.setBounds(674, 141, 47, 26);
		frame.getContentPane().add(requerimientoMinimoEquipo);
		
		nombreEquipoNuevo = new JTextField();
		nombreEquipoNuevo.setBounds(635, 46, 86, 20);
		frame.getContentPane().add(nombreEquipoNuevo);
		nombreEquipoNuevo.setColumns(10);
		
		JButton detellesEmpleado = new JButton("Mostrar Detalles");
		detellesEmpleado.setBounds(10, 278, 126, 20);
		frame.getContentPane().add(detellesEmpleado);
		
		
		
		
		
		ArrayList<String> asd = new ArrayList<>();
		asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");
		
		
		DefaultListModel<String> nombre = new DefaultListModel<>();
		for(String s : asd) {
			nombre.addElement(s);
		}
		
		JList<String> listaEquiposCreados = new JList<>(nombre);
		listaEquiposCreados.setBounds(239, 48, 155, 219);
		frame.getContentPane().add(listaEquiposCreados);
		
	}
}
