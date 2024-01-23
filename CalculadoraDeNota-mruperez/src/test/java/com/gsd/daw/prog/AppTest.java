package com.gsd.daw.prog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder( MethodOrderer.DisplayName.class )
public class AppTest 
{
    @Test
    public void siempreFunciona()
    {
        assertTrue( true );
    }
    
    @Test 
    public void excepcionPorLetraMuyLarga() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("Xs-4");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    @Test 
    public void excepcionPorLetraNoValida() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("X-4");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    @Test 
    public void excepcionPorNumeroNoValido() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("C-s");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    	
    }
    
    @Test 
    public void excepcionPorNumeroMuyGrande() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("C-10.5");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    @Test 
    public void excepcionPorNotaAsistenciaMuyGrande() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("A-1.1");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    @Test 
    public void excepcionPorNotaActitudMuyGrande() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("T-1.1");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    @Test 
    public void devuelveNota() {
    	Nota t = new Nota("C-10");
    	
    	double devolucion = t.getNota();
    	
    	assertEquals(10, devolucion);
    }
    
    @Test 
    public void devuelveTipoDeNota() {
    	Nota t = new Nota("C-10");
    	
    	double devolucion = t.getTipoDeNota();
    	
    	assertEquals('C', devolucion);
    }
    
    @Test 
    public void excepcionPorFaltaDeNotaDeExamen() throws IllegalArgumentException {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("P-5");
    	Nota actitud = new Nota("T-1");
    	
    	Nota[] n = {asistencia, practica, actitud};
    	
    	try {
    		Evaluacion t = new Evaluacion(n);
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    @Test 
    public void lanzaExcepcionPorFaltaDeNotaDePractica() throws IllegalArgumentException {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("C-5");
    	Nota actitud = new Nota("T-1");
    	
    	Nota[] n = {asistencia, practica, actitud};
    	
    	try {
    		Evaluacion t = new Evaluacion(n);
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    @Test 
    public void lanzaExcepcionPorDemasiadasNotasAsistencia() throws IllegalArgumentException {
    	Nota asistencia1 = new Nota("A-1");
    	Nota actitud = new Nota("T-1");
    	Nota examen = new Nota("C-9");
    	Nota practica = new Nota("P-6");
    	Nota asistencia2 = new Nota("A-0.5");
    	
    	Nota[] n = {asistencia1, practica, actitud, examen, asistencia2};
    	
    	try {
    		Evaluacion t = new Evaluacion(n);
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    
    @Test 
    public void calculaNotaNormalBien() {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("P-5");
    	Nota actitud = new Nota("T-1");
    	Nota examen = new Nota("C-8");
    	
    	Nota[] notas = {asistencia, practica, actitud, examen};
    	Evaluacion e = new Evaluacion (notas);
    	
    	e.calcular();
    	assertEquals(7.2, e.getNotaFinal());
    }
    
    @Test 
    public void comprobarQueLaNotaEsCeroSiUnExamenEsMenorQueTres() {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("P-5");
    	Nota actitud = new Nota("T-1");
    	Nota examen = new Nota("C-2.9");
    	
    	Nota[] notas = {asistencia, practica, actitud, examen};
    	Evaluacion e = new Evaluacion (notas);
    	
    	e.calcular();
    	assertEquals(0, e.getNotaFinal());
    }
    
    @Test
    public void comprobarQueLaNotaEsCeroSiUnaPracticaEsMenorQueCuatro() {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("P-3.9");
    	Nota actitud = new Nota("T-1");
    	Nota examen = new Nota("C-10");
    	
    	Nota[] notas = {asistencia, actitud, examen, practica};
    	Evaluacion e = new Evaluacion (notas);
    	
    	e.calcular();
    	assertEquals(0, e.getNotaFinal());
    }
    
    @Test
    public void lanzaExcepcionPorDemasiadasNotasActitud() throws IllegalArgumentException {
    	Nota actitud1 = new Nota("T-1");
    	Nota asistencia = new Nota("A-1");
    	Nota examen = new Nota("C-9");
    	Nota practica = new Nota("P-6");
    	Nota actitud2 = new Nota("T-0.5");
    	
    	Nota[] n = {actitud1, practica, asistencia, examen, actitud2};
    	
    	try {
    		Evaluacion t = new Evaluacion(n);
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    }
    @Test
    public void funcionaClaseMainConArgsCorrectos() {
    	String[] args = {"C-5", "P-8", "A-1", "T-1"};
    	App.main(args);
    	assertTrue(true);
    }
    @Test
    public void comprobarManejoDeExcepcionesClaseMain() {
    	String[] args = {"C-5", "P-8", "A-1", "T-x"};
    	App.main(args);
    	assertTrue(true);
    }
    @Test
    public void comprobarConNotaDeTeoriaMenorQueCuatro() {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("P-3.9");
    	Nota actitud = new Nota("T-1");
    	Nota examen = new Nota("C-3.9");
    	
    	Nota[] notas = {asistencia, actitud, examen, practica};
    	Evaluacion e = new Evaluacion (notas);
    	
    	e.calcular();
    	assertEquals(0, e.getNotaFinal());
    }
    @Test
    public void comprobarCuandoHayDemasiadosArgumentos() {
    	String[] args = {"C-5", "P-8", "A-1", "T-1", "C-6", "P-8", "P-8", "C-9", "C-5", "C-5",
    	"C-5", "C-5", "C-7", "P-4", "P-8", "P-8", "P-6.6", "P-5.08", "P-8", "P-8"};
    	App.main(args);
    	assertTrue(true);
    }
}
