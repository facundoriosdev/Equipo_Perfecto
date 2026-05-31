package model;

public class Incompatible {
	private Empleado empleado1;
	private Empleado empleado2;

	public Incompatible(Empleado empleado1, Empleado empleado2) {
		this.empleado1 = empleado1;
		this.empleado2 = empleado2;
	}

	public Empleado getEmpleado1() {
		return empleado1;
	}

	public Empleado getEmpleado2() {
		return empleado2;
	}

}
