package cx.rain.mc.inkraft.data.story;

import org.jetbrains.annotations.Nullable;

import java.util.Set;

public interface IDataRegistry<K, V> {
    void add(K key, V value);

    void clear();

    @Nullable V get(K key);

    boolean has(K key);

    Set<K> getAll();
}
