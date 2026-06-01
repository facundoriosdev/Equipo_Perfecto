package test;

import static org.junit.Assert.assertEquals;

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
		Empleado empleado1 = new Empleado("pepe", Roles.PROGRAMADOR, 3);
		empleados.add(empleado1);
		Empleado empleado2 = new Empleado("carlos", Roles.PROGRAMADOR, 5);
		empleados.add(empleado2);
		empleados.add(new Empleado("rodrigo", Roles.PROGRAMADOR, 1));
		empleados.add(new Empleado("arturo", Roles.PROGRAMADOR, 2));
		Empleado empleado3 = new Empleado("juan", Roles.PROGRAMADOR, 4);
		empleados.add(empleado3);
		empleados.add(new Empleado("cristian", Roles.PROGRAMADOR, 3));
		empleados.add(new Empleado("luis", Roles.PROGRAMADOR, 1));
		Empleado empleado4 = new Empleado("chano", Roles.LIDER_PROYECTO, 5);
		empleados.add(empleado4);
		Empleado empleado5 = new Empleado("Mauro Daniel Castillo", Roles.LIDER_PROYECTO, 5);
		empleados.add(new Empleado("Mauro Daniel Castillo", Roles.LIDER_PROYECTO, 5));
		incompatibles = new ArrayList<Incompatible>();
		incompatibles.add(new Incompatible(empleado1, empleado2));
		Map<Roles, Integer> requerimientoUsuario = new HashMap<Roles, Integer>();
		requerimientoUsuario.put(Roles.LIDER_PROYECTO, 2);
		requerimientoUsuario.put(Roles.PROGRAMADOR, 2);
		req = new RequerimientoEquipo(requerimientoUsuario);
		mejorEquipo = new Equipo();
		mejorEquipo.agregarMiembro(empleado2);
		mejorEquipo.agregarMiembro(empleado3);
		mejorEquipo.agregarMiembro(empleado4);
		mejorEquipo.agregarMiembro(empleado5);

	}

	@Test
	public void mejorequipo() {
		BackTracking back = new BackTracking(empleados, incompatibles, req);
		back.resolver();
		assertEquals(19, back.getEquipoFinal().getPuntajeTotal());

	}

}
