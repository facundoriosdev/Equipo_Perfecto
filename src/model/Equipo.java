package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Equipo {
	ArrayList<Empleado> miembros;
	private int puntajeTotal;
	private Map<Roles, Integer> roles;

	public Equipo(ArrayList<Empleado> empleados) {
		this.miembros = empleados;
		this.puntajeTotal = 0;
		this.roles = new HashMap<Roles, Integer>();
	}

	public void agregarMiembro(Empleado miembro) {
		this.miembros.add(miembro);
		this.puntajeTotal += miembro.getCalificacion();
		Roles rol = miembro.getRol();
		this.roles.put(rol, this.roles.get(rol) + 1);

	}

	public void removerMiembro(Empleado miembro) {
		this.miembros.remove(miembro);
		this.puntajeTotal -= miembro.getCalificacion();
		Roles rol = miembro.getRol();
		this.roles.put(rol, this.roles.get(rol) - 1);

	}

	public boolean cumpleRequerimientos(RequerimientoEquipo req) {
		for (Roles rol : Roles.values()) {
			if (this.roles.get(rol) != req.getCantidadNecesaria(rol)) {
				return false;
			}
		}
		return true;

	}

	public int getPuntajeTotal() {
		return this.puntajeTotal;
	}
}
