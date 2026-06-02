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
 
	/**
	 * Registra una incompatibilidad bidireccional entre dos empleados.
	 *
	 * Agrega a ambos empleados al registro si no existen e inicializa sus listas de
	 * incompatibilidad, asegurando que cada uno quede en la "lista negra" del otro.
	 *
	 * @param e1 El primer empleado.
	 * @param e2 El segundo empleado, incompatible con el primero.
	 */
	public void registrarEmpleadosIncompatibles(Empleado e1, Empleado e2) {
		agregarEmpleadoOdiado(e1, e2);
		agregarEmpleadoOdiado(e2, e1);
	}

	/**
	 * Verifica en el grafo si existe una incompatibilidad registrada entre dos
	 * empleados.
	 * 
	 * @param e1 El primer empleado a consultar.
	 * @param e2 El segundo empleado a consultar.
	 * @return {@code false} si los empleados son incompatibles (no pueden trabajar
	 *         juntos), {@code true} si no hay restricciones entre ellos.
	 */
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