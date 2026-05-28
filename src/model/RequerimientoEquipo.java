package model;

import java.util.Map;

public class RequerimientoEquipo {

	Map<Roles, Integer> requirimientosEquipo;

	public RequerimientoEquipo(Map<Roles, Integer> requerimientosUsuario) {
		this.requirimientosEquipo = requerimientosUsuario;
	}

	public int getCantidadNecesaria(Roles rol) {
		return this.getCantidadNecesaria(rol);
	}
}
