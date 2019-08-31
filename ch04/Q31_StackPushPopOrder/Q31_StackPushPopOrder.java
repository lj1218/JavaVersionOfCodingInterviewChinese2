package CodingInterviewChinese2.ch04.Q31_StackPushPopOrder;

import java.util.Stack;

/**
 * Created by lj1218.
 * Date: 2019/8/30
 *
 * Page: 168
 * 面试题31：栈的压入、弹出序列
 * 题目：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是
 * 否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1、2、3、4、
 * 5是某栈的压栈序列，序列4、5、3、2、1是该压栈序列对应的一个弹出序列，但
 * 4、3、5、1、2就不可能是该压栈序列的弹出序列。
 */
public class Q31_StackPushPopOrder {

    public static void main(String[] args) {
        Test.main();
    }

    public static boolean isPopOrder(int[] push, int[] pop) {
        if (push == null || pop == null) {
            return false;
        }

        if (push.length != pop.length) {
            return false;
        }

        int nextPush = 0;
        int nextPop = 0;
        Stack<Integer> dataStack = new Stack<>();
        while (nextPop < pop.length) {
            if (!dataStack.isEmpty() && (dataStack.peek() == pop[nextPop])) {
                // 当 pop 序列不为空，且 dataStack 栈顶元素 等于 pop 序列第 1 个弹出元素，
                // 则从 dataStack 弹出栈顶元素，并将 nextPop 指向 pop 序列中下一个弹出元素。
                dataStack.pop();
                ++nextPop;
            } else {
                // 当 pop 序列为空，或者 dataStack 栈顶元素 不等于 pop 序列第 1 个弹出元素，
                // 则从 push 序列中从下标为 nextPush 的元素开始依次压入 dataStack 直至 压入元素 等于 pop 序列第 1 个弹出元素。
                if (nextPush == push.length) {
                    // 如果所有数字都压入辅助栈 dataStack 了，说明不可能是该压栈序列的弹出序列，退出循环
                    break;
                }
                while (nextPush < push.length) {
//                    dataStack.push(push[nextPush++]);
//                    if (push[nextPush - 1] == pop[nextPop]) {
//                        break;
//                    }
                    // 比注释掉的代码效率更高：少了 1 次不必要的 push/pop 操作
                    if (push[nextPush] == pop[nextPop]) {
                        // 如果 push 序列中下标为 nextPush 的元素 等于 pop 序列中下标为 nextPop 的元素，
                        // 指针后移，并退出当前循环
                        ++nextPush;
                        ++nextPop;
                        break;
                    }
                    dataStack.push(push[nextPush++]);
                }
            }
        }

        return nextPop == pop.length && dataStack.isEmpty();
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
    }

    // ====================测试代码====================
    private static void test(String testName, int[] pPush, int[] pPop, boolean expected) {
        if (testName != null) {
            System.out.printf("%s begins: ", testName);
        }

        if (Q31_StackPushPopOrder.isPopOrder(pPush, pPop) == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("failed.");
        }
    }

    private static void Test1() {
        int push[] = {
                1, 2, 3, 4, 5
        };
        int pop[] = {
                4, 5, 3, 2, 1
        };

        test("Test1", push, pop, true);
    }

    private static void Test2() {
        int push[] = {
                1, 2, 3, 4, 5
        };
        int pop[] = {
                3, 5, 4, 2, 1
        };

        test("Test2", push, pop, true);
    }

    private static void Test3() {
        int push[] = {
                1, 2, 3, 4, 5
        };
        int pop[] = {
                4, 3, 5, 1, 2
        };

        test("Test3", push, pop, false);
    }

    private static void Test4() {
        int push[] = {
                1, 2, 3, 4, 5
        };
        int pop[] = {
                3, 5, 4, 1, 2
        };

        test("Test4", push, pop, false);
    }

    // push和pop序列只有一个数字
    private static void Test5() {
        int push[] = {
                1
        };
        int pop[] = {
                2
        };

        test("Test5", push, pop, false);
    }

    private static void Test6() {
        int push[] = {
                1
        };
        int pop[] = {
                1
        };

        test("Test6", push, pop, true);
    }

    private static void Test7() {
        test("Test7", null, null, false);
    }
}
