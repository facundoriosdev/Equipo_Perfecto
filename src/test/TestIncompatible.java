package test;

import static org.junit.Assert.*;
import org.junit.Test;
import model.*;
import model.Empleado;

public class TestIncompatible {
	
	
	@Test
	public void testCrearIncompatibilidad() {

	    Empleado a = new Empleado("A", Roles.TESTER, 4);

	    Empleado b = new Empleado("B", Roles.TESTER, 5);

	    Incompatible inc =new Incompatible(a, b);

	    assertEquals(a, inc.getEmpleado1());
	    assertEquals(b, inc.getEmpleado2());
	}

}
