package CodingInterviewChinese2.ch02.Q14_CuttingRope;

/**
 * Created by lj1218.
 * Date: 2019/8/25
 *
 * Page: 96
 * 面试题14：剪绳子
 * 题目：给你一根长度为n绳子，请把绳子剪成m段（m、n都是整数，n>1并且m≥1）。
 * 每段的绳子的长度记为k[0]、k[1]、……、k[m]。k[0]*k[1]*…*k[m]可能的最大乘
 * 积是多少？例如当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此
 * 时得到最大的乘积18。
 *
 * 提示：动态规划、贪婪算法
 */
public class Q14_CuttingRope {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test10();
        test11();
    }

    // 动态规划算法
    public static int maxProductAfterCutting_solution1(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        int[] products = new int[length + 1];
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;

        int max = 0;
        for (int i = 4; i <= length; ++i) {
            max = 0;
            for (int j = 1; j <= i / 2; ++j) {
                int product = products[j] * products[i - j];
                if (max < product) {
                    max = product;
                }
            }
            products[i] = max;
        }

        return max;
    }

    // 贪婪算法（需要用数学方式证明贪婪选择是正确的）
    public static int maxProductAfterCutting_solution2(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }

        // 尽可能多地剪出长度为 3 的绳子段
        int timesOf3 = length / 3;

        // 当绳子最后剩下的长度为 4 的时候，不能再剪出长度为 3 的绳子段
        // 此时更好的方法是把绳子剪成长度为 2 的两段，因为 2*2 > 3*1
        if (length - 3 * timesOf3 == 1) {
            timesOf3 -= 1;
        }

        int timesOf2 = (length - 3 * timesOf3) / 2;

        int result = 1;
        for (int i = 0; i < timesOf3; ++i) {
            result *= 3;
        }
        for (int i = 0; i < timesOf2; ++i) {
            result *= 2;
        }
        return result;
    }

    // ====================测试代码====================
    public static void test(String testName, int length, int expected) {
        int result1 = maxProductAfterCutting_solution1(length);
        if (result1 == expected) {
            System.out.println("Solution1 for " + testName + " passed.");
        } else {
            System.out.println("Solution1 for " + testName + " FAILED.");
        }

        int result2 = maxProductAfterCutting_solution2(length);
        if (result2 == expected) {
            System.out.println("Solution2 for " + testName + " passed.");
        } else {
            System.out.println("Solution2 for " + testName + " FAILED.");
        }
    }

    public static void test1() {
        int length = 1;
        int expected = 0;
        test("test1", length, expected);
    }

    public static void test2() {
        int length = 2;
        int expected = 1;
        test("test2", length, expected);
    }

    public static void test3() {
        int length = 3;
        int expected = 2;
        test("test3", length, expected);
    }

    public static void test4() {
        int length = 4;
        int expected = 4;
        test("test4", length, expected);
    }

    public static void test5() {
        int length = 5;
        int expected = 6;
        test("test5", length, expected);
    }

    public static void test6() {
        int length = 6;
        int expected = 9;
        test("test6", length, expected);
    }

    public static void test7() {
        int length = 7;
        int expected = 12;
        test("test7", length, expected);
    }

    public static void test8() {
        int length = 8;
        int expected = 18;
        test("test8", length, expected);
    }

    public static void test9() {
        int length = 9;
        int expected = 27;
        test("test9", length, expected);
    }

    public static void test10() {
        int length = 10;
        int expected = 36;
        test("test10", length, expected);
    }

    public static void test11() {
        int length = 50;
        int expected = 86093442;
        test("test11", length, expected);
    }
}
