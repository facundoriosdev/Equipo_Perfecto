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

public class ConstructorVisual {
	
	
	JFrame frame;
	JTextField nombreEquipoNuevo;
	JTextField informacionSolicitada;
	JSpinner reqMinimoEquipo;
	JButton visualizarDisponibles, detallesEmpleado, detallesEquipo;
	JComboBox<String> empleadosDisponibles;
	
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
		asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");asd.add("hola");
		DefaultListModel<String> nombre = new DefaultListModel<>();
		for(String s : asd) {
			nombre.addElement(s);
		}
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		
		JPanel panelDetalles = new JPanel();
		panelDetalles.setBounds(10, 0, 516, 567);
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
		
		informacionSolicitada = new JTextField();
		informacionSolicitada.setBounds(10, 317, 283, 239);
		panelDetalles.add(informacionSolicitada);
		informacionSolicitada.setColumns(10);
		
		
		
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JPanel panelCreacion = new JPanel();
		panelCreacion.setBounds(595, 11, 166, 124);
		frame.getContentPane().add(panelCreacion);
		panelCreacion.setLayout(null);
		
		
		
		visualizarDisponibles = new JButton("Crear Equipo");
		visualizarDisponibles.setBounds(61, 82, 95, 23);
		panelCreacion.add(visualizarDisponibles);
		
		
		// 12 x 5 = 60, maximo de desempeño que puede tener un equipo
		reqMinimoEquipo = new JSpinner();
		reqMinimoEquipo.setBounds(109, 42, 47, 26);
		reqMinimoEquipo.setValue(13);
		panelCreacion.add(reqMinimoEquipo);
		
		
		
		
		nombreEquipoNuevo = new JTextField();
		nombreEquipoNuevo.setBounds(70, 11, 86, 20);
		panelCreacion.add(nombreEquipoNuevo);
		nombreEquipoNuevo.setColumns(10);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

}
