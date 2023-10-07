package co.inventorsoft.academy.collections.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;

public class Range<T extends Comparable<T>> implements Set<T> {

    private TreeSet<T> elements;

    // factory method to create a Range object with start and end points
    public static <T extends Comparable<T>> Range<T> of(T start, T end) {
        return new Range<>(start, end);
    }

    // factory method to create a Range object with custom function for incrementation
    public static <T extends Comparable<T>> Range<T> of(T start, T end, Function<T, T> function) {
        return new Range<>(start, end, function);
    }

    // constructor used for regular start and end points
    private Range(T start, T end) {
        elements = new TreeSet<>();

        // validate that start is not greater than end
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start cannot be greater than end");
        }

        // if start equals end, return an empty set
        if (start.equals(end)) {
            return;
        }

        // populate set based on type by default
        if (start instanceof Integer) {
            for (Integer i = (Integer) start; i <= (Integer) end; i++) {
                elements.add((T) i);
            }
        } else if (start instanceof Double) {
            for (Double i = (Double) start; i <= (Double) end; i += 0.1d) {
                elements.add((T) i);
            }
        } else if (start instanceof Float ) {
            for (Float i = (Float) start; i <= (Float) end; i += 0.1f) {
                elements.add((T) i);
            }
        } else if (start instanceof Byte) {
            for (Byte i = (Byte) start; i <= (Byte) end; i++) {
                elements.add((T) i);
            }
        } else if (start instanceof Short) {
            for (Short i = (Short) start; i <= (Short) end; i++) {
                elements.add((T) i);
            }
        } else if (start instanceof Character) {
            for (Character i = (Character) start; i <= (Character) end; i++) {
                elements.add((T) Character.valueOf(i));
            }
        } else {
            throw new IllegalArgumentException("Unsupported type: " + start.getClass().getName());
        }

    }

    // constructor used for custom function for increments
    private Range(T start, T end, Function function) {
        elements = new TreeSet<>();

        // validate that start is not greater than end
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("Start cannot be greater than end");
        }

        // if start equals end, return an empty set
        if (start.equals(end)) {
            return;
        }

        // populating set using custom function
        if (function != null) {
            T current = start;
            while (current.compareTo(end) <= 0) {
                elements.add(current);
                current = (T) function.apply(current);
            }
        }
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean contains(Object o) {
        return elements.contains(o);
    }

    public Iterator<T> iterator() {
        return elements.iterator();
    }

    public Object[] toArray() {
        return new Object[0];
    }

    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    public boolean add(T t) {
        return elements.add(t);
    }

    public boolean remove(Object o) {
        return elements.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    public boolean addAll(Collection<? extends T> c) {
        return elements.addAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return elements.retainAll(c);
    }

    public boolean removeAll(Collection<?> c) {
        return elements.removeAll(c);
    }

    public void clear() {
        elements.clear();
    }
}
