/*
 * Test
 * Antonov Kirill(c), 2010
 */

package GUI;

import gui.Program;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProgramTest {

    @Test
    public void testSimpleLet() throws Exception {
        String programContent = "let x:int = 5 in x + 99";
        Program instance = new Program(programContent);
        String expResult = "104";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    
    @Test
    public void testSimpleIf() throws Exception {
        String programContent = "if 4<5 then true else false";
        Program instance = new Program(programContent);
        String expResult = "true";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testIfWithEquality() throws Exception {
        String programContent = "if 4=5 then true else false";
        Program instance = new Program(programContent);
        String expResult = "false";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testIfWithUnEquality() throws Exception {
        String programContent = "if 4!=5 then true else false";
        Program instance = new Program(programContent);
        String expResult = "true";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testFunction() throws Exception {
        String programContent = "let f:(int->int) = fun x:(int->int) -> 10 in f(9)";
        Program instance = new Program(programContent);
        String expResult = "10";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testDoubleLet() throws Exception {
        String programContent = "let x:int = 3 in let y:int = 2 in x+y";
        Program instance = new Program(programContent);
        String expResult = "5";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    
    @Test
    public void testLetSq2() throws Exception {
        String programContent = "let x:int = 4 in x; 5; 6-3; 90/10 end";
        Program instance = new Program(programContent);
        String expResult = "9";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    @Test
    public void testLetBool() throws Exception {
        String programContent = "let x:bool = true in x";
        Program instance = new Program(programContent);
        String expResult = "true";
        String result = instance.Interpret();
        assertEquals(expResult, result);
    }

    
}
