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
    // 方法一 代码实现不太好理解，参见 方法三
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
        // 只有1个骰子，每个点数出现次数为1
        for (int i = 1; i <= MAX_VALUE; ++i) {
            probabilities[flag][i] = 1;
        }

        // 2个以上骰子（依次计算 2 ~ number 个骰子点数和出现的次数）
        for (int k = 2; k <= number; ++k) {
//            // 对于 k 个骰子的和，肯定不会出现和小于 k 的情况，所以将和小于 k 出现的次数清零
//            // 注意：因为下面的 for 循环中用 i - j >= k - 1 将 和小于 k 出现的次数排除在外，因此这里的清零操作也就多余了。
//            for (int i = 0; i < k; ++i) {
//                probabilities[1 - flag][i] = 0;
//            }

            // 依次计算 k 个骰子点数和出现的次数（范围：1*k ~ MAX_VALUE*k）
            for (int i = k; i <= MAX_VALUE * k; ++i) {
                int probability = 0;
                for (int j = 1; j <= MAX_VALUE && i - j >= k - 1; ++j) {
                    // i-j >= k-1 表示，前 k-1 个骰子点数之和 >= k-1，小于它的为无效值，需要排除掉。
                    probability += probabilities[flag][i - j];
                }
                probabilities[1 - flag][i] = probability;
            }

            flag = 1 - flag;
        }

        double total = (int) Math.pow(MAX_VALUE, number);
        for (int i = number; i <= MAX_VALUE * number; ++i) {
            double ratio = (double) probabilities[flag][i] / total;
            System.out.printf("%d: %e\n", i, ratio);
        }
    }

    // ====================方法三====================
    // 方法三 为 方法一 简化版，递归思路更好理解
    public static void printProbability_Solution3(int number) {
        if (number < 1) {
            return;
        }

        int maxSum = number * MAX_VALUE;
        int minSum = number; // minSum = number * 1

        // 点数和总共有：maxSum - minSum + 1 种可能
        int[] probabilities = new int[maxSum + 1]; // 此处浪费了 minSum 个 int 空间（下标 0 ~ minSum-1 没用到）

        probability2(number, 0, probabilities);

        int total = (int) Math.pow(MAX_VALUE, number);
        for (int i = minSum; i <= maxSum; ++i) {
            double ratio = (double) probabilities[i] / total;
            System.out.printf("%d: %e\n", i, ratio);
        }
    }

    /**
     * 计算 diceNumber 颗骰子点数总和出现的次数
     *
     * @param diceNumber    剩余骰子数
     * @param sum           已累加的骰子点数总和（不包含剩余的 diceNumber 个骰子点数之和）
     * @param probabilities 数组对应的下标为骰子点数总和，其值为点数总和出现的次数
     */
    private static void probability2(int diceNumber, int sum, int[] probabilities) {
        if (diceNumber == 0) {
            probabilities[sum]++;
        } else {
            for (int i = 1; i <= MAX_VALUE; ++i) {
                probability2(diceNumber - 1, i + sum, probabilities);
            }
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

        System.out.println("Test for solution3");
        Q60_DicesProbability.printProbability_Solution3(n);

        System.out.println();
    }
}
