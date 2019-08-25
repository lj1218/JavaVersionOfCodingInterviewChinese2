package CodingInterviewChinese2.ch03.Q16_Power;


/**
 * Created by lj1218.
 * Date: 2019/8/25
 *
 * Page: 110
 * 面试题16：数值的整数次方
 * 题目：实现函数double Power(double base, int exponent)，求base的exponent
 * 次方。不得使用库函数，同时不需要考虑大数问题。
 */
public class Q16_Power {

    public static void main(String[] args) {
        // 底数、指数都为正数
        Test("Test1", 2, 3, 8, false);

        // 底数为负数、指数为正数
        Test("Test2", -2, 3, -8, false);

        // 指数为负数
        Test("Test3", 2, -3, 0.125, false);

        // 指数为0
        Test("Test4", 2, 0, 1, false);

        // 底数、指数都为0
        Test("Test5", 0, 0, 1, false);

        // 底数为0、指数为正数
        Test("Test6", 0, 4, 0, false);

        // 底数为0、指数为负数
        Test("Test7", 0, -4, 0, true);
    }

    public static boolean g_InvalidInput = false;

    public static double power(double base, int exponent) {
        g_InvalidInput = false;

        if (equal(base, 0.0) && exponent < 0) {
            g_InvalidInput = true;
            return 0.0;
        }

        int absExponent = exponent;
        if (exponent < 0) {
            absExponent = -exponent;
        }

        double result = powerWithUnsignedExponent(base, absExponent);
        if (exponent < 0) {
            result = 1.0 / result;
        }

        return result;
    }

//    public static double powerWithUnsignedExponent(double base, int exponent) {
//        double result = 1.0;
//
//        for (int i = 0; i < exponent; ++i) {
//            result *= base;
//        }
//
//        return result;
//    }

    public static double powerWithUnsignedExponent(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }

        double result = powerWithUnsignedExponent(base, exponent >>> 1);
        result *= result;
        if ((exponent & 0x1) == 0x1) {
            result *= base;
        }

        return result;
    }

    public static boolean equal(double d1, double d2) {
        return (d1 - d2 > -0.000001) && (d1 - d2 < 0.000001);
    }

    // ====================测试代码====================
    public static void Test(
            String testName, double base, int exponent,
            double expectedResult, boolean expectedFlag
    ) {
        double result = power(base, exponent);
        if (equal(result, expectedResult) && g_InvalidInput == expectedFlag) {
            System.out.println(testName + " passed");
        } else {
            System.out.println(testName + " FAILED");
        }
    }
}
