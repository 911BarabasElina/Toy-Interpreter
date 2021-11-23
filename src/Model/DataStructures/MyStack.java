package Model.DataStructures;

import java.util.*;

public class MyStack<TElement> implements MyIStack<TElement>
{
    private final Stack<TElement> stack;

    public MyStack() {
        stack = new Stack<TElement>();
    }

    @Override
    public TElement pop() {
        return stack.pop();
    }

    @Override
    public void push(TElement newElement) {
        stack.push(newElement);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<TElement> getReversed() {
        List<TElement> list= Arrays.asList((TElement[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }

    @Override
    public Integer size() {
        return stack.size();
    }

    @Override
    public String toString() {

        return getReversed().toString();

        //return this.stack.toString();
    }



}

