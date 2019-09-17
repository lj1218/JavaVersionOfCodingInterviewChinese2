package CodingInterviewChinese2.ch06.Q61_ContinousCards;

import java.util.Arrays;

/**
 * Created by lj1218.
 * Date: 2019/9/17
 *
 * Page: 298
 * 面试题61：扑克牌的顺子
 * 题目：从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王可以看成任意数字。
 */
public class Q61_ContinousCards {

    public static void main(String[] args) {
        Test.main();
    }

    public static boolean isContinuous(int[] numbers) {
        // 0 看作 大、小王
        if (numbers == null || numbers.length < 5) {
            return false;
        }

        Arrays.sort(numbers);

        int numberOfZero = 0;
        int numberOfGaps = 0; // 缺失的数字总数

        // 统计数组中 0 的个数
        for (int n : numbers) {
            if (n != 0) {
                break;
            }
            ++numberOfZero;
        }

        // 统计数组中的间隔数目
        int small = numberOfZero;
        int big = small + 1;
        while (big < numbers.length) {
            if (numbers[small] == numbers[big]) {
                // 两个数相等，说明有对子，不可能是顺子
                return false;
            }

            numberOfGaps += numbers[big] - numbers[small] - 1;
            ++small;
            ++big;
        }

        return numberOfZero >= numberOfGaps;
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
        Test12();
    }

    // ====================测试代码====================
    private static void test(String testName, int[] numbers, boolean expected) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        if (Q61_ContinousCards.isContinuous(numbers) == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    private static void Test1() {
        int numbers[] = {1, 3, 2, 5, 4};
        test("Test1", numbers, true);
    }

    private static void Test2() {
        int numbers[] = {1, 3, 2, 6, 4};
        test("Test2", numbers, false);
    }

    private static void Test3() {
        int numbers[] = {0, 3, 2, 6, 4};
        test("Test3", numbers, true);
    }

    private static void Test4() {
        int numbers[] = {0, 3, 1, 6, 4};
        test("Test4", numbers, false);
    }

    private static void Test5() {
        int numbers[] = {1, 3, 0, 5, 0};
        test("Test5", numbers, true);
    }

    private static void Test6() {
        int numbers[] = {1, 3, 0, 7, 0};
        test("Test6", numbers, false);
    }

    private static void Test7() {
        int numbers[] = {1, 0, 0, 5, 0};
        test("Test7", numbers, true);
    }

    private static void Test8() {
        int numbers[] = {1, 0, 0, 7, 0};
        test("Test8", numbers, false);
    }

    private static void Test9() {
        int numbers[] = {3, 0, 0, 0, 0};
        test("Test9", numbers, true);
    }

    private static void Test10() {
        int numbers[] = {0, 0, 0, 0, 0};
        test("Test10", numbers, true);
    }

    // 有对子
    private static void Test11() {
        int numbers[] = {1, 0, 0, 1, 0};
        test("Test11", numbers, false);
    }

    // 鲁棒性测试
    private static void Test12() {
        test("Test12", null, false);
    }
}
