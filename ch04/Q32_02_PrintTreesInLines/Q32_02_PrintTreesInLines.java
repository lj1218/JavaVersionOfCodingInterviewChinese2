package CodingInterviewChinese2.ch04.Q32_02_PrintTreesInLines;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by lj1218.
 * Date: 2019/8/31
 *
 * Page: 174
 * 面试题32（二）：分行从上到下打印二叉树
 * 题目：从上到下按层打印二叉树，同一层的结点按从左到右的顺序打印，每一层打印到一行。
 *
 * 提示：队列 + 当前层待打印节点数变量 + 下一层节点个数变量
 */
public class Q32_02_PrintTreesInLines {

    public static void main(String[] args) {
        Test.main();
    }

    public static <E> void print(BinaryTreeNode<E> treeRoot) {
        if (treeRoot == null) {
            return;
        }

        Queue<BinaryTreeNode<E>> queue = new ArrayDeque<>();
        queue.add(treeRoot);
        int toBePrinted = 1;
        int nextLevel = 0;
        while (!queue.isEmpty()) {
            while (toBePrinted-- > 0) {
                BinaryTreeNode<E> node = queue.remove();
                System.out.print(node + " ");
                if (node.getLChild() != null) {
                    queue.add(node.getLChild());
                    ++nextLevel;
                }
                if (node.getRChild() != null) {
                    queue.add(node.getRChild());
                    ++nextLevel;
                }
            }
            System.out.println();

            toBePrinted = nextLevel;
            nextLevel = 0;
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
    }

    // ====================测试代码====================
    private static <E> void test(
            String testName, BinaryTreeNode<E> treeRoot, String expectedResult
    ) {
        if (testName == null) {
            return;
        }

        System.out.printf("====%s Begins: ====\n", testName);
        System.out.printf("Expected Result is:\n%s", expectedResult);
        System.out.println("Actual Result is:");
        Q32_02_PrintTreesInLines.print(treeRoot);
        System.out.println();
    }

    //            8
    //        6      10
    //       5 7    9  11
    private static void Test1() {
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

        test("Test1", pNode8, "8 \n6 10 \n5 7 9 11 \n\n");
    }

    //            5
    //          4
    //        3
    //      2
    private static void Test2() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);

        BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
        BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
        BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);

        test("Test2", pNode5, "5 \n4 \n3 \n2 \n\n");

    }

    //        5
    //         4
    //          3
    //           2
    private static void Test3() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);

        BinaryTreeNode.connectTreeNodes(pNode5, null, pNode4);
        BinaryTreeNode.connectTreeNodes(pNode4, null, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode2);

        test("Test3", pNode5, "5 \n4 \n3 \n2 \n\n");
    }

    private static void Test4() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        test("Test4", pNode5, "5 \n\n");
    }

    private static void Test5() {
        test("Test5", null, "");
    }

    //        100
    //        /
    //       50
    //         \
    //         150
    private static void Test6() {
        BinaryTreeNode<Integer> pNode100 = new BinaryTreeNode<>(100);
        BinaryTreeNode<Integer> pNode50 = new BinaryTreeNode<>(50);
        BinaryTreeNode<Integer> pNode150 = new BinaryTreeNode<>(150);

        BinaryTreeNode.connectTreeNodes(pNode100, pNode50, null);
        BinaryTreeNode.connectTreeNodes(pNode50, null, pNode150);

        test("Test6", pNode100, "100 \n50 \n150 \n\n");
    }

}

