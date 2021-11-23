package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.Expression;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;
import Repository.RepositoryException;

import java.io.IOException;

public class WhileStatement implements IStatements{

    Expression expression;
    IStatements statements;

    public WhileStatement(Expression expression1, IStatements statements)
    {
        this.expression = expression1;
        this.statements = statements;
    }

    public String toString()
    {
        return "while(" + this.expression + "){" + statements.toString() + "}";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, IOException, RepositoryException {

        Value expressionValue = this.expression.evaluate(state.getTableOfSymbols(),state.getHeap());
        if(!(expressionValue.getType() instanceof BoolType))
            throw new ExpressionException("The expression is not a boolean");

        if(((BoolValue) expressionValue).getValue())
        {
            state.getExecutionStack().push(this);
            state.getExecutionStack().push(statements);
        }

        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new WhileStatement(this.expression , this.statements);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        Type typeExpression=expression.typeCheck(type);
        if(typeExpression.equals(new BoolType()))
        {
            statements.typeCheck(type.clone());
            return type;
        }
        else
            throw new StatementException("While Statement: The condition has not the type bool");
    }
}
