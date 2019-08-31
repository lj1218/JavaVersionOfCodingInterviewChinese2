package CodingInterviewChinese2.ch04.Q33_SequenceOfBST;

/**
 * Created by lj1218.
 * Date: 2019/8/31
 *
 * Page: 179
 * 面试题33：二叉搜索树的后序遍历序列
 * 题目：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则返回true，否则返回false。假设输入的数组的任意两个数字都互不相同。
 *
 * 提示：二叉搜索树的特点是每个节点的值大于其左子树所有节点，小于其右子树的所有节点。
 * 数组最后1个数是二叉搜索树的根节点，可以将整数数组划分成两个子数组分别对应左右子树，
 * 再递归地将子数组划分下去，直至数组中剩下1个数为止。
 */
public class Q33_SequenceOfBST {

    public static void main(String[] args) {
        Test.main();
    }

    public static boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null) {
            return false;
        }
        return verifySequenceOfBSTCore(sequence, 0, sequence.length - 1);
    }

    // {5, 7, 6, 9, 11, 10, 8}
    private static boolean verifySequenceOfBSTCore(int[] sequence, int start, int end) {
        if (start == end) {
            // 单个节点的情况
            return true;
        }

        int root = sequence[end];

        // 找出左子树序列
        int i = start;
        for (; i < end; ++i) {
            if (sequence[i] > root) {
                // i 指向右子树序列中第1个节点
                break;
            }
        }

        // 判断右子树序列中的节点是否均大于根节点的值
        int j = i;
        for (; j < end; ++j) {
            if (sequence[j] < root) {
                // 右子树序列中有小于根节点值的节点，说明不是二叉搜索树
                return false;
            }
        }

        boolean left = true;
        if (i - start > 1) {
            // 左子树长度大于1，则递归
            left = verifySequenceOfBSTCore(sequence, start, i - 1);
        }

        boolean right = true;
        if (end - i > 1) {
            // 右子树长度大于1，则递归
            right = verifySequenceOfBSTCore(sequence, i, end - 1);
        }

        return left && right;
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

    // ====================测试代码====================
    private static void test(String testName, int[] sequence, boolean expected) {
        if (testName != null) {
            System.out.printf("%s begins: ", testName);
        }

        if (Q33_SequenceOfBST.verifySequenceOfBST(sequence) == expected) {
            System.out.println("passed.");
        } else {
            System.out.println("failed.");
        }
    }

    //            10
    //         /      \
    //        6        14
    //       /\        /\
    //      4  8     12  16
    private static void Test1() {
        int[] data = {4, 8, 6, 12, 16, 14, 10};
        test("Test1", data, true);
    }

    //           5
    //          / \
    //         4   7
    //            /
    //           6
    private static void Test2() {
        int[] data = {4, 6, 7, 5};
        test("Test2", data, true);
    }

    //               5
    //              /
    //             4
    //            /
    //           3
    //          /
    //         2
    //        /
    //       1
    private static void Test3() {
        int[] data = {1, 2, 3, 4, 5};
        test("Test3", data, true);
    }

    // 1
    //  \
    //   2
    //    \
    //     3
    //      \
    //       4
    //        \
    //         5
    private static void Test4() {
        int[] data = {5, 4, 3, 2, 1};
        test("Test4", data, true);
    }

    // 树中只有1个结点
    private static void Test5() {
        int[] data = {5};
        test("Test5", data, true);
    }

    private static void Test6() {
        int[] data = {7, 4, 6, 5};
        test("Test6", data, false);
    }

    private static void Test7() {
        int[] data = {4, 6, 12, 8, 16, 14, 10};
        test("Test7", data, false);
    }

    private static void Test8() {
        test("Test8", null, false);
    }
}
