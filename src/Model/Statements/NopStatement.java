package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Types.Type;

public class NopStatement implements IStatements{

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new NopStatement();
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        return type;
    }
}

