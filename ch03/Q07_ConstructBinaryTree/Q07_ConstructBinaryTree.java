package CodingInterviewChinese2.ch03.Q07_ConstructBinaryTree;

import java.util.Arrays;

/**
 * Created by lj1218.
 * Date: 2019/8/22
 * Page: 62
 * 面试题7：重建二叉树
 *   题目：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输
 * 入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列 {1,
 * 2, 4, 7, 3, 5, 6, 8} 和中序遍历序列 {4, 7, 2, 1, 5, 3, 8, 6}，则重建出
 * 图2.6所示的二叉树并输出它的头结点。
 *
 *           1
 *         /   \
 *        2    3
 *       /    / \
 *      4    5  6
 *       \     /
 *       7    8
 *
 */
public class Q07_ConstructBinaryTree {

    public static void main(String[] args) throws Exception {
        BinaryTree.test();
        System.out.println();

        Test1();
        Test2();
        Test3();
        Test4();
        Test5();
        Test6();
        Test7();
    }

    public static BinaryTree<Integer> constructBinTree(int[] preOrderList, int[] inOrderList) throws Exception {
        if (preOrderList == null || inOrderList == null) {
            return null;
        }

        return new BinaryTree<>(
                constructBinTree0(preOrderList, 0, preOrderList.length - 1,
                        inOrderList, 0, inOrderList.length - 1)
        );
    }

    public static Node<Integer> constructBinTree0(
            int[] preOrderList, int preOrderListStart, int preOrderListEnd,
            int[] inOrderList, int inOrderListStart, int inOrderListEnd
    ) throws Exception {
        // 前序遍历序列的第一个数字是根节点的值
        int rootValue = preOrderList[preOrderListStart];
        Node<Integer> rootNode = new Node<>(rootValue);
        if (preOrderListStart == preOrderListEnd) {
            if (inOrderListStart == inOrderListEnd &&
                    preOrderList[preOrderListStart] == inOrderList[inOrderListStart]) {
                return rootNode;
            }
            throw new Exception("Invalid input");
        }

        // 在中序遍历序列中找到根节点的值所在下标
        int rootInInOrderList;
        for (rootInInOrderList = inOrderListStart; rootInInOrderList <= inOrderListEnd; ++rootInInOrderList) {
            if (inOrderList[rootInInOrderList] == rootValue) {
                break;
            }
        }

        // 找不到抛出异常
        if (rootInInOrderList > inOrderListEnd) {
            throw new Exception("Invalid input");
        }

        // 递归构建左子树
        int lSubTreeLen = rootInInOrderList - inOrderListStart; // 左子树长度
        if (rootInInOrderList > inOrderListStart) {
            rootNode.setLChild(constructBinTree0(
                    preOrderList, preOrderListStart + 1, preOrderListStart + lSubTreeLen,
                    inOrderList, inOrderListStart, rootInInOrderList - 1));
        }

        // 递归构建右子树
        if (rootInInOrderList < inOrderListEnd) {
            rootNode.setRChild(constructBinTree0(
                    preOrderList, preOrderListStart + lSubTreeLen + 1, preOrderListEnd,
                    inOrderList, rootInInOrderList + 1, inOrderListEnd
            ));
        }

        return rootNode;
    }

    // ====================测试代码====================
    private static void Test(String testName, int[] preOrderList, int[] inOrderList) {
        if(testName != null)
            System.out.println(testName + " begins:");

        System.out.println("The preorder sequence is: " + Arrays.toString(preOrderList));
        System.out.println("The inorder sequence is : " + Arrays.toString(inOrderList));
        try {
            BinaryTree<Integer> binaryTree = constructBinTree(preOrderList, inOrderList);
            if (binaryTree != null) {
                binaryTree.printPreOrder();
                binaryTree.printInOrder();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input.");
        }
        System.out.println();
    }

    // 普通二叉树
    //              1
    //           /     \
    //          2       3
    //         /       / \
    //        4       5   6
    //         \         /
    //          7       8
    private static void Test1() {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};

        Test("Test1", preorder, inorder);
    }

    // 所有结点都没有右子结点
    //            1
    //           /
    //          2
    //         /
    //        3
    //       /
    //      4
    //     /
    //    5
    private static void Test2() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {5, 4, 3, 2, 1};

        Test("Test2", preorder, inorder);
    }

    // 所有结点都没有左子结点
    //            1
    //             \
    //              2
    //               \
    //                3
    //                 \
    //                  4
    //                   \
    //                    5
    private static void Test3() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {1, 2, 3, 4, 5};

        Test("Test3", preorder, inorder);
    }

    // 树中只有一个结点
    private static void Test4() {
        int[] preorder = {1};
        int[] inorder = {1};

        Test("Test4", preorder, inorder);
    }

    // 完全二叉树
    //              1
    //           /     \
    //          2       3
    //         / \     / \
    //        4   5   6   7
    private static void Test5() {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};

        Test("Test5", preorder, inorder);
    }

    // 输入空指针
    private static void Test6() {
        Test("Test6", null, null);
    }

    // 输入的两个序列不匹配
    private static void Test7() {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 8, 1, 6, 3, 7};

        Test("Test7: for unmatched input", preorder, inorder);
    }
}

class Node<E> {
    private E value;
    private Node<E> lChild;
    private Node<E> rChild;

    public Node(E value) {
        this.value = value;
        lChild = rChild = null;
    }

    public void setLChild(Node<E> node) {
        lChild = node;
    }

    public void setRChild(Node<E> node) {
        rChild = node;
    }

    public Node<E> getLChild() {
        return lChild;
    }

    public Node<E> getRChild() {
        return rChild;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}

class BinaryTree<E> {
    private Node<E> root;

    public BinaryTree(Node<E> root) {
        this.root = root;
    }

    // 前序打印
    public void printPreOrder() {
        System.out.print("preorder:");
        printPreOrder0(root);
        System.out.println();
    }

    private void printPreOrder0(Node<E> root) {
        if (root == null) {
            return;
        }

        System.out.print(" " + root);

        if (root.getLChild() != null) {
            printPreOrder0(root.getLChild());
        }

        if (root.getRChild() != null) {
            printPreOrder0(root.getRChild());
        }
    }

    // 中序打印
    public void printInOrder() {
        System.out.print("inorder :");
        printInOrder0(root);
        System.out.println();
    }

    private void printInOrder0(Node<E> root) {
        if (root == null) {
            return;
        }

        if (root.getLChild() != null) {
            printInOrder0(root.getLChild());
        }

        System.out.print(" " + root);

        if (root.getRChild() != null) {
            printInOrder0(root.getRChild());
        }
    }

    public static void test() {
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node6 = new Node<>(6);
        Node<Integer> node7 = new Node<>(7);
        Node<Integer> node8 = new Node<>(8);

        node1.setLChild(node2);
        node1.setRChild(node3);

        node2.setLChild(node4);
        node4.setRChild(node7);

        node3.setLChild(node5);
        node3.setRChild(node6);

        node6.setLChild(node8);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(node1);
        binaryTree.printPreOrder();
        binaryTree.printInOrder();
    }
}
