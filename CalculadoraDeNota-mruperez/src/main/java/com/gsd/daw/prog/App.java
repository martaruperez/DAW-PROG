package com.gsd.daw.prog;

public class App 
{
    public static void main( String[] args )
    {
		try {
			Nota[] notas = new Nota [args.length];
			for(int i = 0; i < args.length; i++) {
				notas[i] = new Nota(args[i]);
			}
		} 
		catch(IllegalArgumentException e) {
			System.err.println("ERROR: Argumentos no validos");
			return;
		}
    }
    
}
