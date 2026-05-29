package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JPanel;

public class InterfazPrincipal {
	
	private JFrame frmEquipoPerfecto;
	private JTextField nombreEquipoNuevo;
	private JTextField informacionSolicitada;
	private ConstructorVisual visual;

	
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
		
	}
	
	public void spinnerValor(ChangeEvent e) throws IllegalArgumentException { 
		
		if((int) visual.reqMinimoEquipo.getNextValue() > 26) {
			visual.reqMinimoEquipo.setValue(25);
			throw new IllegalArgumentException("La calificación del equipo no puede ser mayor que 25");
		}
		if((int) visual.reqMinimoEquipo.getValue() < 12) {
			visual.reqMinimoEquipo.setValue(12);
			throw new IllegalArgumentException("La calificación del equipo no puede ser menor que 12");
		}
		System.out.println(visual.reqMinimoEquipo.getValue());
	}
}
