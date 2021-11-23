package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyStack;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Types.Type;
import Repository.RepositoryException;

import java.io.IOException;

public class ForkStatement implements IStatements{

    private IStatements threadStatement;

    public ForkStatement(IStatements statement)
    {
        this.threadStatement =statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException, IOException, RepositoryException {

        if(threadStatement == null)
            return null;
        return new ProgramState(new MyStack<>(),
                state.getTableOfSymbols().clone(),
                state.getOutput(),
                state.getFileTable(),
                state.getHeap(),
                threadStatement,ProgramState.getNewProgramId());

    }

    @Override
    public IStatements deepCopy() {
        return new ForkStatement(this.threadStatement);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        threadStatement.typeCheck(type.clone());
        return type;
    }

    public String toString() {

        return "fork("+this.threadStatement +")";
    }
}
