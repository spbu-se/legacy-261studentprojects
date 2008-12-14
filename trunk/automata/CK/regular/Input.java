package Regular;


/**
 *
 * @author Кирилл
 */
import java.io.*;

public class Input {
   private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
   
   public String getLine() {
       String line = null;
       try {
          line = in.readLine(); 
        } catch ( IOException e ) { }
        return line;
    }
    public static char getChar(){
        int charCode = -1;
        
        try {
            charCode = System.in.read();
        } catch ( IOException e ){
            System.out.println("Error : wrong expression\n");
            System.exit(0);
        }
        return (char)charCode;
        
    }
}