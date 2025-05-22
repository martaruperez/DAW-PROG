package com.gsd.daw.prog;

//import java.util.Arrays;

public class Ip {

    private final int CLASE_A = 127;
    private final int MASCARA_CLASE_A = 8;
    private final int CLASE_B = 191;
    private final int MASCARA_CLASE_B = 16;
    private final int CLASE_C = 223;
    private final int MASCARA_CLASE_C = 24;
    private final int LONGITUD_IP = 32;
    private int[] ip = new int[4];
    private int mascara;
    private char clase;

    public Ip(String[] entrada) {
        if (!validarEntrada(entrada)) {
            throw new IllegalArgumentException("ERROR, USO: CalculadoraCIDR <ip> <bits de máscara>");
        }
        this.ip = dividirIp(entrada[0]);
        this.mascara = Integer.parseInt(entrada[1]);
        asignarClase();
    }

    private boolean validarEntrada(String[] entrada) {
        if (entrada.length != 2) return false;
        if (!esInt(entrada[1])) return false;
        return validarIp(entrada[0]) && validarMascara(Integer.parseInt(entrada[1]));
    }


    private boolean validarIp(String ip) {
        int[] ipDividida = dividirIp(ip);
        if ( ipDividida[0] == 0 || ipDividida[0] == 255) {
            return false;
        }

        for (int i = 0; i < ipDividida.length; i++) {
            int byteIp = ipDividida[i];
            if (byteIp < 0 || byteIp > 255) {
                return false;
            }
        }

        return true;
    }

    private static boolean esInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    private boolean validarMascara(int mascara) {
        if (mascara < 1 || mascara > 32) {
            throw new IllegalArgumentException("ERROR: la máscara [" + mascara + "] es incorrecta, debe estar entre 1 y 32");
        }
        return true;
    }


    private int[] dividirIp(String entradaIp) {
        String[] partes = entradaIp.split("\\.");
        int[] ipDividida = new int[4];

        if (partes.length != 4) {
            throw new IllegalArgumentException("ERROR: la IP introducida [" + entradaIp + "] no es válida. Número de bytes incorrecto.");
        }

        for (int i = 0; i < partes.length; i++) {
            try {
                ipDividida[i] = Integer.parseInt(partes[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ERROR: la IP introducida [" + entradaIp + "] no es válida. El byte " + (i + 1) + " no parece ser un número.");
            }

            if (ipDividida[i] < 0 || ipDividida[i] > 255) {
                throw new IllegalArgumentException("ERROR: la IP introducida [" + entradaIp + "] no es válida. El byte " + (i + 1) + " es mayor que 255 o menor que 0.");
            }
        }

        return ipDividida;
    }


    private void asignarClase() {
        int primerOcteto = ip[0];
        if (primerOcteto <= CLASE_A) clase = 'A';
        else if (primerOcteto <= CLASE_B) clase = 'B';
        else if (primerOcteto <= CLASE_C) clase = 'C';
        else clase = 'O'; 
    }

    private boolean haySubnetting() {
        if (clase == 'O') return false;
        return (clase == 'A' && mascara > MASCARA_CLASE_A) ||
               (clase == 'B' && mascara > MASCARA_CLASE_B) ||
               (clase == 'C' && mascara > MASCARA_CLASE_C);
    }

    @Override
    public String toString() {
        String resultado = ip[0] + "." + ip[1] + "." + ip[2] + "." + ip[3] + "/" + mascara + "\n";
        
        if(clase!='O') {resultado += clase + "\n";}
        else {resultado += "OTRA\n";}
        
        if (haySubnetting()) {resultado += "Subnetting: true";} 
        else {resultado += "Subnetting: false";}
        
        return resultado;
    }
}
