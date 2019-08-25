package CodingInterviewChinese2.ch03.Q13_RobotMove;

/**
 * Created by lj1218.
 * Date: 2019/8/25
 *
 * Page: 92
 * 面试题13：机器人的运动范围
 *   题目：地上有一个m行n列的方格。一个机器人从坐标(0, 0)的格子开始移动，它
 * 每一次可以向左、右、上、下移动一格，但不能进入行坐标和列坐标的数位之和
 * 大于k的格子。例如，当k为18时，机器人能够进入方格(35, 37)，因为3+5+3+7=18。
 * 但它不能进入方格(35, 38)，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 提示：回溯法
 */
public class Q13_RobotMove {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
    }

    /**
     * 计算机器人能达到的格子数
     *
     * @param threshold 机器人不能进入行坐标和列坐标的数位之和大于 threshold 的格子
     * @param rows      方格行数
     * @param cols      方格列数
     * @return 机器人能达到的格子数
     */
    public static int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) {
            return 0;
        }

        boolean[][] visited = new boolean[rows][cols];
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                visited[row][col] = false;
            }
        }

        return movingCountCore(threshold, rows, cols, 0, 0, visited);
    }

    /**
     * 以坐标 (row, col) 为起点机器人能达到的格子数
     *
     * @param threshold 机器人不能进入行坐标和列坐标的数位之和大于 threshold 的格子
     * @param rows      方格行数
     * @param cols      方格列数
     * @param row       机器人起点坐标的行标
     * @param col       机器人起点坐标的列表
     * @param visited   访问标记矩阵
     * @return 以坐标 (row, col) 为起点机器人能达到的格子数
     */
    private static int movingCountCore(
            int threshold, int rows, int cols,
            int row, int col, boolean[][] visited
    ) {
        if (!check(threshold, rows, cols, row, col, visited)) {
            return 0;
        }

        visited[row][col] = true; // 标记该格子为已访问
        return 1 + movingCountCore(threshold, rows, cols, row - 1, col, visited)
                + movingCountCore(threshold, rows, cols, row + 1, col, visited)
                + movingCountCore(threshold, rows, cols, row, col - 1, visited)
                + movingCountCore(threshold, rows, cols, row, col + 1, visited);
    }

    // 判断机器人能否进入坐标为 (row, col) 的方格
    private static boolean check(
            int threshold, int rows, int cols,
            int row, int col, boolean[][] visited
    ) {
        if (row < 0 || col < 0 || row >= rows || col >= cols) {
            // 走到格子外
            return false;
        }

        if (visited[row][col]) {
            // 已走过的格子，不能再次访问
            return false;
        }

        if (getDigitSum(row) + getDigitSum(col) > threshold) {
            // 不能进入行坐标和列坐标的数位之和大于 threshold 的格子
            return false;
        }

        return true;
    }

    // 计算一个数字的数位之和
    private static int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    // ====================测试代码====================
    public static void test(
            String testName, int threshold,
            int rows, int cols, int expected
    ) {
        if (testName != null) {
            System.out.printf("%s begins: ", testName);
        }

        if (movingCount(threshold, rows, cols) == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }
    }

    // 方格多行多列
    public static void test1() {
        test("Test1", 5, 10, 10, 21);
    }

    // 方格多行多列
    public static void test2() {
        test("Test2", 15, 20, 20, 359);
    }

    // 方格只有一行，机器人只能到达部分方格
    public static void test3() {
        test("Test3", 10, 1, 100, 29);
    }

    // 方格只有一行，机器人能到达所有方格
    public static void test4() {
        test("Test4", 10, 1, 10, 10);
    }

    // 方格只有一列，机器人只能到达部分方格
    public static void test5() {
        test("Test5", 15, 100, 1, 79);
    }

    // 方格只有一列，机器人能到达所有方格
    public static void test6() {
        test("Test6", 15, 10, 1, 10);
    }

    // 方格只有一行一列
    public static void test7() {
        test("Test7", 15, 1, 1, 1);
    }

    // 方格只有一行一列
    public static void test8() {
        test("Test8", 0, 1, 1, 1);
    }

    // 机器人不能进入任意一个方格
    public static void test9() {
        test("Test9", -10, 10, 10, 0);
    }
}
