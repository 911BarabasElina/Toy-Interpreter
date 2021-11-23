package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.Type;

public class PrintStatement implements IStatements{

    private Expression expression;

    public PrintStatement(Expression expression)
    {
        this.expression=expression;
    }

    public String toString() { return "print("+expression.toString()+")";}

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        state.getOutput().add(expression.evaluate(state.getTableOfSymbols(), state.getHeap()));
        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new PrintStatement(this.expression);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        expression.typeCheck(type);
        return type;
    }
}
