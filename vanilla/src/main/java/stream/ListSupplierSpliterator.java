package stream;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;

// https://docs.oracle.com/javase/8/docs/api/java/util/Spliterator.html
public class ListSupplierSpliterator<T> implements Spliterator<T> {

    private List<T> items;
    private int pointer = 0;

    private final Supplier<List<T>> supplier;

    public ListSupplierSpliterator(Supplier<List<T>> supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean tryAdvance(Consumer<? super T> action) {
        if (items == null || pointer >= items.size()) {
            items = supplier.get();
            if (items == null || items.isEmpty()) {
                return false;
            }
            pointer = 0;
        }
        action.accept(items.get(pointer));
        pointer++;
        return true;
    }

    @Override
    public Spliterator<T> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return Long.MAX_VALUE;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}