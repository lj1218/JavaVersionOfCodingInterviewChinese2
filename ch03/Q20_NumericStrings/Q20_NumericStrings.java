package CodingInterviewChinese2.ch03.Q20_NumericStrings;

/**
 * Created by lj1218.
 * Date: 2019/8/28
 *
 * Page: 127
 * 面试题20：表示数值的字符串
 * 题目：请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，
 * 字符串“+100”、“5e2”、“-123”、“3.1416”及“-1E-16”都表示数值，但“12e”、
 * “1a3.14”、“1.2.3”、“+-5”及“12e+5.4”都不是
 *
 * 提示：分成 整数、小数、指数 3部分 分别进行匹配
 */
public class Q20_NumericStrings {

    public static void main(String[] args) {
        new Test().main();
    }

    // 数字的格式可以用A[.[B]][e|EC]或者.B[e|EC]表示，其中A和C都是
    // 整数（可以有正负号，也可以没有），而B是一个无符号整数
    public static boolean isNumeric(char[] str) {
        if (str == null) {
            return false;
        }

        Counter scanPos = new Counter();
        boolean numeric = scanInteger(str, scanPos);

        // 如果出现 '.'，则接下来是数字的小数部分
        if (scanPos.getVal() < str.length && str[scanPos.getVal()] == '.') {
            scanPos.inc(1);

            // 下面一行代码用 || 的原因
            // 1. 小数可以没有整数部分，如 .123 等于 0.123
            // 2. 小数点后面可以没有数字，如 233. 等于 233.0
            // 3. 当然，小数点前面和后面都可以有数字，如 233.666
            numeric = scanUnsignedInteger(str, scanPos) || numeric;
        }

        // 如果出现 'e' 或 'E'，则接下来是数字的指数部分
        if (scanPos.getVal() < str.length
                && (str[scanPos.getVal()] == 'e'
                || str[scanPos.getVal()] == 'E')) {
            scanPos.inc(1);

            // 下面一行代码用 && 的原因
            // 1. 当 e 或 E 前面没有数字时，整个字符串不能表示数字，如 .e1、e1
            // 2. 当 e 或 E 后面没有整数时，整个字符串不能表示数字，如 12e、12e+5.4
            numeric = numeric && scanInteger(str, scanPos);
        }

        return numeric && scanPos.getVal() == str.length;
    }

    public static boolean scanUnsignedInteger(char[] str, Counter scanPos) {
        int before = scanPos.getVal();
        while (scanPos.getVal() < str.length
                && str[scanPos.getVal()] >= '0'
                && str[scanPos.getVal()] <= '9'
        ) {
            scanPos.inc(1);
        }

        // 当 str 中存在若干 0~9 的数字时，返回 true
        return scanPos.getVal() > before;
    }

    // 整数的格式可以用[+|-]B表示, 其中B为无符号整数
    public static boolean scanInteger(char[] str, Counter scanPos) {
        if (scanPos.getVal() >= str.length) {
            return false;
        }

        if (str[scanPos.getVal()] == '+' || str[scanPos.getVal()] == '-') {
            scanPos.inc(1);
        }
        return scanUnsignedInteger(str, scanPos);
    }

    static class Counter {
        private int val;

        public Counter() {
            val = 0;
        }

        public Counter reset() {
            val = 0;
            return this;
        }

        public int inc(int n) {
            val += n;
            return val;
        }

        public int getVal() {
            return val;
        }

        @Override
        public String toString() {
            return "" + val;
        }
    }

    static class Test {

        public void main() {
            Test("Test1", "100", true);
            Test("Test2", "123.45e+6", true);
            Test("Test3", "+500", true);
            Test("Test4", "5e2", true);
            Test("Test5", "3.1416", true);
            Test("Test6", "600.", true);
            Test("Test7", "-.123", true);
            Test("Test8", "-1E-16", true);
            Test("Test9", "1.79769313486232E+308", true);

            System.out.print("\n\n");

            Test("Test10", "12e", false);
            Test("Test11", "1a3.14", false);
            Test("Test12", "1+23", false);
            Test("Test13", "1.2.3", false);
            Test("Test14", "+-5", false);
            Test("Test15", "12e+5.4", false);
            Test("Test16", ".", false);
            Test("Test17", ".e1", false);
            Test("Test18", "e1", false);
            Test("Test19", "+.", false);
            Test("Test20", "", false);
            Test("Test21", null, false);
        }

        // ====================测试代码====================
        void Test(String testName, String str, boolean expected) {
            if (testName != null) {
                System.out.printf("%s begins: ", testName);
            }

            if (isNumeric(str == null ? null : str.toCharArray()) == expected) {
                System.out.println("Passed.");
            } else {
                System.out.println("FAILED.");
            }
        }
    }
}
