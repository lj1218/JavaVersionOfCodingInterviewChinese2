package CodingInterviewChinese2.ch06.Q55_01_TreeDepth;

/**
 * Created by lj1218.
 * Date: 2019/9/7
 *
 * Page: 271
 * 面试题55（一）：二叉树的深度
 * 题目：输入一棵二叉树的根结点，求该树的深度。从根结点到叶结点依次经过的
 * 结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 提示：树的深度等于根节点的左、右子树最大深度 + 1
 */
public class Q55_01_TreeDepth {

    public static void main(String[] args) {
        Test.main();
    }

    public static int treeDepth(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int depthLeftChild = treeDepth(root.getLChild());
        int depthRightChild = treeDepth(root.getRChild());
        return Math.max(depthLeftChild, depthRightChild) + 1;
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
    }

    // ====================测试代码====================
    public static void test(String testName, BinaryTreeNode<Integer> pRoot, int expected) {
        int result = Q55_01_TreeDepth.treeDepth(pRoot);
        if (expected == result) {
            System.out.printf("%s passed.\n", testName);
        } else {
            System.out.printf("%s FAILED.\n", testName);
        }
    }

    //            1
    //         /      \
    //        2        3
    //       /\         \
    //      4  5         6
    //        /
    //       7
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
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode6);
        BinaryTreeNode.connectTreeNodes(pNode5, pNode7, null);

        test("Test1", pNode1, 4);
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
    public static void Test2() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, pNode2, null);
        BinaryTreeNode.connectTreeNodes(pNode2, pNode3, null);
        BinaryTreeNode.connectTreeNodes(pNode3, pNode4, null);
        BinaryTreeNode.connectTreeNodes(pNode4, pNode5, null);

        test("Test2", pNode1, 5);
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
    public static void Test3() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, null, pNode2);
        BinaryTreeNode.connectTreeNodes(pNode2, null, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode4);
        BinaryTreeNode.connectTreeNodes(pNode4, null, pNode5);

        test("Test3", pNode1, 5);
    }

    // 树中只有1个结点
    public static void Test4() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        test("Test4", pNode1, 1);
    }

    // 树中没有结点
    public static void Test5() {
        test("Test5", null, 0);
    }
}
