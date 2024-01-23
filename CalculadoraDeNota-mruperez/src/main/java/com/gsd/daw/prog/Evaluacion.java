package com.gsd.daw.prog;
import java.text.DecimalFormat;

public class Evaluacion {
	private Nota[] notas;
	private int cExamenes = 0;
	private int cPracticas = 0;
	
	public Evaluacion(Nota[] notas) throws IllegalArgumentException{
		if(!validar(notas)) {
			throw new IllegalArgumentException("ERROR: Las notas introducidas no son validas.");
		}
		this.notas = notas;
	}
	
	private boolean validar(Nota[] notas) {
		int cAsistencia = 0;
		int cActitud = 0;
		
		for(int i = 0; i < notas.length; i++) {
			switch(notas[i].getTipoDeNota()) {
				case 'A':
					cAsistencia++;
				break;
				case 'T':
					cActitud++;
				break;
				case 'P':
					this.cPracticas++;
				break;
				case 'C':
					this.cExamenes++;
				break;
			}
		}
		
		//que no haya menos de 4 notas (una por cada tipo)
		if(cAsistencia != 1) {
			return false;
		}
		if(cActitud != 1) {
			return false;
		}
		//que haya al menos un examen
		if(this.cExamenes < 1) {
			return false;
		}
		//que haya al menos una practica
		if(this.cPracticas < 1) {
			return false;
		}
		
		return true; 
	}
	
	public void calcular() {
		double notasTeoria = 0;
		double notasParticipacion = 0;
		double notaFinal;
		double mediaTeoria;
		
		for(int i = 0; i < notas.length; i++) {
			if(notas[i].getTipoDeNota()=='C') {
				if(notas[i].getNota() < 3) {
					System.out.println("0.00");
					return; 
				}
				notasTeoria += notas[i].getNota();
			}
			if(notas[i].getTipoDeNota()=='P') {
				if(notas[i].getNota() < 4) {
					System.out.println("0.00");
					return; 
				}
				notasTeoria += notas[i].getNota();
			} 
			if(notas[i].getTipoDeNota()=='A' || notas[i].getTipoDeNota()=='T') {
				notasParticipacion += notas[i].getNota();
			}
		}
		

		mediaTeoria = (notasTeoria / (this.cExamenes + this.cPracticas) );
		
		if(mediaTeoria < 4) {
			notaFinal = mediaTeoria;
		}
		else {
			notaFinal = (mediaTeoria * 0.8)+ notasParticipacion; 
		}
		if(notaFinal == 10.00) {
			System.out.println("MH");
			return;
		}
		 DecimalFormat print = new DecimalFormat("#.00");
		 System.out.println(print.format(notaFinal));
	}
	
}
