package Auto.src.Regular;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Кирилл
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParserException{
        Input in = new Input();
        String regExp = new String(in.getLine());
        
        regExp = Parser.markEnd(regExp);
        Automaton automat = Parser.parse( regExp);
        automat.printAutomaton();
        String word = new String(in.getLine());
        System.out.println(automat.checkTail( word, 0 , 0));
    //таблица
    //граф
    }
}
