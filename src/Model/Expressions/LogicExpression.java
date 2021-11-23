package Model.Expressions;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.Objects;

public class LogicExpression implements Expression{

    private final Expression expression1;
    private final Expression expression2;
    private final int operator;

    public LogicExpression(Expression expression1, Expression expression2, int operator)
    {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }


    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyHeap<Integer, Value> heap) throws ExpressionException {
       Value number1= this.expression1.evaluate(table, heap);
       if(Objects.equals(number1.getType().toString(), "bool"))
       {
           Value number2= this.expression2.evaluate(table,heap);
           BoolValue value1 = (BoolValue)number1;
           if(Objects.equals(number2.getType().toString(), "bool"))
           {
               BoolValue value2 = (BoolValue)number2;
               switch(operator) {
                   case 1 :
                        return new BoolValue(value1.getValue()||value2.getValue());
                   case 2:
                       return new BoolValue(value1.getValue()&&value2.getValue());
               }
           }
           else throw new ExpressionException("Operand2 is not a boolean");
       }
       else throw new ExpressionException("Operand1 is not a boolean");
       return null;
    }

    public String toString() {return this.expression1 + " " + this.operator + " "  + this.expression2;}

    @Override
    public Type typeCheck(MyIDictionary<String, Type> type) throws ExpressionException {
        Type type1,type2;
        type1=expression1.typeCheck(type);
        type2=expression2.typeCheck(type);

        if(type1.equals(new BoolType()))
        {
            if (type2.equals(new BoolType()))
            {
                return new BoolType();
            }
            else
                throw new ExpressionException("The second operand is not a boolean");
        }
        else
            throw new ExpressionException("The first operand is not a boolean");

    } //TODO

}

