package CodingInterviewChinese2.ch06.Q58_02_LeftRotateString;

import CodingInterviewChinese2.util.Common;

/**
 * Created by lj1218.
 * Date: 2019/9/8
 *
 * Page: 286
 * 面试题58（二）：左旋转字符串
 * 题目：字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。比如输入字符串"abcdefg"和数
 * 字2，该函数将返回左旋转2位得到的结果"cdefgab"。
 */
public class Q58_02_LeftRotateString {

    public static void main(String[] args) {
        Test.main();
    }

    // =============== 解法1 ===============
    public static void leftRotateString(char[] str, int n) {
        if (str == null || str.length <= 1 || n <= 0 || n >= str.length) {
            return;
        }

        // 翻转字符串的前面n个字符
        reverse(str, 0, n - 1);

        // 翻转字符串的后面部分
        reverse(str, n, str.length - 1);

        // 翻转整个字符串
        reverse(str, 0, str.length - 1);
    }

    private static void reverse(char[] s, int begin, int end) {
        if (s == null || begin < 0 || end >= s.length) {
            return;
        }

        while (begin < end) {
            Common.swap(s, begin++, end--);
        }
    }

    // =============== 解法2 ===============
    // 递归解法
    public static void leftRotateString_Solution2(char[] str, int distance) {
        if (str == null)
            return;
        int len = str.length;
        rightRotateStringRecursively(str, len, len - (distance % len));
    }

    private static void rightRotateStringRecursively(char[] str, int size, int distance) {
        if (size == 0)
            return;
        if (distance < 0)
            distance += size;
        if (distance == 0)
            return;

        for (int cycleStart = 0; cycleStart < distance; ++cycleStart) {
            char displaced = str[cycleStart];
            int i = cycleStart;
            boolean replaced = false;
            char replacedVal;
            for (i += distance; i < size; i += distance) {
                replacedVal = str[i];
                str[i] = displaced;
                displaced = replacedVal;
                replaced = true;
            }
            if (replaced) {
                str[cycleStart] = displaced;
            }
        }

        int d = size % distance;
        if (d == 0)
            return;
        rightRotateStringRecursively(str, distance, distance - d);
    }

    // =============== 解法3 ===============
    // 解法2对应的非递归解法
    public static void leftRotateString_Solution3(char[] str, int distance) {
        if (str == null)
            return;
        int len = str.length;
        rightRotateStringNonRecursively(str, len, len - (distance % len));
    }

    private static void rightRotateStringNonRecursively(char[] str, int size, int distance) {
        if (size == 0)
            return;
        if (distance < 0)
            distance += size;
        if (distance == 0)
            return;

        while (distance > 0) {
            for (int cycleStart = 0; cycleStart < distance; ++cycleStart) {
                char displaced = str[cycleStart];
                int i = cycleStart;
                boolean replaced = false;
                char replacedVal;
                for (i += distance; i < size; i += distance) {
                    replacedVal = str[i];
                    str[i] = displaced;
                    displaced = replacedVal;
                    replaced = true;
                }
                if (replaced) {
                    str[cycleStart] = displaced;
                }
            }

            int d = size % distance;
            if (d == 0)
                break;
            size = distance;
            distance -= d;
        }
    }

    // =============== 解法4 ===============
    // 参考 Java 1.8 Collections.rotate() (rotate1)
    public static void leftRotateString_Solution4(char[] str, int distance) {
        if (str == null)
            return;
        int len = str.length;
        rightRotateString0(str, len, len - (distance % len));
    }

    private static void rightRotateString0(char[] str, int size, int distance) {
        if (size == 0)
            return;
        if (distance < 0)
            distance += size;
        if (distance == 0)
            return;

        for (int cycleStart = 0, nMoved = 0; nMoved < size; ++cycleStart) {
            char tmp;
            char displaced = str[cycleStart];
            int i = cycleStart;
            do {
                i += distance;
                if (i >= size)
                    i -= size;
                tmp = str[i];
                str[i] = displaced;
                displaced = tmp;
                ++nMoved;
            } while (i != cycleStart);
        }
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
    }

    // ====================测试代码====================
    private static void test(String testName, char[] input, int num, char[] expectedResult) {
        if (testName == null) {
            return;
        }

        System.out.printf("[Solution1] %s begins: ", testName);

        Q58_02_LeftRotateString.leftRotateString(input, num);

        if ((input == null && expectedResult == null)
                || (input != null && strcmp(input, expectedResult) == 0)) {
            System.out.print("Passed.\n\n");
        } else {
            System.out.print("Failed.\n\n");
        }
    }

    private static void testWithSolution2(String testName, char[] input, int num, char[] expectedResult) {
        if (testName == null) {
            return;
        }

        System.out.printf("[Solution2] %s begins: ", testName);

        Q58_02_LeftRotateString.leftRotateString_Solution2(input, num);

        if ((input == null && expectedResult == null)
                || (input != null && strcmp(input, expectedResult) == 0)) {
            System.out.print("Passed.\n\n");
        } else {
            System.out.print("Failed.\n\n");
        }
    }

    private static void testWithSolution3(String testName, char[] input, int num, char[] expectedResult) {
        if (testName == null) {
            return;
        }

        System.out.printf("[Solution3] %s begins: ", testName);

        Q58_02_LeftRotateString.leftRotateString_Solution3(input, num);

        if ((input == null && expectedResult == null)
                || (input != null && strcmp(input, expectedResult) == 0)) {
            System.out.print("Passed.\n\n");
        } else {
            System.out.print("Failed.\n\n");
        }
    }

    private static void testWithSolution4(String testName, char[] input, int num, char[] expectedResult) {
        if (testName == null) {
            return;
        }

        System.out.printf("[Solution4] %s begins: ", testName);

        Q58_02_LeftRotateString.leftRotateString_Solution4(input, num);

        if ((input == null && expectedResult == null)
                || (input != null && strcmp(input, expectedResult) == 0)) {
            System.out.print("Passed.\n\n");
        } else {
            System.out.print("Failed.\n\n");
        }
    }

    private static int strcmp(char[] s1, char[] s2) {
        if (s1 == null && s2 == null) {
            return 0;
        }

        if (s1 == null) {
            return -1;
        }

        if (s2 == null) {
            return 1;
        }

        int p = 0;
        while (p < s1.length && p < s2.length) {
            if (s1[p] != s2[p]) {
                return s1[p] - s2[p];
            }
            ++p;
        }

        return s1.length - s2.length;
    }

    // 功能测试
    private static void Test1() {
        String input = "abcdefg";
        String expected = "cdefgab";

        test("Test1", input.toCharArray(), 2, expected.toCharArray());
        testWithSolution2("Test1", input.toCharArray(), 2, expected.toCharArray());
        testWithSolution3("Test1", input.toCharArray(), 2, expected.toCharArray());
        testWithSolution4("Test1", input.toCharArray(), 2, expected.toCharArray());
    }

    // 边界值测试
    private static void Test2() {
        String input = "abcdefg";
        String expected = "bcdefga";

        test("Test2", input.toCharArray(), 1, expected.toCharArray());
        testWithSolution2("Test2", input.toCharArray(), 1, expected.toCharArray());
        testWithSolution3("Test2", input.toCharArray(), 1, expected.toCharArray());
        testWithSolution4("Test2", input.toCharArray(), 1, expected.toCharArray());
    }

    // 边界值测试
    private static void Test3() {
        String input = "abcdefg";
        String expected = "gabcdef";

        test("Test3", input.toCharArray(), 6, expected.toCharArray());
        testWithSolution2("Test3", input.toCharArray(), 6, expected.toCharArray());
        testWithSolution3("Test3", input.toCharArray(), 6, expected.toCharArray());
        testWithSolution4("Test3", input.toCharArray(), 6, expected.toCharArray());
    }

    // 鲁棒性测试
    private static void Test4() {
        test("Test4", null, 6, null);
        testWithSolution2("Test4", null, 6, null);
        testWithSolution3("Test4", null, 6, null);
        testWithSolution4("Test4", null, 6, null);
    }

    // 鲁棒性测试
    private static void Test5() {
        String input = "abcdefg";
        String expected = "abcdefg";

        test("Test5", input.toCharArray(), 0, expected.toCharArray());
        testWithSolution2("Test5", input.toCharArray(), 0, expected.toCharArray());
        testWithSolution3("Test5", input.toCharArray(), 0, expected.toCharArray());
        testWithSolution4("Test5", input.toCharArray(), 0, expected.toCharArray());
    }

    // 鲁棒性测试
    private static void Test6() {
        String input = "abcdefg";
        String expected = "abcdefg";

        test("Test6", input.toCharArray(), 7, expected.toCharArray());
        testWithSolution2("Test6", input.toCharArray(), 7, expected.toCharArray());
        testWithSolution3("Test6", input.toCharArray(), 7, expected.toCharArray());
        testWithSolution4("Test6", input.toCharArray(), 7, expected.toCharArray());
    }
}
