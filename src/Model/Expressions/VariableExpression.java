package Model.Expressions;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.Types.Type;
import Model.Values.Value;

public class VariableExpression implements Expression{

    private String id;

    public VariableExpression (String id) {
        this.id=id;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyHeap<Integer, Value> heap) throws ExpressionException {
        if(!table.keys().contains(id))
            throw new ExpressionException("Variable " + id + " is not defined");
        return table.get(id);
    }
    public String toString() {return this.id;}

    @Override
    public Type typeCheck(MyIDictionary<String, Type> type) throws ExpressionException {
        return type.get(id);
    }

}