package CodingInterviewChinese2.ch05.Q40_KLeastNumbers;

import CodingInterviewChinese2.util.Common;
import CodingInterviewChinese2.util.MaxHeap;

import java.util.Arrays;

/**
 * Created by lj1218.
 * Date: 2019/9/2
 *
 * Page: 209
 * 面试题40：最小的k个数
 * 题目：输入n个整数，找出其中最小的k个数。例如输入4、5、1、6、2、7、3、8
 * 这8个数字，则最小的4个数字是1、2、3、4。
 */
public class Q40_KLeastNumbers {

    public static void main(String[] args) {
        Test.main();
    }

    // ====================方法1====================
    // 不会修改输入数组元素顺序（借助最大堆），时间复杂度 O(n)，适用于海量数据
    public static Integer[] getLeastNumbers_Solution1(Integer[] input, int k) throws Exception {
        if (input == null || input.length == 0 || k <= 0 || k > input.length) {
            throw new Exception("Invalid parameters");
        }

        MaxHeap<Integer> maxHeap = new MaxHeap<>(Arrays.copyOf(input, k)); // 用 input 前 k 个元素建立大顶堆
        for (int i = k; i < input.length; ++i) {
            if (input[i] < maxHeap.getHeapElem()) {
                maxHeap.replaceHeapElem(input[i]);
            }
        }

        return maxHeap.getHeapElements();
    }

    // ====================方法2====================
    // 会修改输入数组元素顺序（快排思想），时间复杂度 O(nlogk)，不适用于海量数据
    public static Integer[] getLeastNumbers_Solution2(Integer[] input, int k) throws Exception {
        if (input == null || input.length == 0 || k <= 0 || k > input.length) {
            throw new Exception("Invalid parameters");
        }

        int start = 0;
        int end = input.length - 1;
        int index = Common.partition(input, start, end);
        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
            } else {
                start = index + 1;
            }
            index = Common.partition(input, start, end);
        }

        return Arrays.copyOf(input, k);
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
    private static void test(String testName, Integer[] data, int[] expectedResult, int k) {
        if (testName == null)
            return;

        System.out.printf("===== %s begins: =====\n", testName);

        if (expectedResult == null) {
            System.out.println("The input is invalid, we don't expect any result.");
        } else {
            System.out.println("Expected result:");
            System.out.println(Arrays.toString(expectedResult));
            System.out.println();
        }

        Integer[] output = new Integer[0];
        try {
            output = Q40_KLeastNumbers.getLeastNumbers_Solution1(data, k);
        } catch (Exception e) {
            // input is invalid
        }
        System.out.println("Result for solution1:");
        if (expectedResult != null) {
            System.out.println(Arrays.toString(output));
        }

        output = new Integer[0];
        try {
            output = Q40_KLeastNumbers.getLeastNumbers_Solution2(data, k);
        } catch (Exception e) {
            // input is invalid
        }
        System.out.println("Result for solution2:");
        if (expectedResult != null) {
            System.out.println(Arrays.toString(output));
        }
        System.out.println();
    }

    // k小于数组的长度
    private static void Test1() {
        Integer[] data = {4, 5, 1, 6, 2, 7, 3, 8};
        int[] expected = {1, 2, 3, 4};
        test("Test1", data, expected, expected.length);
    }

    // k等于数组的长度
    private static void Test2() {
        Integer[] data = {4, 5, 1, 6, 2, 7, 3, 8};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
        test("Test2", data, expected, expected.length);
    }

    // k大于数组的长度
    private static void Test3() {
        Integer[] data = {4, 5, 1, 6, 2, 7, 3, 8};
        test("Test3", data, null, 10);
    }

    // k等于1
    private static void Test4() {
        Integer[] data = {4, 5, 1, 6, 2, 7, 3, 8};
        int[] expected = {1};
        test("Test4", data, expected, expected.length);
    }

    // k等于0
    private static void Test5() {
        Integer[] data = {4, 5, 1, 6, 2, 7, 3, 8};
        test("Test5", data, null, 0);
    }

    // 数组中有相同的数字
    private static void Test6() {
        Integer[] data = {4, 5, 1, 6, 2, 7, 2, 8};
        int[] expected = {1, 2};
        test("Test6", data, expected, expected.length);
    }

    // 输入空指针
    private static void Test7() {
        test("Test7", null, null, 0);
    }
}
