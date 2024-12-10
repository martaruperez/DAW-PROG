package com.gsd.daw.prog.figuras;

public class Color {
	private Byte red;
	private Byte green;
	private Byte blue;
	
	Color(Byte red, Byte green, Byte blue)throws IllegalArgumentException{
		if(null == red || null == green || null == blue) {
			throw new IllegalArgumentException("ERROR: Los argumentos no pueden ser valores nulos");
		}
		if(null == red || null == green || null == blue) {
			throw new IllegalArgumentException("ERROR: Los argumentos no pueden ser valores nulos");
		}
		
		this.blue = blue;
		this.red = red;
		this.green = green; 
	}
	
	public String toSvg() {
		return "";
	}
}
