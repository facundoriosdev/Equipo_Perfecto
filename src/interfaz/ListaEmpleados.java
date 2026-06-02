package interfaz;

import java.util.ArrayList;
import model.*;

public class ListaEmpleados {
	
	ArrayList<Empleado> empleadosDisponibles, empleadosNoDisponibles;
	ArrayList<Incompatible> incompatibles;
	
	public void cargarEmpleados(){
		
		empleadosNoDisponibles = new ArrayList<Empleado>();
		
		empleadosDisponibles = new ArrayList<Empleado>();
		empleadosDisponibles.add(new Empleado("pepe",Roles.PROGRAMADOR, 5));
		empleadosDisponibles.add(new Empleado("carlos",Roles.PROGRAMADOR, 5));
		empleadosDisponibles.add(new Empleado("rodrigo",Roles.PROGRAMADOR, 5));
		empleadosDisponibles.add(new Empleado("arturo",Roles.PROGRAMADOR, 5));
		empleadosDisponibles.add(new Empleado("juan",Roles.PROGRAMADOR, 5));
		empleadosDisponibles.add(new Empleado("cristian",Roles.PROGRAMADOR, 5));
		empleadosDisponibles.add(new Empleado("luis",Roles.PROGRAMADOR, 5));
		empleadosDisponibles.add(new Empleado("chano",Roles.LIDER_PROYECTO, 5));
		empleadosDisponibles.add(new Empleado("Mauro Daniel Castillo",Roles.LIDER_PROYECTO, 5));
		empleadosDisponibles.add(new Empleado("hola",Roles.ARQUITECTO, 1));
		empleadosDisponibles.add(new Empleado("hola1",Roles.ARQUITECTO, 1));
		
		Empleado empleado4 = new Empleado("chan4o", Roles.LIDER_PROYECTO, 1);
		Empleado empleado5 = new Empleado("Mauro Daniel Castillo", Roles.LIDER_PROYECTO, 1);
		Empleado empleado6 = new Empleado("chano", Roles.LIDER_PROYECTO, 5);
		empleadosDisponibles.add(empleado4);
		empleadosDisponibles.add(empleado5);
		empleadosDisponibles.add(empleado6);
	
		incompatibles = new ArrayList<Incompatible>();
		incompatibles.add(new Incompatible(empleado4, empleado5));
	}
}


