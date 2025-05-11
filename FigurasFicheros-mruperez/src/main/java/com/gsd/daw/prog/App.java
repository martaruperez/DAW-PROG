package com.gsd.daw.prog;

import com.gsd.daw.prog.lenguaje.*;

public class App {

	public static void main(String[] args) throws IllegalArgumentException{
		
		if(args.length < 2) {
			throw new IllegalArgumentException("ERROR: No ha introducido suficientes argumentos.");
		}
		if(args.length > 2) {
			return;
		}
		
		Traductor t = new Traductor(args[0], args[1]);
		t.traducir();
	}
}
