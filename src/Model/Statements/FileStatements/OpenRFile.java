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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;

public class OpenRFile implements IStatements {

    private Expression filePath;

    public OpenRFile(Expression expression)
    {
        this.filePath =expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, FileNotFoundException, RepositoryException {

       //IStatements top_stack = state.getExecutionStack().pop();
       Value result = this.filePath.evaluate(state.getTableOfSymbols(), state.getHeap());
       if(!Objects.equals(result.getType(), new StringType()))
       {
           throw new StatementException("The expression's type is not String Type\n");
       }
       if(state.getFileTable().keys().contains(result.toString()))
       {
           throw new StatementException("This key already exists in the File Table\n");
       }

       File file = new File(result.toString());
       if (!file.exists()) {
           throw new RepositoryException("The file cannot be created");
       }
       BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
       state.getFileTable().put(result.toString(),bufferedReader);

        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new OpenRFile(this.filePath);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        if(!filePath.typeCheck(type).equals(new StringType()))
            throw new StatementException("Open File Statement: The file path must be a string");
        return type;

    }

    @Override
    public String toString() {
        return "openFile(" + filePath.toString() + ")";
    }
}
