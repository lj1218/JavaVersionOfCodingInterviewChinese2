package CodingInterviewChinese2.util;

/**
 * Created by lj1218.
 * Date: 2019/9/2
 * <p>
 * 最小堆
 * <p>
 * 参考：啊哈！算法（第7章 神奇的树  第3节 堆--神奇的优先队列）
 */
public class MinHeap<E extends Comparable<E>> extends AbstractHeap<E> {

    public MinHeap(Class<? extends E[]> type) {
        this(0, type);
    }

    public MinHeap(int initialCapacity, Class<? extends E[]> type) {
        super(initialCapacity, type);
    }

    public MinHeap(E[] data) throws Exception {
        super(data);
    }

    /**
     * 大元素向下调整
     *
     * @param i 需要向下调整的节点编号
     */
    protected void shiftDown(int i) {
        int t; // 存储较小结点的下标（以便于 i 交换）
        int sonIndex = 2 * i + 1; // 左儿子
        while (sonIndex < size) {
            // 首先判断它和左儿子的关系，并用 t 记录值较小的节点下标
            if (elementData[i].compareTo(elementData[sonIndex]) > 0) {
                t = sonIndex;
            } else {
                t = i;
            }

            // 如果它有右儿子，再对右儿子进行讨论
            sonIndex = sonIndex + 1; // 右儿子
            if (sonIndex < size) {
                // 如果右儿子的值更大，更新较小的结点下标
                if (elementData[t].compareTo(elementData[sonIndex]) > 0) {
                    t = sonIndex;
                }
            }

            // 如果发现较小的结点下标不是自己，说明子结点中有比父结点更小的
            if (t != i) {
                Common.swap(elementData, i, t);
                i = t; // 更新 i 为刚才与它交换的儿子结点的编号，便于接下来向下调整
            } else {
                // 否则说明当前的父结点已经比两个子结点都要小了，不需要再进行调整了
                break;
            }

            sonIndex = 2 * i + 1; // 更新子结点下标，指向左子结点
        }
    }

    /**
     * 小元素向上调整
     *
     * @param i 需要向上调整的节点编号
     */
    protected void shiftUp(int i) {
        if (i == 0) {
            return; // 堆顶元素不需要调整
        }

        while (i != 0) {
            int parentIndex = (i - 1) / 2;
            // 判断是否比父结点的值小
            if (elementData[i].compareTo(elementData[parentIndex]) < 0) {
                Common.swap(elementData, i, parentIndex); // 和父结点交换
            } else {
                break;
            }
            i = parentIndex; // 更新编号 i 为它父结点的编号，从而便于下一次继续向上调整
        }
    }

    /**
     * 校验是否为最小堆
     */
    protected boolean validate() {
        for (int i = 0; i <= size / 2 - 1; ++i) {
            if (elementData[i].compareTo(elementData[2 * i + 1]) > 0
                    || (2 * i + 2 < size && elementData[i].compareTo(elementData[2 * i + 2]) > 0)) {
                return false;
            }
        }
        return true;
    }
}
