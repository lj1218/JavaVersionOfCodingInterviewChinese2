package CodingInterviewChinese2.ch05.Q50_01_FirstNotRepeatingChar;

/**
 * Created by lj1218.
 * Date: 2019/9/6
 *
 * Page: 243
 * 面试题50（一）：字符串中第一个只出现一次的字符
 * 题目：在字符串中找出第一个只出现一次的字符。如输入"abaccdeff"，则输出'b'。
 *
 * 提示：使用数组构建简单哈希表，字符值作为键，出现次数作为值。
 */
public class Q50_01_FirstNotRepeatingChar {

    public static void main(String[] args) {
        Test.main();
    }

    public static char firstNotRepeatingChar(String string) {
        if (string == null || string.length() == 0) {
            return '\0';
        }

        char[] appearance = new char[256];
        for (int i = 0; i < string.length(); ++i) {
            appearance[string.charAt(i)]++;
        }

        for (int i = 0; i < string.length(); ++i) {
            if (appearance[string.charAt(i)] == 1) {
                return string.charAt(i);
            }
        }

        return '\0';
    }
}

class Test {

    public static void main() {
        // 常规输入测试，存在只出现一次的字符
        test("test1", "google", 'l');

        // 常规输入测试，不存在只出现一次的字符
        test("test2", "aabccdbd", '\0');

        // 常规输入测试，所有字符都只出现一次
        test("test3", "abcdefg", 'a');

        // 鲁棒性测试，输入nullptr
        test("test4", null, '\0');
    }

    // ====================测试代码====================
    private static void test(String testName, String string, char expected) {
        if (testName == null) {
            return;
        }

        if (Q50_01_FirstNotRepeatingChar.firstNotRepeatingChar(string) == expected) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " failed");
        }
    }
}
