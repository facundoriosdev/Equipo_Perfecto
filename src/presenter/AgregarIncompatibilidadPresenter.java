package presenter;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import interfaz.ViewAgregarIncompatibilidad;
import model.Empleado;
import model.Incompatible;

public class AgregarIncompatibilidadPresenter {
	private ViewAgregarIncompatibilidad view;
	private MenuPresenter menuPresenter;

	public AgregarIncompatibilidadPresenter(ViewAgregarIncompatibilidad view, MenuPresenter menuPresenter,
			ArrayList<Empleado> empleadosActuales) {
		this.view = view;
		this.menuPresenter = menuPresenter;
		DefaultListModel<Empleado> modelo = new DefaultListModel<>();

		for (Empleado e : empleadosActuales) {
			modelo.addElement(e);
		}

		// 2. Le pasamos el mismo modelo a las dos listas
		this.view.setModelEmpleados1(modelo);
		this.view.setModelEmpleados2(modelo);

	}

	public void agregarPulsado() {
		Empleado emp1 = view.getEmpleadoSeleccionado1();
		Empleado emp2 = view.getEmpleadoSeleccionado2();

		if (emp1 == null || emp2 == null) {
			view.mostrarMensaje("Cuidado: Debe seleccionar un empleado de cada lista.");
			return;
		}
		if (emp1.equals(emp2)) {
			view.mostrarMensaje("Error: No puede marcar a un empleado como incompatible consigo mismo.");
			return;
		}
		Incompatible nuevaIncompatibilidad = new Incompatible(emp1, emp2);
		menuPresenter.recibirNuevaIncompatibilidad(nuevaIncompatibilidad);
		view.mostrarMensaje("¡Incompatibilidad registrada con éxito!");
		view.cerrar();
	}

	public void salir() {
		view.cerrar();

	}
}
