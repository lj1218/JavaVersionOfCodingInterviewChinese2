package CodingInterviewChinese2.ch04.Q36_ConvertBinarySearchTree;

/**
 * Created by lj1218.
 * Date: 2019/8/31
 *
 * Page: 191
 * 面试题36：二叉搜索树与双向链表
 * 题目：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求
 * 不能创建任何新的结点，只能调整树中结点指针的指向。
 */
public class Q36_ConvertBinarySearchTree {

    public static void main(String[] args) {
        Test.main();
    }

    public static <E> BinaryTreeNode<E> convert(BinaryTreeNode<E> root) {
        BinaryTreeNode<E> lastNodeInList = convertNode(root, null);

        // lastNodeInList 指向双向链表的尾节点，我们需要返回头结点
        BinaryTreeNode<E> headOfList = lastNodeInList;
        if (headOfList != null) {
            while (headOfList.getLChild() != null) {
                headOfList = headOfList.getLChild();
            }
        }
        return headOfList;
    }

    // Java 不能传递没有指针的概念，因此只能将 lastNodeInList 作为参数传入，将更新后的 lastNodeInList 作为返回值返回。
    // 注释：这段递归代码理解起来不太直观。
    private static <E> BinaryTreeNode<E> convertNode(BinaryTreeNode<E> node, BinaryTreeNode<E> lastNodeInList) {
        if (node == null) {
            return lastNodeInList;
        }

        if (node.getLChild() != null) {
            // 转换左子树，并返回转换之后的双向链表尾节点
            lastNodeInList = convertNode(node.getLChild(), lastNodeInList);
        }

        // 将当前节点与左子树转成链表后的尾节点连接起来
        node.setLChild(lastNodeInList);
        if (lastNodeInList != null) {
            lastNodeInList.setRChild(node);
        }

        // 更新当前节点为最新尾节点
        lastNodeInList = node;

        if (node.getRChild() != null) {
            // 转换右子树，并返回转换之后的双向链表尾节点
            lastNodeInList = convertNode(node.getRChild(), lastNodeInList);
        }
        return lastNodeInList;
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
    private static void PrintDoubleLinkedList(BinaryTreeNode<Integer> headOfList) {
        BinaryTreeNode<Integer> node = headOfList;

        System.out.print("The nodes from left to right are:\n");
        while (node != null) {
            System.out.printf("%d\t", node.getValue());

            if (node.getRChild() == null) {
                break;
            }

            node = node.getRChild();
        }

        System.out.print("\nThe nodes from right to left are:\n");
        while (node != null) {
            System.out.printf("%d\t", node.getValue());

            if (node.getLChild() == null) {
                break;
            }

            node = node.getLChild();
        }
        System.out.println();
    }

    private static void test(String testName, BinaryTreeNode<Integer> rootOfTree) {
        if (testName == null) {
            return;
        }

        System.out.printf("========== %s begins: ==========\n", testName);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(rootOfTree);
        binaryTree.printTree();
        System.out.println();

        BinaryTreeNode<Integer> pHeadOfList = Q36_ConvertBinarySearchTree.convert(rootOfTree);

        PrintDoubleLinkedList(pHeadOfList);
        System.out.println();
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
