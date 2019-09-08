package CodingInterviewChinese2.ch06.Q57_01_TwoNumbersWithSum;

/**
 * Created by lj1218.
 * Date: 2019/9/8
 *
 * Page: 280
 * 面试题57（一）：和为s的两个数字
 * 题目：输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们
 * 的和正好是s。如果有多对数字的和等于s，输出任意一对即可。
 */
public class Q57_01_TwoNumbersWithSum {

    public static void main(String[] args) {
        Test.main();
    }

    public static boolean findNumbersWithSum(int[] data, int sum, int[] numbers) {
        if (data == null || data.length < 2) {
            return false;
        }

        int ahead = data.length - 1;
        int behind = 0;
        while (behind < ahead) {
            int curSum = data[ahead] + data[behind];
            if (curSum == sum) {
                numbers[0] = data[behind];
                numbers[1] = data[ahead];
                return true;
            } else if (curSum > sum) {
                --ahead;
            } else {
                ++behind;
            }
        }

        return false;
    }
}

class Test {

    public static void main() {
        Test1();
        Test2();
        Test3();
        Test4();
    }

    // ====================测试代码====================
    private static void test(String testName, int[] data, int sum, boolean expectedReturn) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        int[] numbers = new int[2];
        boolean result = Q57_01_TwoNumbersWithSum.findNumbersWithSum(data, sum, numbers);
        if (result == expectedReturn) {
            if (result) {
                if (numbers[0] + numbers[1] == sum) {
                    System.out.println("Passed.");
                } else {
                    System.out.println("FAILED.");
                }
            } else {
                System.out.println("Passed.");
            }
        } else {
            System.out.println("FAILED.");
        }
    }

    // 存在和为s的两个数字，这两个数字位于数组的中间
    private static void Test1() {
        int[] data = {1, 2, 4, 7, 11, 15};
        test("Test1", data, 15, true);
    }

    // 存在和为s的两个数字，这两个数字位于数组的两段
    private static void Test2() {
        int[] data = {1, 2, 4, 7, 11, 16};
        test("Test2", data, 17, true);
    }

    // 不存在和为s的两个数字
    private static void Test3() {
        int[] data = {1, 2, 4, 7, 11, 16};
        test("Test3", data, 10, false);
    }

    // 鲁棒性测试
    private static void Test4() {
        test("Test4", null, 0, false);
    }
}
