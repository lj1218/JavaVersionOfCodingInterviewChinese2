package CodingInterviewChinese2.ch06.Q59_01_MaxInSlidingWindow;

import java.util.*;

/**
 * Created by lj1218.
 * Date: 2019/9/9
 *
 * Page: 288
 * 面试题59（一）：滑动窗口的最大值
 * 题目：给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。例如，
 * 如果输入数组{2, 3, 4, 2, 6, 2, 5, 1}及滑动窗口的大小3，那么一共存在6个
 * 滑动窗口，它们的最大值分别为{4, 4, 6, 6, 6, 5}
 */
public class Q59_01_MaxInSlidingWindow {

    public static void main(String[] args) {
        Test.main();
    }

    public static List<Integer> maxInWindows(List<Integer> num, int windowSize) {
        List<Integer> result = new ArrayList<>();
        if (num == null || windowSize <= 0 || windowSize > num.size()) {
            return result;
        }

        Deque<Value<Integer>> deque = new ArrayDeque<>();
        int index;
        // 初始化双端队列（先滑动一个窗口的大小）
        for (index = 0; index < windowSize; ++index) {
            Value<Integer> val = new Value<>(num.get(index), index);
            while (!deque.isEmpty() && val.compareTo(deque.peekLast()) >= 0) {
                deque.removeLast();
            }
            deque.addLast(val);
        }
        result.add(deque.peekFirst().getV());

        int len = num.size();
        for (; index < len; ++index) {
            if (index - deque.peekFirst().getIndex() >= windowSize) {
                // 双端队列头部元素滑出滑动窗口
                deque.removeFirst();
            }

            Value<Integer> val = new Value<>(num.get(index), index);
            while (!deque.isEmpty() && val.compareTo(deque.peekLast()) >= 0) {
                deque.removeLast();
            }
            deque.addLast(val);

            result.add(deque.peekFirst().getV()); // 双端队列头部元素为滑动窗口最大值
        }

        return result;
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

    public static void main() {
        Test1();
        Test2();
        Test3();
        Test4();
        Test5();
        Test6();
        Test7();
        Test8();
        Test9();
    }

    // ====================测试代码====================
    private static void test(String testName, List<Integer> num, int windowSize, List<Integer> expected) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        List<Integer> result = Q59_01_MaxInSlidingWindow.maxInWindows(num, windowSize);

        if (result.size() != expected.size()) {
            System.out.println("FAILED.");
            return;
        }

        Iterator<Integer> itResult = result.iterator();
        Iterator<Integer> itExpected = expected.iterator();
        while (itResult.hasNext() && itExpected.hasNext()) {
            if (!itResult.next().equals(itExpected.next())) {
                System.out.println("FAILED.");
                return;
            }
        }

        System.out.println("Passed.");
    }

    private static void Test1() {
        int[] num = {2, 3, 4, 2, 6, 2, 5, 1};
        List<Integer> vecNumbers = new ArrayList<>();
        for (int n : num) {
            vecNumbers.add(n);
        }

        int[] expected = {4, 4, 6, 6, 6, 5};
        List<Integer> vecExpected = new ArrayList<>();
        for (int n : expected) {
            vecExpected.add(n);
        }
        int windowSize = 3;

        test("Test1", vecNumbers, windowSize, vecExpected);
    }

    private static void Test2() {
        int[] num = {1, 3, -1, -3, 5, 3, 6, 7};
        List<Integer> vecNumbers = new ArrayList<>();
        for (int n : num) {
            vecNumbers.add(n);
        }

        int expected[] = {3, 3, 5, 5, 6, 7};
        List<Integer> vecExpected = new ArrayList<>();
        for (int n : expected) {
            vecExpected.add(n);
        }

        int windowSize = 3;

        test("Test2", vecNumbers, windowSize, vecExpected);
    }

    // 输入数组单调递增
    private static void Test3() {
        int[] num = {1, 3, 5, 7, 9, 11, 13, 15};
        List<Integer> vecNumbers = new ArrayList<>();
        for (int n : num) {
            vecNumbers.add(n);
        }

        int expected[] = {7, 9, 11, 13, 15};
        List<Integer> vecExpected = new ArrayList<>();
        for (int n : expected) {
            vecExpected.add(n);
        }

        int windowSize = 4;

        test("Test3", vecNumbers, windowSize, vecExpected);
    }

    // 输入数组单调递减
    private static void Test4() {
        int[] num = {16, 14, 12, 10, 8, 6, 4};
        List<Integer> vecNumbers = new ArrayList<>();
        for (int n : num) {
            vecNumbers.add(n);
        }

        int expected[] = {16, 14, 12};
        List<Integer> vecExpected = new ArrayList<>();
        for (int n : expected) {
            vecExpected.add(n);
        }

        int windowSize = 5;

        test("Test4", vecNumbers, windowSize, vecExpected);
    }

    // 滑动窗口的大小为1
    private static void Test5() {
        int[] num = {10, 14, 12, 11};
        List<Integer> vecNumbers = new ArrayList<>();
        for (int n : num) {
            vecNumbers.add(n);
        }

        int expected[] = {10, 14, 12, 11};
        List<Integer> vecExpected = new ArrayList<>();
        for (int n : expected) {
            vecExpected.add(n);
        }

        int windowSize = 1;

        test("Test5", vecNumbers, windowSize, vecExpected);
    }

    // 滑动窗口的大小等于数组的长度
    private static void Test6() {
        int[] num = {10, 14, 12, 11};
        List<Integer> vecNumbers = new ArrayList<>();
        for (int n : num) {
            vecNumbers.add(n);
        }

        int expected[] = {14};
        List<Integer> vecExpected = new ArrayList<>();
        for (int n : expected) {
            vecExpected.add(n);
        }

        int windowSize = 4;

        test("Test6", vecNumbers, windowSize, vecExpected);
    }

    // 滑动窗口的大小为0
    private static void Test7() {
        int[] num = {10, 14, 12, 11};
        List<Integer> vecNumbers = new ArrayList<>();
        for (int n : num) {
            vecNumbers.add(n);
        }

        List<Integer> vecExpected = new ArrayList<>();

        int windowSize = 0;

        test("Test7", vecNumbers, windowSize, vecExpected);
    }

    // 滑动窗口的大小大于输入数组的长度
    private static void Test8() {
        int[] num = {10, 14, 12, 11};
        List<Integer> vecNumbers = new ArrayList<>();
        for (int n : num) {
            vecNumbers.add(n);
        }

        List<Integer> vecExpected = new ArrayList<>();

        int windowSize = 5;

        test("Test8", vecNumbers, windowSize, vecExpected);
    }

    // 输入数组为空
    private static void Test9() {
        List<Integer> vecNumbers = new ArrayList<>();
        List<Integer> vecExpected = new ArrayList<>();

        int windowSize = 5;

        test("Test9", vecNumbers, windowSize, vecExpected);
    }
}
