package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.*;

import javax.swing.JPanel;

public class InterfazPrincipal {
	
	private ConstructorVisual visual;
	private ListaEmpleados listaEmpleados;
	private RequerimientoEquipo requerimientoEquipo;
	private HashMap<String, Equipo> equiposCreados;
	private DefaultListModel<Empleado> empDis;
	private DefaultListModel<String> nombreEquipos;
	private Equipo mejorEquipo;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConstructorVisual mirar = new ConstructorVisual();
					InterfazPrincipal window = new InterfazPrincipal(mirar);
					window.visual.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfazPrincipal(ConstructorVisual visual) {
		this.visual = visual;
		
		nombreEquipos = new DefaultListModel<>();
		
		listaEmpleados = new ListaEmpleados();
		listaEmpleados.cargarEmpleados();
		
		gestorEventos();
	}


	public void gestorEventos() {
		actualizarDisponibles();
		
		
		visual.reqMinimoEquipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinnerValor(e, visual.reqMinimoEquipo); 	
			}
		}); 	
		
		visual.cantidadArquitectos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinnerValor(e, visual.cantidadArquitectos); 	
			}
		}); 
		
		visual.cantidadProgramadores.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinnerValor(e, visual.cantidadProgramadores); 	
			}
		}); 
		
		visual.cantidadTesters.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinnerValor(e, visual.cantidadTesters); 	
			}
		}); 
		
		visual.crearEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creacionEquipo();
			}
		});
		
		visual.detallesEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detallesEmpDisp();
			}
		});
		
		visual.detallesEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detallesEquipoSelec();
			}
		});
		
		
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void detallesEquipoSelec() {
		if(visual.listaEquiposCreados.getSelectedValue() == null) {
			visual.informacionSolicitada.setText("Cuidado: \nSeleccione un equipo para mostrar su información");
			return;
		}
		
		visual.informacionSolicitada.setText(buscarEquipoSelec(visual.listaEquiposCreados.getSelectedValue()).toString());
	}
	
	public void detallesEmpDisp() {
		
		if(visual.empleadosDisponibles.getSelectedValue() == null) {
			visual.informacionSolicitada.setText("Cuidado: \nSeleccione a un empleado para mostrar su información");
		}
		else {
		visual.informacionSolicitada.setText("Nombre: " + visual.empleadosDisponibles.getSelectedValue().getNombre()
				+ "\nRol: " + visual.empleadosDisponibles.getSelectedValue().getRol()
				+ "\nCalificación: " + visual.empleadosDisponibles.getSelectedValue().getCalificacion()
				+ "\nDisponibilidad: " + visual.empleadosDisponibles.getSelectedValue().getDisponible());
		}
	}
	
	
	public void creacionEquipo() {
		
		if(visual.nombreEquipoNuevo.getText().length() == 0) {
			visual.informacionSolicitada.setText("Cuidado: \nEl nombre del equipo debe tener como minimo un caracter");
			return;
		}
		else if((int)visual.cantidadArquitectos.getValue() < 1 && (int) visual.cantidadProgramadores.getValue() < 1 && 
				(int) visual.cantidadTesters.getValue() < 1 && !visual.liderEquipoBoton.isSelected()) {
			visual.informacionSolicitada.setText("Cuidado: \nEl equipo necesita un miembro como minimo");
			return;
		}
		Map<Roles, Integer> rolReq = new HashMap<Roles, Integer>(); 
		rolReq.put(Roles.LIDER_PROYECTO, visual.liderEquipoBoton.isSelected() ? 1:0);
		rolReq.put(Roles.ARQUITECTO, (int) visual.cantidadArquitectos.getValue());
		rolReq.put(Roles.PROGRAMADOR, (int) visual.cantidadProgramadores.getValue());
		rolReq.put(Roles.TESTER, (int) visual.cantidadTesters.getValue());
		requerimientoEquipo = new RequerimientoEquipo(rolReq);
			
		BackTracking nuevo = new BackTracking(listaEmpleados.empleadosDisponibles, listaEmpleados.incompatibles, requerimientoEquipo );
		
		Thread hilo = new Thread(new ResolverRunnable(nuevo)
		);

		hilo.start();

		try {
		    hilo.join();
		}
		catch (InterruptedException e) {
		    e.printStackTrace();
		}
		
		if (!cumpleRequisitosMinimos(nuevo)) {
			visual.informacionSolicitada
			.setText("Advertencia: \nNo se pudo crear un equipo que cumpla con sus requisitos");
			return;
		}
		mejorEquipo = nuevo.getEquipoFinal();
		for (Empleado e : mejorEquipo.getEmpleados()) {
			e.setDisponible(false);
			listaEmpleados.empleadosNoDisponibles.add(e);
			listaEmpleados.empleadosDisponibles.remove(e);
		}
		actualizarDisponibles();
		agregarNuevoEquipo(mejorEquipo, visual.nombreEquipoNuevo.getText());
		agregarEquipoLista(mejorEquipo.getNombre());
		
	}
 
//	visual.informacionSolicitada.setText("\nnoDisp:" + listaEmpleados.empleadosNoDisponibles);
	public void spinnerValor(ChangeEvent e, JSpinner spinner) { 
		if(!spinnerCumpleReqs(spinner)) {
			spinner.setValue(0);
			visual.informacionSolicitada.setText("Cuidado: \nLa calificación del equipo no puede ser menor que 0");
			return;
		}
	}
	
	public void actualizarDisponibles() {
		empDis = new DefaultListModel<>();
		for(Empleado e : listaEmpleados.empleadosDisponibles) {
			if(e.getDisponible())
				empDis.addElement(e);
		}
		visual.empleadosDisponibles.setModel(empDis);
	}
	
	public void agregarNuevoEquipo(Equipo equipo, String nombre){
		equiposCreados = new HashMap<>();
		equipo.setNombre(nombre);
		equiposCreados.put(nombre, equipo);
	}
	
	public void agregarEquipoLista(String nombre) {
		nombreEquipos.addElement(nombre);
		visual.listaEquiposCreados.setModel(nombreEquipos);
	}
	
	public boolean cumpleRequisitosMinimos(BackTracking equipo) {
		if(equipo.getEquipoFinal().getPuntajeTotal() < (int)visual.reqMinimoEquipo.getValue()) {
			return false;
		}
		return true;
	}
	
	public Equipo buscarEquipoSelec(String nombre) {
		Equipo equipo = new Equipo();
		equipo = equiposCreados.get(nombre);
		return equipo;
	}
	
	public boolean spinnerCumpleReqs(JSpinner spinner) {
		if((int) spinner.getValue() < 0 || (int) spinner.getPreviousValue() < -1) {
			return false;
		}
		return true;
	}
}
