package com.gsd.daw.prog.figuras;

public class Circulo {
	private Punto punto;
	private int radio;
	private Stroke stroke;
	
	public Circulo (Punto punto, int radio) {
		this.punto = punto;
		this.radio = radio;
		this.stroke = new Stroke(new Color((byte)0,(byte)0,(byte)0),1);
	}
	
	public void setStroke(Stroke stroke) throws IllegalArgumentException{
		if(null == stroke) {
			throw new IllegalArgumentException("ERROR: El stroke no puede ser nulo");
		}
		this.stroke = stroke;
	}
	
	public String toSvg() {
		return "<circle cx=\""+this.punto.getX()+"\""+" cy=\""+this.punto.getY()+"\" r=\""+this.radio+"\" "+this.stroke.toSvg()+"/>";
	}
}
