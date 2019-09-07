package CodingInterviewChinese2.ch06.Q55_02_BalancedBinaryTree;

/**
 * Created by lj1218.
 * Date: 2019/9/7
 *
 * Page: 273
 * 面试题55（二）：平衡二叉树
 * 题目：输入一棵二叉树的根结点，判断该树是不是平衡二叉树。如果某二叉树中
 * 任意结点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 */
public class Q55_02_BalancedBinaryTree {

    public static void main(String[] args) {
        Test.main();
    }

    // ====================方法1====================
    public static boolean isBalanced_Solution1(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return true;
        }

        int leftDepth = treeDepth(root.getLChild());
        int rightDepth = treeDepth(root.getRChild());

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }

        return isBalanced_Solution1(root.getLChild())
                && isBalanced_Solution1(root.getRChild());
    }

    private static int treeDepth(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int depthLeftChild = treeDepth(root.getLChild());
        int depthRightChild = treeDepth(root.getRChild());
        return Math.max(depthLeftChild, depthRightChild) + 1;
    }

    // ====================方法2====================
    public static boolean isBalanced_Solution2(BinaryTreeNode<Integer> root) {
        return isBalanced(root, new TreeDepth());
    }

    private static boolean isBalanced(BinaryTreeNode<Integer> root, TreeDepth treeDepth) {
        if (root == null) {
            treeDepth.setDepth(0);
            return true;
        }

        TreeDepth leftTreeDepth = new TreeDepth();
        TreeDepth rightTreeDepth = new TreeDepth();
        if (isBalanced(root.getLChild(), leftTreeDepth)
                && isBalanced(root.getRChild(), rightTreeDepth)) {
            if (Math.abs(leftTreeDepth.getDepth() - rightTreeDepth.getDepth()) <= 1) {
                treeDepth.setDepth(Math.max(leftTreeDepth.getDepth(), rightTreeDepth.getDepth()) + 1);
                return true;
            }
        }

        return false;
    }

    static class TreeDepth {
        int depth;

        public TreeDepth() {
            depth = 0;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
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
        Test7();
    }

    // ====================测试代码====================
    public static void test(String testName, BinaryTreeNode<Integer> pRoot, boolean expected) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins:\n", testName);

        System.out.print("Solution1 begins: ");
        if (Q55_02_BalancedBinaryTree.isBalanced_Solution1(pRoot) == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }

        System.out.print("Solution2 begins: ");
        if (Q55_02_BalancedBinaryTree.isBalanced_Solution2(pRoot) == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }

        System.out.println();
    }

    // 完全二叉树
    //             1
    //         /      \
    //        2        3
    //       /\       / \
    //      4  5     6   7
    public static void Test1() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode7 = new BinaryTreeNode<>(7);

        BinaryTreeNode.connectTreeNodes(pNode1, pNode2, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode2, pNode4, pNode5);
        BinaryTreeNode.connectTreeNodes(pNode3, pNode6, pNode7);

        test("Test1", pNode1, true);
    }

    // 不是完全二叉树，但是平衡二叉树
    //             1
    //         /      \
    //        2        3
    //       /\         \
    //      4  5         6
    //        /
    //       7
    public static void Test2() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode7 = new BinaryTreeNode<>(7);

        BinaryTreeNode.connectTreeNodes(pNode1, pNode2, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode2, pNode4, pNode5);
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode6);
        BinaryTreeNode.connectTreeNodes(pNode5, pNode7, null);

        test("Test2", pNode1, true);
    }

    // 不是平衡二叉树
    //             1
    //         /      \
    //        2        3
    //       /\
    //      4  5
    //        /
    //       6
    public static void Test3() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode6 = new BinaryTreeNode<>(6);

        BinaryTreeNode.connectTreeNodes(pNode1, pNode2, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode2, pNode4, pNode5);
        BinaryTreeNode.connectTreeNodes(pNode5, pNode6, null);

        test("Test3", pNode1, false);
    }

    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    public static void Test4() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, pNode2, null);
        BinaryTreeNode.connectTreeNodes(pNode2, pNode3, null);
        BinaryTreeNode.connectTreeNodes(pNode3, pNode4, null);
        BinaryTreeNode.connectTreeNodes(pNode4, pNode5, null);

        test("Test4", pNode1, false);
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
    public static void Test5() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, null, pNode2);
        BinaryTreeNode.connectTreeNodes(pNode2, null, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode4);
        BinaryTreeNode.connectTreeNodes(pNode4, null, pNode5);

        test("Test5", pNode1, false);
    }

    // 树中只有1个结点
    public static void Test6() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        test("Test6", pNode1, true);
    }

    // 树中没有结点
    public static void Test7() {
        test("Test7", null, true);
    }
}
