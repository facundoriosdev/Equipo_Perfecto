package model;

import java.util.Map;

public class RequerimientoEquipo {

	Map<Roles, Integer> requerimientosEquipo;
	int cantidadDeMiembros;

	public RequerimientoEquipo(Map<Roles, Integer> requerimientosUsuario, int cantMiembrosEntrada) {
		this.requerimientosEquipo = requerimientosUsuario;
		this.cantidadDeMiembros = cantMiembrosEntrada;
	}
	public int getCantidadDeRol(Roles rol) {
		return requerimientosEquipo.get(rol);
	}

	public int getCantidadDeMiembros() {
		return this.cantidadDeMiembros;
	}

	public int getCantidadNecesaria(Roles rol) {
		return this.getCantidadNecesaria(rol);
	}
}
