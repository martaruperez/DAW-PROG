package com.gsd.daw.prog;

import com.gsd.daw.prog.figuras.*;

public class App {

	public static void main(String[] args) {
		Circulo p = new Circulo(new Punto(6,7),12);
		
		System.out.println(p.toSvg());
		
		p.setStroke(new Stroke(new Color((byte)45,(byte)0,(byte)68),20));
		
		System.out.println(p.toSvg());
	}
}
