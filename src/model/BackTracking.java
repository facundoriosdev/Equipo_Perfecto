package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingWorker;

import interfaz.InterfazPrincipal;

public class BackTracking extends SwingWorker <Equipo, Object> {
	ArrayList<Empleado> empleados;
	Equipo equipoFinal;
	GestorCompatibilidad gestor;
	RequerimientoEquipo requerimientos;
	private List<Incompatible> incompatibilidades;
	
	//para mostrar estadisticas
	private long llamadasRecursivas = 0;
	private long casosBase = 0;
	private long solucionesValidas = 0;
	private long tiempoInicio;
	private long tiempoFin;
	private long totalEstimado;
	private int[] estadoRamas;
	
	private InterfazPrincipal interfaz;
	private String nombreEquipo;
	private int ultimoProgreso = 0;

	public BackTracking(ArrayList<Empleado> empleados, List<Incompatible>incompatibilidades, RequerimientoEquipo requerimientos, InterfazPrincipal interfaz,String nombreEquipo) {
		this.empleados = empleados;
		this.requerimientos = requerimientos;
		this.incompatibilidades=incompatibilidades;
		this.gestor = new GestorCompatibilidad();
		this.interfaz = interfaz;
	    this.nombreEquipo = nombreEquipo;
	    this.estadoRamas = new int[empleados.size() + 1];
	}

	public void resolver() {
		this.equipoFinal = new Equipo();
		tiempoInicio = System.currentTimeMillis();
        totalEstimado = (long)Math.pow(2, empleados.size());
		for(Incompatible incom: incompatibilidades) {
			this.gestor.registrarEmpleadosIncompatibles(incom.getEmpleado1() ,incom.getEmpleado2() ); //arma el grafo para consultar luego si son incompatibles
		}
		Equipo equipoActual = new Equipo();
		creadorDeEquipos(0, equipoActual);
	    tiempoFin = System.currentTimeMillis();	
	    setProgress(100);
	}

	private void creadorDeEquipos(int indice, Equipo equipoActual) {
		llamadasRecursivas++;
		if (llamadasRecursivas % 500 == 0) { 
			reportarProgreso();
		}
		if (equipoActual.cumpleRequerimientos(requerimientos)) {
		    solucionesValidas++;
			if (equipoActual.getPuntajeTotal() > equipoFinal.getPuntajeTotal()) {
				equipoFinal =equipoActual.clonar();
			}
			return;
		}
		if (indice == empleados.size()) {
			casosBase++;
			return;
		}
		Empleado empleadoActual = empleados.get(indice);
		estadoRamas[indice] = 1;
		if (esValidoAgregar(empleadoActual, equipoActual)) {
			equipoActual.agregarMiembro(empleadoActual);
			creadorDeEquipos(indice + 1, equipoActual);
			equipoActual.removerMiembro(empleadoActual);
		}
		estadoRamas[indice] = 2; 
		creadorDeEquipos(indice + 1, equipoActual);
		estadoRamas[indice] = 0;
	}
	
	private void reportarProgreso() {
		double progresoTotal = 0.0;
		for (int i = 0; i < empleados.size(); i++) {
			double pesoNivel = 100.0 / Math.pow(2, i + 1);
			if (estadoRamas[i] == 2) {
				progresoTotal += pesoNivel; 
			}
		}
		
		int progresoActual = (int) Math.min(99, progresoTotal);
		if (progresoActual > ultimoProgreso) {
			ultimoProgreso = progresoActual;
			setProgress(progresoActual);
		}
	}

	private boolean esValidoAgregar(Empleado empleadoActual, Equipo equipoActual) {
		int cantidadActualDeRol = equipoActual.getCantidadDeCiertoRol(empleadoActual.getRol());
		int cantidadMaximaDeRol = requerimientos.getCantidadDeRol(empleadoActual.getRol());
		if (cantidadActualDeRol >= cantidadMaximaDeRol) {
			return false;
		}
		for (Empleado actual : equipoActual.getEmpleados()) {
			if (!gestor.isPuedenTrabajarJuntos(empleadoActual, actual)) {
				return false;
			}
		}
		return true;

	}
	@Override
	protected Equipo doInBackground() {
	    resolver();
	    return equipoFinal;
	}
	@Override
	protected void done() {
		setProgress(100); 
		try {
	        Equipo equipo = get();
	        interfaz.equipoCreado(
	                equipo,
	                nombreEquipo);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	//getters
	public long getLlamadasRecursivas() {
	    return llamadasRecursivas;
	}
	public long getCasosBase() {
	    return casosBase;
	}
	public long getSolucionesValidas() {
	    return solucionesValidas;
	}
	public long getTiempoTotal() {
	    return tiempoFin - tiempoInicio;
	}
	public Equipo getEquipoFinal() {
		return this.equipoFinal;
	}

}
