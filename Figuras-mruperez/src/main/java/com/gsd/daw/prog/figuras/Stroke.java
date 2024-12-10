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
	
	public Color getColor() {
		return color;
	}

	public int getWidth() {
		return width;
	}

//	public String toSvg() {
//		//TODO: completar
//		return " <svg viewBox=\"0 0 1024 768\" xmlns=\"http://www.w3.org/2000/svg\">"
//				+" </svg>";
//	}
	
}
