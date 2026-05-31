package model;


import java.util.ArrayList;

public class Incompatibilidad {

	private ArrayList<Empleado> listaDeIncompatibilidad;
	

	public boolean sonIncompatibles(Empleado empleado, Empleado empleado2) {
		if (this.listaDeIncompatibilidad.contains(empleado) && this.listaDeIncompatibilidad.contains(empleado2)) {
			return true;
		}
		return false;
		
	}}
