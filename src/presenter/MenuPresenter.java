package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JSpinner;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import interfaz.ListaEmpleados;
import interfaz.ViewAgregarEmpleado;
import interfaz.ViewMenu;
import model.BackTracking;
import model.Empleado;
import model.Equipo;
import model.RequerimientoEquipo;
import model.Roles;

public class MenuPresenter {
	
	private ViewMenu _view;
	
	private ListaEmpleados _listaEmpleados;
	private RequerimientoEquipo _requerimientoEquipo;
	private HashMap<String, Equipo> _equiposCreados;
	private DefaultListModel<Empleado> _empDis;
	private DefaultListModel<String> _nombreEquipos;
	private Equipo _mejorEquipo;

	public MenuPresenter(ViewMenu view) {
		this._view = view;
		
		_nombreEquipos = new DefaultListModel<>();
		_equiposCreados = new HashMap<>(); 
		_listaEmpleados = new ListaEmpleados();
		_listaEmpleados.cargarEmpleados(); // esto es temporal, realmente no sirve para nada mas que testear. Esto hay que conectarlo con las clases de negocio
		
		conectarEventos();
		actualizarDisponibles();
	}

	private void conectarEventos() {
		// podriamos hacer esto asi o directamente que el boton crearEquipo haga esta verificacion, lo dejo asi porque fue lo que se me ocurrio primero
		ChangeListener validadorSpinners = new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSpinner spinner = (JSpinner) e.getSource();
				if ((int) spinner.getValue() < 0) {
					spinner.setValue(0);
					_view.setInformacionText("Cuidado: \nNo se puede establecer un valor negativo");
				}
			}
		};
		
		_view.getSpinnerReqMinimo().addChangeListener(validadorSpinners);
		_view.getSpinnerArquitectos().addChangeListener(validadorSpinners);
		_view.getSpinnerProgramadores().addChangeListener(validadorSpinners);
		_view.getSpinnerTesters().addChangeListener(validadorSpinners);
		
		_view.getBtnDetallesEmpleado().addActionListener(e -> detallesEmpDisp());
		_view.getBtnDetallesEquipo().addActionListener(e -> detallesEquipoSelec());
		
		_view.getBtnAgregarEmpleado().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaAgregarEmpleado();
			}
		});

		// atentos que hacen con este boton, porque esto inicia el thread
		_view.getBtnCrearEquipo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creacionEquipoConThread();
			}
		});
	}

	private void abrirVentanaAgregarEmpleado() {
	
		 ViewAgregarEmpleado nuevaVista = new ViewAgregarEmpleado();
		 AgregarEmpleadoPresenter presenter = new AgregarEmpleadoPresenter(nuevaVista, _listaEmpleados);
		 nuevaVista.hacerVisible();
		_view.setInformacionText("Abriendo menú para agregar nuevo empleado...");
	}

	public void detallesEquipoSelec() {
		String equipoSeleccionado = _view.getEquipoSeleccionado();
		if(equipoSeleccionado == null) {
			_view.setInformacionText("Cuidado: \nSeleccione un equipo para mostrar su información");
			return;
		}
		_view.setInformacionText(buscarEquipoSelec(equipoSeleccionado).toString());
	}
	
	public void detallesEmpDisp() {
		Empleado emp = _view.getEmpleadoSeleccionado();
		if(emp == null) {
			_view.setInformacionText("Cuidado: \nSeleccione a un empleado para mostrar su información");
		} else {
			_view.setInformacionText("Nombre: " + emp.getNombre()
					+ "\nRol: " + emp.getRol()
					+ "\nCalificación: " + emp.getCalificacion()
					+ "\nDisponibilidad: " + emp.getDisponible());
		}
	}
	
	public void creacionEquipoConThread() {
		if(_view.getNombreEquipoNuevo().trim().isEmpty()) {
			_view.setInformacionText("Cuidado: \nEl nombre del equipo no puede estar vacío");
			return;
		}
		
		int cantArq = _view.getCantidadArquitectos();
		int cantProg = _view.getCantidadProgramadores();
		int cantTest = _view.getCantidadTesters();
		boolean tieneLider = _view.isLiderSelected();
		
		if(cantArq == 0 && cantProg == 0 && cantTest == 0 && !tieneLider) {
			_view.setInformacionText("Cuidado: \nEl equipo necesita un miembro como mínimo");
			return;
		}
		
		// no estoy seguro si esto deberia estar aca, es probable que esto deberia ir en otra clase que sea exclusiva para el swingworker
		Map<Roles, Integer> rolReq = new HashMap<>(); 
		rolReq.put(Roles.LIDER_PROYECTO, tieneLider ? 1 : 0);
		rolReq.put(Roles.ARQUITECTO, cantArq);
		rolReq.put(Roles.PROGRAMADOR, cantProg);
		rolReq.put(Roles.TESTER, cantTest);
		_requerimientoEquipo = new RequerimientoEquipo(rolReq);
			
		BackTracking nuevoBacktracking = new BackTracking(
				_listaEmpleados.getEmpleadosDisponibles(), 
				_listaEmpleados.getIncompatibles(), 
				_requerimientoEquipo
		);
		
	//intento de swingworker 
		
		_view.getBtnCrearEquipo().setEnabled(false);
		_view.setInformacionText("Calculando el mejor equipo, aguarde un momento... ⏳");

		SwingWorker<Equipo, Void> worker = new SwingWorker<Equipo, Void>() {
			@Override
			protected Equipo doInBackground() throws Exception {// realmente no estoy seguro si esto funciona, solo rezo porque si
				
				nuevoBacktracking.resolver(); 
				return nuevoBacktracking.getEquipoFinal();
			}

			@Override
			protected void done() {
				
				try {
					_mejorEquipo = get(); 
					
					if (_mejorEquipo == null || _mejorEquipo.getPuntajeTotal() < _view.getReqMinimoEquipo()) {
						_view.setInformacionText("Advertencia: \nNo se pudo crear un equipo que cumpla con sus requisitos o puntaje mínimo.");
					} else {
						
						for (Empleado e : _mejorEquipo.getEmpleados()) {
							e.setDisponible(false);
							_listaEmpleados.getEmpleadosNoDisponibles().add(e);
							_listaEmpleados.getEmpleadosDisponibles().remove(e);
						}
						
						actualizarDisponibles();
						agregarNuevoEquipo(_mejorEquipo, _view.getNombreEquipoNuevo());
						agregarEquipoLista(_mejorEquipo.getNombre());
						
						_view.setInformacionText("¡Equipo armado con éxito! ✅\nPuntaje total: " + _mejorEquipo.getPuntajeTotal());
					}
				} catch (Exception ex) {
					_view.setInformacionText("Ocurrió un error inesperado al procesar el algoritmo.");
					ex.printStackTrace();
				} finally {
				
					_view.getBtnCrearEquipo().setEnabled(true);
				}
			}
		};
		
		worker.execute(); 
	}
 
	public void actualizarDisponibles() {
		_empDis = new DefaultListModel<>();
		for(Empleado e : _listaEmpleados.getEmpleadosDisponibles()) {
			if(e.getDisponible()) _empDis.addElement(e);
		}
		_view.setModelEmpleados(_empDis);
	}
	
	public void agregarNuevoEquipo(Equipo equipo, String nombre){
	    equipo.setNombre(nombre);
	    _equiposCreados.put(nombre, equipo);
	}
	
	public void agregarEquipoLista(String nombre) {
		_nombreEquipos.addElement(nombre);
		_view.setModelEquipos(_nombreEquipos);
	}
	
	public Equipo buscarEquipoSelec(String nombre) {
		return _equiposCreados.get(nombre);
	}
}