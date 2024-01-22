package com.gsd.daw.prog;

public class App 
{
    public static void main( String[] args )
    {
		try {
			Nota nota = new Nota(args);
		} 
		catch(IllegalArgumentException e) {
			System.err.println("ERROR: Argumentos no validos");
		}
    }
}
