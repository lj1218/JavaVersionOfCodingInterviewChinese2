package CodingInterviewChinese2.ch04.Q30_MinInStack;

import java.util.Stack;

/**
 * Created by lj1218.
 * Date: 2019/8/29
 *
 * Page: 165
 * 面试题30：包含min函数的栈
 * 题目：定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的min
 * 函数。在该栈中，调用min、push及pop的时间复杂度都是O(1)。
 *
 * 提示：使用辅助栈存最小值。
 */
public class Q30_MinInStack {

    public static void main(String[] args) {
        Test.main();
    }

}

class StackWithMin<E extends Comparable> {
    private Stack<E> mData;  // 数据栈，存放栈的所有元素
    private Stack<E> mMin;   // 辅助栈，存放栈的最小元素

    public StackWithMin() {
        mData = new Stack<>();
        mMin = new Stack<>();
    }

    public void push(E e) {
        // 把新元素添加到数据栈
        mData.push(e);

        // 当新元素比之前的最小元素小时，把新元素插入辅助栈里；
        // 否则把之前的最小元素重复插入辅助栈里
        if (mMin.isEmpty() || e.compareTo(mMin.peek()) < 0) {
            mMin.push(e);
        } else {
            mMin.push(mMin.peek());
        }
    }

    public E pop() {
        if (mData.isEmpty()) {
            return null;
        }

        mMin.pop();
        return mData.pop();
    }

    public E min() {
        if (mMin.isEmpty()) {
            return null;
        }

        return mMin.peek();
    }
}

class Test {

    public static void main() {
        StackWithMin<Integer> stack = new StackWithMin<>();

        stack.push(3);
        test("Test1", stack, 3);

        stack.push(4);
        test("Test2", stack, 3);

        stack.push(2);
        test("Test3", stack, 2);

        stack.push(3);
        test("Test4", stack, 2);

        stack.pop();
        test("Test5", stack, 2);

        stack.pop();
        test("Test6", stack, 3);

        stack.pop();
        test("Test7", stack, 3);

        stack.push(0);
        test("Test8", stack, 0);
    }

    private static void test(String testName, StackWithMin<Integer> stack, int expected) {
        if (testName != null) {
            System.out.printf("%s begins: ", testName);
        }

        if (stack.min() == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }
}
