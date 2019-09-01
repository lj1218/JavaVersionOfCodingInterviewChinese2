package CodingInterviewChinese2.ch04.Q37_SerializeBinaryTrees;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by lj1218.
 * Date: 2019/9/1
 *
 * Page: 194
 * 面试题37：序列化二叉树
 * 题目：请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 提示：前序遍历，null 指针序列化为一个特殊的字符（如 '$'）。
 * 另外，节点的数值之间用特殊字符（如 ','）隔开。
 */
public class Q37_SerializeBinaryTrees {

    public static void main(String[] args) throws IOException {
        Test.main();
    }

    public static void serialize(BinaryTreeNode<Integer> root, PrintWriter out) {
        if (root == null) {
            out.print("$,");
            return;
        }

        out.print(root + ",");
        serialize(root.getLChild(), out);
        serialize(root.getRChild(), out);
    }

    public static BinaryTreeNode<Integer> deSerialize(String[] strArray) {
        NumberReader nr = new NumberReader(strArray);
        return deSerializeCore(nr);
    }

    private static BinaryTreeNode<Integer> deSerializeCore(NumberReader nr) {
        BinaryTreeNode<Integer> root = null;
        Integer number = nr.readOne();
        if (number != null) {
            root = new BinaryTreeNode<>(number);
            root.setLChild(deSerializeCore(nr));
            root.setRChild(deSerializeCore(nr));
        }
        return root;
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

class NumberReader {
    private int index;
    private String[] strArr;

    public NumberReader(String[] strArr) {
        this.strArr = strArr;
        index = 0;
    }

    // 从 strArr 依次读取一个整数，非整数返回 null
    public Integer readOne() {
        if (strArr == null) {
            return null;
        }

        Integer number = null;
        try {
            number = Integer.parseInt(strArr[index++]);
        } catch (NumberFormatException e) {
            // ignore
        }
        return number;
    }
}

class Test {

    public static void main() throws IOException {
        Test1();
        Test2();
        Test3();
        Test4();
        Test5();
        Test6();
    }

    // ==================== Test Code ====================
    private static boolean isSameTree(BinaryTreeNode<Integer> pRoot1, BinaryTreeNode<Integer> pRoot2) {
        if (pRoot1 == null && pRoot2 == null)
            return true;

        if (pRoot1 == null || pRoot2 == null)
            return false;

        if (!pRoot1.getValue().equals(pRoot2.getValue()))
            return false;

        return isSameTree(pRoot1.getLChild(), pRoot2.getLChild()) &&
                isSameTree(pRoot1.getRChild(), pRoot2.getRChild());
    }

    private static void test(String testName, BinaryTreeNode<Integer> pRoot) throws IOException {
        if (testName == null) {
            return;
        }

        System.out.printf("============ %s begins: ============\n", testName);

        // print original tree
        BinaryTree<Integer> originalTree = new BinaryTree<>(pRoot);
        originalTree.printTree();
        System.out.println();

        // serialize tree
        String fileName = "test.txt";
        String charsetName = "UTF-8";
        PrintWriter out = new PrintWriter(fileName, charsetName);
        Q37_SerializeBinaryTrees.serialize(pRoot, out);
        out.close();

        // print the serialized file
        Scanner in = new Scanner(Paths.get(fileName), charsetName);
        String serializedString = null;
        while (in.hasNextLine()) {
            serializedString = in.nextLine();
            System.out.println(serializedString);
        }
        in.close();
        System.out.println();

        // deserialize tree from file
        String[] strArray = null;
        if (serializedString != null) {
            strArray = serializedString.split(",");
        }
//        System.out.println(Arrays.toString(strArray));
        BinaryTreeNode<Integer> pNewRoot = Q37_SerializeBinaryTrees.deSerialize(strArray);

        // print deserialized tree
        BinaryTree<Integer> newTree = new BinaryTree<>(pNewRoot);
        newTree.printTree();

        if (isSameTree(pRoot, pNewRoot)) {
            System.out.print("The deserialized tree is same as the original tree.\n\n");
        } else {
            System.out.print("The deserialized tree is NOT same as the original tree.\n\n");
        }
        System.out.println();
    }

    //            8
    //        6      10
    //       5 7    9  11
    private static void Test1() throws IOException {
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

        test("Test1", pNode8);
    }

    //            5
    //          4
    //        3
    //      2
    private static void Test2() throws IOException {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);

        BinaryTreeNode.connectTreeNodes(pNode5, pNode4, null);
        BinaryTreeNode.connectTreeNodes(pNode4, pNode3, null);
        BinaryTreeNode.connectTreeNodes(pNode3, pNode2, null);

        test("Test2", pNode5);
    }

    //        5
    //         4
    //          3
    //           2
    private static void Test3() throws IOException {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(4);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(3);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(2);

        BinaryTreeNode.connectTreeNodes(pNode5, null, pNode4);
        BinaryTreeNode.connectTreeNodes(pNode4, null, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode3, null, pNode2);

        test("Test3", pNode5);
    }

    private static void Test4() throws IOException {
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);

        test("Test4", pNode5);
    }

    private static void Test5() throws IOException {
        test("Test5", null);
    }

    //        5
    //         5
    //          5
    //         5
    //        5
    //       5 5
    //      5   5
    private static void Test6() throws IOException {
        BinaryTreeNode<Integer> pNode1 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode2 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode3 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode4 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode5 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode61 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode62 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode71 = new BinaryTreeNode<>(5);
        BinaryTreeNode<Integer> pNode72 = new BinaryTreeNode<>(5);

        BinaryTreeNode.connectTreeNodes(pNode1, null, pNode2);
        BinaryTreeNode.connectTreeNodes(pNode2, null, pNode3);
        BinaryTreeNode.connectTreeNodes(pNode3, pNode4, null);
        BinaryTreeNode.connectTreeNodes(pNode4, pNode5, null);
        BinaryTreeNode.connectTreeNodes(pNode5, pNode61, pNode62);
        BinaryTreeNode.connectTreeNodes(pNode61, pNode71, null);
        BinaryTreeNode.connectTreeNodes(pNode62, null, pNode72);

        test("Test6", pNode1);
    }
}
