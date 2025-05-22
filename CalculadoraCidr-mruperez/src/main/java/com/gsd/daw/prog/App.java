package com.gsd.daw.prog;
import java.util.Arrays;
public class App 
{
    public static void main( String[] args )
    {
        try {
     	   Ip ip = new Ip(args);
           System.out.println(ip.toString());
        }catch(IllegalArgumentException e) {
     	   System.out.println(e.getMessage());
     	   return;
        }
    }
}
