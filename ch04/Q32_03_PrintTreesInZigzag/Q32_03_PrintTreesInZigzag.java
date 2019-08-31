package CodingInterviewChinese2.ch04.Q32_03_PrintTreesInZigzag;

import java.util.Stack;

/**
 * Created by lj1218.
 * Date: 2019/8/31
 *
 * Page: 176
 * 面试题32（三）：之字形打印二叉树
 * 题目：请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺
 * 序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，
 * 其他行以此类推。
 *
 * 提示：利用两个栈，打印奇数层时先入栈左子节点，偶数层先入栈右子节点。
 */
public class Q32_03_PrintTreesInZigzag {

    public static void main(String[] args) {
        Test.main();
    }

    public static <E> void print(BinaryTreeNode<E> treeRoot) {
        if (treeRoot == null) {
            return;
        }

        Stack<BinaryTreeNode<E>> current = new Stack<>();
        Stack<BinaryTreeNode<E>> next = new Stack<>();
        current.push(treeRoot);
        int level = 1;
        while (!current.isEmpty()) {
            BinaryTreeNode<E> node = current.pop();
            System.out.print(node + " ");
            if ((level & 0x1) == 0x1) {
                // 打印奇数层
                if (node.getLChild() != null) {
                    next.push(node.getLChild());
                }
                if (node.getRChild() != null) {
                    next.push(node.getRChild());
                }
            } else {
                // 打印偶数层
                if (node.getRChild() != null) {
                    next.push(node.getRChild());
                }
                if (node.getLChild() != null) {
                    next.push(node.getLChild());
                }
            }

            if (current.isEmpty()) {
                // 当前层打印完毕，打印下一层
                System.out.println();
                ++level;
                Stack<BinaryTreeNode<E>> temp = current;
                current = next;
                next = temp;
            }
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
    private static <E> void test(
            String testName, BinaryTreeNode<E> treeRoot, String expectedResult
    ) {
        if (testName == null) {
            return;
        }

        System.out.printf("====%s Begins: ====\n", testName);
        System.out.printf("Expected Result is:\n%s", expectedResult);
        System.out.println("Actual Result is:");
        Q32_03_PrintTreesInZigzag.print(treeRoot);
        System.out.println();
    }

    //            8
    //        6      10
    //       5 7    9  11
    public static void Test1() {
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

        test("Test1", pNode8, "8 \n10 6 \n5 7 9 11 \n\n");
    }

    //            5
    //          4
    //        3
    //      2
    public static void Test2() {
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
    public static void Test3() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);

        BinaryTreeNode.connectTreeNodes(pNode5, null, pNode4);
        BinaryTreeNode.connectTreeNodes(pNode4, null, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode2);

        test("Test3", pNode5, "5 \n4 \n3 \n2 \n\n");
    }

    public static void Test4() {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        test("Test4", pNode5, "5 \n\n");
    }

    public static void Test5() {
        test("Test5", null, "");
    }

    //        100
    //        /
    //       50
    //         \
    //         150
    public static void Test6() {
        BinaryTreeNode<Integer> pNode100 = new BinaryTreeNode<>(100);
        BinaryTreeNode<Integer> pNode50 = new BinaryTreeNode<>(50);
        BinaryTreeNode<Integer> pNode150 = new BinaryTreeNode<>(150);

        BinaryTreeNode.connectTreeNodes(pNode100, pNode50, null);
        BinaryTreeNode.connectTreeNodes(pNode50, null, pNode150);

        test("Test6", pNode100, "100 \n50 \n150 \n\n");
    }

    //                8
    //        4              12
    //     2     6       10      14
    //   1  3  5  7     9 11   13  15
    public static void Test7() {
        BinaryTreeNode<Integer> pNode8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode12 = new BinaryTreeNode<>(12);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);
        BinaryTreeNode<Integer> pNode6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode10 = new BinaryTreeNode<>(10);
        BinaryTreeNode<Integer> pNode14 = new BinaryTreeNode<>(14);
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(1);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode7 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> pNode9 = new BinaryTreeNode<>(9);
        BinaryTreeNode<Integer> pNode11 = new BinaryTreeNode<>(11);
        BinaryTreeNode<Integer> pNode13 = new BinaryTreeNode<>(13);
        BinaryTreeNode<Integer> pNode15 = new BinaryTreeNode<>(15);

        BinaryTreeNode.connectTreeNodes(pNode8, pNode4, pNode12);
        BinaryTreeNode.connectTreeNodes(pNode4, pNode2, pNode6);
        BinaryTreeNode.connectTreeNodes(pNode12, pNode10, pNode14);
        BinaryTreeNode.connectTreeNodes(pNode2, pNode1, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode6, pNode5, pNode7);
        BinaryTreeNode.connectTreeNodes(pNode10, pNode9, pNode11);
        BinaryTreeNode.connectTreeNodes(pNode14, pNode13, pNode15);

        test("Test7", pNode8, "8 \n12 4 \n2 6 10 14 \n15 13 11 9 7 5 3 1 \n\n");
    }
}
