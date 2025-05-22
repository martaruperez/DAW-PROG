package com.gsd.daw.prog;

public class Format {
	private String formato;
	
	public Format(String formato) {
		this.formato=formato;
	}
	
	public String aplicar(String modificador) {
		String resultado="";
		int j=0;
		
		for(int i=0;i<this.formato.length();i++) {
			if(i>=(this.formato.length() - modificador.length())) {
				resultado+=String.valueOf(modificador.charAt(j));
				j++;
			}
			else {
				resultado+=String.valueOf(this.formato.charAt(i));
			}
		}
		
		return resultado;
	}
	public String superponer (String modificado){
		String resultado="";
		int j=0;
		if(modificado.length()<formato.length()) {
			return "ERROR: No se puede superponer";
		}
		for(int i=0; i<modificado.length();i++) {
			if(i<(modificado.length()- this.formato.length())) {
				resultado+=modificado.charAt(i);
			}
			else {
				resultado+=this.formato.charAt(j);
				j++;
			}
		}
		
		return resultado;
	}
}
