package CodingInterviewChinese2.ch03.Q19_RegularExpressonsMatching;

/**
 * Created by lj1218.
 * Date: 2019/8/28
 *
 * Page: 124
 * 面试题19：正则表达式匹配
 * 题目：请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'
 * 表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题
 * 中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"'
 * 和"ab*ac*a"匹配，但与"aa.a"及"ab*a"均不匹配。
 */
public class Q19_RegularExpressonsMatching {

    public static void main(String[] args) {
        Test.main();
    }

    public static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null) {
            return false;
        }

        return matchCore(str, 0, pattern, 0);
    }

    public static boolean matchCore(
            char[] str, int strMatchPos,
            char[] pattern, int patternMatchPos
    ) {
        if (strMatchPos == str.length && patternMatchPos == pattern.length) {
            return true;
        }

        if (strMatchPos < str.length && patternMatchPos >= pattern.length) {
            return false;
        }

        // 注意数组越界问题
        if (patternMatchPos < pattern.length - 1 && pattern[patternMatchPos + 1] == '*') {
            if ((strMatchPos < str.length && pattern[patternMatchPos] == str[strMatchPos])
                    || (pattern[patternMatchPos] == '.' && strMatchPos < str.length)) {
                // move on the next state
                return matchCore(str, strMatchPos + 1, pattern, patternMatchPos + 2)
                        // stay on the current state
                        || matchCore(str, strMatchPos + 1, pattern, patternMatchPos)
                        // ignore a '*'
                        || matchCore(str, strMatchPos, pattern, patternMatchPos + 2);
            } else {
                // ignore a '*'
                return matchCore(str, strMatchPos, pattern, patternMatchPos + 2);
            }
        }

        // 注意数组越界问题
        if ((strMatchPos < str.length && patternMatchPos < pattern.length
                && pattern[patternMatchPos] == str[strMatchPos])
                || (pattern[patternMatchPos] == '.' && strMatchPos < str.length)) {
            return matchCore(str, strMatchPos + 1, pattern, patternMatchPos + 1);
        }

        return false;
    }
}

class Test {

    public static void main() {
        Test("Test01", "", "", true);
        Test("Test02", "", ".*", true);
        Test("Test03", "", ".", false);
        Test("Test04", "", "c*", true);
        Test("Test05", "a", ".*", true);
        Test("Test06", "a", "a.", false);
        Test("Test07", "a", "", false);
        Test("Test08", "a", ".", true);
        Test("Test09", "a", "ab*", true);
        Test("Test10", "a", "ab*a", false);
        Test("Test11", "aa", "aa", true);
        Test("Test12", "aa", "a*", true);
        Test("Test13", "aa", ".*", true);
        Test("Test14", "aa", ".", false);
        Test("Test15", "ab", ".*", true);
        Test("Test16", "ab", ".*", true);
        Test("Test17", "aaa", "aa*", true);
        Test("Test18", "aaa", "aa.a", false);
        Test("Test19", "aaa", "a.a", true);
        Test("Test20", "aaa", ".a", false);
        Test("Test21", "aaa", "a*a", true);
        Test("Test22", "aaa", "ab*a", false);
        Test("Test23", "aaa", "ab*ac*a", true);
        Test("Test24", "aaa", "ab*a*c*a", true);
        Test("Test25", "aaa", ".*", true);
        Test("Test26", "aab", "c*a*b", true);
        Test("Test27", "aaca", "ab*a*c*a", true);
        Test("Test28", "aaba", "ab*a*c*a", false);
        Test("Test29", "bbbba", ".*a*a", true);
        Test("Test30", "bcbbabab", ".*a*a", false);
    }

    // ====================测试代码====================
    public static void Test(String testName, String string, String pattern, boolean expected) {
        if (testName != null) {
            System.out.printf("%s begins: ", testName);
        }

        if (Q19_RegularExpressonsMatching.match(
                string.toCharArray(), pattern.toCharArray()) == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }
    }
}
