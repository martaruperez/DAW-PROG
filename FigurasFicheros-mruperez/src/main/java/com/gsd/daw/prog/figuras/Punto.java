package com.gsd.daw.prog.figuras;

public class Punto {
	private Integer x;
	private Integer y;
	
	public Punto(Integer x, Integer y) throws IllegalArgumentException{
		if(null==x || null==y) {
			throw new IllegalArgumentException("ERROR: No se admiten valores nulos");
		}
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
