package CodingInterviewChinese2.ch05.Q44_DigitsInSequence;

/**
 * Created by lj1218.
 * Date: 2019/9/3
 *
 * Page: 225
 * 面试题44：数字序列中某一位的数字
 * 题目：数字以0123456789101112131415…的格式序列化到一个字符序列中。在这
 * 个序列中，第5位（从0开始计数）是5，第13位是1，第19位是4，等等。请写一
 * 个函数求任意位对应的数字。
 */
public class Q44_DigitsInSequence {

    public static void main(String[] args) {
        Test.main();
    }

}

// class Solution1 逻辑更清晰
class Solution1 {

    /**
     * 求第 n 位的数字（假设解为 r）
     *
     * 位数  范围            数字个数           累计数字个数
     *
     * 1    0~9             10 * 1           10
     *
     * 2    10 ~ 99         90 * 2           190
     *
     * 3    100 ~ 999       900 * 3          2890
     *
     * 4    1000 ~ 9999     9000 * 4         38890
     *
     * 5    10000 ~ 99999   90000 * 5        ...
     * ...
     * ...
     *
     * 算法：
     *
     * 1、假设 f(m) 表示 位数为 1~m 的所有数字个数总和，例如 f(1) 表示 0~9 组成的数字总数 9（9*1），f(2) 表示 0~99 组成的数字总数 190 (f(1) + (99-10+1)*2)...
     * 得到递推关系：
     *   f(m) = f(m-1) + m * [9 * 10^(m-1)]
     *
     * 2、求出满足 f(m-1) < n <= f(m) 的 m 值；
     *
     * 3、令 x = n - f(m-1)，说明 n 位数字等价于 m 位数（从 10^(m-1) 开始）组成数字中的第 x 位。
     *
     * 4、因为每个数有 m 位，所以：
     *   a = x / m;
     *   b = x % m; // 余数
     *   假设 beginNumber(int digits) 表示长度为 digits 起始数字
     *   if (b == 0) {
     *       则 r = beginNumber(m) + a - 1 的最后一位数
     *   } else {
     *     则 r = beginNumber(m) + a 的从左往右数第 b 位（等价于从右往左数第 m + 1 - b 位）
     *   }
     */

    public static int digitAtIndex(int index) {
        if (index < 0) {
            return -1;
        }
        return digitAtIndexCore(index + 1);
    }

    // index 从 1 开始计数
    private static int digitAtIndexCore(int index) {
        // 求出满足 f(m-1) < n <= f(m) 的 m 值
        int m = 1;
        while (digitsSumUp(m) < index) {
            ++m;
        }

        int x = index - digitsSumUp(m - 1);
        int a = x / m;
        int b = x % m;
        if (b == 0) {
            return getDigitFromRight(beginNumber(m) + a - 1, 1);
        }
        return getDigitFromRight(beginNumber(m) + a, m - b + 1);
    }

    private static int beginNumber(int digits) {
        if (digits == 1)
            return 0;
        return (int) Math.pow(10, digits - 1);
    }

    // 计算位数为 1~m 的所有数字个数总和
    private static int digitsSumUp(int m) {
        int sum = 0;
        for (int i = 1; i <= m; ++i) {
            sum += (i * countOfIntegers(i));
        }
        return sum;
    }

    private static int countOfIntegers(int digits) {
        if (digits == 1) {
            return 10;
        }

        int count = (int) Math.pow(10, digits - 1);
        return 9 * count;
    }

    private static int getDigitFromRight(int num, int index) {
        if (num < 0 || index < 0) {
            return 0;
        }

        for (int i = 1; i < index; ++i) {
            num /= 10;
        }
        return num % 10;
    }
}

class Solution2 {

    // 算法：见教材
    public static int digitAtIndex(int index) {
        if (index < 0) {
            return -1;
        }

        int digits = 1;
        while (true) {
            int numbers = countOfIntegers(digits);
            if (index < numbers * digits) {
                return digitAtIndex(index, digits);
            }

            index -= numbers * digits;
            ++digits;
        }
    }

    private static int countOfIntegers(int digits) {
        if (digits == 1) {
            return 10;
        }

        int count = (int) Math.pow(10, digits - 1);
        return 9 * count;
    }

    private static int digitAtIndex(int index, int digits) {
        int number = beginNumber(digits) + index / digits;
        int indexFromRight = digits - index % digits;
        for (int i = 1; i < indexFromRight; ++i) {
            number /= 10;
        }
        return number % 10;
    }

    private static int beginNumber(int digits) {
        if (digits == 1)
            return 0;
        return (int) Math.pow(10, digits - 1);
    }
}

class Test {

    public static void main() {
        test1();
        test2();
    }

    public static void test1() {
        System.out.println("===== testWithSolution1 =====");
        testWithSolution1("Test1", 0, 0);
        testWithSolution1("Test2", 1, 1);
        testWithSolution1("Test3", 9, 9);
        testWithSolution1("Test4", 10, 1);
        testWithSolution1("Test5", 189, 9);  // 数字99的最后一位，9
        testWithSolution1("Test6", 190, 1);  // 数字100的第一位，1
        testWithSolution1("Test7", 1000, 3); // 数字370的第一位，3
        testWithSolution1("Test8", 1001, 7); // 数字370的第二位，7
        testWithSolution1("Test9", 1002, 0); // 数字370的第三位，0
    }

    public static void test2() {
        System.out.println("===== testWithSolution2 =====");
        testWithSolution2("Test1", 0, 0);
        testWithSolution2("Test2", 1, 1);
        testWithSolution2("Test3", 9, 9);
        testWithSolution2("Test4", 10, 1);
        testWithSolution2("Test5", 189, 9);  // 数字99的最后一位，9
        testWithSolution2("Test6", 190, 1);  // 数字100的第一位，1
        testWithSolution2("Test7", 1000, 3); // 数字370的第一位，3
        testWithSolution2("Test8", 1001, 7); // 数字370的第二位，7
        testWithSolution2("Test9", 1002, 0); // 数字370的第三位，0
    }

    // ====================测试代码====================
    private static void testWithSolution1(String testName, int inputIndex, int expectedOutput) {
        if (Solution1.digitAtIndex(inputIndex) == expectedOutput) {
            System.out.println(testName + " passed.");
        } else {
            System.out.println(testName + " FAILED.");
        }
    }

    private static void testWithSolution2(String testName, int inputIndex, int expectedOutput) {
        if (Solution2.digitAtIndex(inputIndex) == expectedOutput) {
            System.out.println(testName + " passed.");
        } else {
            System.out.println(testName + " FAILED.");
        }
    }
}
