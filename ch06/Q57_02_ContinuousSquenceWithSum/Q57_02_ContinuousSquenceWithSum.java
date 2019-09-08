package CodingInterviewChinese2.ch06.Q57_02_ContinuousSquenceWithSum;

/**
 * Created by lj1218.
 * Date: 2019/9/8
 *
 * Page: 282
 * 面试题57（二）：为s的连续正数序列
 * 题目：输入一个正数s，打印出所有和为s的连续正数序列（至少含有两个数）。
 * 例如输入15，由于1+2+3+4+5=4+5+6=7+8=15，所以结果打印出3个连续序列1～5、
 * 4～6和7～8。
 */
public class Q57_02_ContinuousSquenceWithSum {

    public static void main(String[] args) {
        Test.main();
    }

    public static void findContinuousSequence(int sum) {
        if (sum < 3) {
            return;
        }

        int small = 1;
        int big = 2;
        int middle = (1 + sum) / 2;
        int curSum = small + big;

        while (small < middle) {
            if (curSum == sum) {
                printContinuousSequence(small, big);
            }

            while (curSum > sum && small < middle) {
                curSum -= small;
                ++small;

                if (curSum == sum) {
                    printContinuousSequence(small, big);
                }
            }

            ++big;
            curSum += big;
        }
    }

    private static void printContinuousSequence(int small, int big) {
        for (int i = small; i <= big; ++i) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

class Test {

    public static void main() {
        test("test1", 1);
        test("test2", 3);
        test("test3", 4);
        test("test4", 9);
        test("test5", 15);
        test("test6", 100);
    }

    // ====================测试代码====================
    private static void test(String testName, int sum) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s for %d begins: \n", testName, sum);
        Q57_02_ContinuousSquenceWithSum.findContinuousSequence(sum);
        System.out.println();
    }
}
