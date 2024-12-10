package com.gsd.daw.prog.figuras;

public class Color {
	private byte red;
	private byte green;
	private byte blue;
	
	public Color(Byte red, Byte green, Byte blue)throws IllegalArgumentException{
		if(null == red || null == green || null == blue) {
			throw new IllegalArgumentException("ERROR: Los argumentos no pueden ser valores nulos");
		}
		this.blue = blue ;
		this.red = red ;
		this.green = green ; 
	}
	
	public String toSvg() {
		//El valor del Byte se usa como Unsigned
		return "rgb("+ String.valueOf(Byte.toUnsignedInt(this.red))+","+ String.valueOf(Byte.toUnsignedInt(this.green))+","+ String.valueOf(Byte.toUnsignedInt(this.blue))+")";
	}
}
