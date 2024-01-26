package daw.prog.AjusteBrilloFil_mruperez;

public class Utilidades {
	public static boolean esNumero(String cadena) {
		int cNum= 0; 
		
		if(cadena.charAt(0) == '-') {
			cNum++;
		}
		for(int i = 0; i < cadena.length(); i++) {
			if(cadena.charAt(i) >= '0' && cadena.charAt(i) <= '9' ){
				cNum++;
			}
		}
		
		if(cNum == cadena.length()) {
			return true; 
		}
		
		return false;
	}
}
