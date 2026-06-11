package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.BackTracking;
import model.Empleado;
import model.Equipo;
import model.Incompatible;
import model.RequerimientoEquipo;
import model.Roles;

public class TestBackTracking {
	private ArrayList<Empleado> empleados;
	private ArrayList<Incompatible> incompatibles;
	private RequerimientoEquipo req;
	private Equipo mejorEquipo;

	@Before
	public void iniciar() {
		empleados = new ArrayList<Empleado>();
		Empleado empleado1 = new Empleado("pepe", Roles.PROGRAMADOR, 1);
		Empleado empleado2 = new Empleado("carlos", Roles.PROGRAMADOR, 5);
		Empleado empleado3 = new Empleado("juan", Roles.PROGRAMADOR, 5);
		empleados.add(empleado1);
		empleados.add(empleado2);
		empleados.add(empleado3);
		
		Empleado empleado4 = new Empleado("chano", Roles.LIDER_PROYECTO, 1);
		Empleado empleado5 = new Empleado("Mauro Daniel Castillo", Roles.LIDER_PROYECTO, 1);
		Empleado empleado6 = new Empleado("chano", Roles.LIDER_PROYECTO, 5);
		empleados.add(empleado5);
		empleados.add(empleado4); 
		empleados.add(empleado6);
		
		empleados.add(new Empleado("messi", Roles.LIDER_PROYECTO, 5));
		
		incompatibles = new ArrayList<Incompatible>();
		incompatibles.add(new Incompatible(empleado1, empleado2));
		Map<Roles, Integer> requerimientoUsuario = new HashMap<Roles, Integer>();
		requerimientoUsuario.put(Roles.LIDER_PROYECTO, 2);
		requerimientoUsuario.put(Roles.PROGRAMADOR, 2);
		req = new RequerimientoEquipo(requerimientoUsuario);
		mejorEquipo = new Equipo();
		mejorEquipo.agregarMiembro(empleado1);
		mejorEquipo.agregarMiembro(empleado2);
		mejorEquipo.agregarMiembro(empleado3);
		mejorEquipo.agregarMiembro(empleado4);
		mejorEquipo.agregarMiembro(empleado5);
		mejorEquipo.agregarMiembro(empleado6);

	}

	@Test
	public void mejorequipo() {
		BackTracking back = new BackTracking(empleados, incompatibles, req);
		back.resolver();
		assertEquals(20, back.getEquipoFinal().getPuntajeTotal());
	}
	

	@Test
	public void sinEsValidoAgregar() {
		assertTrue();
	}

}
