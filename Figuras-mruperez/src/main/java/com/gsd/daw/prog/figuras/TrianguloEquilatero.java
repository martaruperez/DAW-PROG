package com.gsd.daw.prog.figuras;

public class TrianguloEquilatero {
	private Punto origen;
	private Integer tamanoLado;
	private Stroke stroke;
	public TrianguloEquilatero(Punto origen, Integer tamanoLado) throws IllegalArgumentException{
		if(null==origen || null==tamanoLado) {
			throw new IllegalArgumentException("ERROR: Ningún valor puede tener un valor nulo");
		}
		this.stroke = new Stroke(new Color((byte)0,(byte)0,(byte)0),1);
		this.origen = origen;
		this.tamanoLado = tamanoLado; 
	}
	
	public void setStroke(Stroke stroke) throws IllegalArgumentException{
		if(null == stroke) {
			throw new IllegalArgumentException("ERROR: El stroke no puede ser nulo");
		}
		this.stroke = stroke;
	}
	
	public String toSvg() {
		return "";
	}
}
