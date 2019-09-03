package CodingInterviewChinese2.ch05.Q43_NumberOf1;

/**
 * Created by lj1218.
 * Date: 2019/9/3
 *
 * Page: 221
 * 面试题43：从1到n整数中1出现的次数
 * 题目：输入一个整数n，求从1到n这n个整数的十进制表示中1出现的次数。例如
 * 输入12，从1到12这些整数中包含1 的数字有1，10，11和12，1一共出现了5次。
 */
public class Q43_NumberOf1 {

    public static void main(String[] args) {
        Test.main();
    }

    public static int NumberOf1Between1AndN_Solution1(int n) {
        int num = 0;
        for (int i = 1; i <= n; ++i) {
            num += numberOf1(i);
        }
        return num;
    }

    private static int numberOf1(int n) {
        int num = 0;
        while (n > 0) {
            if (n % 10 == 1) {
                ++num;
            }
            n /= 10;
        }
        return num;
    }

    public static int NumberOf1Between1AndN_Solution2(int n) {
        if (n <= 0) {
            return 0;
        }
        char[] strN = ("" + n).toCharArray();
        return NumberOf1Between1AndN_Solution2(strN, 0);
    }

    private static int NumberOf1Between1AndN_Solution2(char[] strN, int startIndex) {
        if (startIndex == strN.length) {
            return 0;
        }

        int first = strN[startIndex] - '0';
        int length = strN.length - startIndex;
        if (length == 1 && first == 0) {
            return 0;
        }

        if (length == 1 && first > 0) {
            return 1;
        }

        // 假设 strN 是 "21345"
        // numFirstDigit 是数字 10000 ~ 19999 的第一位中的数目
        int numFirstDigit = 0;
        if (first > 1) {
            numFirstDigit = (int) Math.pow(10, length - 1);
        } else if (first == 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = startIndex + 1; i < strN.length; ++i) {
                sb.append(strN[i]);
            }
            numFirstDigit = Integer.parseInt(sb.toString()) + 1;
        }

        // numOtherDigits 是 1346 ~ 21345 除第一位之外的数位中的数目
        int numOtherDigits = first * (length - 1) * ((int) Math.pow(10, length - 2));
        // numRecursive 是 1 ~ 1345 中的数目
        int numRecursive = NumberOf1Between1AndN_Solution2(strN, startIndex + 1);

        return numFirstDigit + numOtherDigits + numRecursive;
    }

    // https://blog.csdn.net/zhuge134/article/details/89301174
    public static int NumberOf1Between1AndN_Solution3(int n) {
        /*
         * 以n=12345为例, 从低位依次遍历到高位，以m=100为例，
         *
         * 此时n被分为高位和低位两个数字，a=n/m（这个例子中是123），和b=n%m（这个例子中是45），考虑高位数字中的最后一位数字，这个例子中为3，对于这个数字k分为三种情况：
         *
         * 1. k=0, 那么如果这个位置上的数字要想为1，就意味着左边两个数字必须要小于12，否则组合起来就会大于n（例如n=12045, 121xx>12045）, 所以对于这种情况，该位置数字的为1的组合共有a/10*m。（在我们的例子中靠左边的两个数字组合可以是0-11，所以还是12种）；
         *
         * 2. k=1, 对于这种情况又可以分为两种子情况：
         *
         *   2.1 左边两个数字小于原来的数字，那么右边两个数字就可以是0-(m-1)的任意数字，所以组合共有a/10*m
         *
         *   2.2 左边两个数字与原来的相等，那么右边两个数字只能是0-b范围内的数字，组合共有b+1中
         *
         * 所以k=1的情况 组合共有a/10*m+b+1
         *
         * 3. k>=2, 对于这种情况，当这个为数字为1时，左边两个数字可以是0-12中任意数字，右边两个可以是0-(m-1)中的任意数字，所以组合共有(a/10+1)*m
         *
         *
         * 代码中之所以是a+8是考虑到k>=2时a+8正好进位，此时(a+8)/10等价于(a/10+1)；而当k<2时，a+8不进位，(a+8)/10等价于a/10
         * ————————————————
         */
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }
}

class Test {

    public static void main() {
        test("Test1", 1, 1);
        test("Test2", 5, 1);
        test("Test3", 10, 2);
        test("Test4", 55, 16);
        test("Test5", 99, 20);
        test("Test6", 10000, 4001);
        test("Test7", 21345, 18821);
        test("Test8", 0, 0);
    }

    // ====================测试代码====================
    private static void test(String testName, int n, int expected) {
        if (testName == null)
            return;

        System.out.printf("%s begins: \n", testName);

        if (Q43_NumberOf1.NumberOf1Between1AndN_Solution1(n) == expected) {
            System.out.println("Solution1 passed.");
        } else {
            System.out.println("Solution1 failed.");
        }

        if (Q43_NumberOf1.NumberOf1Between1AndN_Solution2(n) == expected) {
            System.out.println("Solution2 passed.");
        } else {
            System.out.println("Solution2 failed.");
        }

        if (Q43_NumberOf1.NumberOf1Between1AndN_Solution3(n) == expected) {
            System.out.println("Solution3 passed.");
        } else {
            System.out.println("Solution3 failed.");
        }

        System.out.println();
    }
}
