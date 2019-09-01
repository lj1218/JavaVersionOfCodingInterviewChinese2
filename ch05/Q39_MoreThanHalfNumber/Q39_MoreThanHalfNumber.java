package CodingInterviewChinese2.ch05.Q39_MoreThanHalfNumber;

/**
 * Created by lj1218.
 * Date: 2019/9/1
 *
 * Page: 205
 * 面试题39：数组中出现次数超过一半的数字（著名的 "主元素"问题 或 "寻找多数元素"问题）
 * 题目：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例
 * 如输入一个长度为9的数组{1, 2, 3, 2, 2, 2, 5, 4, 2}。由于数字2在数组中
 * 出现了5次，超过数组长度的一半，因此输出2。
 *
 * 提示：除了下面给出的解法，还可以借鉴快排思想快速找到位于中间位置的数（左边的数小于等于它，
 * 右边的数大于等于它，但两边不一定有序），但会修改输入的数组。
 */
public class Q39_MoreThanHalfNumber {

    public static void main(String[] args) {
        Test.main();
    }

    public static int moreThanHalfNumber(int[] numbers) throws Exception {
        if (numbers == null || numbers.length == 0) {
            throw new Exception("invalid array");
        }

        int result = numbers[0];
        int count = 1;
        for (int i = 1; i < numbers.length; ++i) {
            if (result == numbers[i]) {
                ++count;
            } else {
                if (count == 1) {
                    result = numbers[i];
                } else {
                    --count;
                }
            }
        }

        if (!checkMoreThanHalf(numbers, result)) {
            throw new Exception("no element occurs half times in array");
        }
        return result;
    }

    private static boolean checkMoreThanHalf(int[] numbers, int number) {
        int times = 0;
        for (int n : numbers) {
            if (n == number) {
                ++times;
            }
        }
        return 2 * times > numbers.length;
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
    private static void test(String testName, int[] numbers, int expectedValue, boolean invalidArray) {
        if (testName == null)
            return;

        System.out.printf("%s begins: \n", testName);
        int result = 0;
        try {
            result = Q39_MoreThanHalfNumber.moreThanHalfNumber(numbers);
            if (result == expectedValue) {
                System.out.println("Passed.");
            } else {
                System.out.println("Failed.");
            }
        } catch (Exception e) {
            if (invalidArray) {
                System.out.println("Passed.");
            } else {
                System.out.println("Failed.");
            }
        }
    }

    // 存在出现次数超过数组长度一半的数字
    private static void Test1() {
        int[] numbers = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        test("Test1", numbers, 2, false);
    }

    // 不存在出现次数超过数组长度一半的数字
    private static void Test2() {
        int[] numbers = {1, 2, 3, 2, 4, 2, 5, 2, 3};
        test("Test2", numbers, 0, true);
    }

    // 出现次数超过数组长度一半的数字都出现在数组的前半部分
    private static void Test3() {
        int[] numbers = {2, 2, 2, 2, 2, 1, 3, 4, 5};
        test("Test3", numbers, 2, false);
    }

    // 出现次数超过数组长度一半的数字都出现在数组的后半部分
    private static void Test4() {
        int[] numbers = {1, 3, 4, 5, 2, 2, 2, 2, 2};
        test("Test4", numbers, 2, false);
    }

    // 输入空指针
    private static void Test5() {
        int[] numbers = {1};
        test("Test5", numbers, 1, false);
    }

    // 输入空指针
    private static void Test6() {
        test("Test6", null, 0, true);
    }
}
