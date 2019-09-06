package CodingInterviewChinese2.ch06.Q53_02_MissingNumber;

/**
 * Created by lj1218.
 * Date: 2019/9/6
 *
 * Page: 266
 * 面试题53（二）：0到n-1中缺失的数字
 * 题目：一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字
 * 都在范围0到n-1之内。在范围0到n-1的n个数字中有且只有一个数字不在该数组
 * 中，请找出这个数字。
 */
public class Q53_02_MissingNumber {

    public static void main(String[] args) {
        Test.main();
    }

    public static int getMissingNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }

        int low = 0;
        int high = numbers.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid == numbers[mid]) {
                // 在右边
                low = mid + 1;
            } else {
                if (mid == 0 || (mid - 1 == numbers[mid - 1])) {
                    return mid;
                }
                // 在左边
                high = mid - 1;
            }
        }

        // 数组中所有数字都等于其对应的下标值，说明缺失的数据为最后1个
        if (low == numbers.length) {
            return numbers.length;
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
    }

    // ====================测试代码====================
    private static void test(String testName, int numbers[], int expected) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        int result = Q53_02_MissingNumber.getMissingNumber(numbers);
        if (result == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    // 缺失的是第一个数字0
    private static void Test1() {
        int numbers[] = {1, 2, 3, 4, 5};
        int expected = 0;
        test("Test1", numbers, expected);
    }

    // 缺失的是最后一个数字
    private static void Test2() {
        int numbers[] = {0, 1, 2, 3, 4};
        int expected = 5;
        test("Test2", numbers, expected);
    }

    // 缺失的是中间某个数字0
    private static void Test3() {
        int numbers[] = {0, 1, 2, 4, 5};
        int expected = 3;
        test("Test3", numbers, expected);
    }

    // 数组中只有一个数字，缺失的是第一个数字0
    private static void Test4() {
        int numbers[] = {1};
        int expected = 0;
        test("Test4", numbers, expected);
    }

    // 数组中只有一个数字，缺失的是最后一个数字1
    private static void Test5() {
        int numbers[] = {0};
        int expected = 1;
        test("Test5", numbers, expected);
    }

    // 空数组
    private static void Test6() {
        int expected = -1;
        test("Test6", null, expected);
    }
}
