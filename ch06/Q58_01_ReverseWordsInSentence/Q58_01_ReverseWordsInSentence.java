package CodingInterviewChinese2.ch06.Q58_01_ReverseWordsInSentence;

import CodingInterviewChinese2.Util;

/**
 * Created by lj1218.
 * Date: 2019/9/8
 *
 * Page: 284
 * 面试题58（一）：翻转单词顺序
 * 题目：输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，
 * 则输出"student. a am I"。
 *
 * 提示：先翻转整个句子，再翻转每个单词。
 */
public class Q58_01_ReverseWordsInSentence {

    public static void main(String[] args) {
        Test.main();
    }

    public static void reverseSentence(char[] sentence) {
        if (sentence == null || sentence.length == 0) {
            return;
        }

        int begin = 0;
        int end = sentence.length - 1;

        // 翻转整个句子
        reverse(sentence, begin, end);

        // 翻转句子中的每个单词
        begin = end = 0;
        while (begin < sentence.length) {
            if (sentence[begin] == ' ') {
                ++begin;
                ++end;
            } else if (end == sentence.length || sentence[end] == ' ') {
                reverse(sentence, begin, end - 1);
                begin = ++end;
            } else {
                ++end;
            }
        }
    }

    private static void reverse(char[] s, int begin, int end) {
        if (s == null || begin < 0 || end >= s.length) {
            return;
        }

        while (begin < end) {
            Util.swap(s, begin++, end--);
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
    }

    // ====================测试代码====================
    private static void test(String testName, char[] input, char[] expectedResult) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        Q58_01_ReverseWordsInSentence.reverseSentence(input);

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

    // 功能测试，句子中有多个单词
    private static void Test1() {
        String input = "I am a student.";
        String expected = "student. a am I";

        test("Test1", input.toCharArray(), expected.toCharArray());
    }

    // 功能测试，句子中只有一个单词
    private static void Test2() {
        String input = "Wonderful";
        String expected = "Wonderful";

        test("Test2", input.toCharArray(), expected.toCharArray());
    }

    // 鲁棒性测试
    private static void Test3() {
        test("Test3", null, null);
    }

    // 边界值测试，测试空字符串
    private static void Test4() {
        test("Test4", "".toCharArray(), "".toCharArray());
    }

    // 边界值测试，字符串中只有空格
    private static void Test5() {
        String input = "   ";
        String expected = "   ";
        test("Test5", input.toCharArray(), expected.toCharArray());
    }
}
