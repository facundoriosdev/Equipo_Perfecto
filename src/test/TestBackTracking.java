package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.Empleado;
import model.Roles;


public class TestBackTracking {
	private ArrayList<Empleado> empleados;
	
	@Before
	public void iniciar() {
	empleados = new ArrayList<Empleado>();
	
	empleados.add(new Empleado("pepe",Roles.PROGRAMADOR, 5));
	empleados.add(new Empleado("carlos",Roles.PROGRAMADOR, 5));
	empleados.add(new Empleado("rodrigo",Roles.PROGRAMADOR, 5));
	empleados.add(new Empleado("arturo",Roles.PROGRAMADOR, 5));
	empleados.add(new Empleado("juan",Roles.PROGRAMADOR, 5));
	empleados.add(new Empleado("cristian",Roles.PROGRAMADOR, 5));
	empleados.add(new Empleado("luis",Roles.PROGRAMADOR, 5));
	empleados.add(new Empleado("chano",Roles.LIDER_PROYECTO, 5));
	empleados.add(new Empleado("Mauro Daniel Castillo",Roles.LIDER_PROYECTO, 5));
	}
	
	@Test
	public void test()	 {
		
		fail("Not yet implemented");
	}

}
