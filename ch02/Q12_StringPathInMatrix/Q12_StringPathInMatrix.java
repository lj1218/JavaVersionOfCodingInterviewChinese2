package CodingInterviewChinese2.ch02.Q12_StringPathInMatrix;

/**
 * Created by lj1218.
 * Date: 2019/8/25
 *
 * Page: 89
 * 面试题12：矩阵中的路径
 *   题目：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有
 * 字符的路径。路径可以从矩阵中任意一格开始，每一步可以在矩阵中向左、右、
 * 上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入
 * 该格子。例如在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字
 * 母用下划线标出）。但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个
 * 字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 * A B T G
 * C F C S
 * J D E H
 *
 * 提示：回溯法
 */
public class Q12_StringPathInMatrix {

    public static void main(String[] args) {
        Test1();
        Test2();
        Test3();
        Test4();
        Test5();
        Test6();
        Test7();
        Test8();
        Test9();
        Test10();
        Test11();
        Test12();
        Test13();
        Test14();
    }

    public static boolean hasPath(char[][] matrix, char[] str) {
        if (matrix == null || matrix.length < 1 ||
                matrix[0].length < 1 || str == null) {
            return false;
        }

        // 创建并初始化标记矩阵
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                visited[row][col] = false;
            }
        }

        // 将矩阵中每个元素依次作为起点进行搜索
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (hasPathCore(matrix, str, row, col, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean hasPathCore(
            char[][] matrix, char[] str, int row, int col,
            int pathLength, boolean[][] visited
    ) {
        if (str.length == pathLength) {
            // 字符串完全匹配
            return true;
        }

        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length) {
            // 超出矩阵边界
            return false;
        }

        if (visited[row][col]) {
            // 路径已包含当前单元格
            return false;
        }

        if (matrix[row][col] == str[pathLength]) {
            // 当前单元格与 str 中的字符匹配，则从 上、下、左、右 4个方向匹配下一个字符
            ++pathLength;
            visited[row][col] = true; // 标记该单元格为已访问
            boolean hasPath = hasPathCore(matrix, str, row - 1, col, pathLength, visited) ||  // 上
                    hasPathCore(matrix, str, row + 1, col, pathLength, visited) ||  // 下
                    hasPathCore(matrix, str, row, col - 1, pathLength, visited) ||  // 左
                    hasPathCore(matrix, str, row, col + 1, pathLength, visited);  // 右
            if (hasPath) {
                return true;
            }

            visited[row][col] = false; // 标记该单元格为未访问
        }

        return false;
    }

    // ====================测试代码====================
    public static void Test(String testName, char[][] matrix, char[] str, boolean expected) {
        if (testName != null)
            System.out.printf("%s begins: ", testName);

        if (hasPath(matrix, str) == expected)
            System.out.println("Passed.");
        else
            System.out.println("FAILED.");
    }

    //ABTG
    //CFCS
    //JDEH

    //BFCE
    public static void Test1() {
        char[][] matrix = {
                "ABTG".toCharArray(),
                "CFCS".toCharArray(),
                "JDEH".toCharArray(),
        };
        char[] str = "BFCE".toCharArray();

        Test("Test1", matrix, str, true);
    }

    //ABCE
    //SFCS
    //ADEE

    //SEE
    public static void Test2() {
        char[][] matrix = {
                "ABCE".toCharArray(),
                "SFCS".toCharArray(),
                "ADEE".toCharArray(),
        };
        char[] str = "SEE".toCharArray();

        Test("Test2", matrix, str, true);
    }

    //ABTG
    //CFCS
    //JDEH

    //ABFB
    public static void Test3() {
        char[][] matrix = {
                "ABTG".toCharArray(),
                "CFCS".toCharArray(),
                "JDEH".toCharArray(),
        };
        char[] str = "ABFB".toCharArray();

        Test("Test3", matrix, str, false);
    }

    //ABCEHJIG
    //SFCSLOPQ
    //ADEEMNOE
    //ADIDEJFM
    //VCEIFGGS

    //SLHECCEIDEJFGGFIE
    public static void Test4() {
        char[][] matrix = {
                "ABCEHJIG".toCharArray(),
                "SFCSLOPQ".toCharArray(),
                "ADEEMNOE".toCharArray(),
                "ADIDEJFM".toCharArray(),
                "VCEIFGGS".toCharArray(),
        };
        char[] str = "SLHECCEIDEJFGGFIE".toCharArray();

        Test("Test4", matrix, str, true);
    }

    //ABCEHJIG
    //SFCSLOPQ
    //ADEEMNOE
    //ADIDEJFM
    //VCEIFGGS

    //SGGFIECVAASABCEHJIGQEM
    public static void Test5() {
        char[][] matrix = {
                "ABCEHJIG".toCharArray(),
                "SFCSLOPQ".toCharArray(),
                "ADEEMNOE".toCharArray(),
                "ADIDEJFM".toCharArray(),
                "VCEIFGGS".toCharArray(),
        };
        char[] str = "SGGFIECVAASABCEHJIGQEM".toCharArray();

        Test("Test5", matrix, str, true);
    }

    //ABCEHJIG
    //SFCSLOPQ
    //ADEEMNOE
    //ADIDEJFM
    //VCEIFGGS

    //SGGFIECVAASABCEEJIGOEM
    public static void Test6() {
        char[][] matrix = {
                "ABCEHJIG".toCharArray(),
                "SFCSLOPQ".toCharArray(),
                "ADEEMNOE".toCharArray(),
                "ADIDEJFM".toCharArray(),
                "VCEIFGGS".toCharArray(),
        };
        char[] str = "SGGFIECVAASABCEEJIGOEM".toCharArray();

        Test("Test6", matrix, str, false);
    }

    //ABCEHJIG
    //SFCSLOPQ
    //ADEEMNOE
    //ADIDEJFM
    //VCEIFGGS

    //SGGFIECVAASABCEHJIGQEMS
    public static void Test7() {
        char[][] matrix = {
                "ABCEHJIG".toCharArray(),
                "SFCSLOPQ".toCharArray(),
                "ADEEMNOE".toCharArray(),
                "ADIDEJFM".toCharArray(),
                "VCEIFGGS".toCharArray(),
        };
        char[] str = "SGGFIECVAASABCEHJIGQEMS".toCharArray();

        Test("Test7", matrix, str, false);
    }

    //AAAA
    //AAAA
    //AAAA

    //AAAAAAAAAAAA
    public static void Test8() {
        char[][] matrix = {
                "AAAA".toCharArray(),
                "AAAA".toCharArray(),
                "AAAA".toCharArray(),
        };
        char[] str = "AAAAAAAAAAAA".toCharArray();

        Test("Test8", matrix, str, true);
    }

    //AAAA
    //AAAA
    //AAAA

    //AAAAAAAAAAAAA
    public static void Test9() {
        char[][] matrix = {
                "AAAA".toCharArray(),
                "AAAA".toCharArray(),
                "AAAA".toCharArray(),
        };
        char[] str = "AAAAAAAAAAAAA".toCharArray();

        Test("Test9", matrix, str, false);
    }

    //A

    //A
    public static void Test10() {
        char[][] matrix = {
                "A".toCharArray(),
        };
        char[] str = "A".toCharArray();

        Test("Test10", matrix, str, true);
    }

    //A

    //B
    public static void Test11() {
        char[][] matrix = {
                "A".toCharArray(),
        };
        char[] str = "B".toCharArray();

        Test("Test11", matrix, str, false);
    }

    //null

    //A
    public static void Test12() {
        Test("Test12", null, "A".toCharArray(), false);
    }

    //A

    //null
    public static void Test13() {
        char[][] matrix = {
                "A".toCharArray(),
        };
        Test("Test13", matrix, null, false);
    }

    public static void Test14() {
        Test("Test14", null, null, false);
    }
}
