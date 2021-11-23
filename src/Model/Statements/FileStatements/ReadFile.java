package Model.Statements.FileStatements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.Expression;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Statements.IStatements;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.RepositoryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class ReadFile implements IStatements {

    private final Expression expression;
    private final StringValue variableName;

    public ReadFile(Expression expression, StringValue variable_name)
    {
        this.expression=expression;
        this.variableName =variable_name;

    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, IOException, RepositoryException {
        //state.getExecutionStack().pop();
        if(!state.getTableOfSymbols().keys().contains(variableName.getValue()) || !state.getTableOfSymbols().get(variableName.getValue()).getType().toString().equals("int"))
        {
            throw new StatementException("The variable was not declared or its type is not int\n");
        }
        Value result=this.expression.evaluate(state.getTableOfSymbols(), state.getHeap());
        if(!Objects.equals(result.getType(), new StringType()))
        {
            throw new StatementException("The result is not a string value\n");
        }

        if(!state.getFileTable().keys().contains(result.toString()))
        {
            throw new StatementException("There is no file with the name: "+ result +"\n");
        }

        BufferedReader file_descriptor=state.getFileTable().get(result.toString());
        String line=file_descriptor.readLine();
        if(line==null)
        {
            state.getTableOfSymbols().put(variableName.getValue(),new IntValue(0));
        }

        int value=Integer.parseInt(line);
        state.getTableOfSymbols().put(variableName.getValue(),new IntValue(value));
        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new ReadFile(this.expression,this.variableName);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        Type typeVariable = type.get(variableName.getValue());
        Type typeExpression = expression.typeCheck(type);
        if(!typeVariable.equals(new IntType()))
            throw new StatementException("Read File Statement: the type of the variable must be an int");
        if(!typeExpression.equals(new StringType()))
            throw new StatementException("Read File Statement: the type of the expression must be a string");

        return type;
    }

    @Override
    public String toString() {
        return "readFile(" + expression.toString() + "," + variableName + ")";
    }
}
