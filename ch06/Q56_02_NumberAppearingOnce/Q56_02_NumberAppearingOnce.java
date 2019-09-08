package CodingInterviewChinese2.ch06.Q56_02_NumberAppearingOnce;

/**
 * Created by lj1218.
 * Date: 2019/9/8
 *
 * Page: 278
 * 面试题56（二）：数组中唯一只出现一次的数字
 * 题目：在一个数组中除了一个数字只出现一次之外，其他数字都出现了三次。请
 * 找出那个只出现一次的数字。
 */
public class Q56_02_NumberAppearingOnce {

    public static void main(String[] args) throws Exception {
        Test.main();
    }

    public static int findNumberAppearingOnce(int[] numbers) throws Exception {
        if (numbers == null || numbers.length == 0) {
            throw new Exception("Invalid input.");
        }

        int[] bitSum = new int[32]; // 下标值越大，存的位越高
        for (int n : numbers) {
            int bitMask = 0x1;
            for (int i = 0; i < 32; ++i) {
                int bit = n & bitMask;
                if (bit != 0) {
                    bitSum[i] += 1;
                }
                bitMask <<= 1;
            }
        }

        int result = 0;
        for (int i = bitSum.length - 1; i >= 0; --i) {
            result <<= 1;
            bitSum[i] %= 3;  // 除以 3 的余数就是唯一出现 1 次的数字的位的值
            result += bitSum[i];
        }

        return result;
    }
}

class Test {

    public static void main() throws Exception {
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
    private static void test(String testName, int[] numbers, int expected) throws Exception {
        int result = Q56_02_NumberAppearingOnce.findNumberAppearingOnce(numbers);
        if (result == expected) {
            System.out.printf("%s passed.\n", testName);
        } else {
            System.out.printf("%s FAILED.\n", testName);
        }
    }

    // 所有数字都是正数，唯一的数字是最小的
    private static void Test1() throws Exception {
        int[] numbers = {1, 1, 2, 2, 2, 1, 3};
        int expected = 3;
        test("Test1", numbers, expected);
    }

    // 所有数字都是正数，唯一的数字的大小位于中间
    private static void Test2() throws Exception {
        int[] numbers = {4, 3, 3, 2, 2, 2, 3};
        int expected = 4;
        test("Test2", numbers, expected);
    }

    // 所有数字都是正数，唯一的数字是最大的
    private static void Test3() throws Exception {
        int[] numbers = {4, 4, 1, 1, 1, 7, 4};
        int expected = 7;
        test("Test3", numbers, expected);
    }

    // 唯一的数字是负数
    private static void Test4() throws Exception {
        int[] numbers = {-10, 214, 214, 214};
        int expected = -10;
        test("Test4", numbers, expected);
    }

    // 除了唯一的数字，其他数字都是负数
    private static void Test5() throws Exception {
        int[] numbers = {-209, 3467, -209, -209};
        int expected = 3467;
        test("Test5", numbers, expected);
    }

    // 重复的数字有正数也有负数
    private static void Test6() throws Exception {
        int[] numbers = {1024, -1025, 1024, -1025, 1024, -1025, 1023};
        int expected = 1023;
        test("Test6", numbers, expected);
    }

    // 所有数字都是负数
    private static void Test7() throws Exception {
        int[] numbers = {-1024, -1024, -1024, -1023};
        int expected = -1023;
        test("Test7", numbers, expected);
    }

    // 唯一的数字是0
    private static void Test8() throws Exception {
        int[] numbers = {-23, 0, 214, -23, 214, -23, 214};
        int expected = 0;
        test("Test8", numbers, expected);
    }

    // 除了唯一的数字，其他数字都是0
    private static void Test9() throws Exception {
        int[] numbers = {0, 3467, 0, 0, 0, 0, 0, 0};
        int expected = 3467;
        test("Test9", numbers, expected);
    }
}
