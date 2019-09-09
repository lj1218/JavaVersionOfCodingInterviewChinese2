package CodingInterviewChinese2.ch06.Q59_02_QueueWithMax;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lj1218.
 * Date: 2019/9/9
 *
 * Page: 292
 * 面试题59（二）：队列的最大值
 * 题目：请定义一个队列并实现函数 max 得到队列里的最大值，要求函数 max、
 * push_back 和 pop_front 的时间复杂度都是 O(1)。
 */
public class Q59_02_QueueWithMax {

    public static void main(String[] args) throws Exception {
        Test.main();
    }
}

class QueueWithMax<E extends Comparable<E>> {
    private Queue<Value<E>> data;     // 普通队列
    private Deque<Value<E>> maximums; // 双端队列
    private int currentIndex;

    public QueueWithMax() {
        data = new LinkedList<>();
        maximums = new ArrayDeque<>();
        currentIndex = 0;
    }

    public E max() throws Exception {
        if (maximums.isEmpty()) {
            throw new Exception("queue is empty");
        }
        return maximums.peekFirst().getV();
    }

    public void pushBack(E number) {
        Value<E> val = new Value<>(number, currentIndex++);
        while (!maximums.isEmpty() && val.compareTo(maximums.peekLast()) >= 0) {
            maximums.removeLast();
        }
        maximums.addLast(val);

        data.offer(val);
    }

    public void popFront() throws Exception {
        if (maximums.isEmpty()) {
            throw new Exception("queue is empty");
        }

        if (maximums.peekFirst().getIndex() == data.peek().getIndex()) {
            maximums.removeFirst();
        }

        data.remove();
    }
}

class Value<E extends Comparable<E>> implements Comparable<Value<E>> {
    private E v;
    private int index;

    public Value(E v, int index) {
        this.v = v;
        this.index = index;
    }

    public E getV() {
        return v;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Value<E> o) {
        if (this == o) {
            return 0;
        }

        if (o == null) {
            return 1;
        }

        return this.v.compareTo(o.v);
    }
}

class Test {

    public static void main() throws Exception {
        QueueWithMax<Integer> queue = new QueueWithMax<>();
        // {2}
        queue.pushBack(2);
        test("Test1", queue, 2);

        // {2, 3}
        queue.pushBack(3);
        test("Test2", queue, 3);

        // {2, 3, 4}
        queue.pushBack(4);
        test("Test3", queue, 4);

        // {2, 3, 4, 2}
        queue.pushBack(2);
        test("Test4", queue, 4);

        // {3, 4, 2}
        queue.popFront();
        test("Test5", queue, 4);

        // {4, 2}
        queue.popFront();
        test("Test6", queue, 4);

        // {2}
        queue.popFront();
        test("Test7", queue, 2);

        // {2, 6}
        queue.pushBack(6);
        test("Test8", queue, 6);

        // {2, 6, 2}
        queue.pushBack(2);
        test("Test9", queue, 6);

        // {2, 6, 2, 5}
        queue.pushBack(5);
        test("Test9", queue, 6);

        // {6, 2, 5}
        queue.popFront();
        test("Test10", queue, 6);

        // {2, 5}
        queue.popFront();
        test("Test11", queue, 5);

        // {5}
        queue.popFront();
        test("Test12", queue, 5);

        // {5, 1}
        queue.pushBack(1);
        test("Test13", queue, 5);
    }

    // ====================测试代码====================
    private static void test(String testName, QueueWithMax<Integer> queue, int expected) throws Exception {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        if (queue.max().equals(expected)) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }
    }
}
