package skypro.hw2_14;

public class IntegerListImpl implements IntegerList {
    private static final int DEFAULT_SIZE = 15;
    private final Integer[] data;
    private int capacity;

    public IntegerListImpl() {
        this(DEFAULT_SIZE);
    }

    public IntegerListImpl(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0!");
        }
        data = new Integer[size];
        capacity = 0;
    }

    @Override
    public Integer add(Integer item) {
        return add(capacity, item);
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItem(item);
        checkIndex(index, true);
        if (capacity >= data.length) {
            throw new IllegalArgumentException("There is no space!");
        }
        System.arraycopy(data, index, data, index + 1, capacity - index);
        capacity++;
        return data[index] = item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkItem(item);
        checkIndex(index, false);
        return data[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        return remove(indexOf(item));
    }

    @Override
    public Integer remove(int index) {
        // 3 2 1 5 null null, capacity = 4, index = 2
        // 3 2 5 5 null null
        // 3 2 item 1 5 null
        checkIndex(index, false);
        Integer item = data[index];
        if (index + 1 < capacity) {
            System.arraycopy(data, index + 1, data, index, capacity - index - 1);
        }
        data[--capacity] = null;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        checkItem(item);

        Integer[] arr = toArray();

        sortInsertion(arr);
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arr[mid])) {
                return true;
            }
            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;


    }

    @Override
    public int indexOf(Integer item) {
        checkItem(item);
        for (int i = capacity - 1; i >= 0; i--) {
            if (item.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkItem(item);
        for (int i = 0; i < capacity; i++) {
            if (item.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index, false);
        return data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!get(i).equals((otherList.get(i)))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        capacity = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] copy = new Integer[capacity];
        System.arraycopy(data, 0, copy, 0, capacity);
        return copy;
    }

    private void checkItem(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("List cannot contains null!");
        }
    }

    private void checkIndex(int index, boolean include) {
        if (index < 0 || (include ? index > capacity : index >= capacity)) {
            throw new IllegalArgumentException("index must be in range: [0, capacity]1");
        }
    }

    private static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
