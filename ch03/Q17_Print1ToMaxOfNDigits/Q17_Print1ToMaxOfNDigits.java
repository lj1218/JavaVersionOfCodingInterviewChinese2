package CodingInterviewChinese2.ch03.Q17_Print1ToMaxOfNDigits;

import java.util.Arrays;

/**
 * Created by lj1218.
 * Date: 2019/8/26
 *
 * Page: 114
 * 面试题17：打印1到最大的n位数
 * 题目：输入数字n，按顺序打印出从1最大的n位十进制数。比如输入3，则
 * 打印出1、2、3一直到最大的3位数即999。
 *
 * 提示：大数问题，用字符数组模拟。
 */
public class Q17_Print1ToMaxOfNDigits {

    public static void main(String[] args) {
        Test(1);
        Test(2);
        Test(3);
        Test(0);
        Test(-1);
    }

    // ==================== 方法一 ====================
    public static void Print1ToMaxOfNDigits_1(int n) {
        if (n <= 0) {
            return;
        }

        char[] number = new char[n];
        Arrays.fill(number, '0');

        while (!Increment(number)) {
            PrintNumber(number);
        }
    }

    // 字符串number表示一个数字，在 number上增加1
    // 如果做加法溢出，则返回true；否则为false
    public static boolean Increment(char[] number) {
        boolean isOverflow = false;
        int nTakeOver = 1; // 从个位开始有进位（相当于加1）
        int nLength = number.length;

        for (int i = nLength - 1; i >= 0; --i) {
            int nSum = number[i] - '0' + nTakeOver;
            if (nSum > 9) {
                if (i == 0) {
                    // 最高位有进位，表示溢出
                    isOverflow = true;
                }
                nSum -= 10;
                number[i] = (char) (nSum + '0');
            } else {
                // 没有进位退出循环
                number[i] = (char) (nSum + '0');
                break;
            }
        }

        return isOverflow;
    }

    // ==================== 方法二 ====================
    public static void Print1ToMaxOfNDigits_2(int n) {
        if (n <= 0) {
            return;
        }

        // 对书中的代码进行了优化，简化了代码
        char[] number = new char[n];
        Print1ToMaxOfNDigitsRecursively(number, 0);
    }

    private static void Print1ToMaxOfNDigitsRecursively(char[] number, int index) {
        if (number.length == index) {
            PrintNumber(number);
            return;
        }

        for (int i = 0; i < 10; ++i) {
            number[index] = (char) (i + '0');
            Print1ToMaxOfNDigitsRecursively(number, index + 1);
        }
    }

    // ====================公共函数====================
    // 字符串number表示一个数字，数字有若干个0开头
    // 打印出这个数字，并忽略开头的0
    public static void PrintNumber(char[] number) {
        int i;
        for (i = 0; i < number.length; ++i) {
            if (number[i] != '0') {
                break;
            }
        }

        boolean printTab = i < number.length;
        for (; i < number.length; ++i) {
            System.out.print(number[i]);
        }

        if (printTab) {
            System.out.print("\t");
        }
    }

    // ====================测试代码====================
    public static void Test(int n) {
        System.out.printf("\nTest for %d begins:\n", n);

        Print1ToMaxOfNDigits_1(n);
        System.out.println();
        Print1ToMaxOfNDigits_2(n);

        System.out.printf("\nTest for %d ends.\n", n);
    }
}
