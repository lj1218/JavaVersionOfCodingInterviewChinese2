package CodingInterviewChinese2.ch06.Q54_KthNodeInBST;

/**
 * Created by lj1218.
 * Date: 2019/9/7
 *
 * Page: 269
 * 面试题54：二叉搜索树的第k个结点
 * 题目：给定一棵二叉搜索树，请找出其中的第k大的结点。
 *
 * 提示：中序遍历二叉树。
 */
public class Q54_KthNodeInBST {

    public static void main(String[] args) {
        Test.main();
    }

    public static BinaryTreeNode<Integer> kthNode(BinaryTreeNode<Integer> root, int k) {
        Recorder recorder = new Recorder();
        kthNodeCore(root, k, recorder);
        return recorder.getNode();
    }

    private static boolean kthNodeCore(BinaryTreeNode<Integer> root, int k, Recorder recorder) {
        if (root == null || k <= 0) {
            return false;
        }

        boolean found = false;

        if (root.getLChild() != null) {
            found = kthNodeCore(root.getLChild(), k, recorder);
        }
        if (found) {
            return true;
        }

        recorder.inc(1);
        if (recorder.getN() == k) {
            recorder.setNode(root);
            return true;
        }

        if (root.getRChild() != null) {
            found = kthNodeCore(root.getRChild(), k, recorder);
        }

        return found;
    }

    static class Recorder {
        private int n;
        private BinaryTreeNode<Integer> node;

        public Recorder() {
            n = 0;
            node = null;
        }

        public void inc(int n) {
            this.n += n;
        }

        public int getN() {
            return n;
        }

        public BinaryTreeNode<Integer> getNode() {
            return node;
        }

        public void setNode(BinaryTreeNode<Integer> node) {
            this.node = node;
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
        TestA();
        TestB();
        TestC();
        TestD();
        TestE();
    }

    // ====================测试代码====================
    private static void test(String testName, BinaryTreeNode<Integer> pRoot, int k, boolean isNull, int expected) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);
        BinaryTreeNode<Integer> pTarget = Q54_KthNodeInBST.kthNode(pRoot, k);
        if ((isNull && pTarget == null) || (pTarget != null && pTarget.getValue().equals(expected)))
            System.out.println("Passed.");
        else
            System.out.println("FAILED.");
    }

    //            8
    //        6      10
    //       5 7    9  11
    private static void TestA() {
        BinaryTreeNode<Integer> pNode8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> pNode6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode10 = new BinaryTreeNode<>(10);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode7 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> pNode9 = new BinaryTreeNode<>(9);
        BinaryTreeNode<Integer> pNode11 = new BinaryTreeNode<>(11);

        BinaryTreeNode.connectTreeNodes(pNode8, pNode6, pNode10);
        BinaryTreeNode.connectTreeNodes(pNode6, pNode5, pNode7);
        BinaryTreeNode.connectTreeNodes(pNode10, pNode9, pNode11);

        test("TestA0", pNode8, 0, true, -1);
        test("TestA1", pNode8, 1, false, 5);
        test("TestA2", pNode8, 2, false, 6);
        test("TestA3", pNode8, 3, false, 7);
        test("TestA4", pNode8, 4, false, 8);
        test("TestA5", pNode8, 5, false, 9);
        test("TestA6", pNode8, 6, false, 10);
        test("TestA7", pNode8, 7, false, 11);
        test("TestA8", pNode8, 8, true, -1);

        System.out.print("\n\n");
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
    private static void TestB() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);

        BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
        BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
        BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);
        BinaryTreeNode.connectTreeNodes(pNode2, pNode1, null);

        test("TestB0", pNode5, 0, true, -1);
        test("TestB1", pNode5, 1, false, 1);
        test("TestB2", pNode5, 2, false, 2);
        test("TestB3", pNode5, 3, false, 3);
        test("TestB4", pNode5, 4, false, 4);
        test("TestB5", pNode5, 5, false, 5);
        test("TestB6", pNode5, 6, true, -1);

        System.out.print("\n\n");
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
    private static void TestC() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, null, pNode2);
        BinaryTreeNode.connectTreeNodes(pNode2, null, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode4);
        BinaryTreeNode.connectTreeNodes(pNode4, null, pNode5);

        test("TestC0", pNode1, 0, true, -1);
        test("TestC1", pNode1, 1, false, 1);
        test("TestC2", pNode1, 2, false, 2);
        test("TestC3", pNode1, 3, false, 3);
        test("TestC4", pNode1, 4, false, 4);
        test("TestC5", pNode1, 5, false, 5);
        test("TestC6", pNode1, 6, true, -1);

        System.out.print("\n\n");
    }

    // There is only one node in a tree
    private static void TestD() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);

        test("TestD0", pNode1, 0, true, -1);
        test("TestD1", pNode1, 1, false, 1);
        test("TestD2", pNode1, 2, true, -1);

        System.out.print("\n\n");
    }

    // empty tree
    private static void TestE() {
        test("TestE0", null, 0, true, -1);
        test("TestE1", null, 1, true, -1);

        System.out.print("\n\n");
    }
}
