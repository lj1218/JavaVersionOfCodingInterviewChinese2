package CodingInterviewChinese2.ch06.Q60_DicesProbability;

/**
 * Created by lj1218.
 * Date: 2019/9/9
 *
 * Page: 294
 * 面试题60：n个骰子的点数
 * 题目：把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s
 * 的所有可能的值出现的概率。
 */
public class Q60_DicesProbability {

    private final static int MAX_VALUE = 6;

    public static void main(String[] args) {
        Test.main();
    }

    // ====================方法一====================
    public static void printProbability_Solution1(int number) {
        if (number < 1) {
            return;
        }

        int maxSum = number * MAX_VALUE;

        // minSum = number * 1
        // 总共有：maxSum - minSum + 1 种可能
        int[] probabilities = new int[maxSum - number + 1];

        probability(number, probabilities);

        int total = (int) Math.pow(MAX_VALUE, number);
        for (int i = number; i <= maxSum; ++i) {
            double ratio = (double) probabilities[i - number] / total;
            System.out.printf("%d: %e\n", i, ratio);
        }
    }

    private static void probability(int number, int[] probabilities) {
        for (int i = 1; i <= MAX_VALUE; ++i) {
            probability(number, number, i, probabilities);
        }
    }

    private static void probability(int original, int current, int sum, int[] probabilities) {
        if (current == 1) {
            probabilities[sum - original]++;
        } else {
            for (int i = 1; i <= MAX_VALUE; ++i) {
                probability(original, current - 1, i + sum, probabilities);
            }
        }
    }

    // ====================方法二====================
    public static void printProbability_Solution2(int number) {
        if (number < 1) {
            return;
        }

        int[][] probabilities = new int[2][MAX_VALUE * number + 1];

        int flag = 0;
        for (int i = 1; i <= MAX_VALUE; ++i) {
            probabilities[flag][i] = 1;
        }

        for (int k = 2; k <= number; ++k) {
            for (int i = 0; i < k; ++i) {
                probabilities[1 - flag][i] = 0;
            }

            for (int i = k; i <= MAX_VALUE * k; ++i) {
                probabilities[1 - flag][i] = 0;
                for (int j = 1; j <= i && j <= MAX_VALUE; ++j) {
                    probabilities[1 - flag][i] += probabilities[flag][i - j];
                }
            }

            flag = 1 - flag;
        }

        double total = (int) Math.pow(MAX_VALUE, number);
        for (int i = number; i <= MAX_VALUE * number; ++i) {
            double ratio = (double) probabilities[flag][i] / total;
            System.out.printf("%d: %e\n", i, ratio);
        }
    }
}

class Test {

    public static void main() {
        test(1);
        test(2);
        test(3);
        test(4);
        test(11);
        test(0);
    }

    // ====================测试代码====================
    private static void test(int n) {
        System.out.printf("Test for %d begins:\n", n);

        System.out.println("Test for solution1");
        Q60_DicesProbability.printProbability_Solution1(n);

        System.out.println("Test for solution2");
        Q60_DicesProbability.printProbability_Solution2(n);

        System.out.println();
    }
}
