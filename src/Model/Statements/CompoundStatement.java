package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Types.Type;

public class CompoundStatement implements IStatements {
    private final IStatements first;
    private final IStatements second;

    public CompoundStatement(IStatements first, IStatements second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        return first.toString() + ";" + second.toString();
    }
    public ProgramState execute(ProgramState state) throws StatementException {

        MyIStack<IStatements> stack=state.getExecutionStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new CompoundStatement(this.first,this.second);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
         MyIDictionary<String,Type> typEnv1 = first.typeCheck(type);
        return second.typeCheck(typEnv1);
    }
}
