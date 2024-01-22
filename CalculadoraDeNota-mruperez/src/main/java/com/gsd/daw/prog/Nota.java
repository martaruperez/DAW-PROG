package com.gsd.daw.prog;

public class Nota {
	private char tipoDeNota;
	private double nota;
	
	public Nota(String arg) throws IllegalArgumentException {
		
	}
	
	private String[] dividirEntrada(String arg){
		return arg.split("-");
	}
	
	private boolean validar(Nota[] notas) {
		//que no haya menos de 4 notas (una por cada tipo)
		
		//que no haya mas de 20 notas
		
		//que haya al menos un examen
		
		
		//que haya al menos una practica
		
		
		//que esté la nota de Actitud
		
		//que haya solo una nota de Actitud
		
		//que esté la nota de Asistencia
		
		
		//que haya solo una nota de Asistencia
		
		//que las notas sean números
		
		
		//que las notas estén entre 0 y 10 o entre 0 y 1 según corresponda
		
		
		//que todas las notas vengan en el formato pedido [CPTA]-<numero-decimal>

		
		return true;
	}
			
}
