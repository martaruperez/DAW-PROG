package daw.prog.CalculadoraDeNota_mruperez;

import java.text.DecimalFormat;

public class App {
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
			Evaluacion evaluacion = new Evaluacion (notas);
			double notaFinal = evaluacion.calcularNota();
			
			if(notaFinal == 10.00) {
				System.out.println("MH");
				return;
			}
			 DecimalFormat print = new DecimalFormat("0.00");
			 System.out.println(print.format(notaFinal));
		} 
		catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
			return;
		}
    }
}
