package Model.Expressions;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class HeapReadingExpression implements Expression{

    public Expression expression;

    public HeapReadingExpression(Expression expression)
    {
        this.expression = expression;
    }

    public String toString()
    {
        return "rH("+this.expression+")";
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> type) throws ExpressionException {
        Type type1 = expression.typeCheck(type);
        if(type1 instanceof RefType refType)
        {
            return refType.getInner();
        }
        else throw new ExpressionException("The rh argument is not a RefType");
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyHeap<Integer, Value> heap) throws ExpressionException {

        Value value;
        value = this.expression.evaluate(table,heap);
        if(!(value.getType() instanceof RefType))
            throw new ExpressionException("This expression could not be evaluated to a referenced type value");
        int address;
        address = ((RefValue) value).getAddress();

        if(!(heap.keys().contains(address)))
            throw new ExpressionException("This address doesn't exist in the heap");
        Value result;
        result=heap.get(address);
        return result;
    }
}
