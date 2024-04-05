package com.gsd.daw.prog;

public class Evaluacion {
	private Nota[] notas;
	private int cantidadDeExamenes = 0;
	private int cantidadDePracticas = 0;
	private double notaFinal;
	
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
					this.cantidadDePracticas++;
				break;
				case 'C':
					this.cantidadDeExamenes++;
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
		if(this.cantidadDeExamenes < 1) {
			return false;
		}
		//que haya al menos una practica
		if(this.cantidadDePracticas < 1) {
			return false;
		}
		
		return true; 
	}
	
	public double calcularNota() {
		double notasParticipacion = 0;
		double mediaTeoria;
		double sumExamenes = 0;
		double sumPracticas = 0;
		
		for(int i = 0; i < notas.length; i++) {
			if(notas[i].getTipoDeNota()=='C') {
				if(notas[i].getNota() < 3) {
					this.notaFinal = 0.00; 
					return this.notaFinal;
				}
				sumExamenes += notas[i].getNota();
			}
			
			if(notas[i].getTipoDeNota()=='P') {
				if(notas[i].getNota() < 5) {
					this.notaFinal = 0.00; 
					return this.notaFinal; 
				}
				sumPracticas += notas[i].getNota();
			} 
			
			if(notas[i].getTipoDeNota()=='A' || notas[i].getTipoDeNota()=='T') {
				notasParticipacion += notas[i].getNota();
			}
		}

		double mediaExamenes = sumExamenes / this.cantidadDeExamenes;
		double mediaPracticas = sumPracticas / this.cantidadDePracticas;
		mediaTeoria = (mediaExamenes + mediaPracticas)/2;
		
		if(mediaTeoria < 4) {
			this.notaFinal = mediaTeoria;
		}
		else {
			//Si la teoría (sobre 10) no es menor que 4
			//la nota final es 80% la teoría, mas actitud, mas asistencia
			this.notaFinal = mediaTeoria*0.80 ;
			this.notaFinal += notasParticipacion;
		}
		
		return this.notaFinal;
	}
	
	public double getNotaFinal() {
		return this.notaFinal;
	}
}
