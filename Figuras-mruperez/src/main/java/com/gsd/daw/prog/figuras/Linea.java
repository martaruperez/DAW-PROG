package com.gsd.daw.prog.figuras;

public class Linea {
	private Punto punto1;
	private Punto punto2;
	private Stroke stroke;
	
	public Linea(Punto punto1, Punto punto2) {
		this.punto1 = punto1;
		this.punto2 = punto2;
		this.stroke = new Stroke(new Color((byte)0,(byte)0,(byte)0),1);
	}
	
	public void setStroke(Stroke stroke) throws IllegalArgumentException{
		if(null == stroke) {
			throw new IllegalArgumentException("ERROR: El stroke no puede ser nulo");
		}
		this.stroke = stroke;
	}
	
	public String toSvg() {
		return "<line x1=\""+this.punto1.getX()+"\" x2=\""+this.punto2.getX()+
				"\" y1=\""+this.punto1.getY()+"\"y2=\""+this.punto2.getY()
				+this.stroke.toSvg()+"/>";
	}
}
