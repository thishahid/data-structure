import java.util.Iterator;

@SuppressWarnings("unchecked")
public class Array<T> implements Iterable<T> {
    private T[] arr;
    private int len = 0;
    private int capacity = 0;

    // FIX 1: Correct constructor chaining syntax
    public Array() {
        this(16);
    }

    public Array(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal capacity: " + capacity);
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() { return len; }

    public boolean isEmpty() { return size() == 0; }

    public T get(int index) {
        if (index >= len || index < 0) throw new IndexOutOfBoundsException();
        return arr[index];
    }

    public void set(int index, T elem) {
        if (index >= len || index < 0) throw new IndexOutOfBoundsException();
        arr[index] = elem;
    }

    public void clear() {
// Optimization: Only clear up to len, not capacity
        for (int i = 0; i < len; i++)
            arr[i] = null;
        len = 0;
    }

    public void add(T elem) {
// FIX 3: Resize only when strictly necessary (len == capacity)
        if (len == capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2; // Double the size

            T[] new_arr = (T[]) new Object[capacity];
// FIX 6: Iterate only up to existing len (i < len)
            for (int i = 0; i < len; i++) {
                new_arr[i] = arr[i];
            }
            arr = new_arr;
        }
        arr[len++] = elem;
    }

    public T removeAt(int rmIndex) {
// FIX 2: Correct logic operator is || (OR), not && (AND)
        if (rmIndex >= len || rmIndex < 0) throw new IndexOutOfBoundsException();

        T data = arr[rmIndex];

// FIX 4: Shift elements left instead of creating a whole new array.
// This is much faster (O(N) shift vs O(N) allocation + copy).
        for (int i = rmIndex; i < len - 1; i++) {
            arr[i] = arr[i + 1];
        }

// Nullify the last element to prevent memory leaks
        arr[len - 1] = null;
        len--;

        return data;
    }

    public boolean remove(Object obj) {
        int index = indexOf(obj);
        if (index == -1) return false;
        removeAt(index);
        return true;
    }

    public int indexOf(Object obj) {
// FIX 5: Handle nulls to avoid NullPointerException
        for (int i = 0; i < len; i++) {
            if (obj == null) {
                if (arr[i] == null) return i;
            } else {
                if (obj.equals(arr[i])) return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            int index = 0;
            public boolean hasNext() { return index < len; }
            public T next() { return arr[index++]; }
        };
    }

    @Override
    public String toString() {
        if (len == 0) return "[]";
        else {
            StringBuilder sb = new StringBuilder(len).append("[");
            for (int i = 0; i < len - 1; i++)
                sb.append(arr[i]).append(", ");
            return sb.append(arr[len - 1]).append("]").toString();
        }
    }
}