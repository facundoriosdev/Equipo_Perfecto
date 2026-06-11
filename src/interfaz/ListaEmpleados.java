package interfaz;

import java.util.ArrayList;
import model.*;

public class ListaEmpleados {
	
	public ArrayList<Empleado> empleadosDisponibles;
	public ArrayList<Empleado> empleadosNoDisponibles;
	private ArrayList<Incompatible> incompatibles;
	ArrayList<Equipo> equiposLista;
	
	public void cargarEmpleados(){
		
		setEmpleadosNoDisponibles(new ArrayList<Empleado>());
		setEmpleadosDisponibles(new ArrayList<Empleado>());

		// LÍDERES
		Empleado l1 = new Empleado("Aitana", Roles.LIDER_PROYECTO, 5);
		Empleado l2 = new Empleado("Atahualpa", Roles.LIDER_PROYECTO, 4);
		// ARQUITECTOS
		Empleado a1 = new Empleado("Valentina", Roles.ARQUITECTO, 5);
		Empleado a2 = new Empleado("Bruno", Roles.ARQUITECTO, 2);
		Empleado a3 = new Empleado("Lucia", Roles.ARQUITECTO, 3);
		Empleado a4 = new Empleado("Usnavy", Roles.ARQUITECTO, 4);
		// PROGRAMADORES
		Empleado p1 = new Empleado("Nicolas", Roles.PROGRAMADOR, 5);
		Empleado p2 = new Empleado("Rayan", Roles.PROGRAMADOR, 1);
		Empleado p3 = new Empleado("Federico", Roles.PROGRAMADOR, 4);
		Empleado p4 = new Empleado("Tomas", Roles.PROGRAMADOR, 3);
		Empleado p5 = new Empleado("Matias", Roles.PROGRAMADOR, 5);
		Empleado p6 = new Empleado("Juan", Roles.PROGRAMADOR, 2);
		Empleado p7 = new Empleado("Isaias", Roles.PROGRAMADOR, 3);
		Empleado p8 = new Empleado("Pedro", Roles.PROGRAMADOR, 4);
		// TESTERS
		Empleado t1 = new Empleado("Bort", Roles.TESTER, 5);
		Empleado t2 = new Empleado("Sofia", Roles.TESTER, 2);
		Empleado t3 = new Empleado("Aixa", Roles.TESTER, 3);
		Empleado t4 = new Empleado("Laura", Roles.TESTER, 2);
		
		// LÍDERES
		getEmpleadosDisponibles().add(l1);
		getEmpleadosDisponibles().add(l2);
		// ARQUITECTOS
		getEmpleadosDisponibles().add(a1);
		getEmpleadosDisponibles().add(a2);
		getEmpleadosDisponibles().add(a3);
		getEmpleadosDisponibles().add(a4);
		// PROGRAMADORES
		getEmpleadosDisponibles().add(p1);
		getEmpleadosDisponibles().add(p2);
		getEmpleadosDisponibles().add(p3);
		getEmpleadosDisponibles().add(p4);
		getEmpleadosDisponibles().add(p5);
		getEmpleadosDisponibles().add(p6);
		getEmpleadosDisponibles().add(p7);
		getEmpleadosDisponibles().add(p8);
		// TESTERS
		getEmpleadosDisponibles().add(t1);
		getEmpleadosDisponibles().add(t2);
		getEmpleadosDisponibles().add(t3);
		getEmpleadosDisponibles().add(t4);
	
		setIncompatibles(new ArrayList<Incompatible>());
		getIncompatibles().add(new Incompatible(t1, p5));
		getIncompatibles().add(new Incompatible(a1, l2));
		getIncompatibles().add(new Incompatible(p6, p7));
		getIncompatibles().add(new Incompatible(p1, a3));
		getIncompatibles().add(new Incompatible(t4, l1));
	}

	public ArrayList<Empleado> getEmpleadosDisponibles() {
		return empleadosDisponibles;
	}

	public void setEmpleadosDisponibles(ArrayList<Empleado> empleadosDisponibles) {
		this.empleadosDisponibles = empleadosDisponibles;
	}

	public ArrayList<Empleado> getEmpleadosNoDisponibles() {
		return empleadosNoDisponibles;
	}

	public void setEmpleadosNoDisponibles(ArrayList<Empleado> empleadosNoDisponibles) {
		this.empleadosNoDisponibles = empleadosNoDisponibles;
	}

	public ArrayList<Incompatible> getIncompatibles() {
		return incompatibles;
	}

	public void setIncompatibles(ArrayList<Incompatible> incompatibles) {
		this.incompatibles = incompatibles;
	}
}


