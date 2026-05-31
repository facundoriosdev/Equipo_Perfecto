package model;

import java.util.ArrayList;

public class BackTracking {
	ArrayList<Empleado> empleados;
	Equipo equipoFinal;
	Incompatibilidad gestor;
	RequerimientoEquipo requerimientos;
	int iteraciones = 0;

	public BackTracking(ArrayList<Empleado> empleados, RequerimientoEquipo requerimientos, Incompatibilidad gestor) {
		this.empleados = empleados;
		this.requerimientos = requerimientos;
		this.gestor = gestor;
	}

	public void resolver() {
		this.equipoFinal = new Equipo(empleados);
		Equipo equipoActual = new Equipo(empleados);
		creadorDeEquipos(0, equipoActual);
		this.iteraciones = 0;
	}

	public void creadorDeEquipos(int indice, Equipo equipoActual) {
		this.iteraciones++;

		if (equipoActual.cumpleRequerimientos(requerimientos)) {

			if (equipoActual.getPuntajeTotal() > equipoFinal.getPuntajeTotal()) {
				equipoFinal = equipoActual;

			}
			return;
		}
		if (indice == empleados.size()) {

			return;
		}
		Empleado empleadoActual = empleados.get(indice);

		if (esValidoAgregar(empleadoActual, equipoActual)) {
			equipoActual.agregarMiembro(empleadoActual);
			creadorDeEquipos(indice + 1, equipoActual);
			equipoActual.removerMiembro(empleadoActual);
		}
		creadorDeEquipos(indice + 1, equipoActual);

	}

	private boolean esValidoAgregar(Empleado empleadoActual, Equipo equipoActual) {
		int cantidadActualDeRol = equipoActual.getCantidadDeCiertoRol(empleadoActual.getRol());
		int cantidadMaximaDeRol = requerimientos.getCantidadDeRol(empleadoActual.getRol());
		if (cantidadActualDeRol > cantidadMaximaDeRol) {
			return false;
		}
		for (Empleado actual : equipoActual.getEmpleados()) {
			if (gestor.sonIncompatibles(empleadoActual, actual)) {
				return false;
			}
		}
		return true;

	}

}
