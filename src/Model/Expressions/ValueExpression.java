package Model.Expressions;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExpression implements Expression {

    private final Value value;


    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyHeap<Integer, Value> heap) throws ExpressionException {
        return value;

    }
    public String toString() {return this.value.toString();}

    @Override
    public Type typeCheck(MyIDictionary<String, Type> type) throws ExpressionException {
        return value.getType();
    }
}

