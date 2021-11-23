package Model.Statements;


import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Types.Type;
import Repository.RepositoryException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStatements {
    ProgramState execute(ProgramState state) throws StatementException, ExpressionException, IOException, RepositoryException;
    IStatements deepCopy();
    MyIDictionary<String, Type> typeCheck(MyIDictionary<String,Type> type) throws StatementException, ExpressionException;

}
