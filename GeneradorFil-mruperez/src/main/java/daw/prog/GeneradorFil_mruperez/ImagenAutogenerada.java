package daw.prog.GeneradorFil_mruperez;

public class ImagenAutogenerada {
	private String comentario = "# Esta es una ImagenFil autogenerada.";
	private int ancho;
	private int altura;
	private int [][] pixeles;
	
	public ImagenAutogenerada(String[] entrada) {
		this.ancho = Integer.parseInt(entrada[0]);
		this.altura = Integer.parseInt(entrada[1]);	
		this.pixeles = new int [this.altura*this.ancho][3];
		generarPixeles();
	}
	
	private void generarPixeles() {
		for (int i = 0; i < this.pixeles.length; i++) {
			for (int j = 0; j < 3; j++) {
				this.pixeles[i][j] = (int)( Math.random()*255);
			}
		}
	}
	
	public void imprimir() {
		System.out.println(this.comentario);
		System.out.println(this.ancho);
		System.out.println(this.altura);
		
		for(int i = 0; i < this.pixeles.length; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(this.pixeles[i][j]);
				if(j < 2) {
					System.out.print(",");
				}
			}
			System.out.println();
		}
		
	}
		
}
