package model;

import java.util.ArrayList;
import java.util.List;

public class BackTracking {
	private ArrayList<Empleado> empleados;
	private Equipo equipoFinal;
	private GestorCompatibilidad gestor;
	private RequerimientoEquipo requerimientos;
	
	private List<Incompatible> incompatibilidades;
	private long tiempoInicio;
	private long tiempoFin;

	public BackTracking(ArrayList<Empleado> empleados, List<Incompatible> incompatibilidades , RequerimientoEquipo requerimientos) {
		this.empleados = empleados;
		this.requerimientos = requerimientos;
		this.incompatibilidades=incompatibilidades;
		this.gestor = new GestorCompatibilidad();
		
	}

	public void resolver() {
		this.equipoFinal = new Equipo();
		tiempoInicio = System.currentTimeMillis();
		for(Incompatible incom: incompatibilidades) {
			this.gestor.registrarEmpleadosIncompatibles(incom.getEmpleado1() ,incom.getEmpleado2() ); //arma el grafo para consultar luego si son incompatibles
		}
		Equipo equipoActual = new Equipo();
		creadorDeEquipos(0, equipoActual);
		tiempoFin = System.currentTimeMillis();	
		
	}

	private void creadorDeEquipos(int indice, Equipo equipoActual) {
		

		if (equipoActual.cumpleRequerimientos(requerimientos)) {

			if (equipoActual.getPuntajeTotal() > equipoFinal.getPuntajeTotal()) {
				equipoFinal =equipoActual.clonar();

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
			if (!gestor.isPuedenTrabajarJuntos(empleadoActual, actual)) {
				return false;
			}
		}
		return true;

	}

	public Equipo getEquipoFinal() {
		return this.equipoFinal;
	}
	public long getTiempoTotal() {
	    return tiempoFin - tiempoInicio;
	}

}
