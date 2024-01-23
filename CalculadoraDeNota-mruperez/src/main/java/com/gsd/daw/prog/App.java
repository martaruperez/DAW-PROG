package com.gsd.daw.prog;

public class App 
{
    public static void main( String[] args ) throws IllegalArgumentException
    {
    	if(args.length < 4) {
    		System.err.println("ERROR: Debe introuducir al menos 4 notas.");
    		return; 
    	}
    	if(args.length > 20) {
    		System.err.println("ERROR: Ha introducido demasiados argumentos.");
    		return;
    	}
    	
		try {
			Nota[] notas = new Nota [args.length];
			for(int i = 0; i < args.length; i++) {
				notas[i] = new Nota(args[i]);
			}
			Evaluacion ev = new Evaluacion (notas);
			ev.calcular();
		} 
		catch(IllegalArgumentException e) {
			System.err.println(e);
			return;
		}
    }
    
}