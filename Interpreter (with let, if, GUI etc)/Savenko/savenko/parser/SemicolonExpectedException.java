/*
 * 
 * Savenko Maria(c)
 */
package savenko.parser;

import savenko.ast.Position;

public class SemicolonExpectedException extends ParserException {

     public SemicolonExpectedException(Position position){
          super(position);
     }

}
