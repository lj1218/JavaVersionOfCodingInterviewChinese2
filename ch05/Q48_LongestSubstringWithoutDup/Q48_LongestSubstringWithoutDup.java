package CodingInterviewChinese2.ch05.Q48_LongestSubstringWithoutDup;

import java.util.Arrays;

/**
 * Created by lj1218.
 * Date: 2019/9/5
 *
 * Page: 236
 * 面试题48：最长不含重复字符的子字符串
 * 题目：请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子
 * 字符串的长度。假设字符串中只包含从'a'到'z'的字符。
 */
public class Q48_LongestSubstringWithoutDup {

    public static void main(String[] args) {
        Test.main();
    }

    // 动态规划
    public static int longestSubstringWithoutDuplication(String input) {
        if (input == null) {
            return 0;
        }

        int[] positions = new int[26];
        Arrays.fill(positions, -1);

        char[] string = input.toCharArray();
        int maxLen = 0; // 最长不含重复字符的子字符串长度
        int maxLenFromEnd = 0; // 以当前位置字符为结尾的最长不重复子串长度
        for (int i = 0; i < string.length; ++i) {
            int lastPos = positions[string[i] - 'a'];
            if ((i - lastPos) <= maxLenFromEnd) {
                // 如果 当前位置 与 当前位置字符上次出现的位置 下标之差 小于等于 maxLenFromEnd，
                // 则说明 string[lastPos+1, i] 为新的最长字符串。
                maxLenFromEnd = i - lastPos;
            } else {
                ++maxLenFromEnd;
                if (maxLenFromEnd > maxLen) {
                    maxLen = maxLenFromEnd;
                }
            }
            positions[string[i] - 'a'] = i; // 更新下标
        }

        return maxLen;
    }
}

class Test {

    public static void main() {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
        test10();
    }

    // ====================测试代码====================
    public static void test(String testName, String input, int expected) {
        int output = Q48_LongestSubstringWithoutDup.longestSubstringWithoutDuplication(input);
        if (output == expected) {
            System.out.println(testName + " passed, with input: " + input);
        } else {
            System.out.println(testName + " FAILED, with input: " + input);
        }
    }

    public static void test1() {
        String input = "abcacfrar";
        int expected = 4;
        test("test1", input, expected);
    }

    public static void test2() {
        String input = "acfrarabc";
        int expected = 4;
        test("test2", input, expected);
    }

    public static void test3() {
        String input = "arabcacfr";
        int expected = 4;
        test("test3", input, expected);
    }

    public static void test4() {
        String input = "aaaa";
        int expected = 1;
        test("test4", input, expected);
    }

    public static void test5() {
        String input = "abcdefg";
        int expected = 7;
        test("test5", input, expected);
    }

    public static void test6() {
        String input = "aaabbbccc";
        int expected = 2;
        test("test6", input, expected);
    }

    public static void test7() {
        String input = "abcdcba";
        int expected = 4;
        test("test7", input, expected);
    }

    public static void test8() {
        String input = "abcdaef";
        int expected = 6;
        test("test8", input, expected);
    }

    public static void test9() {
        String input = "a";
        int expected = 1;
        test("test9", input, expected);
    }

    public static void test10() {
        String input = "";
        int expected = 0;
        test("test10", input, expected);
    }
}
