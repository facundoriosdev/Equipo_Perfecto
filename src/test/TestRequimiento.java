package test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import model.*;

public class TestRequimiento {
	
	
	@Test
	public void testRequerimientos() {
		HashMap<Roles, Integer> _rolReq = new HashMap<>(); 
		RequerimientoEquipo req = new RequerimientoEquipo(_rolReq);
		_rolReq.put(Roles.TESTER, 3);


	    assertEquals(3,req.getCantidadDeRol(Roles.TESTER));
	}

	@Test
	public void testRolSinRequerimiento() {
		HashMap<Roles, Integer> rolReq = new HashMap<>(); 
		
	    RequerimientoEquipo req = new RequerimientoEquipo(rolReq);
	    
	    assertEquals(0,req.getCantidadNecesaria(Roles.ARQUITECTO));
	}
	
	

}
