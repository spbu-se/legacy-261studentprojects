/*
 * Identifier node
 * Antonov Kirill(c), 2010
 */
package ast;

import types.Type;

public class Identifier extends Expression {

    private String identificator;
    private Type type;

    public Identifier(String new_identificator) {
        identificator = new_identificator;
    }

    public Identifier(String new_identificator, Type new_type) {
        identificator = new_identificator;
        type = new_type;
    }

    public String GetName() {
        return identificator;
    }

    public Type GetType() {
        return type;
    }
}
