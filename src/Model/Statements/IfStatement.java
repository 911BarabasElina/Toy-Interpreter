package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.Objects;

public class IfStatement implements IStatements{

    private final Expression expression;
    private IStatements thenStatement;
    private IStatements elseStatement;

    public IfStatement(Expression expression,IStatements thenStatement, IStatements elseStatement)
    {
        this.expression=expression;
        this.thenStatement=thenStatement;
        this.elseStatement=elseStatement;
    }
    public String toString(){ return "(if("+ expression.toString()+") then(" +thenStatement.toString()  +")else("+elseStatement.toString()+"))";}


    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {

        Value condition = expression.evaluate(state.getTableOfSymbols(), state.getHeap());
        if(!Objects.equals(condition.getType().toString(), "bool"))
            throw new ExpressionException("Conditional expression is not a boolean");
        {
            BoolValue value = (BoolValue)condition;
            if(value.getValue())
                state.getExecutionStack().push(this.thenStatement);
            else state.getExecutionStack().push(this.elseStatement);

        }

        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new IfStatement(this.expression,this.thenStatement,this.elseStatement);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        Type typeExpression = expression.typeCheck(type);
        if(typeExpression.equals(new BoolType()))
        {
            thenStatement.typeCheck(type.clone());
            elseStatement.typeCheck(type.clone());
            return type;
        }
        else
            throw new StatementException("If Statement: The condition has not the type bool");

    }
}
