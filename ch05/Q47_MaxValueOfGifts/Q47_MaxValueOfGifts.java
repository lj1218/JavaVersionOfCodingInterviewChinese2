package CodingInterviewChinese2.ch05.Q47_MaxValueOfGifts;

/**
 * Created by lj1218.
 * Date: 2019/9/5
 *
 * Page: 233
 * 面试题47：礼物的最大价值
 * 题目：在一个m×n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值
 * （价值大于0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向左或
 * 者向下移动一格直到到达棋盘的右下角。给定一个棋盘及其上面的礼物，请计
 * 算你最多能拿到多少价值的礼物？
 */
public class Q47_MaxValueOfGifts {

    public static void main(String[] args) {
        Test.main();
    }

    // 空间复杂度：O(m*n)
    public static int getMaxValue_solution1(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0)
            return 0;

        int rows = values.length;
        int cols = values[0].length;
        int[][] maxValues = new int[rows][cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int maxVal = 0;

                // up
                if (i > 0 && maxValues[i - 1][j] > maxVal) {
                    maxVal = maxValues[i - 1][j];
                }

                // left
                if (j > 0 && maxValues[i][j - 1] > maxVal) {
                    maxVal = maxValues[i][j - 1];
                }

                maxValues[i][j] = maxVal + values[i][j];
            }
        }

        return maxValues[rows - 1][cols - 1];
    }

    // 空间复杂度：O(n)
    public static int getMaxValue_solution2(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0)
            return 0;

        int rows = values.length;
        int cols = values[0].length;
        int[] maxValues = new int[cols];

        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                int maxVal = 0;

                // up
                if (maxValues[j] > maxVal) {
                    maxVal = maxValues[j];
                }

                // left
                if (j > 0 && maxValues[j - 1] > maxVal) {
                    maxVal = maxValues[j - 1];
                }

                maxValues[j] = maxVal + values[i][j];
            }
        }

        return maxValues[cols - 1];
    }
}

class Test {

    public static void main() {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
    }

    // ====================测试代码====================
    private static void test(String testName, int[][] values, int expected) {
        if (Q47_MaxValueOfGifts.getMaxValue_solution1(values) == expected) {
            System.out.println(testName + ": solution1 passed.");
        } else {
            System.out.println(testName + ": solution1 FAILED.");
        }

        if (Q47_MaxValueOfGifts.getMaxValue_solution2(values) == expected) {
            System.out.println(testName + ": solution2 passed.");
        } else {
            System.out.println(testName + ": solution2 FAILED.");
        }
    }

    private static void test1() {
        // 三行三列
        int[][] values = {
                {
                        1, 2, 3
                },
                {
                        4, 5, 6
                },
                {
                        7, 8, 9
                }
        };
        int expected = 29;
        test("test1", values, expected);
    }

    private static void test2() {
        //四行四列
        int[][] values = {
                {
                        1, 10, 3, 8
                },
                {
                        12, 2, 9, 6
                },
                {
                        5, 7, 4, 11
                },
                {
                        3, 7, 16, 5
                }
        };
        int expected = 53;
        test("test2", values, expected);
    }

    private static void test3() {
        // 一行四列
        int[][] values = {
                {
                        1, 10, 3, 8
                }
        };
        int expected = 22;
        test("test3", values, expected);
    }

    private static void test4() {
        int[][] values = {
                {
                        1
                },
                {
                        12
                },
                {
                        5
                },
                {
                        3
                }
        };
        int expected = 21;
        test("test4", values, expected);
    }

    private static void test5() {
        // 一行一列
        int[][] values = {
                {
                        3
                }
        };
        int expected = 3;
        test("test5", values, expected);
    }

    private static void test6() {
        // 空指针
        int expected = 0;
        test("test6", null, expected);
    }
}
