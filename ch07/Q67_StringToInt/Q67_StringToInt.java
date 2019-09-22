package CodingInterviewChinese2.ch07.Q67_StringToInt;

/**
 * Created by lj1218.
 * Date: 2019/9/22
 *
 * Page: 318
 * 面试题67：把字符串转换成整数
 * 题目：请你写一个函数StrToInt，实现把字符串转换成整数这个功能。当然，不
 * 能使用atoi或者其他类似的库函数。
 *
 * 提示：
 * （1）第1个字符可能为 '+' 或 '-'
 * （2）剩余字符串非法的几种情况：
 *     a. 总长度为 0
 *     b. 含有 '0'~'9' 之外的其他字符
 *     c. 向上溢出为正时超过 int 能表示的最大整数）或向下溢出（为负时超过 int 能表示的最小整数）
 */
public class Q67_StringToInt {

    public static void main(String[] args) {
        Test.main();
    }

    public static int strToInt(String str) throws Exception {
        if (str == null || str.length() == 0) {
            throw new Exception("Invalid str");
        }

        boolean isMinus = false;
        int pos = 0;
        if (str.charAt(0) == '+') {
            ++pos;
        } else if (str.charAt(0) == '-') {
            ++pos;
            isMinus = true;
        }

        if (str.length() == pos) {
            // str 为 "+" 或者 "-" 的情况
            throw new Exception("Invalid str");
        }

        return (int) strToIntCore(str, pos, isMinus);
    }

    private static long strToIntCore(String str, int pos, boolean isMinus) throws Exception {
        long num = 0;
        int sign = isMinus ? -1 : 1;
        while (pos < str.length()) {
            char digit = str.charAt(pos++);
            if (digit > '9' || digit < '0') {
                throw new Exception("Invalid str");
            }

            num = 10 * num + sign * (digit - '0');
            if ((!isMinus && num > Integer.MAX_VALUE)
                    || (isMinus && num < Integer.MIN_VALUE)) {
                throw new Exception("Overflow");
            }
        }

        return num;
    }
}

class Test {

    public static void main() {
        test(null, false);

        test("", false);

        test("123", true);

        test("+123", true);

        test("-123", true);

        test("1a33", false);

        test("+0", true);

        test("-0", true);

        //有效的最大正整数, 0x7FFFFFFF
        test("+2147483647", true);

        test("-2147483647", true);

        test("+2147483648", false);

        //有效的最小负整数, 0x80000000
        test("-2147483648", true);

        test("+2147483649", false);

        test("-2147483649", false);

        test("+", false);

        test("-", false);
    }

    // ====================测试代码====================
    private static void test(String str, boolean valid) {
        try {
            int result = Q67_StringToInt.strToInt(str);
            System.out.printf("number for %s is: %d.\n", str, result);
        } catch (Exception e) {
            if (!valid) {
                System.out.printf("the input %s is invalid.\n", str);
            }
        }
    }
}
