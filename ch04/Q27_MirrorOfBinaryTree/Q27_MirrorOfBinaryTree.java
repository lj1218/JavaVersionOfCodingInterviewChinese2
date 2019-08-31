package CodingInterviewChinese2.ch04.Q27_MirrorOfBinaryTree;

import java.util.Stack;

/**
 * Created by lj1218.
 * Date: 2019/8/29
 *
 * Page: 157
 * 面试题27：二叉树的镜像
 * 题目：请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */
public class Q27_MirrorOfBinaryTree {

    public static void main(String[] args) {
        Test.main();
    }

    // 前序遍历（递归）
    public static <E> void mirrorRecursively(BinaryTreeNode<E> node) {
        if (node == null) {
            return;
        }

        // 叶节点
        if (node.getLChild() == null && node.getRChild() == null) {
            return;
        }

        BinaryTreeNode<E> temp = node.getLChild();
        node.setLChild(node.getRChild());
        node.setRChild(temp);

        if (node.getLChild() != null) {
            mirrorRecursively(node.getLChild());
        }

        if (node.getRChild() != null) {
            mirrorRecursively(node.getRChild());
        }
    }

    // 迭代
    public static <E> void mirrorIteratively(BinaryTreeNode<E> root) {
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode<E>> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode<E> node = stack.pop();

            BinaryTreeNode<E> temp = node.getLChild();
            node.setLChild(node.getRChild());
            node.setRChild(temp);

            if (node.getLChild() != null) {
                stack.push(node.getLChild());
            }

            if (node.getRChild() != null) {
                stack.push(node.getRChild());
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

class BinaryTree<E> {
    private BinaryTreeNode<E> root;

    public BinaryTree(BinaryTreeNode<E> root) {
        this.root = root;
    }

    public void printTree() {
        printTreeCore(root);
    }

    private void printTreeCore(BinaryTreeNode<E> root) {
        printTreeNode(root);

        if (root != null) {
            if (root.getLChild() != null) {
                printTreeCore(root.getLChild());
            }

            if (root.getRChild() != null) {
                printTreeCore(root.getRChild());
            }
        }
    }

    public void printTreeNode(BinaryTreeNode<E> node) {
        if (node != null) {
            System.out.println("value of this node is: " + node.getValue());

            if (node.getLChild() != null) {
                System.out.println("value of its left child is: " + node.getLChild().getValue());
            } else {
                System.out.println("left child is null");
            }

            if (node.getRChild() != null) {
                System.out.println("value of its right child is: " + node.getRChild().getValue());
            } else {
                System.out.println("right child is null");
            }
        } else {
            System.out.println("this node is null");
        }
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
    // 测试完全二叉树：除了叶子节点，其他节点都有两个子节点
    //            8
    //        6      10
    //       5 7    9  11
    private static void Test1() {
        System.out.println("=====Test1 starts:=====");
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

        BinaryTree<Integer> binaryTree = new BinaryTree<>(pNode8);
        binaryTree.printTree();

        System.out.println("=====Test1: mirrorRecursively=====");
        Q27_MirrorOfBinaryTree.mirrorRecursively(pNode8);
        binaryTree.printTree();

        System.out.println("=====Test1: MirrorIteratively=====");
        Q27_MirrorOfBinaryTree.mirrorIteratively(pNode8);
        binaryTree.printTree();
        System.out.println();
    }

    // 测试二叉树：出叶子结点之外，左右的结点都有且只有一个左子结点
    //            8
    //          7
    //        6
    //      5
    //    4
    private static void Test2() {
        System.out.println("=====Test2 starts:=====");
        BinaryTreeNode<Integer> pNode8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> pNode7 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> pNode6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);

        BinaryTreeNode.connectTreeNodes(pNode8, pNode7, null);
        BinaryTreeNode.connectTreeNodes(pNode7, pNode6, null);
        BinaryTreeNode.connectTreeNodes(pNode6, pNode5, null);
        BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(pNode8);
        binaryTree.printTree();

        System.out.println("=====Test2: mirrorRecursively=====");
        Q27_MirrorOfBinaryTree.mirrorRecursively(pNode8);
        binaryTree.printTree();

        System.out.println("=====Test2: MirrorIteratively=====");
        Q27_MirrorOfBinaryTree.mirrorIteratively(pNode8);
        binaryTree.printTree();
        System.out.println();
    }

    // 测试二叉树：出叶子结点之外，左右的结点都有且只有一个右子结点
    //            8
    //             7
    //              6
    //               5
    //                4
    private static void Test3() {
        System.out.println("=====Test3 starts:=====");
        BinaryTreeNode<Integer> pNode8 = new BinaryTreeNode<>(8);
        BinaryTreeNode<Integer> pNode7 = new BinaryTreeNode<>(7);
        BinaryTreeNode<Integer> pNode6 = new BinaryTreeNode<>(6);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);

        BinaryTreeNode.connectTreeNodes(pNode8, null, pNode7);
        BinaryTreeNode.connectTreeNodes(pNode7, null, pNode6);
        BinaryTreeNode.connectTreeNodes(pNode6, null, pNode5);
        BinaryTreeNode.connectTreeNodes(pNode5, null, pNode4);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(pNode8);
        binaryTree.printTree();

        System.out.println("=====Test3: mirrorRecursively=====");
        Q27_MirrorOfBinaryTree.mirrorRecursively(pNode8);
        binaryTree.printTree();

        System.out.println("=====Test3: MirrorIteratively=====");
        Q27_MirrorOfBinaryTree.mirrorIteratively(pNode8);
        binaryTree.printTree();
        System.out.println();
    }

    // 测试空二叉树：根结点为空指针
    private static void Test4() {
        System.out.println("=====Test4 starts:=====");
        BinaryTreeNode<Integer> pNode = null;

        BinaryTree<Integer> binaryTree = new BinaryTree<>(pNode);
        binaryTree.printTree();

        System.out.println("=====Test4: mirrorRecursively=====");
        Q27_MirrorOfBinaryTree.mirrorRecursively(pNode);
        binaryTree.printTree();

        System.out.println("=====Test4: MirrorIteratively=====");
        Q27_MirrorOfBinaryTree.mirrorIteratively(pNode);
        binaryTree.printTree();
        System.out.println();
    }

    // 测试只有一个结点的二叉树
    private static void Test5() {
        System.out.println("=====Test5 starts:=====");
        BinaryTreeNode<Integer> pNode8 = new BinaryTreeNode<>(8);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(pNode8);
        binaryTree.printTree();

        System.out.println("=====Test4: mirrorRecursively=====");
        Q27_MirrorOfBinaryTree.mirrorRecursively(pNode8);
        binaryTree.printTree();

        System.out.println("=====Test4: MirrorIteratively=====");
        Q27_MirrorOfBinaryTree.mirrorIteratively(pNode8);
        binaryTree.printTree();
        System.out.println();
    }
}
