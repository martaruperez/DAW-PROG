package com.gsd.daw.prog.figuras;

public class Circulo {
	private Punto punto;
	private int radio;
	
	public Circulo (Punto punto, int radio) {
		this.punto = punto;
		this.radio = radio;
	}
	
	public String toSvg() {
		return "";
	}
}
