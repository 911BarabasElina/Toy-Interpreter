package Model.DataStructures;

public interface MyIList<TElement> {

    TElement remove(int index);
    boolean add(TElement element);
    void add(int index, TElement element);
    boolean isEmpty();
    String toString();
    Integer size();
    TElement get(int index);

}
