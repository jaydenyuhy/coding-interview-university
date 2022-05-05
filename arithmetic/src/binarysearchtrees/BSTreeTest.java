package binarysearchtrees;

public class BSTreeTest {

    private static final int arr[] = {1, 5, 4, 3, 2, 6};
//    private static final int arr[] = {1, 5, 4, 3, 2};

    public static void main(String[] args) {
        int i, ilen;
        BSTree<Integer> tree = new BSTree<Integer>();

        System.out.print("== 依次添加: ");
        ilen = arr.length;
        for (i = 0; i < ilen; i++) {
            System.out.print(arr[i] + " ");
            tree.insert(arr[i]);
        }

        System.out.println("== 前序遍历: ");
        tree.preOrder();

        System.out.println("== 中序遍历: ");
        tree.inOrder();

        System.out.println("== 后序遍历: ");
        tree.postOrder();
        System.out.println();

        System.out.println("== 最小值: " + tree.getMin());
        System.out.println("== 最大值: " + tree.getMax());
        System.out.println("== 树的详细信息: ");
        tree.print();

        System.out.println("== 删除根节点: " + arr[1]);
        tree.deleteValue(arr[1]);

        System.out.println("== 中序遍历: ");
        tree.inOrder();
        System.out.println();

        System.out.println("tree height: " + tree.getHeight());

        System.out.println("tree 6的后继者: " + tree.getSuccessor(6));

        // 销毁二叉树
        tree.deleteTree();
    }
}