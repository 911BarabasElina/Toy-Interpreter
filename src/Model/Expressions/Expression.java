package Model.Expressions;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.Types.Type;
import Model.Values.Value;

public interface Expression {
    Value evaluate(MyIDictionary<String, Value> table, MyHeap<Integer, Value> heap) throws ExpressionException;
    String toString();
    Type typeCheck(MyIDictionary<String,Type> type) throws ExpressionException;
}
