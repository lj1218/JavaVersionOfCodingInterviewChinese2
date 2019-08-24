package CodingInterviewChinese2.ch03.Q05_ReplaceSpaces;

/**
 * Created by lj1218.
 * Date: 2019/8/21
 * Page: 51
 * 面试题5：替换空格
 *   题目：请实现一个函数，把字符串中的每个空格替换成 "%20"。例如输入 “We are happy.”，
 * 则输出 “We%20are%20happy.”。
 *
 * 提示：先计算空格个数，然后从后往前替换。
 */
public class Q05_ReplaceSpaces {

    public static void main(String[] args) {
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

    // string 用 '\0' 作为字符串结束标志
    public static void replaceBlank(char[] string) {
        if (string == null || string.length == 0) {
            return;
        }

        int orgLen = 0;
        int numOfBlank = 0;
        for (int i = 0; i < string.length && string[i] != '\0'; ++i) {
            ++orgLen;
            if (string[i] == ' ') {
                ++numOfBlank;
            }
        }

        // newLen 为把空格替换成 '%20' 之后的长度
        int newLen = orgLen + 2 * numOfBlank;
        if (newLen > string.length) {
            // string 数组不够容纳替换之后的所有字符
            return;
        }

        int indexOfOrg = orgLen; // 指向字符串结束标志 '\0'
        int indexOfNew = newLen;
        while (indexOfNew > indexOfOrg) {
            if (string[indexOfOrg] != ' ') {
                string[indexOfNew--] = string[indexOfOrg--];
            } else {
                --indexOfOrg;
                string[indexOfNew--] = '0';
                string[indexOfNew--] = '2';
                string[indexOfNew--] = '%';
            }
        }
    }

    // ====================测试代码====================
    private static void Test(String testName, char[] str, char[] expected) {
        if (testName != null)
            System.out.printf("%s begins: ", testName);

        replaceBlank(str);

        if (expected == null && str == null)
            System.out.println("passed.");
        else if (expected == null)
            System.out.println("failed.");
        else if (strCmp(str, expected) == 0)
            System.out.println("passed.");
        else
            System.out.println("failed.");
    }

    private static int strCmp(char[] s1, char[] s2) {
        for (int p = 0; p < s1.length && p < s2.length; ++p) {
            if (s1[p] != s2[p]) {
                return s1[p] - s2[p];
            } else if (s1[p] == '\0') {
                return 0;
            }
        }
        return s1.length - s2.length;
    }

    // 空格在句子中间
    private static void Test1() {
        String s = "hello world\0  ";
        char[] str = s.toCharArray();
        Test("Test1", str, "hello%20world\0".toCharArray());
    }

    // 空格在句子开头
    private static void Test2() {
        String s = " helloworld\0  ";
        char[] str = s.toCharArray();
        Test("Test2", str, "%20helloworld\0".toCharArray());
    }

    // 空格在句子末尾
    private static void Test3() {
        String s = "helloworld \0  ";
        char[] str = s.toCharArray();
        Test("Test3", str, "helloworld%20\0".toCharArray());
    }

    // 连续有两个空格
    private static void Test4() {
        String s = "hello  world\0    ";
        char[] str = s.toCharArray();
        Test("Test4", str, "hello%20%20world\0".toCharArray());
    }

    // 传入nullptr
    private static void Test5() {
        Test("Test5", null, null);
    }

    // 传入内容为空的字符串
    private static void Test6() {
        Test("Test6", "\0".toCharArray(), "\0".toCharArray());
    }

    //传入内容为一个空格的字符串
    private static void Test7() {
        Test("Test7", " \0  ".toCharArray(), "%20\0".toCharArray());
    }

    // 传入的字符串没有空格
    private static void Test8() {
        String s = "helloworld\0";
        char[] str = s.toCharArray();
        Test("Test8", str, "helloworld\0".toCharArray());
    }

    // 传入的字符串全是空格
    private static void Test9() {
        String s = "   \0      ";
        char[] str = s.toCharArray();
        Test("Test9", str, "%20%20%20\0".toCharArray());
    }
}
