package CodingInterviewChinese2.ch06.Q62_LastNumberInCircle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lj1218.
 * Date: 2019/9/17
 *
 * Page: 300
 * 面试题62：圆圈中最后剩下的数字
 * 题目：0, 1, …, n-1这n个数字排成一个圆圈，从数字0开始每次从这个圆圈里
 * 删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 */
public class Q62_LastNumberInCircle {

    public static void main(String[] args) {
        Test.main();
    }

    // ====================方法1====================
    // 用链表模拟圆圈
    public static int lastRemaining_Solution1(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }

        List<Integer> numbers = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            numbers.add(i);
        }

        int index = -1;
        while (numbers.size() > 1) {
            // 找到要删除的数字，index 为对应的下标
            for (int i = 0; i < m; ++i) {
                ++index;
                if (index >= numbers.size()) {
                    // 到达链表末尾，移到头部
                    index = 0;
                }
            }

            numbers.remove(index--); // 删除一个数字后，将下标值回退1，指向删除数字的前一个数字
        }

        return numbers.get(0); // 返回最后剩下的数字
    }

    // ====================方法2====================
    // 根据数学方法推导出计算公式
    public static int lastRemaining_Solution2(int n, int m) {
        if (n < 1 || m < 1)
            return -1;

        int last = 0;
        for (int i = 2; i <= n; i++)
            last = (last + m) % i;

        return last;
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
    }

    // ====================测试代码====================
    private static void test(String testName, int n, int m, int expected) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: \n", testName);

        if (Q62_LastNumberInCircle.lastRemaining_Solution1(n, m) == expected) {
            System.out.println("Solution1 passed.");
        } else {
            System.out.println("Solution1 failed.");
        }

        if (Q62_LastNumberInCircle.lastRemaining_Solution2(n, m) == expected) {
            System.out.println("Solution2 passed.");
        } else {
            System.out.println("Solution2 failed.");
        }

        System.out.println();
    }

    private static void Test1() {
        test("Test1", 5, 3, 3);
    }

    private static void Test2() {
        test("Test2", 5, 2, 2);
    }

    private static void Test3() {
        test("Test3", 6, 7, 4);
    }

    private static void Test4() {
        test("Test4", 6, 6, 3);
    }

    private static void Test5() {
        test("Test5", 0, 0, -1);
    }

    private static void Test6() {
        test("Test6", 4000, 997, 1027);
    }
}
