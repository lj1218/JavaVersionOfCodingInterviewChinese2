package CodingInterviewChinese2.ch03.Q26_SubstructureInTree;

/**
 * Created by lj1218.
 * Date: 2019/8/29
 *
 * Page: 148
 * 面试题26：树的子结构
 * 题目：输入两棵二叉树A和B，判断B是不是A的子结构。
 *
 * 提示：注意两个 double 类型值的相等性测试，不能直接用 ==
 */
public class Q26_SubstructureInTree {

    public static void main(String[] args) {
        Test.main();
    }

    public static boolean hasSubTree(BinaryTreeNode<Double> root1, BinaryTreeNode<Double> root2) {
        if (root1 != null && root2 != null) {
            if (Equal(root1.getValue(), root2.getValue())) {
                if (doesTree1HaveTree2(root1, root2)) {
                    return true;
                }
            }

            if (hasSubTree(root1.getLChild(), root2)) {
                return true;
            }

            return hasSubTree(root1.getRChild(), root2);
        }
        return false;
    }

    private static boolean doesTree1HaveTree2(BinaryTreeNode<Double> root1, BinaryTreeNode<Double> root2) {
        if (root2 == null) {
            return true;
        }

        if (root1 == null) {
            return false;
        }

        if (!Equal(root1.getValue(), root2.getValue())) {
            return false;
        }

        return doesTree1HaveTree2(root1.getLChild(), root2.getLChild())
                && doesTree1HaveTree2(root1.getRChild(), root2.getRChild());
    }

    private static boolean Equal(Double d1, Double d2) {
        return (d1 - d2 > -0.000001 && d1 - d2 < 0.000001);
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
    }

    // ====================测试代码====================
    public static void test(
            String testName, BinaryTreeNode<Double> pRoot1,
            BinaryTreeNode<Double> pRoot2, boolean expected
    ) {
        if (Q26_SubstructureInTree.hasSubTree(pRoot1, pRoot2) == expected) {
            System.out.printf("%s passed.\n", testName);
        } else {
            System.out.printf("%s failed.\n", testName);
        }
    }

    // 树中结点含有分叉，树B是树A的子结构
    //                  8                8
    //              /       \           / \
    //             8         7         9   2
    //           /   \
    //          9     2
    //               / \
    //              4   7
    public static void Test1() {
        BinaryTreeNode<Double> pNodeA1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA2 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA3 = new BinaryTreeNode<>(7.0);
        BinaryTreeNode<Double> pNodeA4 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeA5 = new BinaryTreeNode<>(2.0);
        BinaryTreeNode<Double> pNodeA6 = new BinaryTreeNode<>(4.0);
        BinaryTreeNode<Double> pNodeA7 = new BinaryTreeNode<>(7.0);

        BinaryTreeNode.connectTreeNodes(pNodeA1, pNodeA2, pNodeA3);
        BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA4, pNodeA5);
        BinaryTreeNode.connectTreeNodes(pNodeA5, pNodeA6, pNodeA7);

        BinaryTreeNode<Double> pNodeB1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeB2 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeB3 = new BinaryTreeNode<>(2.0);

        BinaryTreeNode.connectTreeNodes(pNodeB1, pNodeB2, pNodeB3);

        test("Test1", pNodeA1, pNodeB1, true);
    }

    // 树中结点含有分叉，树B不是树A的子结构
    //                  8                8
    //              /       \           / \
    //             8         7         9   2
    //           /   \
    //          9     3
    //               / \
    //              4   7
    public static void Test2() {
        BinaryTreeNode<Double> pNodeA1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA2 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA3 = new BinaryTreeNode<>(7.0);
        BinaryTreeNode<Double> pNodeA4 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeA5 = new BinaryTreeNode<>(3.0);
        BinaryTreeNode<Double> pNodeA6 = new BinaryTreeNode<>(4.0);
        BinaryTreeNode<Double> pNodeA7 = new BinaryTreeNode<>(7.0);

        BinaryTreeNode.connectTreeNodes(pNodeA1, pNodeA2, pNodeA3);
        BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA4, pNodeA5);
        BinaryTreeNode.connectTreeNodes(pNodeA5, pNodeA6, pNodeA7);

        BinaryTreeNode<Double> pNodeB1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeB2 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeB3 = new BinaryTreeNode<>(2.0);

        BinaryTreeNode.connectTreeNodes(pNodeB1, pNodeB2, pNodeB3);

        test("Test2", pNodeA1, pNodeB1, false);
    }

    // 树中结点只有左子结点，树B是树A的子结构
    //                8                  8
    //              /                   /
    //             8                   9
    //           /                    /
    //          9                    2
    //         /
    //        2
    //       /
    //      5
    public static void Test3() {
        BinaryTreeNode<Double> pNodeA1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA2 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA3 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeA4 = new BinaryTreeNode<>(2.0);
        BinaryTreeNode<Double> pNodeA5 = new BinaryTreeNode<>(5.0);

        BinaryTreeNode.connectTreeNodes(pNodeA1, pNodeA2, null);
        BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA3, null);
        BinaryTreeNode.connectTreeNodes(pNodeA3, pNodeA4, null);
        BinaryTreeNode.connectTreeNodes(pNodeA4, pNodeA5, null);

        BinaryTreeNode<Double> pNodeB1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeB2 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeB3 = new BinaryTreeNode<>(2.0);

        BinaryTreeNode.connectTreeNodes(pNodeB1, pNodeB2, null);
        BinaryTreeNode.connectTreeNodes(pNodeB2, pNodeB3, null);

        test("Test3", pNodeA1, pNodeB1, true);
    }

    // 树中结点只有左子结点，树B不是树A的子结构
    //                8                  8
    //              /                   /
    //             8                   9
    //           /                    /
    //          9                    3
    //         /
    //        2
    //       /
    //      5
    public static void Test4() {
        BinaryTreeNode<Double> pNodeA1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA2 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA3 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeA4 = new BinaryTreeNode<>(2.0);
        BinaryTreeNode<Double> pNodeA5 = new BinaryTreeNode<>(5.0);

        BinaryTreeNode.connectTreeNodes(pNodeA1, pNodeA2, null);
        BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA3, null);
        BinaryTreeNode.connectTreeNodes(pNodeA3, pNodeA4, null);
        BinaryTreeNode.connectTreeNodes(pNodeA4, pNodeA5, null);

        BinaryTreeNode<Double> pNodeB1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeB2 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeB3 = new BinaryTreeNode<>(3.0);

        BinaryTreeNode.connectTreeNodes(pNodeB1, pNodeB2, null);
        BinaryTreeNode.connectTreeNodes(pNodeB2, pNodeB3, null);

        test("Test4", pNodeA1, pNodeB1, false);
    }

    // 树中结点只有右子结点，树B是树A的子结构
    //       8                   8
    //        \                   \
    //         8                   9
    //          \                   \
    //           9                   2
    //            \
    //             2
    //              \
    //               5
    public static void Test5() {
        BinaryTreeNode<Double> pNodeA1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA2 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA3 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeA4 = new BinaryTreeNode<>(2.0);
        BinaryTreeNode<Double> pNodeA5 = new BinaryTreeNode<>(5.0);

        BinaryTreeNode.connectTreeNodes(pNodeA1, null, pNodeA2);
        BinaryTreeNode.connectTreeNodes(pNodeA2, null, pNodeA3);
        BinaryTreeNode.connectTreeNodes(pNodeA3, null, pNodeA4);
        BinaryTreeNode.connectTreeNodes(pNodeA4, null, pNodeA5);

        BinaryTreeNode<Double> pNodeB1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeB2 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeB3 = new BinaryTreeNode<>(2.0);

        BinaryTreeNode.connectTreeNodes(pNodeB1, null, pNodeB2);
        BinaryTreeNode.connectTreeNodes(pNodeB2, null, pNodeB3);

        test("Test5", pNodeA1, pNodeB1, true);
    }

    // 树A中结点只有右子结点，树B不是树A的子结构
    //       8                   8
    //        \                   \
    //         8                   9
    //          \                 / \
    //           9               3   2
    //            \
    //             2
    //              \
    //               5
    public static void Test6() {
        BinaryTreeNode<Double> pNodeA1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA2 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA3 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeA4 = new BinaryTreeNode<>(2.0);
        BinaryTreeNode<Double> pNodeA5 = new BinaryTreeNode<>(5.0);

        BinaryTreeNode.connectTreeNodes(pNodeA1, null, pNodeA2);
        BinaryTreeNode.connectTreeNodes(pNodeA2, null, pNodeA3);
        BinaryTreeNode.connectTreeNodes(pNodeA3, null, pNodeA4);
        BinaryTreeNode.connectTreeNodes(pNodeA4, null, pNodeA5);

        BinaryTreeNode<Double> pNodeB1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeB2 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeB3 = new BinaryTreeNode<>(3.0);
        BinaryTreeNode<Double> pNodeB4 = new BinaryTreeNode<>(2.0);

        BinaryTreeNode.connectTreeNodes(pNodeB1, null, pNodeB2);
        BinaryTreeNode.connectTreeNodes(pNodeB2, pNodeB3, pNodeB4);

        test("Test6", pNodeA1, pNodeB1, false);
    }

    // 树A为空树
    public static void Test7() {
        BinaryTreeNode<Double> pNodeB1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeB2 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeB3 = new BinaryTreeNode<>(3.0);
        BinaryTreeNode<Double> pNodeB4 = new BinaryTreeNode<>(2.0);

        BinaryTreeNode.connectTreeNodes(pNodeB1, null, pNodeB2);
        BinaryTreeNode.connectTreeNodes(pNodeB2, pNodeB3, pNodeB4);

        test("Test7", null, pNodeB1, false);
    }

    // 树B为空树
    public static void Test8() {
        BinaryTreeNode<Double> pNodeA1 = new BinaryTreeNode<>(8.0);
        BinaryTreeNode<Double> pNodeA2 = new BinaryTreeNode<>(9.0);
        BinaryTreeNode<Double> pNodeA3 = new BinaryTreeNode<>(3.0);
        BinaryTreeNode<Double> pNodeA4 = new BinaryTreeNode<>(2.0);

        BinaryTreeNode.connectTreeNodes(pNodeA1, null, pNodeA2);
        BinaryTreeNode.connectTreeNodes(pNodeA2, pNodeA3, pNodeA4);

        test("Test8", pNodeA1, null, false);
    }

    // 树A和树B都为空
    public static void Test9() {
        test("Test9", null, null, false);
    }
}
