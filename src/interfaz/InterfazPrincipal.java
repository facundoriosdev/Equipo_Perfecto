package interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JSpinner;
import javax.swing.DefaultListModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.*;


public class InterfazPrincipal {
	
	private ConstructorVisual _visual;
	private ListaEmpleados _listaEmpleados;
	private RequerimientoEquipo _requerimientoEquipo;
	private HashMap<String, Equipo> _equiposCreados;
	private DefaultListModel<Empleado> _empDis;
	private DefaultListModel<String> _nombreEquipos;
	private Equipo _mejorEquipo;

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConstructorVisual mirar = new ConstructorVisual();
					InterfazPrincipal window = new InterfazPrincipal(mirar);
					window._visual.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfazPrincipal(ConstructorVisual visual) {
		this._visual = visual;
		
		_nombreEquipos = new DefaultListModel<>();
		_equiposCreados = new HashMap<>(); 
		_listaEmpleados = new ListaEmpleados();
		_listaEmpleados.cargarEmpleados();
		
		gestorEventos();
	}


	public void gestorEventos() {
		actualizarDisponibles();
		
		
		_visual.reqMinimoEquipo.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinnerValor(e, _visual.reqMinimoEquipo); 	
			}
		}); 	
		
		_visual.cantidadArquitectos.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinnerValor(e, _visual.cantidadArquitectos); 	
			}
		}); 
		
		_visual.cantidadProgramadores.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinnerValor(e, _visual.cantidadProgramadores); 	
			}
		}); 
		
		_visual.cantidadTesters.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				spinnerValor(e, _visual.cantidadTesters); 	
			}
		}); 
		
		_visual.crearEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creacionEquipo();
			}
		});
		
		_visual.detallesEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detallesEmpDisp();
			}
		});
		
		_visual.detallesEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detallesEquipoSelec();
			}
		});
		
		
	}
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void detallesEquipoSelec() {
		if(_visual.listaEquiposCreados.getSelectedValue() == null) {
			_visual.informacionSolicitada.setText("Cuidado: \nSeleccione un equipo para mostrar su información");
			return;
		}
		_visual.informacionSolicitada.setText(buscarEquipoSelec(_visual.listaEquiposCreados.getSelectedValue()).toString());
	}
	
	public void detallesEmpDisp() {
		
		if(_visual.empleadosDisponibles.getSelectedValue() == null) {
			_visual.informacionSolicitada.setText("Cuidado: \nSeleccione a un empleado para mostrar su información");
		}
		else {
		_visual.informacionSolicitada.setText("Nombre: " + _visual.empleadosDisponibles.getSelectedValue().getNombre()
				+ "\nRol: " + _visual.empleadosDisponibles.getSelectedValue().getRol()
				+ "\nCalificación: " + _visual.empleadosDisponibles.getSelectedValue().getCalificacion()
				+ "\nDisponibilidad: " + _visual.empleadosDisponibles.getSelectedValue().getDisponible());
		}
	}
	
	
	public void creacionEquipo() {
		
		if(_visual.nombreEquipoNuevo.getText().length() == 0) {
			_visual.informacionSolicitada.setText("Cuidado: \nEl nombre del equipo debe tener como minimo un caracter");
			return;
		}
		else if((int)_visual.cantidadArquitectos.getValue() < 1 && (int) _visual.cantidadProgramadores.getValue() < 1 && 
				(int) _visual.cantidadTesters.getValue() < 1 && !_visual.liderEquipoBoton.isSelected()) {
			_visual.informacionSolicitada.setText("Cuidado: \nEl equipo necesita un miembro como minimo");
			return;
		}
		Map<Roles, Integer> rolReq = new HashMap<Roles, Integer>(); 
		rolReq.put(Roles.LIDER_PROYECTO, _visual.liderEquipoBoton.isSelected() ? 1:0);
		rolReq.put(Roles.ARQUITECTO, (int) _visual.cantidadArquitectos.getValue());
		rolReq.put(Roles.PROGRAMADOR, (int) _visual.cantidadProgramadores.getValue());
		rolReq.put(Roles.TESTER, (int) _visual.cantidadTesters.getValue());
		_requerimientoEquipo = new RequerimientoEquipo(rolReq);
			
		BackTracking nuevo = new BackTracking(_listaEmpleados.getEmpleadosDisponibles(), _listaEmpleados.getIncompatibles(), _requerimientoEquipo );
		
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
			_visual.informacionSolicitada
			.setText("Advertencia: \nNo se pudo crear un equipo que cumpla con sus requisitos");
			return;
		}
		_mejorEquipo = nuevo.getEquipoFinal();
		for (Empleado e : _mejorEquipo.getEmpleados()) {
			e.setDisponible(false);
			_listaEmpleados.getEmpleadosNoDisponibles().add(e);
			_listaEmpleados.getEmpleadosDisponibles().remove(e);
		}
		actualizarDisponibles();
		agregarNuevoEquipo(_mejorEquipo, _visual.nombreEquipoNuevo.getText());
		agregarEquipoLista(_mejorEquipo.getNombre());
		
	}
 
//	visual.informacionSolicitada.setText("\nnoDisp:" + listaEmpleados.empleadosNoDisponibles);
	public void spinnerValor(ChangeEvent e, JSpinner spinner) { 
		if(!spinnerCumpleReqs(spinner)) {
			spinner.setValue(0);
			_visual.informacionSolicitada.setText("Cuidado: \nNo se puede establecer un valor negativo");
			return;
		}
	}
	
	public void actualizarDisponibles() {
		_empDis = new DefaultListModel<>();
		for(Empleado e : _listaEmpleados.getEmpleadosDisponibles()) {
			if(e.getDisponible())
				_empDis.addElement(e);
		}
		_visual.empleadosDisponibles.setModel(_empDis);
	}
	
	public void agregarNuevoEquipo(Equipo equipo, String nombre){
	    equipo.setNombre(nombre);
	    _equiposCreados.put(nombre, equipo);
	}
	
	public void agregarEquipoLista(String nombre) {
		_nombreEquipos.addElement(nombre);
		_visual.listaEquiposCreados.setModel(_nombreEquipos);
	}
	
	public boolean cumpleRequisitosMinimos(BackTracking equipo) {
		if(equipo.getEquipoFinal().getPuntajeTotal() < (int)_visual.reqMinimoEquipo.getValue()) {
			return false;
		}
		return true;
	}
	
	public Equipo buscarEquipoSelec(String nombre) {
		Equipo equipo = new Equipo();
		equipo = _equiposCreados.get(nombre);
		return equipo;
	}
	
	public boolean spinnerCumpleReqs(JSpinner spinner) {
		if((int) spinner.getValue() < 0 || (int) spinner.getPreviousValue() < -1) {
			return false;
		}
		return true;
	}
}
