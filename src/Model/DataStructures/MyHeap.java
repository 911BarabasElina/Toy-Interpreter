package Model.DataStructures;

import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MyHeap<TKey,TValue> implements MyIDictionary<TKey,TValue>{


    private Map<TKey,TValue> dictionary;
    int address;

    public MyHeap(){
        dictionary = new HashMap<TKey, TValue>();
        address=0;
    }

    public int getNextFreeAddress()
    {
        return ++address;
    }

    @Override
    public TValue remove(TKey key) {
        return dictionary.remove(key);
    }

    @Override
    public TValue get(TKey key) {
        return dictionary.get(key);
    }

    @Override
    public TValue put(TKey key, TValue value) {
        return dictionary.put(key,value);
    }

    @Override
    public boolean isEmpty(){
        return dictionary.isEmpty();
    }

    @Override
    public Set<TKey> keys() {
        return dictionary.keySet();
    }

    @Override
    public String toString() {
        //return this.dictionary.toString();
        StringBuilder string = new StringBuilder();
        for(Map.Entry<TKey, TValue> entry: this.dictionary.entrySet()) {
            string.append("\n");
            string.append(entry.getKey()).append("->").append(entry.getValue().toString());
        }
        string.append("\n");
        return string.toString();
    }

    @Override
    public Integer size() {
        return this.dictionary.size();
    }

    @Override
    public Set<Map.Entry<TKey, TValue>> entrySet() {
        return this.dictionary.entrySet();
    }

    @Override
    public TValue replace(TKey key, TValue new_value) {
       return this.dictionary.replace(key,new_value);
    }

    @Override
    public Map<TKey, TValue> getContent()
    {
        return this.dictionary;
    }

    @Override
    public void setContent(Map<TKey,TValue> new_map)
    {
        this.dictionary = new_map;
    }

    @Override
    public MyIDictionary<TKey, TValue> clone() {
        MyIDictionary<TKey,TValue> cloneMap= new MyDictionary<>();
        this.dictionary.forEach(cloneMap::put);
        return cloneMap;
    }
}
