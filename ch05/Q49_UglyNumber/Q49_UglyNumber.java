package CodingInterviewChinese2.ch05.Q49_UglyNumber;

/**
 * Created by lj1218.
 * Date: 2019/9/5
 *
 * Page: 240
 * 面试题49：丑数
 * 题目：我们把只包含因子2、3和5的数称作丑数（Ugly Number）。求按从小到
 * 大的顺序的第1500个丑数。例如6、8都是丑数，但14不是，因为它包含因子7。
 * 习惯上我们把1当做第一个丑数。
 */
public class Q49_UglyNumber {

    public static void main(String[] args) {
        Test.main();
    }

    // ====================算法1的代码====================
    public static int GetUglyNumber_Solution1(int index) {
        int uglyFound = 0;
        int number = 0;
        while (uglyFound < index) {
            ++number;

            if (isUgly(number)) {
                ++uglyFound;
            }
        }

        return number;
    }

    private static boolean isUgly(int number) {
        while (number % 2 == 0) {
            number /= 2;
        }
        while (number % 3 == 0) {
            number /= 3;
        }
        while (number % 5 == 0) {
            number /= 5;
        }
        return number == 1;
    }

    // ====================算法2的代码====================
    // 空间换时间，空间复杂度：O(n)
    public static int GetUglyNumber_Solution2(int index) {
        if (index <= 0) {
            return 0;
        }

        int[] uglyNumbers = new int[index];
        uglyNumbers[0] = 1;
        int nextUglyIndex = 1;

        int multiply2 = 0;
        int multiply3 = 0;
        int multiply5 = 0;

        while (nextUglyIndex < index) {
            int min = min(uglyNumbers[multiply2] * 2,
                    uglyNumbers[multiply3] * 3,
                    uglyNumbers[multiply5] * 5
            );
            uglyNumbers[nextUglyIndex] = min;

            while (uglyNumbers[multiply2] * 2 <= min) {
                ++multiply2;
            }
            while (uglyNumbers[multiply3] * 3 <= min) {
                ++multiply3;
            }
            while (uglyNumbers[multiply5] * 5 <= min) {
                ++multiply5;
            }

            ++nextUglyIndex;
        }

        return uglyNumbers[index - 1];
    }

    private static int min(int a, int b, int c) {
        int min = a < b ? a : b;
        return min < c ? min : c;
    }
}

class Test {

    public static void main() {
        test("Test1", 1, 1);
        test("Test2", 2, 2);
        test("Test3", 3, 3);
        test("Test4", 4, 4);
        test("Test5", 5, 5);
        test("Test6", 6, 6);
        test("Test7", 7, 8);
        test("Test8", 8, 9);
        test("Test9", 9, 10);
        test("Test10", 10, 12);
        test("Test11", 11, 15);
        test("Test12", 1500, 859963392);
        test("Test13", 0, 0);
    }

    // ====================测试代码====================
    private static void test(String testName, int index, int expected) {
        if (Q49_UglyNumber.GetUglyNumber_Solution1(index) == expected) {
            System.out.println(testName + ": solution1 passed");
        } else {
            System.out.println(testName + ": solution1 failed");
        }

        if (Q49_UglyNumber.GetUglyNumber_Solution2(index) == expected) {
            System.out.println(testName + ": solution2 passed");
        } else {
            System.out.println(testName + ": solution2 failed");
        }

        System.out.println();
    }
}
