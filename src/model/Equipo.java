package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Equipo {
	ArrayList<Empleado> miembros;
	String _nombre;
	private int puntajeTotal;
	private Map<Roles, Integer> roles;
	
	

	public Equipo() {
		this.miembros= new ArrayList<Empleado>();
		this.puntajeTotal = 0;
		this.roles = new HashMap<Roles, Integer>();
		for(Roles rol: Roles.values()) {
			roles.put(rol, 0);
			
		}
	}
	
	public void setNombre(String nombre) {
		_nombre = nombre;
	}
	
	public String getNombre() {
		return _nombre;
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
		for (Roles rol :Roles.values()) {
			if (this.roles.get(rol) != req.getCantidadNecesaria(rol)) {
				return false ;
			}
		}
		return true;

	}

	public int getPuntajeTotal() {
		return this.puntajeTotal;
	}
	public int getCantidadDeCiertoRol(Roles rol) {
		return roles.get(rol);
	}
	public ArrayList <Empleado>getEmpleados() {
		return miembros;
	}
	public Equipo clonar() {
        Equipo equipoClon = new Equipo();
        for(Empleado emp: this.miembros) {
        	equipoClon.agregarMiembro(emp);
        }
        return equipoClon;
    }
	
	@Override
	public String toString() {
		StringBuilder infoEquipo = new StringBuilder();
		infoEquipo.append("Integrantes: ").append(System.lineSeparator());
		
		for(Empleado m : miembros) {
			infoEquipo.append(m.getNombre()).append(System.lineSeparator());
		}
		
		infoEquipo.append("Puntaje total del equipo: ").append(puntajeTotal).append(System.lineSeparator());
		infoEquipo.append("Roles del equipo: ").append(roles);
		return infoEquipo.toString();
		
	}
}
