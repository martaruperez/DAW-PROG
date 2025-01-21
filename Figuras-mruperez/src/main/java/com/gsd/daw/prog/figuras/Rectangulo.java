package com.gsd.daw.prog.figuras;

public class Rectangulo {
	private Punto origen;
	private Integer ancho;
	private Integer alto;
	private Stroke stroke;
	
	public Rectangulo(Punto origen, Integer ancho, Integer alto) {
		this.origen = origen;
		this.ancho = ancho;
		this.alto = alto;
		this.stroke = new Stroke(new Color((byte)0,(byte)0,(byte)0),1);
	}
	
	public void setStroke(Stroke stroke) throws IllegalArgumentException{
		if(null == stroke) {
			throw new IllegalArgumentException("ERROR: El stroke no puede ser nulo");
		}
		this.stroke = stroke;
	}
	
	public String toSvg() {
		return "<rect x=\""+this.origen.getX()+"\" y=\""+this.origen.getY()+
				"\" width=\""+this.ancho+"\" height=\""+this.alto+"\" "
				+this.stroke.toSvg()+"/>";
	}
}
