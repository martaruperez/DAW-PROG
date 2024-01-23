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
    public void excepcionPorLetraNoValida() throws IllegalArgumentException {
    	Nota t = new Nota("X-4");
    	
    	try {
    		
    	}
    	catch(IllegalArgumentException e) {
    		
    	}
    }
    
    @Test 
    public void excepcionPorNumeroNoValido() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("C-9");
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    	assertTrue(false);
    }
    
    @Test 
    public void excepcionPorNumeroMuyGrande() throws IllegalArgumentException {
    	try {
    		Nota t = new Nota("C-10.5");
    	}
    	catch(IllegalArgumentException e) {
    		assertTrue(true);
    	}
    	assertTrue(false);
    }
}
