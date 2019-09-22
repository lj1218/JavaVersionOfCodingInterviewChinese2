package CodingInterviewChinese2.ch06.Q66_ConstuctArray;

/**
 * Created by lj1218.
 * Date: 2019/9/22
 *
 * Page: 312
 * 面试题66：构建乘积数组
 * 题目：给定一个数组A[0, 1, …, n-1]，请构建一个数组B[0, 1, …, n-1]，其
 * 中B中的元素B[i] =A[0]×A[1]×… ×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 * 提示：分成 A[0]×A[1]×… ×A[i-1] 和 A[i+1]×…×A[n-1] 两部分分别累乘计，再分别相乘得到结果。
 *      时间复杂度 O(2*(n-1))
 */
public class Q66_ConstuctArray {

    public static void main(String[] args) {
        Test.main();
    }

    public static double[] buildProductionArray(double[] input) {
        if (input == null || input.length == 0) {
            return new double[0];
        }

        // A[0]×A[1]×… ×A[i-1] 结果直接保存都结果数组中
        // 正向遍历
        double[] output = new double[input.length];
        output[0] = 1;
        for (int i = 1; i < output.length; ++i) {
            output[i] = input[i - 1] * output[i - 1];
        }

        // A[i+1]×…×A[n-1] 保存到变量 temp 中，再分别与前面结果数组中的值相乘得到结果
        // 反向遍历
        double temp = 1;
        for (int i = output.length - 2; i >= 0; --i) {
            temp *= input[i + 1];
            output[i] *= temp;
        }
        return output;
    }
}

class Test {

    public static void main() {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    //================= Test Code =================
    private static boolean EqualArrays(double[] input, double[] output) {
        int length1 = input.length;
        int length2 = output.length;

        if (length1 != length2)
            return false;

        for (int i = 0; i < length1; ++i) {
            if (Math.abs(input[i] - output[i]) > 0.0000001)
                return false;
        }

        return true;
    }

    private static void test(String testName, double[] input, double[] expected) {
        System.out.printf("%s Begins: ", testName);

        double[] output = Q66_ConstuctArray.buildProductionArray(input);
        if (EqualArrays(output, expected)) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }
    }

    private static void test1() {
        // 输入数组中没有0
        double[] input = {1, 2, 3, 4, 5};
        double[] expected = {120, 60, 40, 30, 24};

        test("Test1", input, expected);
    }

    private static void test2() {
        // 输入数组中有一个0
        double[] input = {1, 2, 0, 4, 5};
        double[] expected = {0, 0, 40, 0, 0};

        test("Test2", input, expected);
    }

    private static void test3() {
        // 输入数组中有两个0
        double[] input = {1, 2, 0, 4, 0};
        double[] expected = {0, 0, 0, 0, 0};

        test("Test3", input, expected);
    }

    private static void test4() {
        // 输入数组中有正、负数
        double[] input = {1, -2, 3, -4, 5};
        double[] expected = {120, -60, 40, -30, 24};

        test("Test4", input, expected);
    }

    private static void test5() {
        // 输入输入中只有两个数字
        double[] input = {1, -2};
        double[] expected = {-2, 1};

        test("Test5", input, expected);
    }
}
