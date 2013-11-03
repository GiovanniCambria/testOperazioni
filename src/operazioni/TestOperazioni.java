/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package operazioni;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author debug
 */
public class TestOperazioni {

    // Questo in realtà (in modo più pulito si fa con Reflection, ma
    // adesso lo facciamo così per non complicare il codice
    Map<String, Boolean> testDaFare = new ConcurrentHashMap<String, Boolean>();
    int testOK = 0;
        
    public void run() throws Exception {
        
        testDaFare.put("testSomma", false);
        testDaFare.put("testDivisione", false);

        for( String nomeTest : testDaFare.keySet() )  {
            eseguiTest(nomeTest, 4, 3);
        }
        
        System.out.println("Numero di test effettuati:" + testDaFare.size() );
        System.out.println("Numero di test effettuati con successo:" + testOK );        
        for( String test : testDaFare.keySet() )  {
             System.out.print( (testDaFare.get(test).booleanValue() == true)? "." : "F" );
        }
        System.out.println(); 
    }
    
    private void eseguiTest( String nomeTest, int par1, int par2 ) throws Exception {
        setup();
        try {
            // Anche qui sarebbe da usare Reflection
            if( nomeTest.equals("testSomma") ){
                testSomma(par1, par2);
            }
            else if( nomeTest.equals("testDivisione") ){
                 testDivisione(par1, par2);
            }
            System.out.println("Test " + nomeTest + ": successo");
            testDaFare.put(nomeTest, true);
            testOK += 1;
        } catch(Exception ex ){
            testDaFare.put(nomeTest, false);
            System.out.println("Test " + nomeTest + "- errore: " + ex.getMessage() );        
        }
        tearDown();        
    }

    public boolean assertTrue( boolean r ) throws Exception {
        if( r == true ) {
            return r;
        }
        throw new Exception("qualcosa è andato storto");
    }

    
    Operazioni o;

    private void setup() {
        o = new Operazioni();            
    }
    
    private void tearDown() {
        o = null;
    }    
    
    public void testSomma(int a, int b) throws Exception {
        assertTrue( o.somma(a, b) == a+b );
    }
    
    public void testDivisione(int a, int b) throws Exception
    {
        assertTrue( o.divisione(a, b) == a/b );
    }
}
