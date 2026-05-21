package model;

public class Empleado {
	private String nombre;
	private Roles rol;
	private int calificacion;
	
	public Empleado (String nombre, Roles rol, int calificacion) {
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
}