package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.Value;

import java.util.Set;

public class AssignmentStatement implements IStatements{


    private String id;
    private Expression expression;


    public AssignmentStatement(String id, Expression expression)
    {
        this.id=id;
        this.expression=expression;
    }

    public String toString(){ return this.id+ "="+this.expression.toString();}

    @Override
    public ProgramState execute(ProgramState state) throws StatementException, ExpressionException {
        MyIStack<IStatements> stack = state.getExecutionStack();
        MyIDictionary<String, Value> tableOfSymbols = state.getTableOfSymbols();
        Set<String> keys = tableOfSymbols.keys();
        if(keys.contains(id))
        {
            Value value = this.expression.evaluate(tableOfSymbols, state.getHeap());
            Type typeId = (tableOfSymbols.get(id)).getType();
            if(value.getType().equals(typeId))
            {
                tableOfSymbols.put(id,value);
            }
            else throw new ExpressionException("Declared type of variable" + id + " and type of the assigned expression do not match");

        }
        else throw new StatementException("The used variable " + id + " was not declared before");
        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new AssignmentStatement(this.id,this.expression);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {

        Type typeVariable=type.get(id);
        Type typeExpression=expression.typeCheck(type);
        if(typeVariable.equals(typeExpression))
            return type;
        throw new StatementException("Assignment Statement: right hand side and left hand side have different types ");
    }

}
