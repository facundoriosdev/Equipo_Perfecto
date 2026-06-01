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
		juan = new Empleado("Juan", Roles.PROGRAMADOR, 5);
		maria = new Empleado("Maria", Roles.ARQUITECTO, 1);
		pedro = new Empleado("Pedro", Roles.LIDER_PROYECTO, 3);
	}

	@Test
	public void registrarIncompatibilidadDebeSerBidireccional() {
		gestor.registrarEmpleadosIncompatibles(juan, maria);

		assertFalse("Juan debería odiar a Maria", gestor.isPuedenTrabajarJuntos(juan, maria));
		assertFalse("Maria debería odiar a Juan (Al revés)",
				gestor.isPuedenTrabajarJuntos(maria, juan));
	}

	@Test
	public void empleadosNoRegistradosComoIncompatiblesDeberianSerCompatibles() {
		gestor.registrarEmpleadosIncompatibles(juan, maria);

		assertTrue("Juan y Pedro deberian poder trabajar juntos", gestor.isPuedenTrabajarJuntos(juan, pedro));
		assertTrue("Maria y Pedro deberian poder trabajar juntos", gestor.isPuedenTrabajarJuntos(maria, pedro));
	}

}