package CodingInterviewChinese2.ch04.Q32_01_PrintTreeFromTopToBottom;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by lj1218.
 * Date: 2019/8/30
 *
 * Page: 171
 * 面试题32（一）：不分行从上往下打印二叉树
 * 题目：从上往下打印出二叉树的每个结点，同一层的结点按照从左到右的顺序打印。
 *
 * 提示：利用队列，先将根节点入队，然后依次从队列出队一个元素，打印该元素并将其左右子节点依次入队，直至队列为空。
 */
public class Q32_01_PrintTreeFromTopToBottom {

    public static void main(String[] args) {
        Test.main();
    }

    public static <E> void printFromTopToBottom(BinaryTreeNode<E> treeRoot) {
        if (treeRoot == null) {
            return;
        }

        Queue<BinaryTreeNode<E>> queue = new ArrayDeque<>();
        queue.add(treeRoot);
        while (!queue.isEmpty()) {
            BinaryTreeNode<E> node = queue.remove();
            if (node.getLChild() != null) {
                queue.add(node.getLChild());
            }
            if (node.getRChild() != null) {
                queue.add(node.getRChild());
            }
            System.out.print(node + " ");
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
    }

    // ====================测试代码====================
    private static void test(String testName, BinaryTreeNode<Integer> pRoot) {
        if (testName != null) {
            System.out.printf("%s begins: \n", testName);
        }

        System.out.println("The nodes from top to bottom, from left to right are: ");
        Q32_01_PrintTreeFromTopToBottom.printFromTopToBottom(pRoot);
        System.out.print("\n\n");
    }

    //            10
    //         /      \
    //        6        14
    //       /\        /\
    //      4  8     12  16
    private static void Test1() {
        BinaryTreeNode<Integer> pNode10 = new BinaryTreeNode<>(10);
        BinaryTreeNode<Integer> pNode6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode14 = new BinaryTreeNode<>(14);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> pNode12 = new BinaryTreeNode<>(12);
        BinaryTreeNode<Integer> pNode16 = new BinaryTreeNode<>(16);

        BinaryTreeNode.connectTreeNodes(pNode10, pNode6, pNode14);
        BinaryTreeNode.connectTreeNodes(pNode6, pNode4, pNode8);
        BinaryTreeNode.connectTreeNodes(pNode14, pNode12, pNode16);

        test("Test1", pNode10);
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
    private static void Test2() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);

        BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
        BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
        BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);
        BinaryTreeNode.connectTreeNodes(pNode2, pNode1, null);

        test("Test2", pNode5);
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
    private static void Test3() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, null, pNode2);
        BinaryTreeNode.connectTreeNodes(pNode2, null, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode4);
        BinaryTreeNode.connectTreeNodes(pNode4, null, pNode5);

        test("Test3", pNode1);
    }

    // 树中只有1个结点
    private static void Test4() {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        test("Test4", pNode1);
    }

    // 树中没有结点
    private static void Test5() {
        test("Test5", null);
    }
}
