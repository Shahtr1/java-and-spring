package Behavioral.interpreter;

public class ORExpression implements Expression {

    private final Expression expr1;

    private final Expression expr2;

    public ORExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}
