package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.*;
import org.junit.Before;
import org.junit.Test;

public class TestEquipo {
	
	private ArrayList<Empleado> _empleado;
	private Equipo _equipo;
	
	@Before
	public void testEquipo() {
		_equipo = new Equipo();
		_empleado = new ArrayList<>();
		_empleado.add(new Empleado("Juan", Roles.ARQUITECTO, 5));
		_empleado.add(new Empleado("Juana", Roles.PROGRAMADOR, 1));
		_empleado.add(new Empleado("Julian", Roles.PROGRAMADOR, 3));
		for(Empleado e : _empleado) {
			_equipo.agregarMiembro(e);
		}
	}
	
	@Test
	public void testCantidadCiertoRol() {
		assertEquals(2, _equipo.getCantidadDeCiertoRol(Roles.PROGRAMADOR));
	}
	@Test 
	public void testCantidadEmpleados() {
		assertEquals(3, _equipo.getEmpleados().size());
	}
	@Test 
	public void testClonar() {
		assertEquals(_equipo, _equipo.clonar());
	}
}
