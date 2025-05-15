package com.gsd.daw.prog.figuras;

public class Triangulo {

	private Stroke stroke;
	private Punto[] puntos;
	public Triangulo(Punto[] puntos) throws IllegalArgumentException{
		if(puntos.length!=3) {
			throw new IllegalArgumentException("ERROR: Debe tener tres puntos");
		}
		if(puntos[0]==null||puntos[1]==null||puntos[2]==null) {
			throw new IllegalArgumentException("ERROR: Ningún punto puede tener un valor nulo");
		}
		this.stroke = new Stroke(new Color((byte)0,(byte)0,(byte)0),1);
		this.puntos = puntos;
	}

	public void setStroke(Stroke stroke) throws IllegalArgumentException{
		if(null == stroke) {
			throw new IllegalArgumentException("ERROR: El stroke no puede ser nulo");
		}
		this.stroke = stroke;
	}
	
	public String toSvg() {
		return "<polygon points=\""+this.puntos[0].getX()+"," + this.puntos[0].getY() +" "
                + this.puntos[1].getX() + "," + this.puntos[1].getY() + " "
                + this.puntos[2].getX() + "," + this.puntos[2].getY() +"\" "
                + this.stroke.toSvg()+ "\"/>";
	}
}
