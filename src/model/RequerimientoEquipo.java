package model;

public class RequerimientoEquipo {

	private int lideresProyecto;
	private int arquitectos;
	private int programadores;
	private int testers;

	public RequerimientoEquipo(int lideresProyecto, int arquitectos, int programadores, int testers) {
		this.lideresProyecto = lideresProyecto;
		this.arquitectos = arquitectos;
		this.programadores = programadores;
		this.testers = testers;
	}

	public int getLideresProyecto() {
		return lideresProyecto;
	}

	public int getArquitectos() {
		return arquitectos;
	}

	public int getProgramadores() {
		return programadores;
	}

	public int getTesters() {
		return testers;
	}
	
}
