package CodingInterviewChinese2.ch04.Q38_StringPermutation;

import CodingInterviewChinese2.Util;

/**
 * Created by lj1218.
 * Date: 2019/9/1
 *
 * Page: 197
 * 面试题38：字符串的排列
 * 题目：输入一个字符串，打印出该字符串中字符的所有排列。例如输入字符串abc，
 * 则打印出由字符a、b、c所能排列出来的所有字符串abc、acb、bac、bca、cab和cba。
 */
public class Q38_StringPermutation {

    public static void main(String[] args) {
        Test.main();
    }

    public static void permutation(char[] str) {
        if (str == null) {
            return;
        }

        permutation(str, 0);
    }

    // 递归过程不太好理解
    private static void permutation(char[] str, int begin) {
        if (str.length == begin) {
            for (char ch : str) {
                System.out.print(ch);
            }
            System.out.println();
        } else {
            for (int i = begin; i < str.length; ++i) {
                Util.swap(str, i, begin);

                permutation(str, begin + 1);

                Util.swap(str, i, begin);
            }
        }
    }
}

class Test {

    public static void main() {
        test(null);

        test("");

        test("a");

        test("ab");

        test("abc");
    }

    // ====================测试代码====================
    private static void test(String string) {
        if (string == null) {
            System.out.println("Test for null begins:");
            Q38_StringPermutation.permutation(null);
        } else {
            System.out.printf("Test for [%s] begins:\n", string);
            Q38_StringPermutation.permutation(string.toCharArray());
        }
        System.out.println();
    }
}
