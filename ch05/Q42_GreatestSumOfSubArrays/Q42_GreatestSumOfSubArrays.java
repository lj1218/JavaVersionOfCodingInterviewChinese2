package CodingInterviewChinese2.ch05.Q42_GreatestSumOfSubArrays;

/**
 * Created by lj1218.
 * Date: 2019/9/3
 *
 * Page: 218
 * 面试题42：连续子数组的最大和
 * 题目：输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整
 * 数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n)。
 */
public class Q42_GreatestSumOfSubArrays {

    public static void main(String[] args) {
        Test.main();
    }

    // 解法一：举例分析数组的规律
    public static int findGreatestSumOfSubArray(int[] data) throws Exception {
        if (data == null || data.length == 0) {
            throw new Exception("Invalid input");
        }

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int aData : data) {
            sum += aData;
            if (sum > maxSum) {
                maxSum = sum;
            }
            if (sum <= 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    // 解法二：应用动态规划法
    // 如果用 f(i) 表示以第 i 个数字结尾的子数组的最大和，那么我们需要求出 max[f(i)]，其中 0 <= i < n。
    // 我们可以用如下递归公式求 f(i)：
    //          data[i]            i = 0 或者 f(i-1) <= 0
    // f(i) = {
    //          f(i-1) + data[i]   i != 0 并且 f(i-1) > 0
    public static int findGreatestSumOfSubArray_Solution2(int[] data) throws Exception {
        if (data == null || data.length == 0) {
            throw new Exception("Invalid input");
        }

        int nCurSum = 0;
        int nGreatestSum = 0x80000000;
        for (int i = 0; i < data.length; ++i) {
            if (nCurSum <= 0) {
                nCurSum = data[i];
            } else {
                nCurSum += data[i];
            }

            if (nCurSum > nGreatestSum)
                nGreatestSum = nCurSum;
        }
        return nGreatestSum;
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
    private static void test(String testName, int[] pData, int expected, boolean expectedFlag) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins:\n", testName);

        try {
            int result = Q42_GreatestSumOfSubArrays.findGreatestSumOfSubArray(pData);
            if (result == expected) {
                System.out.println("Passed.");
            } else {
                System.out.println("Failed.");
            }
        } catch (Exception e) {
            if (expectedFlag) {
                System.out.println("Passed.");
            } else {
                System.out.println("Failed.");
            }
        }

        try {
            int result = Q42_GreatestSumOfSubArrays.findGreatestSumOfSubArray_Solution2(pData);
            if (result == expected) {
                System.out.println("Passed.");
            } else {
                System.out.println("Failed.");
            }
        } catch (Exception e) {
            if (expectedFlag) {
                System.out.println("Passed.");
            } else {
                System.out.println("Failed.");
            }
        }
    }

    // 1, -2, 3, 10, -4, 7, 2, -5
    private static void Test1() {
        int[] data = {1, -2, 3, 10, -4, 7, 2, -5};
        test("Test1", data, 18, false);
    }

    // 所有数字都是负数
    // -2, -8, -1, -5, -9
    private static void Test2() {
        int[] data = {-2, -8, -1, -5, -9};
        test("Test2", data, -1, false);
    }

    // 所有数字都是正数
    // 2, 8, 1, 5, 9
    private static void Test3() {
        int[] data = {2, 8, 1, 5, 9};
        test("Test3", data, 25, false);
    }

    // 无效输入
    private static void Test4() {
        test("Test4", null, 0, true);
    }
}
