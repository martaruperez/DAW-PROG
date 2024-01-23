package com.gsd.daw.prog;

public class Evaluacion {
	Nota[] notas;
	
	public Evaluacion(Nota[] notas) throws IllegalArgumentException{
		if(!validar(notas)) {
			throw new IllegalArgumentException("ERROR: Las notas introducidas no son validas.");
		}
		this.notas = notas;
	}
	
	private boolean validar(Nota[] notas) {
		int cAsistencia = 0;
		int cActitud = 0;
		int cExamenes = 0;
		int cPracticas = 0;
		
		for(int i = 0; i < notas.length; i++) {
			switch(notas[i].getTipoDeNota()) {
				case 'A':
					cAsistencia++;
				break;
				case 'T':
					cActitud++;
				break;
				case 'P':
					cPracticas++;
				break;
				case 'C':
					cExamenes++;
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
		if(cExamenes < 1) {
			return false;
		}
		//que haya al menos una practica
		if(cPracticas < 1) {
			return false;
		}
		
		return true; 
	}
	
	public void calcular() {
		for(int i = 0; i < notas.length; i++) {
			
			if(notas[i].getTipoDeNota()=='C') {
				if(notas[i].getNota() < 3) {
					System.out.println("0.00");
					return; 
				}
			
			}
			if(notas[i].getTipoDeNota()=='P') {
				if(notas[i].getNota() < 4) {
					System.out.println("0.00");
					return; 
				}
			
			}
		}
		
	}
	
}
