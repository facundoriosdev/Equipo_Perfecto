package model;

public class Empleado {
	private String nombre;
	private Roles rol;
	private int calificacion;
	private boolean disponible;

	public Empleado(String nombre, Roles rol, int calificacion) {
		this.nombre = nombre;
		this.rol = rol;
		this.calificacion = calificacion;
		this.disponible = true;
	}
	
	public boolean getDisponible() {
		return disponible;
	}
	public void setDisponible(boolean e) {
		disponible = e;
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
	public String toString() {
		return nombre;
	}
}