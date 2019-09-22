package CodingInterviewChinese2.ch07.Q68_CommonParentInTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lj1218.
 * Date: 2019/9/22
 *
 * Page: 326
 * 面试题68：树中两个结点的最低公共祖先
 * 题目：输入两个树结点，求它们的最低公共祖先。
 */
public class Q68_CommonParentInTree {

    public static void main(String[] args) {
        Test.main();
    }

    public static TreeNode<Integer> getLastCommonParent(
            TreeNode<Integer> root, TreeNode<Integer> node1, TreeNode<Integer> node2) {
        if (root == null || node1 == null || node2 == null) {
            return null;
        }

        List<TreeNode<Integer>> path1 = new LinkedList<>();
        getNodePath(root, node1, path1);

        List<TreeNode<Integer>> path2 = new LinkedList<>();
        getNodePath(root, node2, path2);

        return getLastCommonNode(path1, path2);
    }

    // 获取根节点到 node 的路径（不包含 node），路径保存到 path
    private static boolean getNodePath(
            TreeNode<Integer> root, TreeNode<Integer> node, List<TreeNode<Integer>> path) {
        if (root == node) {
            return true;
        }

        path.add(root);

        boolean found = false;

        for (TreeNode<Integer> child : root.getChildren()) {
            if (found = getNodePath(child, node, path)) {
                break;
            }
        }

        if (!found) {
            path.remove(root);
        }

        return found;
    }

    // 获取 path1、path2 中最后一个公共节点
    private static TreeNode<Integer> getLastCommonNode(
            List<TreeNode<Integer>> path1, List<TreeNode<Integer>> path2) {
        TreeNode<Integer> commonNode = null;
        Iterator<TreeNode<Integer>> it1 = path1.iterator();
        Iterator<TreeNode<Integer>> it2 = path2.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            TreeNode<Integer> n1 = it1.next();
            TreeNode<Integer> n2 = it2.next();
            if (n1 != n2) {
                break;
            }
            commonNode = n1;
        }
        return commonNode;
    }
}

// 普通树
class TreeNode<E> {
    private E value;
    private List<TreeNode<E>> children;

    public TreeNode(E value) {
        this.value = value;
        children = new LinkedList<>();
    }

    public E getValue() {
        return value;
    }

    public List<TreeNode<E>> getChildren() {
        return children;
    }

    public static <E> void connectTreeNodes(TreeNode<E> parent, TreeNode<E> child) {
        if (parent != null) {
            parent.children.add(child);
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
    }

    // ====================测试代码====================
    private static void test(
            String testName, TreeNode<Integer> root,
            TreeNode<Integer> node1, TreeNode<Integer> node2,
            TreeNode<Integer> expected) {
        if (testName == null) {
            return;
        }

        System.out.printf("%s begins: ", testName);

        TreeNode<Integer> result = Q68_CommonParentInTree.getLastCommonParent(root, node1, node2);

        if ((expected == null && result == null) ||
                (expected != null && result != null && result == expected)) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    // 形状普通的树
    //              1
    //            /    \
    //           2     3
    //        /     \
    //       4      5
    //      / \   / |  \
    //     6  7  8  9  10
    private static void Test1() {
        TreeNode<Integer> pNode1 = new TreeNode<>(1);
        TreeNode<Integer> pNode2 = new TreeNode<>(2);
        TreeNode<Integer> pNode3 = new TreeNode<>(3);
        TreeNode<Integer> pNode4 = new TreeNode<>(4);
        TreeNode<Integer> pNode5 = new TreeNode<>(5);
        TreeNode<Integer> pNode6 = new TreeNode<>(6);
        TreeNode<Integer> pNode7 = new TreeNode<>(7);
        TreeNode<Integer> pNode8 = new TreeNode<>(8);
        TreeNode<Integer> pNode9 = new TreeNode<>(9);
        TreeNode<Integer> pNode10 = new TreeNode<>(10);

        TreeNode.connectTreeNodes(pNode1, pNode2);
        TreeNode.connectTreeNodes(pNode1, pNode3);

        TreeNode.connectTreeNodes(pNode2, pNode4);
        TreeNode.connectTreeNodes(pNode2, pNode5);

        TreeNode.connectTreeNodes(pNode4, pNode6);
        TreeNode.connectTreeNodes(pNode4, pNode7);

        TreeNode.connectTreeNodes(pNode5, pNode8);
        TreeNode.connectTreeNodes(pNode5, pNode9);
        TreeNode.connectTreeNodes(pNode5, pNode10);

        test("Test1", pNode1, pNode6, pNode8, pNode2);
    }

    // 树退化成一个链表
    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void Test2() {
        TreeNode<Integer> pNode1 = new TreeNode<>(1);
        TreeNode<Integer> pNode2 = new TreeNode<>(2);
        TreeNode<Integer> pNode3 = new TreeNode<>(3);
        TreeNode<Integer> pNode4 = new TreeNode<>(4);
        TreeNode<Integer> pNode5 = new TreeNode<>(5);

        TreeNode.connectTreeNodes(pNode1, pNode2);
        TreeNode.connectTreeNodes(pNode2, pNode3);
        TreeNode.connectTreeNodes(pNode3, pNode4);
        TreeNode.connectTreeNodes(pNode4, pNode5);

        test("Test2", pNode1, pNode5, pNode4, pNode3);
    }

    // 树退化成一个链表，一个结点不在树中
    //               1
    //              /
    //             2
    //            /
    //           3
    //          /
    //         4
    //        /
    //       5
    private static void Test3() {
        TreeNode<Integer> pNode1 = new TreeNode<>(1);
        TreeNode<Integer> pNode2 = new TreeNode<>(2);
        TreeNode<Integer> pNode3 = new TreeNode<>(3);
        TreeNode<Integer> pNode4 = new TreeNode<>(4);
        TreeNode<Integer> pNode5 = new TreeNode<>(5);

        TreeNode.connectTreeNodes(pNode1, pNode2);
        TreeNode.connectTreeNodes(pNode2, pNode3);
        TreeNode.connectTreeNodes(pNode3, pNode4);
        TreeNode.connectTreeNodes(pNode4, pNode5);

        TreeNode<Integer> pNode6 = new TreeNode<>(6);

        test("Test3", pNode1, pNode5, pNode6, null);
    }

    // 输入null
    private static void Test4() {
        test("Test4", null, null, null, null);
    }
}
