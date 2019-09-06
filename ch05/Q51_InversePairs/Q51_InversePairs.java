package CodingInterviewChinese2.ch05.Q51_InversePairs;

/**
 * Created by lj1218.
 * Date: 2019/9/6
 *
 * Page: 249
 * 面试题51：数组中的逆序对
 * 题目：在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组
 * 成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 提示：归并排序思想。
 */
public class Q51_InversePairs {

    public static void main(String[] args) {
        Test.main();
    }

    // 算法修改原始数组
    public static int inversePairs(int[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }

        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);

        return inversePairsCore(data, copy, 0, data.length - 1);
        // inversePairsCore() 执行完毕后，copy 为排序数组，
        // 而 data 分为 [0, (start+end)/2] 和 [(start+end)/2 + 1, end] 两个有序子数组。
    }

    // 将第2个参数指定的数组 copy 在 [start, end] 之间升序排序
    private static int inversePairsCore(int[] data, int[] copy, int start, int end) {
        if (start == end) {
//            copy[start] = data[start]; // 这里的拷贝是不需要的，因为 copy 数组最开始已经拷贝了整个 data 数组
            return 0;
        }

        int length = (end - start) / 2;

        // 因为排序是将 乱序的第1个参数指定的数组 排序为 第2个参数指定的数组，因此我们想要 data 有序，则需把 data 作为第 2 个参数传入
        int left = inversePairsCore(copy, data, start, start + length);
        // 当上一条语句执行完毕，data 数组下标在 [start, start + length] 之间的数已升序排序

        int right = inversePairsCore(copy, data, start + length + 1, end);
        // 当上一条语句执行完毕，data 数组下标在 [start + length + 1, end] 之间的数已升序排序

        // i 初始化为前半段最后一个数字的下标
        int i = start + length;
        // j 初始化为后半段最后一个数字的下标
        int j = end;
        int indexCopy = end;
        int count = 0;

        while (i >= start && j >= start + length + 1) {
            if (data[i] > data[j]) {
                count += (j - start - length);
                copy[indexCopy--] = data[i--];
            } else {
                copy[indexCopy--] = data[j--];
            }
        }

        for (; i >= start; --i) {
            copy[indexCopy--] = data[i];
        }

        for (; j >= start + length + 1; --j) {
            copy[indexCopy--] = data[j];
        }

        return left + right + count;
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
        Test8();
    }

    // ====================测试代码====================
    private static void test(String testName, int[] data, int expected) {
        if (testName == null) {
            return;
        }

        if (Q51_InversePairs.inversePairs(data) == expected) {
            System.out.println(testName + " Passed.");
        } else {
            System.out.println(testName + " Failed.");
        }
    }

    private static void Test1() {
        int[] data = {1, 2, 3, 4, 7, 6, 5};
        int expected = 3;

        test("Test1", data, expected);
    }

    // 递减排序数组
    private static void Test2() {
        int[] data = {6, 5, 4, 3, 2, 1};
        int expected = 15;

        test("Test2", data, expected);
    }

    // 递增排序数组
    private static void Test3() {
        int[] data = {1, 2, 3, 4, 5, 6};
        int expected = 0;

        test("Test3", data, expected);
    }

    // 数组中只有一个数字
    private static void Test4() {
        int[] data = {1};
        int expected = 0;

        test("Test4", data, expected);
    }


    // 数组中只有两个数字，递增排序
    private static void Test5() {
        int[] data = {1, 2};
        int expected = 0;

        test("Test5", data, expected);
    }

    // 数组中只有两个数字，递减排序
    private static void Test6() {
        int[] data = {2, 1};
        int expected = 1;

        test("Test6", data, expected);
    }

    // 数组中有相等的数字
    private static void Test7() {
        int[] data = {1, 2, 1, 2, 1};
        int expected = 3;

        test("Test7", data, expected);
    }

    private static void Test8() {
        int expected = 0;

        test("Test8", null, expected);
    }
}
