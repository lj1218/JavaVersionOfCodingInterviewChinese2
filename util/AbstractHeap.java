package CodingInterviewChinese2.util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by lj1218.
 * Date: 2019/9/2
 * <p>
 * 优先队列 - 堆
 * <p>
 * 参考：啊哈！算法（第7章 神奇的树  第3节 堆--神奇的优先队列）
 * 参考：ArrayList
 */
public abstract class AbstractHeap<E extends Comparable<E>> {
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The array buffer into which the elements of the Heap are stored.
     * The capacity of the Heap is the length of this array buffer.
     */
    transient E[] elementData; // non-private to simplify nested class access

    /**
     * The size of the Heap (the number of elements it contains).
     *
     * @serial
     */
    protected int size;

    public AbstractHeap(Class<? extends E[]> type) {
        this(0, type);
    }

    AbstractHeap(int initialCapacity, Class<? extends E[]> type) {
        if (initialCapacity >= 0) {
            this.elementData = (E[]) Array.newInstance(type.getComponentType(), initialCapacity);
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    AbstractHeap(E[] data) throws Exception {
        this(data.length, (Class<? extends E[]>) data.getClass());
        if (data.length == 0) {
            throw new Exception("data is empty");
        }

        System.arraycopy(data, 0, elementData, 0,
                Math.min(data.length, elementData.length));
        size = data.length;
        construct();
    }

    /**
     * 构建堆（从非叶结点从后往前，逐个结点向下调整直至根结点）
     */
    private void construct() {
        for (int i = size / 2 - 1; i >= 0; --i) {
            shiftDown(i);
        }
        validate0();
    }

    /**
     * 向下调整元素
     *
     * @param i 需要向下调整的节点编号
     */
    abstract protected void shiftDown(int i);

    /**
     * 向上调整元素
     *
     * @param i 需要向上调整的节点编号
     */
    abstract protected void shiftUp(int i);

    /**
     * 用 e 替换堆顶元素，并调整堆
     *
     * @param e 替换堆顶元素
     * @return 被替换的堆顶元素
     * @throws Exception 堆空抛出异常
     */
    public E replaceHeapElem(E e) throws Exception {
        if (size < 1) {
            throw new Exception("no element in heap");
        }
        E old = elementData[0];
        elementData[0] = e;
        shiftDown(0);
        validate0();
        return old;
    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    public E getHeapElem() throws Exception {
        if (size < 1) {
            throw new Exception("no element in heap");
        }
        return elementData[0];
    }

    /**
     * 获取堆中所有元素
     *
     * @return 堆中所有元素组成的数组
     */
    public E[] getHeapElements() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * 校验堆的合法性
     */
    private void validate0() {
        if (!validate()) {
            System.out.println("Invalid MaxHeap");
            System.exit(1);
        }
    }

    abstract protected boolean validate();

    private void ensureCapacityInternal(int minCapacity) {
        if (elementData.length == 0) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return the number of elements in the heap
     */
    public int size() {
        return size;
    }

    /**
     * Adds the specified element to the heap.
     *
     * @param e element to be added to the heap
     * @return true
     */
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        shiftUp(size - 1);
        validate0();
        return true;
    }
}
