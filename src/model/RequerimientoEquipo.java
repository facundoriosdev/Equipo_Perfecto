package model;

import java.util.Map;

public class RequerimientoEquipo {

	Map<Roles, Integer> requerimientosEquipo;

	public RequerimientoEquipo(Map<Roles, Integer> requerimientosUsuario) {
		this.requerimientosEquipo = requerimientosUsuario;
	}

	public int getCantidadDeRol(Roles rol) {
		return requerimientosEquipo.get(rol);
	}

	public int getCantidadNecesaria(Roles rol) {
		Integer cantidad = requerimientosEquipo.get(rol);
		if( cantidad == null)
			return 0;
		return this.requerimientosEquipo.get(rol).intValue();
	}
}
