package Model.Expressions;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.ModelExceptions.ExpressionException;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithmeticExpression implements Expression{

    private Expression expression1;
    private Expression expression2;
    private int operator;

    public ArithmeticExpression(int operator,Expression expression1, Expression expression2)
    {
        this.operator =operator;
        this.expression1=expression1;
        this.expression2=expression2;

    }

    @Override
    public Value evaluate(MyIDictionary<String, Value> table, MyHeap<Integer, Value> heap) throws ExpressionException {
        Value value1, value2;
        value1 = expression1.evaluate(table, heap);
        if (value1.getType().equals(new IntType())) {
            value2 = expression2.evaluate(table,heap);
            if (value2.getType().equals(new IntType())) {
                IntValue integer1 = (IntValue) value1;
                IntValue integer2 = (IntValue) value2;
                int number1, number2;
                number1 = integer1.getValue();
                number2 = integer2.getValue();
                switch (operator) {
                    case 43 :
                        //number1=number1 + number2;
                        return new IntValue(number1 + number2);
                    case 45 :
                        //number1=number1 - number2;
                        return new IntValue(number1 - number2);
                    case 42 :
                        //number1=number1 * number2;
                        return new IntValue(number1 * number2);
                    case 47 :
                        if (number2 == 0) throw new ExpressionException("Division by zero");
                        //number1=number1 / number2;
                        return new IntValue(number1 / number2);
                }
            }
            else throw new ExpressionException("Second operand is not an integer");

        }
        else throw new ExpressionException("First operand is not an integer");
        return null;

    }
    public String toString()
    {
        if(this.operator ==43)
            return this.expression1 + "+"  + this.expression2;
        else if(this.operator ==45)
            return this.expression1 + "-"  + this.expression2;
        else if(this.operator ==42)
            return this.expression1 + "*"  + this.expression2;
        else
            return this.expression1 + "/"  + this.expression2;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> type) throws ExpressionException {
        Type type1,type2;
        type1=expression1.typeCheck(type);
        type2=expression2.typeCheck(type);

        if(type1.equals(new IntType()))
        {
            if (type2.equals(new IntType()))
            {
                return new IntType();
            }
            else
                throw new ExpressionException("The second operand is not an integer");
        }
        else
            throw new ExpressionException("The first operand is not an integer");

    }
}
