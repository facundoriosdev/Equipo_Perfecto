package model;

import java.util.Objects;

public class Empleado {
	private String nombre;
	private Roles rol;
	private int calificacion;

	public Empleado(String nombre, Roles rol, int calificacion) {
		this.nombre = nombre;
		this.rol = rol;
		this.calificacion = calificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public Roles getRol() {
		return rol;
	}

	public int getCalificacion() {
		return calificacion;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Empleado empleado = (Empleado) o;
		return nombre.equals(empleado.nombre);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
}
