package daw.prog.CalculadoraDeNota_mruperez;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder( MethodOrderer.DisplayName.class )
public class AppTest 
{
	/*
	 * Siempre funciona
	 */
    @Test
    public void siempreFunciona()
    {
        assertTrue( true );
    }

    /*
     * COVERAGE Y ROBUSTEZ: Comprueba que al introducir un tipo de nota de mas de un caracter de largo
     * salta una excepcion
     */
    @DisplayName("Lanza excepcion por tipo de nota muy largo")
    @Test 
    public void excepcionPorLetraMuyLarga() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("Cs-4");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {
    		
    	}
    }
    /*
     * COVERAGE Y ROBUSTEZ: Comprueba que al introducir un caracter en el tipo de nota
     * que no sea 'A', 'C', 'P', o 'T' salte un a excepción
     */
    @DisplayName("Lanza excepcion por tipo de nota no valido")
    @Test 
    public void excepcionPorLetraNoValida() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("X-4");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {

    	}
    }
    /*
     *  COVERAGE Y ROBUSTEZ: Comprueba que al introducir un caracter en la nota
     *  que no es numero salta excepción
     */
    @DisplayName("Lanza excepcion por numero no valido")
    @Test 
    public void excepcionPorNumeroNoValido() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("C-s");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {

    	}
    	
    }
    /*
     * COVERAGE Y ROBUSTEZ: Comprueba que al introducir una nota mayor que 10 salta excepción
     */
    @DisplayName("Lanza excepcion por nota demasiado grande")
    @Test 
    public void excepcionPorNumeroMuyGrande() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("C-10.5");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {

    	}
    }
    /*
     * COVERAGE Y ROBUSTEZ: Comprueba que se lanza una excepcion si a nota de asistencia es mayor
     * que uno.
     */
    @DisplayName("Lanza excepcion por nota de asistencia demasiado grande")
    @Test 
    public void excepcionPorNotaAsistenciaMuyGrande() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("A-1.1");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {

    	}
    }
    /*
     * COVERAGE Y ROBUSTEZ:
     * Comprueba que se lanza una excepcion si a nota de actitud es mayor
     * que uno.
     */
    @DisplayName("Lanza excepcion por nota de actitud demasiado grande")
    @Test 
    public void excepcionPorNotaActitudMuyGrande() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("T-1.1");
    		assertTrue(false);
    	}
    	catch(IllegalArgumentException e) {

    	}
    }
    
    /*
     * Clase Nota:
     * Comprueba que guarda la nota bien
     */
    @DisplayName("Guarda la nota bien")
    @Test 
    public void guardaNota() {
    	Nota t = new Nota("C-10");
    	
    	double devolucion = t.getNota();
    	
    	assertEquals(10, devolucion);
    }
    
    /*
     * Clase Nota:
     * Comprueba que devuelve el tipo de nota correcto
     */
    @DisplayName("Guarda el tipo de nota correcto")
    @Test 
    public void guardaTipoDeNota() {
    	Nota t = new Nota("C-10");
    	
    	double devolucion = t.getTipoDeNota();
    	
    	assertEquals('C', devolucion);
    }
    
    /*
     * COVERAGE Y ROBUSTEZ:
     * Comprueba que lanza una excepción si no se 
     * ha introducido ninguna nota de examen.
     */
    @DisplayName("Lanza excepcion si no hay nota de examen")
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

    	}
    }
    
    /*
     * COVERAGE Y ROBUSTEZ:
     * Comprueba que se lanza una excepcion si no hay nota de practicas
     */
    @DisplayName("Lanza excepcion si no hay nota de practicas")
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

    	}
    }
    
    /*
     * COVERAGE Y ROBUSTEZ:
     * Comprueba que lanza una excepción si hay mas de una nota de 
     * asistencia
     */
    @DisplayName("Lanza excepcion si hay demasiadas notas de asistencia")
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

    	}
    }
    
    /*
     * Clase Evaluacion:
     * Comprueba que se devuelve una nota final correcta si los 
     * argumentos son correctos
     */
    @DisplayName("Calcula una nota final correcta")
    @Test 
    public void calculaNotaConArgumentosCorrectosBien() {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("P-5");
    	Nota actitud = new Nota("T-1");
    	Nota examen = new Nota("C-8");
    	
    	Nota[] notas = {asistencia, practica, actitud, examen};
    	Evaluacion e = new Evaluacion (notas);
    	
    	assertEquals(7.2, e.calcularNota());
    }
    
    /*
     * Clase Evaluacion:
     * Comprueba que se devuelve un cero como nota final
     * si una nota de examen es menor que tres
     */
    @DisplayName("Devuelve cero si una nota de examen es menor que tres")
    @Test 
    public void comprobarQueLaNotaEsCeroSiUnExamenEsMenorQueTres() {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("P-5");
    	Nota actitud = new Nota("T-1");
    	Nota examen = new Nota("C-2.9");
    	
    	Nota[] notas = {asistencia, practica, actitud, examen};
    	Evaluacion e = new Evaluacion (notas);
    	
    	assertEquals(0, e.calcularNota());
    }
    
    /*
     * Comprueba que se devuelve un cero como nota final
     * si una nota de examen es menor que tres
     */
    @DisplayName("Devuelve cero si una nota de examen es menor que tres")
    @Test
    public void comprobarQueLaNotaEsCeroSiUnaPracticaEsMenorQueCuatro() {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("P-3.9");
    	Nota actitud = new Nota("T-1");
    	Nota examen = new Nota("C-10");
    	
    	Nota[] notas = {asistencia, actitud, examen, practica};
    	Evaluacion e = new Evaluacion (notas);
    	
    	assertEquals(0, e.calcularNota());
    }
    /*
     * Comprueba que se lanza una excepcion si hay mas de una nota de actitud 
     */
    @DisplayName("Lanza excepcion si hay mas de una nota de actitud")
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

    	}
    }
    
    /*
     * Comprueba que la clase main funciona correctamente si los argumentos
     * son los esperados 
     */
    @DisplayName("La clase main funciona con argumentos correctos")
    @Test
    public void funcionaClaseMainConArgsCorrectos() {
    	String[] args = {"C-5", "P-8", "A-1", "T-1"};
    	App.main(args);
    	assertTrue(true);
    }
    
    /*
     * Comprueba que la clase main funciona con argumentos incorrectos
     */
    @DisplayName("La clase main funciona con argumentos incorrectos")
    @Test
    public void comprobarManejoDeExcepcionesClaseMain() {
    	String[] args = {"C-5", "P-8", "A-1", "T-x"};
    	App.main(args);
    	assertTrue(true);
    }
    
    /*
     * Comprueba que devuelve cero si una nota de practicas es menor que cuatro
     */
    @DisplayName("Devuelve cero si una nota de practicas es menor que cuatro")
    @Test
    public void comprobarConNotaDeTeoriaMenorQueCuatro() {
    	Nota asistencia = new Nota("A-1");
    	Nota practica = new Nota("P-3.9");
    	Nota actitud = new Nota("T-1");
    	Nota examen = new Nota("C-3.9");
    	
    	Nota[] notas = {asistencia, actitud, examen, practica};
    	Evaluacion e = new Evaluacion (notas);
    	
    	assertEquals(0, e.calcularNota());
    }
    
    /*
     * Comprueba que muestra error y se detiene si hay mas de veinte argumentos
     */
    @DisplayName("Muestra error y se detiene si hay demasiados argumentos")
    @Test
    public void comprobarCuandoHayDemasiadosArgumentos() {
    	String[] args = {"C-5", "P-8", "A-1", "T-1", "C-6", "P-8", "P-8", "C-9", "C-5", "C-5",
    	"C-5", "C-5", "C-7", "P-4", "P-8", "P-8", "P-6.6", "P-5.08", "P-8", "P-8", "C-5"};
    	App.main(args);
    	assertTrue(true);
    }
}
