package daw.prog.AjusteBrilloFil_mruperez;

import java.io.FileNotFoundException;

public class App 
{
    public static void main( String[] args )
    {
        if(args.length != 2) {
        	System.out.println("ERROR: Los argumentos no son correctos.");
        	System.out.println("USO: <fichero> <ajuste del brillo>");
        	return;
        }
        if(!Utilidades.esNumero(args[1])) {
        	System.out.println("ERROR: ["+args[1]+"] no es un numero.");
        	return;
        }
        if(Integer.parseInt(args[1]) < -255 ) {
        	System.out.println("ERROR: El ajuste de brillo ["+args[1]+"] es "
        			+ "demasiado pequeño."
        			+ "\n"+ "Debe ser un numero comprendido entre -255 y 255.");
        	return;
        }
        if(Integer.parseInt(args[1]) > 255 ) {
        	System.out.println("ERROR: El ajuste de brillo ["+args[1]+"] es "
        			+ "demasiado grande."
        			+ "\n"+ "Debe ser un numero comprendido entre -255 y 255.");
        	return;
        }
        
        ImagenFil img = null;
        try {
        	img = new ImagenFil(args[0]);
        }
        catch(Exception e) {
        	System.out.println(e.getMessage());
        	return;
        }
        img.ajustarBrillo(args[1]);
    }
  
}
