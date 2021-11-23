package Model.DataStructures;

import java.util.List;
import java.util.Stack;

public interface MyIStack<TElement> {

    TElement pop();
    void push(TElement newElement);
    boolean isEmpty();
    String toString();
    List<TElement> getReversed();
    Integer size();

}
