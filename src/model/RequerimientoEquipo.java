package model;

import java.util.Map;

public class RequerimientoEquipo {

	Map<Roles, Integer> requirimientosEquipo;
	int cantidadDeMiembros;

	public RequerimientoEquipo(Map<Roles, Integer> requerimientosUsuario, int cantMiembrosEntrada) {
		this.requirimientosEquipo = requerimientosUsuario;
		this.cantidadDeMiembros = cantMiembrosEntrada;
	}

	public int getCantidadDeMiembros() {
		return this.cantidadDeMiembros;
	}

	public int getCantidadNecesaria(Roles rol) {
		return this.getCantidadNecesaria(rol);
	}
}
