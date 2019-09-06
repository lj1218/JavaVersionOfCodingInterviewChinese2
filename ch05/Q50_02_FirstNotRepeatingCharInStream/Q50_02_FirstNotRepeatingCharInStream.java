package CodingInterviewChinese2.ch05.Q50_02_FirstNotRepeatingCharInStream;

import java.util.Arrays;

/**
 * Created by lj1218.
 * Date: 2019/9/6
 *
 * Page: 247
 * 面试题50（二）：字符流中第一个只出现一次的字符
 * 题目：请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从
 * 字符流中只读出前两个字符"go"时，第一个只出现一次的字符是'g'。当从该字
 * 符流中读出前六个字符"google"时，第一个只出现一次的字符是'l'。
 */
public class Q50_02_FirstNotRepeatingCharInStream {

    public static void main(String[] args) {
        Test.main();
    }

}

class CharStatistics {
    // appearance[i] = -1: 字符 i 未出现过（默认值，因为 0 为有效下标值，因此不能作为默认值）
    // appearance[i] = -2: 字符 i 多次出现（2次或以上）
    // appearance[i] >= 0: 字符 i 只出现过一次，其下标为 appearance[i]
    private int[] appearance;

    private int curIndex = 0; // 最新插入字符的下标（从 0 开始）

    public CharStatistics() {
        appearance = new int[256];
        Arrays.fill(appearance, -1);
    }

    public void insert(char c) {
        if (appearance[c] == -1) {
            // 字符从未出现过，记录第 1 次出现的下标
            appearance[c] = curIndex;
        } else if (appearance[c] >= 0) {
            // 字符出现过 1 次，将下标置为 -2
            appearance[c] = -2;
        }
        ++curIndex;
    }

    public char firstAppearingOnce() {
        // 找出最小下标对应的字符
        int minIndex = Integer.MAX_VALUE;
        char firstAppearingOnceChar = '\0';
        for (int i = 0; i < appearance.length; ++i) {
            int index = appearance[i];
            if (index >= 0 && index < minIndex) {
                minIndex = index;
                firstAppearingOnceChar = (char) i;
            }
        }
        return firstAppearingOnceChar;
    }
}

class Test {

    public static void main() {
        CharStatistics chars = new CharStatistics();

        test("Test1", chars, '\0');

        chars.insert('g');
        test("Test2", chars, 'g');

        chars.insert('o');
        test("Test3", chars, 'g');

        chars.insert('o');
        test("Test4", chars, 'g');

        chars.insert('g');
        test("Test5", chars, '\0');

        chars.insert('l');
        test("Test6", chars, 'l');

        chars.insert('e');
        test("Test7", chars, 'l');
    }

    // ====================测试代码====================
    private static void test(String testName, CharStatistics chars, char expected) {
        if (testName == null) {
            return;
        }

        if (chars.firstAppearingOnce() == expected) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed");
        }
    }
}
