package com.gsd.daw.prog;

import com.gsd.daw.prog.figuras.*;

public class App {

	public static void main(String[] args) {
		Elipse p = new Elipse(new Punto(6,1),1,37);
		
		System.out.println(p.toSvg());
		
		p.setStroke(new Stroke(new Color((byte)45,(byte)0,(byte)68),20));
		
		System.out.println(p.toSvg());
	}
}
