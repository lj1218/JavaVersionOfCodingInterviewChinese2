package CodingInterviewChinese2.ch02.Q10_Fibonacci;

/**
 * Created by lj1218.
 * Date: 2019/8/24
 *
 * Page: 74
 * 面试题10：斐波那契数列
 *   题目：写一个函数，输入n，求斐波那契（Fibonacci）数列的第n项。
 *
 *            n (n <= 1)
 *   f(n) = {
 *           f(n-1) + f(n-2) (n > 1)
 */
public class Q10_Fibonacci {

    public static void main(String[] args) {
        Test(0, 0);
        Test(1, 1);
        Test(2, 1);
        Test(3, 2);
        Test(4, 3);
        Test(5, 5);
        Test(6, 8);
        Test(7, 13);
        Test(8, 21);
        Test(9, 34);
        Test(10, 55);

        Test(40, 102334155);
    }

    // ====================测试代码====================
    public static void Test(int n, long expected) {
        if (Fibonacci_Solution1(n) == expected)
            System.out.printf("Test for %d in solution1 passed.\n", n);
        else
            System.out.printf("Test for %d in solution1 failed.\n", n);

        if (Fibonacci_Solution2(n) == expected)
            System.out.printf("Test for %d in solution2 passed.\n", n);
        else
            System.out.printf("Test for %d in solution2 failed.\n", n);
    }

    // ====================方法1：递归====================
    public static long Fibonacci_Solution1(int n) {
        if (n <= 1) {
            return n;
        }

        return Fibonacci_Solution1(n - 1) + Fibonacci_Solution1(n - 2);
    }

    // ====================方法2：循环====================
    public static long Fibonacci_Solution2(int n) {
        if (n <= 1) {
            return n;
        }

        long fibN = 0;
        long fibNMinusOne = 1;
        long fibNMinusTwo = 0;
        for (int i = 2; i <= n; ++i) {
            fibN = fibNMinusOne + fibNMinusTwo;
            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne = fibN;
        }
        return fibN;
    }
}
