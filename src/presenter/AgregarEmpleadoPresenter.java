package presenter;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaz.ViewAgregarEmpleado;
import model.Empleado;
import model.Roles;

public class AgregarEmpleadoPresenter {
	private ViewAgregarEmpleado view;
	private MenuPresenter menuPresenter;
	

	public AgregarEmpleadoPresenter(ViewAgregarEmpleado nuevaVista,MenuPresenter menuPresenter, ArrayList<Empleado> empleadosActuales) {
		this.menuPresenter=menuPresenter;
		this.view=nuevaVista;
		
	}

	public void cancelarPulsado() {
		view.cerrar();
		
	}

	public void agregarPulsado(String nombre, Roles rol, int calificacion) {
		if( calificacion<0 || calificacion>5 ) {
			view.mostrarError("Su calificacion :"+ calificacion + ",  debe ser >0 y <5");
			return;
		}if(nombre.isEmpty()) {
			view.mostrarError("El nombre no puede estar vacio");
			return;
		}
		Empleado Nuevoempleado= new Empleado(nombre, rol, calificacion);
		menuPresenter.agregarEmpleado(Nuevoempleado);
		JOptionPane.showMessageDialog(null, "Empleado agregado con éxito");
	    view.cerrar();
	}

	// en construccion

}
