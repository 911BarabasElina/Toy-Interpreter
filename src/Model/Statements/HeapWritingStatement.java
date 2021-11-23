package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.Expression;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.RepositoryException;

import java.io.IOException;

public class HeapWritingStatement implements IStatements{

    public StringValue variableName;
    Expression expression;

    public HeapWritingStatement(StringValue variableName1, Expression expression1)
    {
        this.variableName = variableName1;
        this.expression = expression1;
    }

    public String toString()
    {
        return "wH("+this.variableName + "," + this.expression +")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, IOException, RepositoryException {
        if(!(state.getTableOfSymbols().keys().contains(variableName.getValue())))
            throw new StatementException("The variable doesn't appear in the table of symbols");

        Value value = state.getTableOfSymbols().get(variableName.getValue());

        if(!(value.getType() instanceof RefType))
            throw new StatementException("The type of the variable is not a reference type");

        int address = ((RefValue) value).getAddress();
        if(!state.getHeap().keys().contains(address))
            throw new StatementException("The address doesn't appear in the heap");

        Value expressionValue = this.expression.evaluate(state.getTableOfSymbols(),state.getHeap());
        if(!(expressionValue.getType().equals(((RefValue)value).getLocationType())))
            throw new StatementException("Different types of value");
        state.getHeap().replace(address,expressionValue);
        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new HeapWritingStatement(this.variableName,this.expression);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        Type typeVariable=type.get(variableName.getValue());
        Type typeExpression=expression.typeCheck(type);
        if(typeVariable.equals(new RefType(typeExpression)))
            return type;
        else
            throw new StatementException("Heap Writing Statement: right hand side and left hand side have different types");
    }
}

