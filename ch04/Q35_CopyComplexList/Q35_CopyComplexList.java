package CodingInterviewChinese2.ch04.Q35_CopyComplexList;

/**
 * Created by lj1218.
 * Date: 2019/8/31
 *
 * Page: 187
 * 题目：请实现函数ComplexListNode* Clone(ComplexListNode* pHead)，复
 * 制一个复杂链表。在复杂链表中，每个结点除了有一个m_pNext指针指向下一个
 * 结点外，还有一个m_pSibling 指向链表中的任意结点或者nullptr。
 */
public class Q35_CopyComplexList {

    public static void main(String[] args) {
        Test.main();
    }

    public static <E> ComplexListNode<E> clone(ComplexListNode<E> head) {
        cloneNodes(head);
        connectSiblingNodes(head);
//        return reconnectNodes(head);
        return reconnectNodesInBook(head);
    }

    private static <E> void cloneNodes(ComplexListNode<E> head) {
        ComplexListNode<E> node = head;
        while (node != null) {
            ComplexListNode<E> clonedNode = new ComplexListNode<>(node.getValue());
            clonedNode.setNext(node.getNext());
            node.setNext(clonedNode);
            node = clonedNode.getNext();
        }
    }

    private static <E> void connectSiblingNodes(ComplexListNode<E> head) {
        ComplexListNode<E> node = head;
        while (node != null) {
            ComplexListNode<E> clonedNode = node.getNext();
            if (node.getSibling() != null) {
                clonedNode.setSibling(node.getSibling().getNext());
            }
            node = clonedNode.getNext();
        }
    }

    private static <E> ComplexListNode<E> reconnectNodes(ComplexListNode<E> head) {
        ComplexListNode<E> node = head;
        ComplexListNode<E> nodeTail = head;
        ComplexListNode<E> clonedHead = null;
        ComplexListNode<E> clonedTail = null;
        while (node != null) {
            ComplexListNode<E> clonedNode = node.getNext();
            if (nodeTail != head) {
                nodeTail.setNext(node);
            }
            nodeTail = node;

            if (clonedHead == null) {
                clonedHead = clonedNode;
            } else {
                clonedTail.setNext(clonedNode);
            }
            clonedTail = clonedNode;

            node = clonedNode.getNext();
        }

        return clonedHead;
    }

    private static <E> ComplexListNode<E> reconnectNodesInBook(ComplexListNode<E> head) {
        ComplexListNode<E> node = head;
        ComplexListNode<E> clonedHead = null;
        ComplexListNode<E> clonedNode = null;

        if (node != null) {
            clonedHead = clonedNode = node.getNext();
            node.setNext(clonedNode.getNext());
            node = node.getNext();
        }

        while (node != null) {
            clonedNode.setNext(node.getNext());
            clonedNode = clonedNode.getNext();
            node.setNext(clonedNode.getNext());
            node = node.getNext();
        }

        return clonedHead;
    }
}

class ComplexListNode<E> {
    private E value;
    private ComplexListNode<E> next;
    private ComplexListNode<E> sibling;

    public ComplexListNode(E value) {
        this.value = value;
        next = sibling = null;
    }

    public E getValue() {
        return value;
    }

    public ComplexListNode<E> getNext() {
        return next;
    }

    public void setNext(ComplexListNode<E> next) {
        this.next = next;
    }

    public ComplexListNode<E> getSibling() {
        return sibling;
    }

    public void setSibling(ComplexListNode<E> sibling) {
        this.sibling = sibling;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    public static <E> void buildNodes(
            ComplexListNode<E> node, ComplexListNode<E> next, ComplexListNode<E> sibling
    ) {
        node.setNext(next);
        node.setSibling(sibling);
    }

    public static <E> void printList(ComplexListNode<E> head) {
        ComplexListNode<E> node = head;
        while (node != null) {
            System.out.printf("The value of this node is: %s.\n", node);

            if (node.getSibling() != null) {
                System.out.printf("The value of its sibling is: %s.\n", node.getSibling());
            } else {
                System.out.println("This node does not have a sibling.");
            }
            System.out.println();

            node = node.getNext();
        }
        System.out.println();
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
    private static <E> void test(String testName, ComplexListNode<E> pHead) {
        if (testName != null) {
            System.out.printf("========= %s begins: =========\n", testName);
        }

        System.out.println("The original list is:");
        ComplexListNode.printList(pHead);

        ComplexListNode<E> clonedHead = Q35_CopyComplexList.clone(pHead);

        System.out.println("The cloned list is:");
        ComplexListNode.printList(clonedHead);
        System.out.println();
    }

    //          -----------------
    //         \|/              |
    //  1-------2-------3-------4-------5
    //  |       |      /|\             /|\
    //  --------+--------               |
    //          -------------------------
    private static void Test1() {
        ComplexListNode<Integer> pNode1 = new ComplexListNode<>(1);
        ComplexListNode<Integer> pNode2 = new ComplexListNode<>(2);
        ComplexListNode<Integer> pNode3 = new ComplexListNode<>(3);
        ComplexListNode<Integer> pNode4 = new ComplexListNode<>(4);
        ComplexListNode<Integer> pNode5 = new ComplexListNode<>(5);

        ComplexListNode.buildNodes(pNode1, pNode2, pNode3);
        ComplexListNode.buildNodes(pNode2, pNode3, pNode5);
        ComplexListNode.buildNodes(pNode3, pNode4, null);
        ComplexListNode.buildNodes(pNode4, pNode5, pNode2);

        test("Test1", pNode1);
    }

    // m_pSibling指向结点自身
    //          -----------------
    //         \|/              |
    //  1-------2-------3-------4-------5
    //         |       | /|\           /|\
    //         |       | --             |
    //         |------------------------|
    private static void Test2() {
        ComplexListNode<Integer> pNode1 = new ComplexListNode<>(1);
        ComplexListNode<Integer> pNode2 = new ComplexListNode<>(2);
        ComplexListNode<Integer> pNode3 = new ComplexListNode<>(3);
        ComplexListNode<Integer> pNode4 = new ComplexListNode<>(4);
        ComplexListNode<Integer> pNode5 = new ComplexListNode<>(5);

        ComplexListNode.buildNodes(pNode1, pNode2, null);
        ComplexListNode.buildNodes(pNode2, pNode3, pNode5);
        ComplexListNode.buildNodes(pNode3, pNode4, pNode3);
        ComplexListNode.buildNodes(pNode4, pNode5, pNode2);

        test("Test2", pNode1);
    }

    // m_pSibling形成环
    //          -----------------
    //         \|/              |
    //  1-------2-------3-------4-------5
    //          |              /|\
    //          |               |
    //          |---------------|
    private static void Test3() {
        ComplexListNode<Integer> pNode1 = new ComplexListNode<>(1);
        ComplexListNode<Integer> pNode2 = new ComplexListNode<>(2);
        ComplexListNode<Integer> pNode3 = new ComplexListNode<>(3);
        ComplexListNode<Integer> pNode4 = new ComplexListNode<>(4);
        ComplexListNode<Integer> pNode5 = new ComplexListNode<>(5);

        ComplexListNode.buildNodes(pNode1, pNode2, null);
        ComplexListNode.buildNodes(pNode2, pNode3, pNode4);
        ComplexListNode.buildNodes(pNode3, pNode4, null);
        ComplexListNode.buildNodes(pNode4, pNode5, pNode2);

        test("Test3", pNode1);
    }

    // 只有一个结点
    private static void Test4() {
        ComplexListNode<Integer> pNode1 = new ComplexListNode<>(1);
        ComplexListNode.buildNodes(pNode1, null, pNode1);

        test("Test4", pNode1);
    }

    // 鲁棒性测试
    private static void Test5() {
        test("Test5", null);
    }
}
