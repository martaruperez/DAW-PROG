package com.gsd.daw.prog;

import com.gsd.daw.prog.figuras.*;

public class App {

	public static void main(String[] args) {
		Color prueba = new Color((byte)25, (byte)20, (byte)-300);
		
		System.out.println(prueba.toSvg());
		
		Stroke p = new Stroke(prueba, 10);
		
		System.out.println(p.toSvg());
	}
}
