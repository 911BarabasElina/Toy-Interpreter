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

public class HeapAllocationStatement implements IStatements{

    StringValue variableName;
    Expression expression;

    public HeapAllocationStatement(StringValue variable_name1,Expression expression1)
    {
        this.variableName = variable_name1;
        this.expression = expression1;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, IOException, RepositoryException {

        if(!state.getTableOfSymbols().keys().contains(variableName.getValue()))
            throw new StatementException("The variable doesn't exist in the table of symbols");

        Value variableValue=state.getTableOfSymbols().get(variableName.toString());


        if(!(variableValue.getType() instanceof RefType))
            throw new StatementException("The type of the variable must be a reference type");

        Value expressionValue = this.expression.evaluate(state.getTableOfSymbols(), state.getHeap());
        if(!(expressionValue.getType().equals(((RefValue) variableValue).getLocationType())))
            throw new StatementException("Different types of value");

        int address = state.getHeap().getNextFreeAddress();
        state.getHeap().put(address,expressionValue);
        state.getTableOfSymbols().replace(variableName.toString(), new RefValue(address,((RefValue) variableValue).getLocationType()));
        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new HeapAllocationStatement(this.variableName,this.expression);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        Type typeVariable=type.get(variableName.getValue());
        Type typeExpression=expression.typeCheck(type);
        if(typeVariable.equals(new RefType(typeExpression)))
            return type;
        else
            throw new StatementException("Heap Allocation Statement: right hand side and left hand side have different types ");
    }

    public String toString()
    {
        return "new("+this.variableName +","+this.expression+")";
    }
}
