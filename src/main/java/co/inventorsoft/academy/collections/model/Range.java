package co.inventorsoft.academy.collections.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

public class Range<T extends Comparable<T>> implements Set<T> {

    public static void main(String[] args) {
        Range range = Range.of(Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1);
    }

    private T start;
    private T end;
    private Function<T, T> function;

    // method to create a Range object with start and end points
    public static <T extends Comparable<T>> Range<T> of(T start, T end) {
        Objects.requireNonNull(start, "Start value must not be null");
        Objects.requireNonNull(end, "End value must not be null");
        return new Range<>(start, end);
    }

    // method to create a Range object with custom function for incrementation
    public static <T extends Comparable<T>> Range<T> of(T start, T end, Function<T, T> function) {
        Objects.requireNonNull(start, "Start value must not be null");
        Objects.requireNonNull(end, "End value must not be null");
        Objects.requireNonNull(function, "Function must not be null");
        return new Range<>(start, end, function);
    }

    private Range(T start, T end) {
        this.start = start;
        this.end = end;
        this.function = elem -> {
            Number num = (Number) elem;
            if (elem instanceof Double) {
                return (T) Double.valueOf(num.doubleValue() + 0.1d);
            } else if (elem instanceof Float) {
                return (T) Float.valueOf(num.floatValue() + 0.1f);
            } else if (elem instanceof Long) {
                return (T) Long.valueOf(num.longValue() + 1);
            } else if (elem instanceof Integer) {
                return (T) Integer.valueOf(num.intValue() + 1);
            } else if (elem instanceof Short) {
                return (T) Short.valueOf((short) (num.shortValue() + 1));
            } else {
                return (T) Byte.valueOf((byte) (num.byteValue() + 1));
            }
        };
    }

    private Range(T start, T end, Function function) {
        this.start = start;
        this.end = end;
        this.function = function;
    }

    public int size() {
        int count = 0;
        for (T t : this) {
            count++;
        }
        return count;
    }

    public boolean isEmpty() {
        return start.compareTo(end) == 0;
    }

    public boolean contains(Object o) {
        if (o == null || !(o instanceof Comparable)) {
            return false;
        }

        T other = (T) o;
        return other.compareTo(start) >= 0 && other.compareTo(end) <= 0;
    }

    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private T current = start;

            @Override
            public boolean hasNext() {
                return current.compareTo(end) <= 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                T result = current;
                current = Range.this.function.apply(current);
                return result;
            }
        };
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("toArray operation is not supported for Range");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("toArray operation is not supported for Range");
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException("add operation is not supported for Range");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("remove operation is not supported for Range");
    }

    public boolean containsAll(Collection<?> c) {
        for (Object elem : c) {
            if (!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("addAll operation is not supported for Range");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll operation is not supported for Range");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("removeAll operation is not supported for Range");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("clear operation is not supported for Range");
    }

}
