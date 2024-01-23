package com.gsd.daw.prog;

public class Nota {
	private char tipoDeNota;
	private double nota;
	
	public Nota(String arg) throws IllegalArgumentException {
		String [] entradaDividida = dividirEntrada(arg);
		
		//que todas las notas vengan en el formato pedido [CPTA]-<numero-decimal>
		if(!esLetraValida(entradaDividida[0])) {
			throw new IllegalArgumentException("ERROR: Entrada no valida. "+"\n"+"Formato pedido: [CPTA]-<numero-decimal>");
		}
		if(!esDouble(entradaDividida[1])) {
			throw new IllegalArgumentException("ERROR: Entrada no valida. "+"\n"+"Formato pedido: [CPTA]-<numero-decimal>");
		}
		
		this.tipoDeNota = entradaDividida[0].charAt(0);
		this.nota = Double.parseDouble(entradaDividida[1]);
		
		if(!validar()) {
			System.err.println("ERROR: Entrada no valida. "+"\n"+"Formato pedido: [CPTA]-<numero-decimal>");
			throw new IllegalArgumentException();
		}
		
	}
	
	private String[] dividirEntrada(String arg){
		return arg.split("-");
	}
	
	private boolean esLetraValida(String cadena) {
		if(cadena.length() != 1) {
			return false;
		}
		if(cadena.charAt(0) == 'C') {
			return true;
		}
		if(cadena.charAt(0) == 'P') {
			return true;
		}
		if(cadena.charAt(0) == 'A') {
			return true;
		}
		if(cadena.charAt(0) == 'T') {
			return true;
		}
		return false;
	}
	
	private boolean esDouble(String cadena) {
		//que las notas sean números
	    try {
	        Double.parseDouble(cadena);
	        
	        return true;
	        
	    } catch (NumberFormatException e) {
	        
	        return false;
	    }
	}
	
	private boolean validar() {
		//que las notas estén entre 0 y 10 o entre 0 y 1 según corresponda
		if(this.nota<0) {
			return false;
		}
		if(this.nota>10) {
			return false;
		}
		if(this.tipoDeNota == 'A' && this.nota > 1) {
			return false;
		}
		if(this.tipoDeNota == 'T' && this.nota > 1) {
			return false;
		}
		
		return true;
	}

	public char getTipoDeNota() {
		return this.tipoDeNota;
	}

	public double getNota() {
		return this.nota;
	}
	
}
