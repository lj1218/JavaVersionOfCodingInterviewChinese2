package CodingInterviewChinese2.ch06.Q53_03_IntegerIdenticalToIndex;

/**
 * Created by lj1218.
 * Date: 2019/9/6
 *
 * Page: 267
 * 面试题53（三）：数组中数值和下标相等的元素
 * 题目：假设一个单调递增的数组里的每个元素都是整数并且是唯一的。请编程实
 * 现一个函数找出数组中任意一个数值等于其下标的元素。例如，在数组{-3, -1,
 * 1, 3, 5}中，数字3和它的下标相等。
 *
 * 提示：二分查找。
 */
public class Q53_03_IntegerIdenticalToIndex {

    public static void main(String[] args) {
        Test.main();
    }

    public static int getNumberSameAsIndex(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }

        int low = 0;
        int high = numbers.length - 1;
        while (low <= high) {
            int mid = (high + low) >>> 1;
            if (numbers[mid] == mid) {
                return mid;
            } else if (numbers[mid] > mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
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
    private static void test(String testName, int[] numbers, int expected) {
        if (Q53_03_IntegerIdenticalToIndex.getNumberSameAsIndex(numbers) == expected) {
            System.out.printf("%s passed.\n", testName);
        } else {
            System.out.printf("%s FAILED.\n", testName);
        }
    }

    private static void Test1() {
        int[] numbers = {-3, -1, 1, 3, 5};
        int expected = 3;
        test("Test1", numbers, expected);
    }

    private static void Test2() {
        int[] numbers = {0, 1, 3, 5, 6};
        int expected = 0;
        test("Test2", numbers, expected);
    }

    private static void Test3() {
        int[] numbers = {-1, 0, 1, 2, 4};
        int expected = 4;
        test("Test3", numbers, expected);
    }

    private static void Test4() {
        int[] numbers = {-1, 0, 1, 2, 5};
        int expected = -1;
        test("Test4", numbers, expected);
    }

    private static void Test5() {
        int[] numbers = {0};
        int expected = 0;
        test("Test5", numbers, expected);
    }

    private static void Test6() {
        int[] numbers = {10};
        int expected = -1;
        test("Test6", numbers, expected);
    }

    private static void Test7() {
        test("Test7", null, -1);
    }
}
