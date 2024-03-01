package top.yomi1i.framework.common.core;

import java.io.Serial;
import java.io.Serializable;

/**
 * key value 键值对
 *
 * @author abcran
 * @since 2024/3/1
 */
public class KeyValue<K, V> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private K key;
    private V value;

    public KeyValue() {
    }

    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
