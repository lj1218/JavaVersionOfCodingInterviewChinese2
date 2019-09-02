package CodingInterviewChinese2.util;

import java.util.Arrays;

/**
 * Created by lj1218.
 * Date: 2019/9/2
 * <p>
 * 最大堆
 * <p>
 * 参考：啊哈！算法（第7章 神奇的树  第3节 堆--神奇的优先队列）
 */
public class MaxHeap<E extends Comparable<E>> {
    private final E[] data;
    private final int length;

    public MaxHeap(E[] data) throws Exception {
        if (data == null || data.length == 0) {
            throw new Exception("data is null or empty");
        }
        this.data = Arrays.copyOf(data, data.length);
        length = this.data.length;
        construct();
    }

    /**
     * 构建最大堆（从非叶结点从后往前，逐个结点向下调整直至根结点）
     */
    private void construct() {
        for (int i = length / 2 - 1; i >= 0; --i) {
            shiftDown(i);
        }
        validate();
    }

    /**
     * 小元素向下调整
     *
     * @param i 需要向下调整的节点编号
     */
    private void shiftDown(int i) {
        int t; // 存储较大结点的下标（以便于 i 交换）
        int sonIndex = 2 * i + 1; // 左儿子
        while (sonIndex < length) {
            // 首先判断它和左儿子的关系，并用 t 记录值较大的节点下标
            if (data[i].compareTo(data[sonIndex]) < 0) {
                t = sonIndex;
            } else {
                t = i;
            }

            // 如果它有右儿子，再对右儿子进行讨论
            sonIndex = sonIndex + 1; // 右儿子
            if (sonIndex < length) {
                // 如果右儿子的值更大，更新较大的结点下标
                if (data[t].compareTo(data[sonIndex]) < 0) {
                    t = sonIndex;
                }
            }

            // 如果发现较大的结点下标不是自己，说明子结点中有比父结点更大的
            if (t != i) {
                E temp = data[t];
                data[t] = data[i];
                data[i] = temp;
                i = t; // 更新 i 为刚才与它交换的儿子结点的编号，便于接下来向下调整
            } else {
                // 否则说明当前的父结点已经比两个子结点都要大了，不需要再进行调整了
                break;
            }

            sonIndex = 2 * i + 1; // 更新子结点下标，指向左子结点
        }
    }

    /**
     * 用 e 替换堆顶元素，并调整为最大堆
     *
     * @param e 替换堆顶元素
     */
    public void replaceHeapElem(E e) {
        data[0] = e;
        shiftDown(0);
        validate();
    }

    /**
     * 获取堆顶元素
     *
     * @return 堆顶元素
     */
    public E getHeapElem() {
        return data[0];
    }

    /**
     * 获取堆中所有元素
     *
     * @return 堆中所有元素组成的数组
     */
    public E[] getHeapElements() {
        return Arrays.copyOf(data, data.length);
    }

    /**
     * 校验是否为最大堆
     */
    private void validate() {
        for (int i = 0; i <= length / 2 - 1; ++i) {
            if (data[i].compareTo(data[2 * i + 1]) < 0
                    || (2 * i + 2 < length && data[i].compareTo(data[2 * i + 2]) < 0)) {
                System.out.println("Invalid MaxHeap");
                System.exit(1);
            }
        }
    }
}
