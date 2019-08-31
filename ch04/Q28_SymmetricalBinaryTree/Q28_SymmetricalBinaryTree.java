package CodingInterviewChinese2.ch04.Q28_SymmetricalBinaryTree;

/**
 * Created by lj1218.
 * Date: 2019/8/29
 *
 * Page: 159
 * 面试题28：对称的二叉树
 * 题目：请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和
 * 它的镜像一样，那么它是对称的。
 *
 * 提示：自定义前序遍历算法：父节点 -> 右子节点 -> 左子节点
 */
public class Q28_SymmetricalBinaryTree {

    public static void main(String[] args) {
        Test.main();
    }

    public static <E> boolean isSymmetrical(BinaryTreeNode<E> root) {
        return isSymmetrical(root, root);
    }

    private static <E> boolean isSymmetrical(BinaryTreeNode<E> root1, BinaryTreeNode<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (!root1.getValue().equals(root2.getValue())) {
            return false;
        }

        return isSymmetrical(root1.getLChild(), root2.getRChild())
                && isSymmetrical(root1.getRChild(), root2.getLChild());
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
        Test8();
        Test9();
        Test10();
    }

    // ====================测试代码====================
    private static void test(String testName, BinaryTreeNode<Integer> pRoot, boolean expected) {
        if (testName != null) {
            System.out.printf("%s begins: ", testName);
        }

        if (Q28_SymmetricalBinaryTree.isSymmetrical(pRoot) == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("FAILED.");
        }
    }

    //            8
    //        6      6
    //       5 7    7 5
    private static void Test1() {
        BinaryTreeNode<Integer> pNode8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> pNode61 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode62 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode51 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode71 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> pNode72 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> pNode52 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode8, pNode61, pNode62);
        BinaryTreeNode.connectTreeNodes(pNode61, pNode51, pNode71);
        BinaryTreeNode.connectTreeNodes(pNode62, pNode72, pNode52);

        test("Test1", pNode8, true);
    }

    //            8
    //        6      9
    //       5 7    7 5
    private static void Test2() {
        BinaryTreeNode<Integer> pNode8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> pNode61 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode9 = new BinaryTreeNode<>(9);
        BinaryTreeNode<Integer> pNode51 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode71 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> pNode72 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> pNode52 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode8, pNode61, pNode9);
        BinaryTreeNode.connectTreeNodes(pNode61, pNode51, pNode71);
        BinaryTreeNode.connectTreeNodes(pNode9, pNode72, pNode52);

        test("Test2", pNode8, false);
    }

    //            8
    //        6      6
    //       5 7    7
    private static void Test3() {
        BinaryTreeNode<Integer> pNode8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> pNode61 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode62 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode51 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode71 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> pNode72 = new BinaryTreeNode<>(7);

        BinaryTreeNode.connectTreeNodes(pNode8, pNode61, pNode62);
        BinaryTreeNode.connectTreeNodes(pNode61, pNode51, pNode71);
        BinaryTreeNode.connectTreeNodes(pNode62, pNode72, null);

        test("Test3", pNode8, false);
    }

    //               5
    //              / \
    //             3   3
    //            /     \
    //           4       4
    //          /         \
    //         2           2
    //        /             \
    //       1               1
    private static void Test4() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode31 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode32 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode41 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode42 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode21 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode22 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode11 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode12 = new BinaryTreeNode<>(1);

        BinaryTreeNode.connectTreeNodes(pNode5, pNode31, pNode32);
        BinaryTreeNode.connectTreeNodes(pNode31, pNode41, null);
        BinaryTreeNode.connectTreeNodes(pNode32, null, pNode42);
        BinaryTreeNode.connectTreeNodes(pNode41, pNode21, null);
        BinaryTreeNode.connectTreeNodes(pNode42, null, pNode22);
        BinaryTreeNode.connectTreeNodes(pNode21, pNode11, null);
        BinaryTreeNode.connectTreeNodes(pNode22, null, pNode12);

        test("Test4", pNode5, true);
    }

    //               5
    //              / \
    //             3   3
    //            /     \
    //           4       4
    //          /         \
    //         6           2
    //        /             \
    //       1               1
    private static void Test5() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode31 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode32 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode41 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode42 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode22 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode11 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode12 = new BinaryTreeNode<>(1);

        BinaryTreeNode.connectTreeNodes(pNode5, pNode31, pNode32);
        BinaryTreeNode.connectTreeNodes(pNode31, pNode41, null);
        BinaryTreeNode.connectTreeNodes(pNode32, null, pNode42);
        BinaryTreeNode.connectTreeNodes(pNode41, pNode6, null);
        BinaryTreeNode.connectTreeNodes(pNode42, null, pNode22);
        BinaryTreeNode.connectTreeNodes(pNode6, pNode11, null);
        BinaryTreeNode.connectTreeNodes(pNode22, null, pNode12);

        test("Test5", pNode5, false);
    }

    //               5
    //              / \
    //             3   3
    //            /     \
    //           4       4
    //          /         \
    //         2           2
    //                      \
    //                       1
    private static void Test6() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode31 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode32 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode41 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode42 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode21 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode22 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode12 = new BinaryTreeNode<>(1);

        BinaryTreeNode.connectTreeNodes(pNode5, pNode31, pNode32);
        BinaryTreeNode.connectTreeNodes(pNode31, pNode41, null);
        BinaryTreeNode.connectTreeNodes(pNode32, null, pNode42);
        BinaryTreeNode.connectTreeNodes(pNode41, pNode21, null);
        BinaryTreeNode.connectTreeNodes(pNode42, null, pNode22);
        BinaryTreeNode.connectTreeNodes(pNode21, null, null);
        BinaryTreeNode.connectTreeNodes(pNode22, null, pNode12);

        test("Test6", pNode5, false);
    }

    // 只有一个结点
    private static void Test7() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        test("Test7", pNode1, true);
    }

    // 没有结点
    private static void Test8() {
        test("Test8", null, true);
    }

    // 所有结点都有相同的值，树对称
    //               5
    //              / \
    //             5   5
    //            /     \
    //           5       5
    //          /         \
    //         5           5
    private static void Test9() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode21 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode22 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode31 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode32 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode41 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode42 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, pNode21, pNode22);
        BinaryTreeNode.connectTreeNodes(pNode21, pNode31, null);
        BinaryTreeNode.connectTreeNodes(pNode22, null, pNode32);
        BinaryTreeNode.connectTreeNodes(pNode31, pNode41, null);
        BinaryTreeNode.connectTreeNodes(pNode32, null, pNode42);
        BinaryTreeNode.connectTreeNodes(pNode41, null, null);
        BinaryTreeNode.connectTreeNodes(pNode42, null, null);

        test("Test9", pNode1, true);
    }

    // 所有结点都有相同的值，树不对称
    //               5
    //              / \
    //             5   5
    //            /     \
    //           5       5
    //          /       /
    //         5       5
    private static void Test10() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode21 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode22 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode31 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode32 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode41 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode42 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, pNode21, pNode22);
        BinaryTreeNode.connectTreeNodes(pNode21, pNode31, null);
        BinaryTreeNode.connectTreeNodes(pNode22, null, pNode32);
        BinaryTreeNode.connectTreeNodes(pNode31, pNode41, null);
        BinaryTreeNode.connectTreeNodes(pNode32, pNode42, null);
        BinaryTreeNode.connectTreeNodes(pNode41, null, null);
        BinaryTreeNode.connectTreeNodes(pNode42, null, null);

        test("Test10", pNode1, false);
    }
}
