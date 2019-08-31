package CodingInterviewChinese2.ch04.Q34_PathInTree;

import java.util.Stack;

/**
 * Created by lj1218.
 * Date: 2019/8/31
 *
 * Page: 182
 * 面试题34：二叉树中和为某一值的路径
 * 题目：输入一棵二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所
 * 有路径。从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */
public class Q34_PathInTree {

    public static void main(String[] args) {
        Test.main();
    }

    public static void findPath(BinaryTreeNode<Integer> root, int expectedSum) {
        if (root == null) {
            return;
        }

        Stack<Integer> path = new Stack<>();
        findPath(root, expectedSum, path, 0);
    }

    // 前序遍历
    private static void findPath(
            BinaryTreeNode<Integer> root, int expectedSum,
            Stack<Integer> path, int currentSum
    ) {
        currentSum += root.getValue();
        path.push(root.getValue());

        // 如果是叶结点，并且路径上结点的和等于输入的期望值，则打印出这条路径
        if (root.getLChild() == null && root.getRChild() == null
                && currentSum == expectedSum) {
            for (Integer val : path) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // 如果不是叶结点，则遍历它的子结点
        if (root.getLChild() != null) {
            findPath(root.getLChild(), expectedSum, path, currentSum);
        }
        if (root.getRChild() != null) {
            findPath(root.getRChild(), expectedSum, path, currentSum);
        }

        // 在返回到父结点之前，在路径上删除当前结点
        path.pop();
    }
}

class BinaryTreeNode<E> {
    private E value;
    private BinaryTreeNode<E> lChild;
    private BinaryTreeNode<E> rChild;

    public BinaryTreeNode(E value) {
        this.value = value;
        lChild = rChild = null;
    }

    public E getValue() {
        return value;
    }

    public void setLChild(BinaryTreeNode<E> node) {
        lChild = node;
    }

    public void setRChild(BinaryTreeNode<E> node) {
        rChild = node;
    }

    public BinaryTreeNode<E> getLChild() {
        return lChild;
    }

    public BinaryTreeNode<E> getRChild() {
        return rChild;
    }

    public static <E> void connectTreeNodes(
            BinaryTreeNode<E> parent, BinaryTreeNode<E> lChild, BinaryTreeNode<E> rChild
    ) {
        if (parent != null) {
            parent.lChild = lChild;
            parent.rChild = rChild;
        }
    }

    @Override
    public String toString() {
        return "" + value;
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
    }

    // ====================测试代码====================
    private static void test(String testName, BinaryTreeNode<Integer> pRoot, int expectedSum) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins:\n", testName);
        Q34_PathInTree.findPath(pRoot, expectedSum);
        System.out.println();
    }

    //            10
    //         /      \
    //        5        12
    //       /\
    //      4  7
    // 有两条路径上的结点和为22
    private static void Test1() {
        BinaryTreeNode<Integer> pNode10 = new BinaryTreeNode<>(10);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode12 = new BinaryTreeNode<>(12);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode7 = new BinaryTreeNode<>(7);

        BinaryTreeNode.connectTreeNodes(pNode10, pNode5, pNode12);
        BinaryTreeNode.connectTreeNodes(pNode5, pNode4, pNode7);

        System.out.println("Two paths should be found in Test1.");
        test("Test1", pNode10, 22);
    }

    //            10
    //         /      \
    //        5        12
    //       /\
    //      4  7
    // 没有路径上的结点和为15
    private static void Test2() {
        BinaryTreeNode<Integer> pNode10 = new BinaryTreeNode<>(10);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode12 = new BinaryTreeNode<>(12);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode7 = new BinaryTreeNode<>(7);

        BinaryTreeNode.connectTreeNodes(pNode10, pNode5, pNode12);
        BinaryTreeNode.connectTreeNodes(pNode5, pNode4, pNode7);

        System.out.println("No paths should be found in Test2.");
        test("Test2", pNode10, 15);
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
    // 有一条路径上面的结点和为15
    private static void Test3() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);

        BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
        BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
        BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);
        BinaryTreeNode.connectTreeNodes(pNode2, pNode1, null);

        System.out.println("One path should be found in Test3.");
        test("Test3", pNode5, 15);
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
    // 没有路径上面的结点和为16
    private static void Test4() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, null, pNode2);
        BinaryTreeNode.connectTreeNodes(pNode2, null, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode4);
        BinaryTreeNode.connectTreeNodes(pNode4, null, pNode5);

        System.out.println("No paths should be found in Test4.");
        test("Test4", pNode1, 16);
    }

    // 树中只有1个结点
    private static void Test5() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);

        System.out.println("One path should be found in Test5.");
        test("Test5", pNode1, 1);
    }

    // 树中没有结点
    private static void Test6() {
        System.out.println("No paths should be found in Test6.");
        test("Test6", null, 0);
    }
}
