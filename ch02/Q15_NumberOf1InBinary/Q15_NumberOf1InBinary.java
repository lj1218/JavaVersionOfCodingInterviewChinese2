package CodingInterviewChinese2.ch02.Q15_NumberOf1InBinary;

/**
 * Created by lj1218.
 * Date: 2019/8/25
 *
 * Page: 100
 * 面试题15：二进制中1的个数
 * 题目：请实现一个函数，输入一个整数，输出该数二进制表示中1的个数。例如
 * 把9表示成二进制是1001，有2位是1。因此如果输入9，该函数输出2。
 */
public class Q15_NumberOf1InBinary {

    public static void main(String[] args) {
        // 输入0，期待的输出是0
        Test(0, 0);

        // 输入1，期待的输出是1
        Test(1, 1);

        // 输入10，期待的输出是2
        Test(10, 2);

        // 输入0x7FFFFFFF，期待的输出是31
        Test(0x7FFFFFFF, 31);

        // 输入0xFFFFFFFF（负数），期待的输出是32
        Test(0xFFFFFFFF, 32);

        // 输入0x80000000（负数），期待的输出是1
        Test(0x80000000, 1);
    }

    public static int NumberOf1_Solution1(int n) {
        int count = 0;
        int flag = 1;

        while (flag != 0) {
            if ((n & flag) != 0) {
                ++count;
            }
            flag <<= 1;
        }

        return count;
    }

    public static int NumberOf1_Solution2(int n) {
        int count = 0;

        while (n != 0) {
            ++count;
            n &= (n - 1);
        }

        return count;
    }

    // ====================测试代码====================
    public static void Test(int number, int expected) {
        int actual = NumberOf1_Solution1(number);
        if (actual == expected) {
            System.out.printf("Solution1: Test for %s passed.\n", Integer.toBinaryString(number));
        } else {
            System.out.printf("Solution1: Test for %s failed.\n", Integer.toBinaryString(number));
        }

        actual = NumberOf1_Solution2(number);
        if (actual == expected) {
            System.out.printf("Solution2: Test for %s passed.\n", Integer.toBinaryString(number));
        } else {
            System.out.printf("Solution2: Test for %s failed.\n", Integer.toBinaryString(number));
        }

        System.out.println();
    }
}
