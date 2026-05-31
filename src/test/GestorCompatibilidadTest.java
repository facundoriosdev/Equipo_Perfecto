package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.Empleado;
import model.GestorCompatibilidad;
import model.Roles;

public class GestorCompatibilidadTest {

	private GestorCompatibilidad gestor;
	private Empleado juan;
	private Empleado maria;
	private Empleado pedro;

	@Before
	 public void setUp() {
		gestor = new GestorCompatibilidad();
		juan = new Empleado("Juan", Roles.PROGRAMADOR, 8);
		maria = new Empleado("Maria", Roles.ARQUITECTO, 9);
		pedro = new Empleado("Pedro", Roles.LIDER_PROYECTO, 7);
	}

	@Test
	public void registrarIncompatibilidadDebeSerBidireccional() {
		gestor.registrarEmpleadosIncompatibles(juan, maria);

		assertTrue("Juan debería odiar a Maria", gestor.isPuedenTrabajarJuntos(juan, maria));
		assertTrue("Maria debería odiar a Juan (Al revés)",
				gestor.isPuedenTrabajarJuntos(maria, juan));
	}

	@Test
	public void empleadosNoRegistradosComoIncompatiblesDeberianSerCompatibles() {
		gestor.registrarEmpleadosIncompatibles(juan, maria);

		assertFalse("Juan y Pedro deberian poder trabajar juntos", gestor.isPuedenTrabajarJuntos(juan, pedro));
		assertFalse("Maria y Pedro deberian poder trabajar juntos", gestor.isPuedenTrabajarJuntos(maria, pedro));
	}

}