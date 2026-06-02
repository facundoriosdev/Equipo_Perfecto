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
	
	private Equipo equipo;
	private ConstructorVisual visual;
	private Empleado empleado;
	private RequerimientoEquipo requerimientoEquipo;
	//private HashMap<String, Equipo> equiposCreados;
	
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
		peneParaEventos();
	}


	public void peneParaEventos() {
		
		visual.reqMinimoEquipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinnerValor(e); 	
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
		
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void detallesEmpDisp() {
		
		if(visual.empleadosDisponibles.getSelectedValue() == null) {
			visual.informacionSolicitada.setText("Debe seleccionar a un empleado para mostrar su información");
		}
		else {
		visual.informacionSolicitada.setText("Nombre: " + visual.empleadosDisponibles.getSelectedValue().getNombre()
				+ "\nRol: " + visual.empleadosDisponibles.getSelectedValue().getRol()
				+ "\nCalificación: " + visual.empleadosDisponibles.getSelectedValue().getCalificacion());
		}
	}
	
	
	public void creacionEquipo() {
		
		if(visual.nombreEquipoNuevo.getText().length() == 0 || (int) visual.reqMinimoEquipo.getValue() > 25 
				|| (int) visual.reqMinimoEquipo.getValue() < 12) {
			visual.informacionSolicitada.setText("Cuidado: \nEl nombre del equipo debe tener como minimo un caracter, ademas"
					+ "\nla calificación tiene que ser entre 12 y 26");
		}
		else if((int)visual.cantidadArquitectos.getValue() < 1 && (int) visual.cantidadProgramadores.getValue() < 1 && 
				(int) visual.cantidadTesters.getValue() < 1 && !visual.liderEquipoBoton.isSelected()) {
			visual.informacionSolicitada.setText("El equipo necesita un miembro como minimo");
		}

		else {
			Map<Roles, Integer> rolReq = new HashMap<Roles, Integer>(); 
			
			
			rolReq.put(Roles.LIDER_PROYECTO, visual.liderEquipoBoton.isSelected() ? 1:0);
			rolReq.put(Roles.ARQUITECTO, (int) visual.cantidadArquitectos.getValue());
			rolReq.put(Roles.PROGRAMADOR, (int) visual.cantidadProgramadores.getValue());
			rolReq.put(Roles.TESTER, (int) visual.cantidadTesters.getValue());
			requerimientoEquipo = new RequerimientoEquipo(rolReq);
			
			visual.informacionSolicitada.setText(rolReq.toString());
			
			BackTracking nuevo = new BackTracking(visual.empleadosNoDisponibles, visual.incompatibles, requerimientoEquipo );
			nuevo.resolver();
//			Equipo mejorEquipo = nuevo.getEquipoFinal();
//			visual.informacionSolicitada.setText(mejorEquipo.toString());
		}
	}
 
	
	public void spinnerValor(ChangeEvent e) throws IllegalArgumentException { 
		
		if((int) visual.reqMinimoEquipo.getNextValue() > 26) {
			visual.reqMinimoEquipo.setValue(25);
			visual.informacionSolicitada.setText("La calificación del equipo no puede ser mayor que 25");
			throw new IllegalArgumentException("La calificación del equipo no puede ser mayor que 25");
		}
		if((int) visual.reqMinimoEquipo.getValue() < 12) {
			visual.reqMinimoEquipo.setValue(12);
			visual.informacionSolicitada.setText("La calificación del equipo no puede ser menor que 12");
			throw new IllegalArgumentException("La calificación del equipo no puede ser menor que 12");
		}
	}
	
}
