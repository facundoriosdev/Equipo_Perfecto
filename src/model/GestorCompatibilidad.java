package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Gestor de compatibilidades para la validación de equipos de trabajo.
 * 
 * Esta clase mantiene un grafo bidireccional de incompatibilidades utilizando
 * un {@code Map}. Cada clave es un objeto {@code Empleado}, y su valor es un
 * {@code Set} que contiene exclusivamente el listado de empleados con los
 * cuales no puede trabajar.
 */
public class GestorCompatibilidad {

	private Map<Empleado, Set<Empleado>> grafoIncompatibilidades;

	public GestorCompatibilidad() {
		this.grafoIncompatibilidades = new HashMap<Empleado, Set<Empleado>>();
	}
 
	//basicamente un grafo
	public void registrarEmpleadosIncompatibles(Empleado e1, Empleado e2) {
		agregarEmpleadoOdiado(e1, e2);
		agregarEmpleadoOdiado(e2, e1);
	}

	
	public boolean isPuedenTrabajarJuntos(Empleado e1, Empleado e2) {
		Set<Empleado> odiados = grafoIncompatibilidades.get(e1);
		return odiados == null || !odiados.contains(e2);
	}

	private void agregarEmpleadoOdiado(Empleado base, Empleado agregado) {
		Set<Empleado> listado = this.grafoIncompatibilidades.get(base);
		if (listado == null) {
			listado = new HashSet<Empleado>();
			this.grafoIncompatibilidades.put(base, listado);
		}
		listado.add(agregado);
	}

}