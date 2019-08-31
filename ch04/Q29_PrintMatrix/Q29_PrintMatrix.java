package CodingInterviewChinese2.ch04.Q29_PrintMatrix;

/**
 * Created by lj1218.
 * Date: 2019/8/29
 *
 * Page: 161
 * 面试题29：顺时针打印矩阵
 * 题目：输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 */
public class Q29_PrintMatrix {

    public static void main(String[] args) {
        Test.main();
    }

    // 数字打印方向为 右->下->左->上 循环直至打印出所有数据。
    // 而每打印完成一次 右 或 左，则行数减1；
    // 每打印完成一次 下 或 上，则列数减1；
    // 当行或者列数减为 0 时，表示数字打印完成。
    public static <T> void printMatrixInCircle(T[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0;
        int col = 0;
        while (true) {
            if (cols == 0) {
                break;
            }

            // 从左到右打印一行
            for (int i = 0; i < cols; ++i) {
                System.out.print(matrix[row][col++] + "\t");
            }
            --col; // 回退一列
            ++row; // 移到下一行
            --rows; // 未打印行数减1

            if (rows == 0) {
                break;
            }

            // 从上到下打印一列
            for (int i = 0; i < rows; ++i) {
                System.out.print(matrix[row++][col] + "\t");
            }
            --row; // 回退一行
            --col; // 移到前一列
            --cols; // 未打印列数减1

            if (cols == 0) {
                break;
            }

            // 从右到左打印一行
            for (int i = 0; i < cols; ++i) {
                System.out.print(matrix[row][col--] + "\t");
            }
            ++col; // 回退一列
            --row; // 移到上一行
            --rows; // 未打印行数减1

            if (rows == 0) {
                break;
            }

            // 从下到上打印一列
            for (int i = 0; i < rows; ++i) {
                System.out.print(matrix[row--][col] + "\t");
            }
            ++row; // 回退一行
            ++col; // 移到后一列
            --cols; // 未打印列数减1
        }
    }
}

class Test {

    public static void main() {
        /*
        1
        */
        test(1, 1);

        /*
        1    2
        3    4
        */
        test(2, 2);

        /*
        1    2    3    4
        5    6    7    8
        9    10   11   12
        13   14   15   16
        */
        test(4, 4);

        /*
        1    2    3    4    5
        6    7    8    9    10
        11   12   13   14   15
        16   17   18   19   20
        21   22   23   24   25
        */
        test(5, 5);

        /*
        1
        2
        3
        4
        5
        */
        test(1, 5);

        /*
        1    2
        3    4
        5    6
        7    8
        9    10
        */
        test(2, 5);

        /*
        1    2    3
        4    5    6
        7    8    9
        10   11   12
        13   14   15
        */
        test(3, 5);

        /*
        1    2    3    4
        5    6    7    8
        9    10   11   12
        13   14   15   16
        17   18   19   20
        */
        test(4, 5);

        /*
        1    2    3    4    5
        */
        test(5, 1);

        /*
        1    2    3    4    5
        6    7    8    9    10
        */
        test(5, 2);

        /*
        1    2    3    4    5
        6    7    8    9    10
        11   12   13   14    15
        */
        test(5, 3);

        /*
        1    2    3    4    5
        6    7    8    9    10
        11   12   13   14   15
        16   17   18   19   20
        */
        test(5, 4);
    }

    // ====================测试代码====================
    private static void test(int columns, int rows) {
        System.out.printf("Test Begin: %d columns, %d rows.\n", columns, rows);

        if (columns < 1 || rows < 1)
            return;

        Integer[][] numbers = new Integer[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                numbers[i][j] = i * columns + j + 1;
            }
        }

        Q29_PrintMatrix.printMatrixInCircle(numbers);
        System.out.println();
    }
}
