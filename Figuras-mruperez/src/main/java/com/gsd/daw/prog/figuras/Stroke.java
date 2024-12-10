package com.gsd.daw.prog.figuras;

public class Stroke {
 // Representa un trazo (stroke) en SVG.
 // Se soportan los atributos color y anchura
 // del trazo.
	
	private Color color;
	private int width;
	
	public Stroke(Color color, Integer width) throws IllegalArgumentException{
		if(null==color || width == null) {
			throw new IllegalArgumentException("ERROR: Los argumentos no pueden ser valores nulos.");
		}
		this.color = color;
		this.width = width;
	}

	public String toSvg() {
		return "stroke=\"" + this.color.toSvg()+"\" stroke-width=\""+String.valueOf(this.width)+"\" fill=\"none\"";
	}
	
}
