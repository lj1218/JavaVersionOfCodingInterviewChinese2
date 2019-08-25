package CodingInterviewChinese2.ch02.Q03_1_DuplicationInArray;

import CodingInterviewChinese2.Util;

/**
 * Created by lj1218.
 * Date: 2019/8/21
 *
 * Page: 39
 * 面试3 - 题目一：找出数组中重复的数字。
 *   在一个长度为 n 的数组里的所有数字都在 0~n-1 的范围内。数组中某些
 * 数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
 * 找出数组中任意一个重复的数字。例如，如果输入长度为 7 的数组 {2,3,1,0,2,5,3}，
 * 那么对应的输出是重复的数字 2 或者 3。
 *
 * 提示：利用下标与数值之间的对应关系。
 */
public class Q03_1_DuplicationInArray {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    /**
     * 找出整型数组中任一重复数字
     *
     * @param numbers 整型数组
     * @return 找不到重复，或者不合符要求，返回负数；否则，返回重复数字
     */
    public static int duplicate(int[] numbers) {
        if (numbers == null) {
            return -1;
        }

        for (int number : numbers) {
            if (number < 0 || number >= numbers.length) {
                return -1;
            }
        }

        for (int i = 0; i < numbers.length; ++i) {
            while (numbers[i] != i) {
                int m = numbers[i];
                if (m == numbers[m]) {
                    return m;
                }
                Util.swap(numbers, i, m);
            }
        }

        return -1;
    }


    // ====================测试代码====================
    public static void test(String testName, int[] expects, int real) {
        for (int expect : expects) {
            if (expect == real) {
                System.out.println(testName + " pass");
                return;
            }
        }
        System.out.println(testName + " failed");
    }

    private static void test1() {
        int[] numbers = {2, 1, 3, 1, 4};
        test("test1", new int[]{1}, duplicate(numbers));
    }

    private static void test2() {
        int[] numbers = {2, 4, 3, 1, 4};
        test("test2", new int[]{4}, duplicate(numbers));
    }

    private static void test3() {
        int[] numbers = {2, 4, 2, 1, 4};
        test("test3", new int[]{2, 4}, duplicate(numbers));
    }

    private static void test4() {
        int[] numbers = {2, 1, 3, 0, 4};
        test("test4", new int[]{-1}, duplicate(numbers));
    }

    private static void test5() {
        int[] numbers = {2, 1, 3, 5, 4};
        test("test5", new int[]{-1}, duplicate(numbers));
    }

    private static void test6() {
        test("test6", new int[]{-1}, duplicate(null));
    }
}
