package CodingInterviewChinese2.ch06.Q53_01_NumberOfK;

/**
 * Created by lj1218.
 * Date: 2019/9/6
 *
 * Page: 263
 * 面试题53（一）：数字在排序数组中出现的次数
 * 题目：统计一个数字在排序数组中出现的次数。例如输入排序数组{1, 2, 3, 3,
 * 3, 3, 4, 5}和数字3，由于3在这个数组中出现了4次，因此输出4。
 */
public class Q53_01_NumberOfK {

    public static void main(String[] args) {
        Test.main();
    }

    public static int getNumberOfK(int[] data, int k) {
        if (data == null || data.length == 0) {
            return 0;
        }

        int lastK = getLastK(data, k, 0, data.length - 1);
        if (lastK == -1) {
            return 0;
        }

        return lastK - getFirstK(data, k, 0, data.length - 1) + 1;
    }

    // 找到数组中第一个k的下标。如果数组中不存在k，返回-1
    private static int getFirstK(int[] data, int k, int start, int end) {
        if (start > end) {
            return -1;
        }

        int middle = (start + end) / 2;
        int middleData = data[middle];
        if (k < middleData) {
            // k 位于前半部分
            return getFirstK(data, k, start, middle - 1);
        } else if (k > middleData) {
            // k 位于后半部分
            return getFirstK(data, k, middle + 1, end);
        }

        // k == middleData
        if (middle - 1 >= 0) {
            if (data[middle - 1] != k) {
                // 前一个与 k 不等，而 middle 等于 k 时，那么就找到了第一个 k 的下标 middle
                return middle;
            }

            // middle 及 前一个数 都等于 k，说明第一个 k 的下标 <= middle - 1，则第一个 k 位于前半部分
            return getFirstK(data, k, start, middle - 1);
        }

        return middle; // 此时 middle 等于 0，为第一个 k 出现的位置
    }

    // 找到数组中最后一个k的下标。如果数组中不存在k，返回-1
    private static int getLastK(int[] data, int k, int start, int end) {
        if (start > end) {
            return -1;
        }

        int middle = (start + end) / 2;
        int middleData = data[middle];
        if (k < middleData) {
            // k 位于前半部分
            return getLastK(data, k, start, middle - 1);
        } else if (k > middleData) {
            // k 位于后半部分
            return getLastK(data, k, middle + 1, end);
        }

        // k == middleData
        if (middle + 1 < data.length) {
            if (data[middle + 1] != k) {
                // 后一个与 k 不等，而 middle 等于 k 时，那么就找到了最后一个 k 的下标 middle
                return middle;
            }

            // middle 及 后一个数 都等于 k，说明最后一个 k 的下标 >= middle + 1，则最后一个 k 位于后半部分
            return getLastK(data, k, middle + 1, end);
        }

        return middle; // 此时 middle 等于 data.length - 1，为最后一个 k 出现的位置
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
        Test9();
        Test10();
        Test11();
    }

    // ====================测试代码====================
    private static void test(String testName, int[] data, int k, int expected) {
        if (testName == null) {
            return;
        }
        System.out.printf("%s begins: ", testName);

        int result = Q53_01_NumberOfK.getNumberOfK(data, k);
        if (result == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    // 查找的数字出现在数组的中间
    private static void Test1() {
        int data[] = {1, 2, 3, 3, 3, 3, 4, 5};
        test("Test1", data, 3, 4);
    }

    // 查找的数组出现在数组的开头
    private static void Test2() {
        int data[] = {3, 3, 3, 3, 4, 5};
        test("Test2", data, 3, 4);
    }

    // 查找的数组出现在数组的结尾
    private static void Test3() {
        int data[] = {1, 2, 3, 3, 3, 3};
        test("Test3", data, 3, 4);
    }

    // 查找的数字不存在
    private static void Test4() {
        int data[] = {1, 3, 3, 3, 3, 4, 5};
        test("Test4", data, 2, 0);
    }

    // 查找的数字比第一个数字还小，不存在
    private static void Test5() {
        int data[] = {1, 3, 3, 3, 3, 4, 5};
        test("Test5", data, 0, 0);
    }

    // 查找的数字比最后一个数字还大，不存在
    private static void Test6() {
        int data[] = {1, 3, 3, 3, 3, 4, 5};
        test("Test6", data, 6, 0);
    }

    // 数组中的数字从头到尾都是查找的数字
    private static void Test7() {
        int data[] = {3, 3, 3, 3};
        test("Test7", data, 3, 4);
    }

    // 数组中的数字从头到尾只有一个重复的数字，不是查找的数字
    private static void Test8() {
        int data[] = {3, 3, 3, 3};
        test("Test8", data, 4, 0);
    }

    // 数组中只有一个数字，是查找的数字
    private static void Test9() {
        int data[] = {3};
        test("Test9", data, 3, 1);
    }

    // 数组中只有一个数字，不是查找的数字
    private static void Test10() {
        int data[] = {3};
        test("Test10", data, 4, 0);
    }

    // 鲁棒性测试，数组空指针
    private static void Test11() {
        test("Test11", null, 0, 0);
    }
}
