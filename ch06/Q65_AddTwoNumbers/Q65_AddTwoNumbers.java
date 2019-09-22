package CodingInterviewChinese2.ch06.Q65_AddTwoNumbers;

/**
 * Created by lj1218.
 * Date: 2019/9/22
 *
 * Page: 310
 * 面试题65：不用加减乘除做加法
 * 题目：写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷
 * 四则运算符号。
 *
 * 提示：位运算。
 */
public class Q65_AddTwoNumbers {

    public static void main(String[] args) {
        Test.main();
    }

    public static int add(int n1, int n2) {
        int sum;
        int carry;

        while (true) {
            sum = (n1 ^ n2);
            carry = (n1 & n2) << 1;
            if (carry == 0) {
                break;
            }
            n1 = sum;
            n2 = carry;
        }

        return sum;
    }
}

class Test {

    public static void main() {
        test(1, 2, 3);
        test(111, 899, 1010);

        test(-1, 2, 1);
        test(1, -2, -1);

        test(3, 0, 3);
        test(0, -4, -4);

        test(-2, -8, -10);
    }

    // ====================测试代码====================
    private static void test(int num1, int num2, int expected) {
        int result = Q65_AddTwoNumbers.add(num1, num2);
        if (result == expected) {
            System.out.printf("%d + %d is %d. Passed\n", num1, num2, result);
        } else {
            System.out.printf("%d + %d is %d. FAILED\n", num1, num2, result);
        }
    }
}
