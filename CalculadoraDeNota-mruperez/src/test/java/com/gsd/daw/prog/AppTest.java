package com.gsd.daw.prog;

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
}
