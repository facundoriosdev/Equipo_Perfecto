package presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;

import interfaz.ViewAgregarEmpleado;
import interfaz.ViewAgregarIncompatibilidad;
import interfaz.ViewMenu;
import model.BackTracking;
import model.Empleado;
import model.Equipo;
import model.Incompatible;
import model.RequerimientoEquipo;
import model.Roles;

public class MenuPresenter {

	private ViewMenu _view;
	private RequerimientoEquipo _requerimientoEquipo;
	private HashMap<String, Equipo> _equiposCreados;
	private DefaultListModel<String> _nombreEquipos;
	private ArrayList<Empleado> empleadosDisponibles = new ArrayList<>();
	private ArrayList<Incompatible> incompatibilidades = new ArrayList<>();

	public MenuPresenter(ViewMenu view) {

		this._view = view;
		_nombreEquipos = new DefaultListModel<>();
		_equiposCreados = new HashMap<>();
		this._view.setPresenter(this);

	}

	public void abrirVentanaAgregarEmpleado() {

		ViewAgregarEmpleado nuevaVista = new ViewAgregarEmpleado();
		AgregarEmpleadoPresenter presenterSecundario = new AgregarEmpleadoPresenter(nuevaVista, this,
				empleadosDisponibles);
		nuevaVista.setPresenter(presenterSecundario);
		nuevaVista.hacerVisible();
	}

	public void abrirVentanaAgregarIncompatibilidad() {
		ViewAgregarIncompatibilidad nuevaVistaIncompatibilidad = new ViewAgregarIncompatibilidad();
		AgregarIncompatibilidadPresenter nuevoPresenterIncompatibilidad = new AgregarIncompatibilidadPresenter(
				nuevaVistaIncompatibilidad, this, this.empleadosDisponibles);
		nuevaVistaIncompatibilidad.setPresenter(nuevoPresenterIncompatibilidad);
		nuevaVistaIncompatibilidad.hacerVisible();
	}

	public void detallesIncompatible() {
		Incompatible seleccionado = _view.getIncompatibleSeleccionado();
		if (seleccionado == null) {
			_view.setInformacionText("Cuidado: \nSeleccione una incompatibilidad para mostrar su información");
		} else {
			Empleado e1 = seleccionado.getEmpleado1();
			Empleado e2 = seleccionado.getEmpleado2();

			_view.setInformacionText("--- Detalle de Incompatibilidad ---\n" + "Persona 1: " + e1.getNombre()
					+ " (Rol: " + e1.getRol() + ", Puntaje: " + e1.getCalificacion() + ")\n" + "Persona 2: "
					+ e2.getNombre() + " (Rol: " + e2.getRol() + ", Puntaje: " + e2.getCalificacion() + ")");
		}
	}

	public void detallesEquipoSelec() {
		String equipoSeleccionado = _view.getEquipoSeleccionado();
		if (equipoSeleccionado == null) {
			_view.setInformacionText("Cuidado: \nSeleccione un equipo para mostrar su información");
			return;
		}
		_view.setInformacionText(buscarEquipoSelec(equipoSeleccionado).toString());
	}

	public void detallesEmpDisp() {
		Empleado emp = _view.getEmpleadoSeleccionado();
		if (emp == null) {
			_view.setInformacionText("Cuidado: \nSeleccione a un empleado para mostrar su información");
		} else {
			_view.setInformacionText("Nombre: " + emp.getNombre() + "\nRol: " + emp.getRol() + "\nCalificación: "
					+ emp.getCalificacion() + "\nDisponibilidad: " + emp.getDisponible());
		}
	}

	public void creacionEquipoConThread() {
		String nombreEquipo = _view.getNombreEquipoNuevo().trim();
		if (_view.getNombreEquipoNuevo().trim().isEmpty()) {
			_view.setInformacionText("Cuidado: \nEl nombre del equipo no puede estar vacío");
			return;
		}
		if (_view.getCantidadArquitectos()<0 || _view.getCantidadLideres()<0 || _view.getCantidadProgramadores()<0 || _view.getCantidadTesters()<0 || _view.getReqMinimoEquipo()<0) {
			_view.setInformacionText("Por favor, ingrese valores mayores o iguales a 0");
			return;
		}
		if (_equiposCreados.containsKey(nombreEquipo)) {
			_view.setInformacionText("Cuidado: \nYa existe un equipo llamado así.\nPor favor, cambie el nombre en el cuadro de texto.");
			return;
		}

		Map<Roles, Integer> rolReq = new HashMap<>();
		rolReq.put(Roles.LIDER_PROYECTO, _view.getCantidadLideres());
		rolReq.put(Roles.ARQUITECTO, _view.getCantidadArquitectos());
		rolReq.put(Roles.PROGRAMADOR, _view.getCantidadProgramadores());
		rolReq.put(Roles.TESTER, _view.getCantidadTesters());
		_requerimientoEquipo = new RequerimientoEquipo(rolReq);
		
		ArrayList<Empleado> empleadosLibresParaAlgoritmo = new ArrayList<>(); //elimino a los empleados que ya estan en algun equipo
		for (Empleado e : this.empleadosDisponibles) {
			if (e.getDisponible()) {
				empleadosLibresParaAlgoritmo.add(e);
			}
		}

		BackTracking nuevoBacktracking = new BackTracking(this.empleadosDisponibles, this.incompatibilidades,
				_requerimientoEquipo);
		_view.mostrarBarraCarga(true);
		_view.getBtnCrearEquipo().setEnabled(false);
		_view.setInformacionText(
				"Calculando el equipo perfecto... ⏳\n(Esto puede tardar dependiendo de las combinaciones)");

		BackTrackingWorker worker = new BackTrackingWorker(this, nuevoBacktracking);
		worker.execute();
	}

	public void finalizarCreacionEquipo(Equipo mejorEquipo, long tiempoTotal) {
		_view.mostrarBarraCarga(false);
		if (mejorEquipo == null || mejorEquipo.getEmpleados().isEmpty() || mejorEquipo.getPuntajeTotal() < _view.getReqMinimoEquipo()) {
			_view.setInformacionText("Advertencia: \nNo se pudo armar un equipo que cumpla sus requisitos mínimos.");

		} else {

			for (Empleado e : mejorEquipo.getEmpleados()) {
				e.setDisponible(false);
			}

			actualizarDisponibles();
			agregarNuevoEquipo(mejorEquipo, _view.getNombreEquipoNuevo());
			agregarEquipoLista(mejorEquipo.getNombre());

			_view.setInformacionText("¡Equipo armado con éxito! ✅\nPuntaje: " + mejorEquipo.getPuntajeTotal()
					+ "\nTiempo de búsqueda: " + tiempoTotal + " ms.");
		}

		_view.getBtnCrearEquipo().setEnabled(true);
	}

	public void agregarNuevoEquipo(Equipo equipo, String nombre) {
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

	private void actualizarDisponibles() {
		DefaultListModel<Empleado> modeloEmp = new DefaultListModel<>();
		for (Empleado e : this.empleadosDisponibles) {
			if (e.getDisponible())
				modeloEmp.addElement(e);
		}
		_view.setModelEmpleados(modeloEmp);
	}

	public void agregarEmpleado(Empleado nuevoEmpleado) {
		this.empleadosDisponibles.add(nuevoEmpleado);
		actualizarDisponibles();
	}

	public void eliminarEmpleado() {
		Empleado seleccionado = _view.getEmpleadoSeleccionado();
		if (seleccionado != null) {
			empleadosDisponibles.remove(seleccionado);
			// borra las incompatibilidades que existian con este
			incompatibilidades.removeIf(
					inc -> inc.getEmpleado1().equals(seleccionado) || inc.getEmpleado2().equals(seleccionado));

			actualizarDisponibles();
			actualizarIncompatibilidades();
			_view.setInformacionText("Empleado eliminado: " + seleccionado.getNombre());
		} else {
			_view.setInformacionText("Cuidado: Seleccione un empleado para eliminar.");
		}
	}

	private void actualizarIncompatibilidades() {
		DefaultListModel<Incompatible> modeloInc = new DefaultListModel<>();
		for (Incompatible inc : this.incompatibilidades) {
			modeloInc.addElement(inc);
		}
		_view.setModelIncompatibles(modeloInc);
	}

	public void eliminarIncompatible() {
		Incompatible seleccionado = _view.getIncompatibleSeleccionado();
		if (seleccionado != null) {
			incompatibilidades.remove(seleccionado);
			actualizarIncompatibilidades();
			_view.setInformacionText("Incompatibilidad eliminada.");
		} else {
			_view.setInformacionText("Cuidado: Seleccione una incompatibilidad para eliminar.");
		}
	}

	public void recibirNuevaIncompatibilidad(Incompatible nuevaIncompatibilidad) {
		this.incompatibilidades.add(nuevaIncompatibilidad);
		actualizarIncompatibilidades();
	}

	public void mostrarErrorAlgoritmo(Exception ex) {
		_view.setInformacionText("Ocurrió un error inesperado al procesar el algoritmo.");
		ex.printStackTrace();
		_view.getBtnCrearEquipo().setEnabled(true);
	}
}