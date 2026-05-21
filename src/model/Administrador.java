package model;

import java.util.ArrayList;

public class Administrador {
	
	ArrayList<Empleado> empleados;
	ArrayList<Incompatibilidad> incompatibilidades;

	
	public boolean sonIncompatibles(Empleado empleado1, Empleado empleado2) {	
		for (Incompatibilidad inc : incompatibilidades) {
			boolean caso1 = inc.getEmpleado1().equals(empleado1) && inc.getEmpleado2().equals(empleado2);
			boolean caso2 = inc.getEmpleado2().equals(empleado2) && inc.getEmpleado1().equals(empleado1);
			if (caso1 || caso2) {
				return true;
			}
		}
		return false;
	}
}
