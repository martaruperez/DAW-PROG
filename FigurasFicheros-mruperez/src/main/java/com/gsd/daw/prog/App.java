package com.gsd.daw.prog;

import com.gsd.daw.prog.lenguaje.*;

public class App {

	public static void main(String[] args) {
		
		if(args.length < 2) {
			System.out.println("ERROR: No ha introducido suficientes argumentos.");
			return;
		}
		if(args.length > 2) {
			return;
		}
		
		Traductor t = new Traductor(args[0], args[1]);
		t.traducir();
	}
}
