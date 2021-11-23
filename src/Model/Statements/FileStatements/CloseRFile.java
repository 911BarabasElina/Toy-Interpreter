package Model.Statements.FileStatements;

import Model.DataStructures.MyIDictionary;
import Model.Expressions.Expression;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Statements.IStatements;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.Value;
import Repository.RepositoryException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class CloseRFile implements IStatements {

    private Expression filePath;

    public CloseRFile(Expression expression)
    {
        this.filePath = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, IOException, RepositoryException {

        Value result = this.filePath.evaluate(state.getTableOfSymbols(), state.getHeap());
        if(!Objects.equals(result.getType(), new StringType()))

        {
            throw new StatementException("The result is not a string value");
        }
        if(!state.getFileTable().keys().contains(result.toString()))
        {
            throw new StatementException("There is no file with the name: "+ result +"\n");
        }
        BufferedReader file_descriptor=state.getFileTable().get(result.toString());
        file_descriptor.close();
        state.getFileTable().remove(result.toString());

        return null;

    }

    @Override
    public IStatements deepCopy() {
        return new CloseRFile(this.filePath);

    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        if(!filePath.typeCheck(type).equals(new StringType()))
            throw new StatementException("Close File Statement: The file path must be a string");
        return type;

    }

    @Override
    public String toString() {
        return "closeFile(" + filePath.toString() + ")";
    }
}
