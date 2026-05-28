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

	private JFrame frmEquipoPerfecto;
	private JTextField nombreEquipoNuevo;
	private JTextField informacionSolicitada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazPrincipal window = new InterfazPrincipal();
					window.frmEquipoPerfecto.setVisible(true);
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
		frmEquipoPerfecto = new JFrame();
		frmEquipoPerfecto.setTitle("Equipo Perfecto");
		frmEquipoPerfecto.setBounds(100, 100, 787, 606);
		frmEquipoPerfecto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEquipoPerfecto.getContentPane().setLayout(null);
		
		
		
		JButton visualizarDisponibles = new JButton("Crear Equipo");
		visualizarDisponibles.setBounds(588, 129, 133, 45);
		frmEquipoPerfecto.getContentPane().add(visualizarDisponibles);
		
		
		
		JSpinner requerimientoMinimoEquipo = new JSpinner();
		requerimientoMinimoEquipo.setBounds(674, 92, 47, 26);
		frmEquipoPerfecto.getContentPane().add(requerimientoMinimoEquipo);
		
		nombreEquipoNuevo = new JTextField();
		nombreEquipoNuevo.setBounds(635, 53, 86, 20);
		frmEquipoPerfecto.getContentPane().add(nombreEquipoNuevo);
		nombreEquipoNuevo.setColumns(10);
		
		JButton detellesEmpleado = new JButton("Mostrar Detalles");
		detellesEmpleado.setBounds(209, 95, 126, 20);
		frmEquipoPerfecto.getContentPane().add(detellesEmpleado);
		
		
				JComboBox empleadosDisponibles = new JComboBox();
		empleadosDisponibles.setBounds(209, 48, 126, 31);
		frmEquipoPerfecto.getContentPane().add(empleadosDisponibles);
		
		JButton detallesEquipo = new JButton("Mostrar detalles \r\n\tdel equipo");
		
		detallesEquipo.setBounds(10, 278, 178, 45);
		frmEquipoPerfecto.getContentPane().add(detallesEquipo);
		
		informacionSolicitada = new JTextField();
		informacionSolicitada.setBounds(10, 371, 285, 167);
		frmEquipoPerfecto.getContentPane().add(informacionSolicitada);
		informacionSolicitada.setColumns(10);
		
		
		
		
		ArrayList<String> asd = new ArrayList<>();
		asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");
		
		
		DefaultListModel<String> nombre = new DefaultListModel<>();
		for(String s : asd) {
			nombre.addElement(s);
		}
		
		JList<String> listaEquiposCreados = new JList<>(nombre);
		listaEquiposCreados.setBounds(10, 48, 178, 219);
		frmEquipoPerfecto.getContentPane().add(listaEquiposCreados);
		

		
	}
}
