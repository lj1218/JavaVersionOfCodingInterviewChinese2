package CodingInterviewChinese2.ch03.Q08_NextNodeInBinaryTree;

/**
 * Created by lj1218.
 * Date: 2019/8/23
 * Page: 65
 * 面试题8：二叉树的下一个结点
 *   题目：给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
 * 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。
 */
public class Q08_NextNodeInBinaryTree {

    public static void main(String[] args) {
        Test1_7();
        Test8_11();
        Test12_15();
        Test16();
    }

    public static Node<Integer> getNext(Node<Integer> node) {
        if (node == null) {
            return null;
        }

        Node<Integer> next = node.getRChild();
        if (next != null) {
            // 如果一个节点有右子树，那么它的下一个节点就是它的右子树中的最左子节点。
            // 也就是说，从右子节点出发一直沿着指向左子节点的指针，我们就能找到它的下一个节点。
            while (next.getLChild() != null) {
                next = next.getLChild();
            }
            return next;
        }

        Node<Integer> child = node;
        Node<Integer> parent = child.getParent();
        // 说明：该 if 语句块 可以删除，下面的 while 语句块内已将这种情形包含在内。
        if (parent != null && parent.getLChild() == child) {
            // 如果一个节点没有右子树，该节点是它父节点的左子节点，那么它的下一个节点就是它的父节点。
            return parent;
        }

        // 如果一个节点既没有右子树，并且它还是它父节点的右子节点。我们可以沿着指向父节点的指针一直向上遍历，
        // 直到找到一个是它父节点的左子节点的节点。如果这样的节点存在，那么这个节点的父节点就是我们要找的下一个节点。
        while (parent != null) {
            if (parent.getLChild() == child) {
                return parent;
            }
            child = parent;
            parent = child.getParent();
        }

        return null;
    }

    // ====================测试代码====================
    public static void Test(String testName, Node<Integer> node, Node<Integer> expected) {
        if (testName != null)
            System.out.print(testName + " begins: ");

        Node next = getNext(node);
        if (next == expected)
            System.out.println("Passed.");
        else
            System.out.println("FAILED.");
    }

    //            8
    //        6      10
    //       5 7    9  11
    public static void Test1_7() {
        Node<Integer> pNode8 = new Node<>(8);
        Node<Integer> pNode6 = new Node<>(6);
        Node<Integer> pNode10 = new Node<>(10);
        Node<Integer> pNode5 = new Node<>(5);
        Node<Integer> pNode7 = new Node<>(7);
        Node<Integer> pNode9 = new Node<>(9);
        Node<Integer> pNode11 = new Node<>(11);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(pNode8);
        binaryTree.connectTreeNodes(pNode8, pNode6, pNode10);
        binaryTree.connectTreeNodes(pNode6, pNode5, pNode7);
        binaryTree.connectTreeNodes(pNode10, pNode9, pNode11);
        binaryTree.printInOrder();

        Test("Test1", pNode8, pNode9);
        Test("Test2", pNode6, pNode7);
        Test("Test3", pNode10, pNode11);
        Test("Test4", pNode5, pNode6);
        Test("Test5", pNode7, pNode8);
        Test("Test6", pNode9, pNode10);
        Test("Test7", pNode11, null);
    }

    //            5
    //          4
    //        3
    //      2
    public static void Test8_11() {
        Node<Integer> pNode5 = new Node<>(5);
        Node<Integer> pNode4 = new Node<>(4);
        Node<Integer> pNode3 = new Node<>(3);
        Node<Integer> pNode2 = new Node<>(2);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(pNode5);
        binaryTree.connectTreeNodes(pNode5, pNode4, null);
        binaryTree.connectTreeNodes(pNode4, pNode3, null);
        binaryTree.connectTreeNodes(pNode3, pNode2, null);
        binaryTree.printInOrder();

        Test("Test8", pNode5, null);
        Test("Test9", pNode4, pNode5);
        Test("Test10", pNode3, pNode4);
        Test("Test11", pNode2, pNode3);
    }

    //        2
    //         3
    //          4
    //           5
    public static void Test12_15() {
        Node<Integer> pNode2 = new Node<>(2);
        Node<Integer> pNode3 = new Node<>(3);
        Node<Integer> pNode4 = new Node<>(4);
        Node<Integer> pNode5 = new Node<>(5);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(pNode2);
        binaryTree.connectTreeNodes(pNode2, null, pNode3);
        binaryTree.connectTreeNodes(pNode3, null, pNode4);
        binaryTree.connectTreeNodes(pNode4, null, pNode5);
        binaryTree.printInOrder();

        Test("Test12", pNode5, null);
        Test("Test13", pNode4, pNode5);
        Test("Test14", pNode3, pNode4);
        Test("Test15", pNode2, pNode3);
    }

    public static void Test16() {
        Node<Integer> pNode5 = new Node<>(5);

        BinaryTree<Integer> binaryTree = new BinaryTree<>(pNode5);
        binaryTree.printInOrder();

        Test("Test16", pNode5, null);
    }
}

class Node<E> {
    private E value;
    private Node<E> parent;
    private Node<E> lChild;
    private Node<E> rChild;

    public Node(E value) {
        this.value = value;
        parent = lChild = rChild = null;
    }

    public void setParent(Node<E> node) {
        parent = node;
    }

    public void setLChild(Node<E> node) {
        lChild = node;
    }

    public void setRChild(Node<E> node) {
        rChild = node;
    }

    public Node<E> getParent() {
        return parent;
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

    // 连接树节点
    public void connectTreeNodes(Node<E> parent, Node<E> leftChild, Node<E> rightChild) {
        if (parent != null) {
            parent.setLChild(leftChild);
            parent.setRChild(rightChild);
            if (leftChild != null) {
                leftChild.setParent(parent);
            }
            if (rightChild != null) {
                rightChild.setParent(parent);
            }
        }
    }

    // 中序打印
    public void printInOrder() {
        System.out.print("inorder:");
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
}
