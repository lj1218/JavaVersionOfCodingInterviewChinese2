package CodingInterviewChinese2.ch03.Q03_2_DuplicationInArrayNoEdit;

/**
 * Created by lj1218.
 * Date: 2019/8/21
 * Page: 41
 * 面试3 - 题目二：不修改数组找出重复的数字
 *   在一个长度为 n+1 的数组里所有数字都在 1~n 的范围内，所以数组
 * 中至少有一个数字是重复的。请找出数组中任意一个重复的数字，但不能
 * 修改输入的数组。例如，如果输入长度为 8 的数组 {2,3,5,4,3,2,6,7}，
 * 那么对应的输出是重复的数字 2 或者 3。
 *
 * 提示：二分查找 + 统计（上/下 段数字）出现次数。
 */
public class Q03_2_DuplicationInArrayNoEdit {

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
            if (number < 1 || number >= numbers.length) {
                return -1;
            }
        }

        int start = 1;
        int end = numbers.length - 1;
        while (end >= start) {
            int mid = (start + end) >>> 1;
            int count = countRange(numbers, start, mid);
            if (end == start) {
                if (count > 1) {
                    return start;
                } else {
                    break;
                }
            }

            if (count > (mid - start + 1)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    private static int countRange(int[] array, int start, int end) {
        if (array == null || start > end) {
            return -1;
        }

        int count = 0;
        for (int e : array) {
            if (e >= start && e <= end) {
                ++count;
            }
        }
        return count;
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

    // 多个重复的数字
    private static void test1() {
        int[] numbers = {2, 3, 5, 4, 3, 2, 6, 7};
        test("test1", new int[]{2, 3}, duplicate(numbers));
    }

    // 一个重复的数字
    private static void test2() {
        int[] numbers = {3, 2, 1, 4, 4, 5, 6, 7};
        test("test2", new int[]{4}, duplicate(numbers));
    }

    // 重复的数字是数组中最小的数字
    private static void test3() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 1, 8};
        test("test3", new int[]{1}, duplicate(numbers));
    }

    // 重复的数字是数组中最大的数字
    private static void test4() {
        int[] numbers = {1, 7, 3, 4, 5, 6, 8, 2, 8};
        test("test4", new int[]{8}, duplicate(numbers));
    }

    // 数组中只有两个数字
    private static void test5() {
        int[] numbers = {1, 1};
        test("test5", new int[]{1}, duplicate(numbers));
    }

    // 重复的数字位于数组当中
    private static void test6() {
        int[] numbers = {3, 2, 1, 3, 4, 5, 6, 7};
        test("test6", new int[]{3}, duplicate(numbers));
    }

    // 多个重复的数字
    private static void test7() {
        int[] numbers = {1, 2, 2, 6, 4, 5, 6};
        test("test7", new int[]{2, 6}, duplicate(numbers));
    }

    // 一个数字重复三次
    private static void test8() {
        int[] numbers = {1, 2, 2, 6, 4, 5, 2};
        test("test8", new int[]{2}, duplicate(numbers));
    }

    // 没有重复的数字
    private static void test9() {
        int[] numbers = {1, 2, 6, 4, 5, 3};
        test("test9", new int[]{-1}, duplicate(numbers));
    }

    // 无效的输入
    private static void test10() {
        test("test10", new int[]{-1}, duplicate(null));
    }
}
