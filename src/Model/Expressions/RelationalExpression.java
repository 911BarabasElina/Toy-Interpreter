package Model.Expressions;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationalExpression implements Expression{

    private final Expression expression1;
    private final Expression expression2;
    private final String operator;

    public RelationalExpression(Expression expression1, Expression expression2, String operator)
    {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyHeap<Integer, Value> heap) throws ExpressionException {
       Value value1,value2;
       value1=expression1.evaluate(table, heap);
       if(!value1.getType().equals(new IntType()))
       {
           throw new ExpressionException("The first operand is not an integer");
       }
       value2=expression2.evaluate(table, heap);
       if(!value2.getType().equals(new IntType()))
       {
           throw new ExpressionException("The second operand is not an integer");
       }

       int integerValue1=((IntValue)value1).getValue();
       int integerValue2=((IntValue)value2).getValue();
        return switch (operator) {
            case "==" -> new BoolValue(integerValue1 == integerValue2);
            case "<=" -> new BoolValue(integerValue1 <= integerValue2);
            case ">=" -> new BoolValue(integerValue1 >= integerValue2);
            case "<" -> new BoolValue(integerValue1 < integerValue2);
            case "!=" -> new BoolValue(integerValue1 != integerValue2);
            case ">" -> new BoolValue(integerValue1 > integerValue2);
            default -> throw new ExpressionException("The operator is invalid");
        };
    }
    public String toString(){return this.expression1+" "+this.operator+" "+this.expression2;}

    @Override
    public Type typeCheck(MyIDictionary<String, Type> type) throws ExpressionException {
        Type type1,type2;
        type1=expression1.typeCheck(type);
        type2=expression2.typeCheck(type);

        if(type1.equals(new IntType()))
        {
            if (type2.equals(new IntType()))
            {
                return new BoolType();
            }
            else
                throw new ExpressionException("The second operand is not an integer");
        }
        else
            throw new ExpressionException("The first operand is not an integer");

    }
}


