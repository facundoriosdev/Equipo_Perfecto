package interfaz;

import java.util.ArrayList;
import model.*;

public class ListaEmpleados {
	
	ArrayList<Empleado> empleadosDisponibles, empleadosNoDisponibles;
	ArrayList<Incompatible> incompatibles;
	ArrayList<Equipo> equiposLista;
	
	public void cargarEmpleados(){
		
		empleadosNoDisponibles = new ArrayList<Empleado>();
		empleadosDisponibles = new ArrayList<Empleado>();

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
		empleadosDisponibles.add(l1);
		empleadosDisponibles.add(l2);
		// ARQUITECTOS
		empleadosDisponibles.add(a1);
		empleadosDisponibles.add(a2);
		empleadosDisponibles.add(a3);
		empleadosDisponibles.add(a4);
		// PROGRAMADORES
		empleadosDisponibles.add(p1);
		empleadosDisponibles.add(p2);
		empleadosDisponibles.add(p3);
		empleadosDisponibles.add(p4);
		empleadosDisponibles.add(p5);
		empleadosDisponibles.add(p6);
		empleadosDisponibles.add(p7);
		empleadosDisponibles.add(p8);
		// TESTERS
		empleadosDisponibles.add(t1);
		empleadosDisponibles.add(t2);
		empleadosDisponibles.add(t3);
		empleadosDisponibles.add(t4);
	
		incompatibles = new ArrayList<Incompatible>();
		incompatibles.add(new Incompatible(t1, p5));
		incompatibles.add(new Incompatible(a1, l2));
		incompatibles.add(new Incompatible(p6, p7));
		incompatibles.add(new Incompatible(p1, a3));
		incompatibles.add(new Incompatible(t4, l1));
	}
}


