package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import model.Empleado;
import model.Roles;

public class TestEmpleado {
	
	private	Empleado _empleado = new Empleado("Juana", Roles.ARQUITECTO, 4);
	
	
	@Test
	public void testNombre() {
		assertEquals("Juana", _empleado.getNombre());
	}
	
	@Test
	public void testRol() {
		assertEquals(Roles.ARQUITECTO, _empleado.getRol());
	}
	@Test
	public void testCalificacion() {
		assertEquals(4, _empleado.getCalificacion());
	}
}
