package CodingInterviewChinese2.ch05.Q46_TranslateNumbersToStrings;

/**
 * Created by lj1218.
 * Date: 2019/9/4
 *
 * Page: 231
 * 面试题46：把数字翻译成字符串
 * 题目：给定一个数字，我们按照如下规则把它翻译为字符串：0翻译成"a"，1翻
 * 译成"b"，……，11翻译成"l"，……，25翻译成"z"。一个数字可能有多个翻译。例
 * 如12258有5种不同的翻译，它们分别是"bccfi"、"bwfi"、"bczi"、"mcfi"和
 * "mzi"。请编程实现一个函数用来计算一个数字有多少种不同的翻译方法。
 *
 * 提示：用递归（自上而下）的思路分析问题，用循环（自下而上）方式写代码。
 */
public class Q46_TranslateNumbersToStrings {

    public static void main(String[] args) {
        Test.main();
    }

    public static int getTranslationCount(int number) {
        if (number < 0) {
            return 0;
        }
        String numberInString = "" + number;
//        return getTranslationCount(numberInString);
        return getTranslationCount2(numberInString);
    }

    // 方法一：空间复杂度为 O(n)
    private static int getTranslationCount(String number) {
        int length = number.length();
        int[] counts = new int[length];

        for (int i = length - 1; i >= 0; --i) {
            if (i == length - 1) {
                counts[i] = 1;
            } else {
                int count = counts[i + 1];
                int d1 = number.charAt(i) - '0';
                int d2 = number.charAt(i + 1) - '0';
                int d1d2 = d1 * 10 + d2;
                if (d1d2 >= 10 && d1d2 <= 25) {
                    if (i + 2 < length) {
                        count += counts[i + 2];
                    } else {
                        count += 1;
                    }
                }
                counts[i] = count;
            }
        }

        return counts[0];
    }

    // 方法二：不需要辅助数组，空间复杂度为 O(1)
    private static int getTranslationCount2(String number) {
        int length = number.length();
        int countI = 0;       // f(i)
        int countIPlus1 = 0;  // f(i+1)
        int countIPlus2 = 0;  // f(i+2)
        for (int i = length - 1; i >= 0; --i) {
            if (i == length - 1) {
                // 设置 countI 并更新变量 countIPlus1
                countI = countIPlus1 = 1;
            } else {
                int d1 = number.charAt(i) - '0';
                int d2 = number.charAt(i + 1) - '0';
                int d1d2 = d1 * 10 + d2;
                if (d1d2 >= 10 && d1d2 <= 25) {
                    if (i + 2 < length) {
                        countI += countIPlus2;
                    } else {
                        countI += 1;
                    }
                }

                // 更新变量，以便下一次迭代
                countIPlus2 = countIPlus1;
                countIPlus1 = countI;
            }
        }

        return countI;
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
    }

    // ====================测试代码====================
    private static void test(String testName, int number, int expected) {
        if (Q46_TranslateNumbersToStrings.getTranslationCount(number) == expected) {
            System.out.println(testName + " passed.");
        } else {
            System.out.println(testName + " FAILED.");
        }
    }

    private static void Test1() {
        int number = 0;
        int expected = 1;
        test("Test1", number, expected);
    }

    private static void Test2() {
        int number = 10;
        int expected = 2;
        test("Test2", number, expected);
    }

    private static void Test3() {
        int number = 125;
        int expected = 3;
        test("Test3", number, expected);
    }

    private static void Test4() {
        int number = 126;
        int expected = 2;
        test("Test4", number, expected);
    }

    private static void Test5() {
        int number = 426;
        int expected = 1;
        test("Test5", number, expected);
    }

    private static void Test6() {
        int number = 100;
        int expected = 2;
        test("Test6", number, expected);
    }

    private static void Test7() {
        int number = 101;
        int expected = 2;
        test("Test7", number, expected);
    }

    private static void Test8() {
        int number = 12258;
        int expected = 5;
        test("Test8", number, expected);
    }

    private static void Test9() {
        int number = -100;
        int expected = 0;
        test("Test9", number, expected);
    }
}
