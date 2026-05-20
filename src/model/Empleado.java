package model;

public class Empleado {
	private String nombre;
	private Roles rol;
	private int calificacion;
	
	public Empleado (String nombre, Roles rol, int calificacion) {
	    this.nombre = nombre;
	    if(nombre == null || nombre.isBlank()) {
	        throw new IllegalArgumentException("El nombre no puede estar vacío");
	    }
	    this.rol = rol;
	    this.calificacion = calificacion;
	    if(calificacion < 1 || calificacion > 5) {
	        throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
	    }
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
