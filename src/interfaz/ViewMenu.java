package interfaz;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.Empleado;

	public class ViewMenu {
		
			private JFrame frame;
			private JTextField nombreEquipoNuevo;
			private JSpinner reqMinimoEquipo, cantidadProgramadores, cantidadTesters, cantidadArquitectos;
			private JButton crearEquipo, detallesEmpleado, detallesEquipo;
			private JList<Empleado> empleadosDisponibles;
			private JTextArea informacionSolicitada;
			private JList<String> listaEquiposCreados;
			private JRadioButton liderEquipoBoton;
			private JTextField txtArquitectos;
			private JTextField txtProgramadores;
			private JTextField txtTesters;
			private JTextField txtNombreDeEquipo;
			private JTextField txtCalificacinMinima;
			private JButton btnAgregarEmpleado;
			public ViewMenu() {
				initialize();
			}
			
		
			private void initialize() {
				frame = new JFrame();
				frame.setTitle("Equipo Perfecto");
				frame.setBounds(100, 100, 787, 606);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(null); 
				
				
				
				JPanel panelDetalles = new JPanel();
				panelDetalles.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panelDetalles.setBounds(10, 0, 516, 290);
				frame.getContentPane().add(panelDetalles);
				panelDetalles.setLayout(null);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(10, 11, 178, 219);
				panelDetalles.add(scrollPane_1);
				
				listaEquiposCreados = new JList<>();
				scrollPane_1.setViewportView(listaEquiposCreados);
				
				detallesEquipo = new JButton("Mostrar detalles del equipo");
				detallesEquipo.setBounds(10, 241, 178, 45);
				panelDetalles.add(detallesEquipo);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(242, 21, 158, 219);
				panelDetalles.add(scrollPane);
				
				
				empleadosDisponibles = new JList<Empleado>();
				scrollPane.setViewportView(empleadosDisponibles);
				empleadosDisponibles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				empleadosDisponibles.setValueIsAdjusting(true);
				
				
				
				detallesEmpleado = new JButton("Mostrar Detalles");
				detallesEmpleado.setBounds(256, 241, 126, 20);
				panelDetalles.add(detallesEmpleado);
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				JPanel panelCreacion = new JPanel();
				panelCreacion.setBorder(new LineBorder(new Color(0, 0, 0)));
				panelCreacion.setBounds(595, 11, 166, 545);
				frame.getContentPane().add(panelCreacion);
				panelCreacion.setLayout(null);
				
			
				crearEquipo = new JButton("Crear Equipo");
				crearEquipo.setBounds(10, 482, 146, 52);
				panelCreacion.add(crearEquipo);
				
				
				// 12 x 5 = 60, maximo de desempeño que puede tener un equipo
				reqMinimoEquipo = new JSpinner();
				reqMinimoEquipo.setBounds(120, 231, 36, 20);
				reqMinimoEquipo.setValue(0);
				panelCreacion.add(reqMinimoEquipo);
				
				
				nombreEquipoNuevo = new JTextField();
				nombreEquipoNuevo.setBounds(10, 42, 146, 20);
				panelCreacion.add(nombreEquipoNuevo);
				nombreEquipoNuevo.setColumns(10);
				
				cantidadArquitectos = new JSpinner();
				cantidadArquitectos.setBounds(120, 136, 36, 20);
				panelCreacion.add(cantidadArquitectos);
				
				cantidadTesters = new JSpinner();
				cantidadTesters.setBounds(120, 200, 36, 20);
				panelCreacion.add(cantidadTesters);
				
				cantidadProgramadores = new JSpinner();
				cantidadProgramadores.setBounds(120, 169, 36, 20);
				panelCreacion.add(cantidadProgramadores);
				
				liderEquipoBoton = new JRadioButton("Lider de equipo"); //esto hay que cambiarlo tambien por un JSpinner porque pueden ser varios lideres de equipo
				liderEquipoBoton.setBounds(10, 81, 150, 23);
				panelCreacion.add(liderEquipoBoton);
				
				txtArquitectos = new JTextField();
				txtArquitectos.setBorder(null);
				txtArquitectos.setFocusable(false);
				txtArquitectos.setEditable(false);
				txtArquitectos.setText("Arquitectos: ");
				txtArquitectos.setBounds(10, 136, 106, 20);
				panelCreacion.add(txtArquitectos);
				txtArquitectos.setColumns(10);
				
				txtProgramadores = new JTextField();
				txtProgramadores.setBorder(null);
				txtProgramadores.setFocusable(false);
				txtProgramadores.setEditable(false);
				txtProgramadores.setText("Programadores: ");
				txtProgramadores.setBounds(10, 169, 106, 20);
				panelCreacion.add(txtProgramadores);
				txtProgramadores.setColumns(10);
				
				txtTesters = new JTextField();
				txtTesters.setBorder(null);
				txtTesters.setFocusable(false);
				txtTesters.setOpaque(false);
				txtTesters.setEditable(false);
				txtTesters.setText("Testers: ");
				txtTesters.setBounds(10, 199, 106, 20);
				panelCreacion.add(txtTesters);
				txtTesters.setColumns(10);
				
				txtNombreDeEquipo = new JTextField();
				txtNombreDeEquipo.setBorder(null);
				txtNombreDeEquipo.setEditable(false);
				txtNombreDeEquipo.setFocusable(false);
				txtNombreDeEquipo.setText("Nombre de Equipo:");
				txtNombreDeEquipo.setBounds(10, 11, 146, 20);
				panelCreacion.add(txtNombreDeEquipo);
				txtNombreDeEquipo.setColumns(10);
				
				txtCalificacinMinima = new JTextField();
				txtCalificacinMinima.setText("Calificación minima");
				txtCalificacinMinima.setBorder(null);
				txtCalificacinMinima.setFocusable(false);
				txtCalificacinMinima.setEditable(false);
				txtCalificacinMinima.setBounds(10, 230, 123, 20);
				panelCreacion.add(txtCalificacinMinima);
				txtCalificacinMinima.setColumns(10);
				
				JButton btnAgregarEmpleado = new JButton("Agregar Empleado");
				btnAgregarEmpleado.setBounds(10, 403, 146, 52);
				panelCreacion.add(btnAgregarEmpleado);
				
				JScrollPane scrollPane_2 = new JScrollPane();
				scrollPane_2.setBounds(10, 324, 516, 232);
				frame.getContentPane().add(scrollPane_2);
				
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
				informacionSolicitada = new JTextArea();
				scrollPane_2.setViewportView(informacionSolicitada);
				informacionSolicitada.setFocusable(false);
				informacionSolicitada.setEditable(false);
				
			}
			//metodos getters
			public String getNombreEquipoNuevo() { return nombreEquipoNuevo.getText(); }
			public int getCantidadArquitectos() { return (int) cantidadArquitectos.getValue(); }
			public int getCantidadProgramadores() { return (int) cantidadProgramadores.getValue(); }
			public int getCantidadTesters() { return (int) cantidadTesters.getValue(); }
			public boolean isLiderSelected() { return liderEquipoBoton.isSelected(); }
			public int getReqMinimoEquipo() { return (int) reqMinimoEquipo.getValue(); }
			public Empleado getEmpleadoSeleccionado() { return empleadosDisponibles.getSelectedValue(); }
			public String getEquipoSeleccionado() { return listaEquiposCreados.getSelectedValue(); }
			//setters
			public void setInformacionText(String text) { informacionSolicitada.setText(text); }
			public void setModelEmpleados(ListModel<Empleado> model) { empleadosDisponibles.setModel(model); }
			public void setModelEquipos(ListModel<String> model) { listaEquiposCreados.setModel(model); }
			public void hacerVisible() { this.frame.setVisible(true); }
			//botones
			public JButton getBtnCrearEquipo() { return crearEquipo; }
			public JButton getBtnDetallesEmpleado() { return detallesEmpleado; }
			public JButton getBtnDetallesEquipo() { return detallesEquipo; }
			public JButton getBtnAgregarEmpleado() { return btnAgregarEmpleado; }
			public JSpinner getSpinnerReqMinimo() { return reqMinimoEquipo; }
			public JSpinner getSpinnerArquitectos() { return cantidadArquitectos; }
			public JSpinner getSpinnerProgramadores() { return cantidadProgramadores; }
			public JSpinner getSpinnerTesters() { return cantidadTesters; }
		}

