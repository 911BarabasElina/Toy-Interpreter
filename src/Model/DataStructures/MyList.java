package Model.DataStructures;

import java.util.ArrayList;
import java.util.List;

public class MyList<TElement> implements MyIList<TElement>{

    private final List<TElement> list;


    public MyList() {
        list = new ArrayList<TElement>();
    }

    @Override
    public TElement remove(int index) {
       return list.remove(index);
    }

    @Override
    public boolean add(TElement element) {
        return list.add(element);
    }

    @Override
    public void add(int index, TElement element) {
       list.add(index,element);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() { return this.list.toString();}

    @Override
    public Integer size() {
        return this.list.size();
    }

    @Override
    public TElement get(int index) {
        return this.list.get(index);
    }
}
