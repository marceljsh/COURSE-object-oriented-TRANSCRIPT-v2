package academic.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ReversedIterable<T> implements Iterable<T> {
    private final List<T> original;

    public ReversedIterable(List<T> original) {
        this.original = original;
    }

    public Iterator<T> iterator() {
        final ListIterator<T> i = original.listIterator(original.size());

        return new Iterator<T>() {
            public boolean hasNext() {
                return i.hasPrevious();
            }

            public T next() {
                return i.previous();
            }

            public void remove() {
                i.remove();
            }
        };
    }

    public static <T> ReversedIterable<T> reverse(List<T> original) {
        return new ReversedIterable<T>(original);
    }
}
