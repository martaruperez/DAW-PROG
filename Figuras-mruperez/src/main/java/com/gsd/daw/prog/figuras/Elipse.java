package com.gsd.daw.prog.figuras;

public class Elipse {
	private Stroke stroke;
	private Punto centro;
	private int radio1;
	private int radio2;
	
	public Elipse(Punto centro, int radio1, int radio2) {
		this.centro = centro;
		this.radio1 = radio1;
		this.radio2 = radio2;
		this.stroke = new Stroke(new Color((byte)0,(byte)0,(byte)0),1);
	}

	public String toSvg() {
		return "<ellipse cx=\""+this.centro.getX()+"\" cy=\""+this.centro.getY()+
				"\" rx=\""+this.radio1+"\" ry=\""+this.radio2+"\" "
				+this.stroke.toSvg()+"/>";
	}

	public void setStroke(Stroke stroke) throws IllegalArgumentException{
		if(null == stroke) {
			throw new IllegalArgumentException("ERROR: El stroke no puede ser nulo");
		}
		this.stroke = stroke;
	}
}
