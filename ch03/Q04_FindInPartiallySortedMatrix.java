package CodingInterviewChinese2.ch03;

/**
 * Created by lj1218.
 * Date: 2019/8/21
 * Page: 44
 * 面试题4：二维数组中的查找
 *   题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的
 * 一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *   例如下面的二维数组就是每行、每列都递增排序。如果在这个数组中
 * 查找数字7，则返回 true；如果查找数字 5，由于数组不含有该数字，
 * 则返回 false。
 *
 * 1  2  8  9
 *
 * 2  4  9  12
 *
 * 4  7  10 13
 *
 * 6  8  11 15
 *
 * 提示：从右上角开始扫描。
 */
public class Q04_FindInPartiallySortedMatrix {

    public static void main(String[] args) {
        Test1();
        Test2();
        Test3();
        Test4();
        Test5();
        Test6();
        Test7();
    }

    public static boolean find(int[][] matrix, int numberToBeFind) {
        if (matrix == null) {
            return false;
        }

        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }
        int columns = matrix[0].length;

        for (int row = 0, col = columns - 1; row < rows && col >= 0; ) {
            if (matrix[row][col] == numberToBeFind) {
                return true;
            } else if (matrix[row][col] > numberToBeFind) {
                --col; // 划掉该列
            } else {
                ++row; // 划掉该行
            }
        }

        return false;
    }


    // ====================测试代码====================
    private static void Test(String testName, int[][] matrix, int number, boolean expected) {
        if (testName != null) {
            System.out.print(testName + " begins: ");
        }

        boolean result = find(matrix, number);
        if (result == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    //  1   2   8   9
    //  2   4   9   12
    //  4   7   10  13
    //  6   8   11  15
    // 要查找的数在数组中
    private static void Test1() {
        int matrix[][] = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        Test("Test1", matrix, 7, true);
    }

    //  1   2   8   9
    //  2   4   9   12
    //  4   7   10  13
    //  6   8   11  15
    // 要查找的数不在数组中
    private static void Test2() {
        int matrix[][] = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        Test("Test1", matrix, 5, false);
    }

    //  1   2   8   9
    //  2   4   9   12
    //  4   7   10  13
    //  6   8   11  15
    // 要查找的数是数组中最小的数字
    private static void Test3() {
        int matrix[][] = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        Test("Test3", matrix, 1, true);
    }

    //  1   2   8   9
    //  2   4   9   12
    //  4   7   10  13
    //  6   8   11  15
    // 要查找的数是数组中最大的数字
    private static void Test4() {
        int matrix[][] = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        Test("Test4", matrix, 15, true);
    }

    //  1   2   8   9
    //  2   4   9   12
    //  4   7   10  13
    //  6   8   11  15
    // 要查找的数比数组中最小的数字还小
    private static void Test5() {
        int matrix[][] = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        Test("Test5", matrix, 0, false);
    }

    //  1   2   8   9
    //  2   4   9   12
    //  4   7   10  13
    //  6   8   11  15
    // 要查找的数比数组中最大的数字还大
    private static void Test6() {
        int matrix[][] = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        Test("Test6", matrix,  16, false);
    }

    // 鲁棒性测试，输入空指针
    private static void Test7() {
        Test("Test7", null, 16, false);
    }
}
