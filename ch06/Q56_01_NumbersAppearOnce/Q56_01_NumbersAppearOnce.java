package CodingInterviewChinese2.ch06.Q56_01_NumbersAppearOnce;

/**
 * Created by lj1218.
 * Date: 2019/9/8
 *
 * Page: 275
 * 面试题56（一）：数组中只出现一次的两个数字
 * 题目：一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序
 * 找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 */
public class Q56_01_NumbersAppearOnce {

    public static void main(String[] args) {
        Test.main();
    }

    public static int[] findNumsAppearOnce(int[] data) {
        if (data == null || data.length < 2) {
            return new int[2];
        }

        int xorResult = 0;
        for (int d : data) {
            xorResult ^= d;
        }

        int firstBitIs1 = findFirstBitIs1(xorResult);

        int xorFirstGroup = 0;
        int xorSecondGroup = 0;
        for (int d : data) {
            if (isBit1(d, firstBitIs1)) {
                xorFirstGroup ^= d;
            } else {
                xorSecondGroup ^= d;
            }
        }

        return new int[]{xorFirstGroup, xorSecondGroup};
    }

    // 找到num从右边数起第一个是1的位
    private static int findFirstBitIs1(int num) {
        int indexBit = 0;
        while (((num & 0x1) == 0) && indexBit < 32) {
            num >>>= 1;
            ++indexBit;
        }
        return indexBit;
    }

    // 判断数字num的第indexBit位是不是1
    private static boolean isBit1(int num, int indexBit) {
        num >>>= indexBit;
        return (num & 0x1) == 0x1;
    }
}

class Test {

    public static void main() {
        Test1();
        Test2();
        Test3();
    }

    // ====================测试代码====================
    private static void test(String testName, int[] data, int expected1, int expected2) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        int[] result = Q56_01_NumbersAppearOnce.findNumsAppearOnce(data);
        int result1 = result[0];
        int result2 = result[1];

        if ((expected1 == result1 && expected2 == result2) ||
                (expected2 == result1 && expected1 == result2)) {
            System.out.print("Passed.\n\n");
        } else {
            System.out.print("Failed.\n\n");
        }
    }

    private static void Test1() {
        int[] data = {2, 4, 3, 6, 3, 2, 5, 5};
        test("Test1", data, 4, 6);
    }

    private static void Test2() {
        int[] data = {4, 6};
        test("Test2", data, 4, 6);
    }

    private static void Test3() {
        int[] data = {4, 6, 1, 1, 1, 1};
        test("Test3", data, 4, 6);
    }
}
