package Model.DataStructures;

import java.security.KeyStore;
import java.util.Enumeration;
import java.util.Set;
import java.util.Map;
public interface MyIDictionary<TKey,TValue> {

    TValue remove(TKey key);
    TValue get(TKey key);
    TValue put(TKey key, TValue value);
    boolean isEmpty();
    Set<TKey> keys();
    String toString();
    Integer size();
    Set<Map.Entry<TKey, TValue>> entrySet();
    TValue replace(TKey key,TValue new_value);
    Map<TKey, TValue> getContent();
    void setContent(Map<TKey,TValue> new_map);
    MyIDictionary<TKey,TValue> clone();
}
