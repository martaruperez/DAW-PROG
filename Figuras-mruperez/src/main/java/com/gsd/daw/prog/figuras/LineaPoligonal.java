package com.gsd.daw.prog.figuras;

public class LineaPoligonal {
	private Punto[] puntos;
	private Stroke stroke;

	public LineaPoligonal(Punto[] puntos) {
		this.puntos = puntos;
		this.stroke = new Stroke(new Color((byte)0,(byte)0,(byte)0),1);
	}
	
	private String puntosToString() {
		String s = "";
		for(int i = 0; i < puntos.length; i++) {
			s+=puntos[i].getX()+","+puntos[i].getY();
	        if (i < puntos.length - 1) {
	            s += " ";
	        }
		}
		return s;
	}
	
	public String toSvg() {
		return "<polyline points=\""+puntosToString()+"\" "
				+this.stroke.toSvg()+"/>";
	}
	
	public void setStroke(Stroke stroke) throws IllegalArgumentException{
		if(null == stroke) {
			throw new IllegalArgumentException("ERROR: El stroke no puede ser nulo");
		}
		this.stroke = stroke;
	}
}
