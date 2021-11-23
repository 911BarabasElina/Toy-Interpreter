package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.ModelExceptions.StatementException;
import Model.ProgramState;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;

import java.util.Objects;

public class VariableDeclarationStatement implements IStatements{

    private final String id;
    private final Type type;

   public VariableDeclarationStatement(String id, Type type)
   {
       this.id = id;
       this.type = type;
   }

    public String toString() {return this.type + " " + this.id;}
    @Override
    public ProgramState execute(ProgramState state) throws StatementException {
        if(state.getTableOfSymbols().keys().contains(id))
            throw new StatementException("Variable is already declared");
        if(Objects.equals(type.toString(), "int"))
            state.getTableOfSymbols().put(id, type.defaultValue());
        else if(Objects.equals(type.toString(), "bool"))
            state.getTableOfSymbols().put(id, type.defaultValue());
        else if(Objects.equals(type.toString(), "string"))
            state.getTableOfSymbols().put(id, type.defaultValue());
        else
            state.getTableOfSymbols().put(id, type.defaultValue()); //it means that is a ref value

        return null;
    }

    @Override
    public IStatements deepCopy() {
        return new VariableDeclarationStatement(this.id,this.type);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> type) throws StatementException, ExpressionException {
        type.put(this.id,this.type);
        return type;
    }
}
