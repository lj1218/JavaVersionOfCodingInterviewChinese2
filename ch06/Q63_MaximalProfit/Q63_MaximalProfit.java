package CodingInterviewChinese2.ch06.Q63_MaximalProfit;

/**
 * Created by lj1218.
 * Date: 2019/9/18
 *
 * Page: 304
 * 面试题63：股票的最大利润
 * 题目：假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖交易该股
 * 票可能获得的利润是多少？例如一只股票在某些时间节点的价格为{9, 11, 8, 5,
 * 7, 12, 16, 14}。如果我们能在价格为5的时候买入并在价格为16时卖出，则能
 * 收获最大的利润11。
 */
public class Q63_MaximalProfit {

    public static void main(String[] args) {
        Test.main();
    }

    public static int maxDiff(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            return 0;
        }

        int minNumber = Math.min(numbers[0], numbers[1]);
        int maxDiff = numbers[1] - numbers[0];
        for (int i = 2; i < numbers.length; ++i) {
            int curDiff = numbers[i] - minNumber;
            if (maxDiff < curDiff) {
                maxDiff = curDiff;
            }
            minNumber = Math.min(minNumber, numbers[i]);
        }

        return maxDiff;
    }
}

class Test {

    public static void main() {
        Test1();
        Test2();
        Test3();
        Test4();
        Test5();
        Test6();
        Test7();
        Test8();
    }

    // ==================== Test Code ====================
    private static void test(String testName, int[] numbers, int expected) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        if (Q63_MaximalProfit.maxDiff(numbers) == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }
    }

    private static void Test1() {
        int numbers[] = {4, 1, 3, 2, 5};
        test("Test1", numbers, 4);
    }

    // 价格递增
    private static void Test2() {
        int numbers[] = {1, 2, 4, 7, 11, 16};
        test("Test2", numbers, 15);
    }

    // 价格递减
    private static void Test3() {
        int numbers[] = {16, 11, 7, 4, 2, 1};
        test("Test3", numbers, -1);
    }

    // 价格全部相同
    private static void Test4() {
        int numbers[] = {16, 16, 16, 16, 16};
        test("Test4", numbers, 0);
    }

    private static void Test5() {
        int numbers[] = {9, 11, 5, 7, 16, 1, 4, 2};
        test("Test5", numbers, 11);
    }

    private static void Test6() {
        int numbers[] = {2, 4};
        test("Test6", numbers, 2);
    }

    private static void Test7() {
        int numbers[] = {4, 2};
        test("Test7", numbers, -2);
    }

    private static void Test8() {
        test("Test8", null, 0);
    }
}
