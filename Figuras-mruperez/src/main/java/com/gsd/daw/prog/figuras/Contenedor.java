package com.gsd.daw.prog.figuras;

import java.util.ArrayList;
import java.util.List;

public class Contenedor {
    private int ancho;
    private int alto;
    private List<Object> figuras;
    private String svg;

    public Contenedor(int ancho, int alto) {
        if (ancho == 0 || alto == 0) {
            throw new IllegalArgumentException("ERROR: El ancho y el alto no pueden ser nulos.");
        }
        this.ancho = ancho;
        this.alto = alto;
        this.figuras = new ArrayList<>();
        this.svg = "<svg width=\"" + ancho + "\" height=\"" + alto + "\" xmlns=\"http://www.w3.org/2000/svg\" style=\"border:1px solid black;\">";
    }

    public void addCirculo(Circulo figura) {
        if (figura == null) {
            throw new IllegalArgumentException("ERROR: La figura no puede ser nula.");
        }
        figuras.add(figura);
        this.svg+="\n"+figura.toSvg();
    }

    public void addElipse(Elipse figura) {
        if (figura == null) {
            throw new IllegalArgumentException("ERROR: La figura no puede ser nula.");
        }
        figuras.add(figura);
        this.svg+="\n"+figura.toSvg();
    }

    public void addLinea(Linea figura) {
        if (figura == null) {
            throw new IllegalArgumentException("ERROR: La figura no puede ser nula.");
        }
        figuras.add(figura);
        this.svg+="\n"+figura.toSvg();
    }

    public void addLineaPoligonal(LineaPoligonal figura) {
        if (figura == null) {
            throw new IllegalArgumentException("ERROR: La figura no puede ser nula.");
        }
        figuras.add(figura);
        this.svg+="\n"+figura.toSvg();
    }

    public void addPoligono(Poligono figura) {
        if (figura == null) {
            throw new IllegalArgumentException("ERROR: La figura no puede ser nula.");
        }
        figuras.add(figura);
        this.svg+="\n"+figura.toSvg();
    }

    public void addRectangulo(Rectangulo figura) {
        if (figura == null) {
            throw new IllegalArgumentException("ERROR: La figura no puede ser nula.");
        }
        figuras.add(figura);
        this.svg+="\n"+figura.toSvg();
    }

    public String toSvg() {
    	return this.svg+"\n</svg>";
    }
}

