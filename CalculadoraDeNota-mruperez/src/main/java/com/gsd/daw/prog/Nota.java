package com.gsd.daw.prog;

public class Nota {
	private double[] notaExamenes = {-1};
	private double[] notaPracticas = {-1};
	private int[] notaActitud = {-1};
	private int[] notaAsistencia = {-1};
	
	public Nota(String[] args) throws IllegalArgumentException {
		for(int i = 0; i < args.length; i++) {
			almacenarNotas(args[i]);
		}
		if(!validar(args)) {
			throw new IllegalArgumentException();
		}
	}
	
	private void almacenarNotas(String arg) {
		// TODO: quitar esto de aqui y hacer un metodo de split
		String[][] notaPartida = new String[] [2];
		notaPartida = arg.split("-");
		
		switch (notaPartida[0]) {
			case "A":
				
			break;
			case "T":
				almacenarNotasActitud (notaPartida[1]);
			break;
			case "C":
				
			break;
			case "P":
				
			break;
			default: 
			
			break;
		}
	}
	
	private void almacenarNotasActitud (String stringNota) {
		if(this.notaActitud[0]==-1) {
			this.notaActitud[0]=Integer.parseInt(stringNota);
			return;
		}
		
		int[] notaActitud = new int [this.notaActitud.length+1];
		for(int i = 0; i < this.notaActitud.length; i++) {
			notaActitud[i] = this.notaActitud[i];
		}
		notaActitud[this.notaActitud.length]=Integer.parseInt(stringNota);
		this.notaActitud = notaActitud;
	}
	
	private boolean validar(String[] args) {
		//que no haya menos de 4 notas (una por cada tipo)
		if(args.length < 1) {
			return false;
		}
		
		//que no haya mas de 20 notas
		if(args.length > 20) {
			return false;
		}
		
		//que haya al menos un examen
		
		
		//que haya al menos una practica
		
		
		//que esté la nota de Actitud
		if(this.notaActitud[0] == -1) {
			return false;
		}
		
		//que haya solo una nota de Actitud
		if(this.notaActitud.length != 1) {
			return false;
		}
		
		//que esté la nota de Asistencia
		
		//que haya solo una nota de Asistencia
		if(this.notaAsistencia.length != 1) {
			return false;
		}
		
		//que las notas sean números
		
		
		//que las notas estén entre 0 y 10 o entre 0 y 1 según corresponda
		
		
		//que todas las notas vengan en el formato pedido [CPTA]-<numero-decimal>
		
		return true;
	}
	
	public double[] getNotaExamenes() {
		return this.notaExamenes;
	}

	public double[] getNotaPracticas() {
		return this.notaPracticas;
	}

	public int[] getNotaActitud() {
		return this.notaActitud;
	}

	public int[] getNotaAsistencia() {
		return this.notaAsistencia;
	}
	
	
}
