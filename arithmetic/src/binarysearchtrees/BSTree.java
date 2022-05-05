package binarysearchtrees;

public class BSTree<E extends Comparable<E>> implements ITree<E> {

    BSTNode<E> mRoot;

    @Override
    public void insert(E key) {
        BSTNode<E> z = new BSTNode<>(key, null, null, null);
        insert(this, z);
    }

    @Override
    public int getNodeCount() {
        return nodeCount(mRoot);
    }

    private int nodeCount(BSTNode<E> node){
        return 1 + nodeCount(node.left) + nodeCount(node.right);
    }

    @Override
    public void printValues() {
        preOrder();
    }

    @Override
    public void deleteTree() {
        destroy(mRoot);
        mRoot = null;
    }

    private void destroy(BSTNode<E> tree) {
        if (tree == null) {
            return;
        }
        destroy(tree.left);
        destroy(tree.right);
        tree = null;
    }

    @Override
    public boolean isInTree(E o) {
        return search(o, mRoot) != null;
    }

    private BSTNode<E> search(E o, BSTNode<E> tree) {
        if (tree != null) {
            int cmp = o.compareTo(tree.key);
            if (cmp < 0) {
                return search(o, tree.left);
            } else if (cmp > 0) {
                return search(o, tree.right);
            } else {
                return tree;
            }
        }
        return null;
    }

    @Override
    public int getHeight() {
        return height(mRoot);
    }

    private int height(BSTNode<E> node){
        if(node == null){
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    @Override
    public E getMin() {
        BSTNode<E> x = mRoot;
        while (x != null && x.left != null) {
            x = x.left;
        }
        return x == null ? null : x.key;
    }

    @Override
    public E getMax() {
        BSTNode<E> x = mRoot;
        while (x != null && x.right != null) {
            x = x.right;
        }
        return x == null ? null : x.key;
    }

    @Override
    public boolean isBinarySearchTree() {
        return false;
    }

    @Override
    public void deleteValue(E o) {
        BSTNode<E> node = search(o, mRoot);
        if (node != null) {
            remove(this, node);
        }
    }

    @Override
    public E getSuccessor(E o) {
        BSTNode<E> node = search(o, mRoot);
        if(node != null){
            node = successor(node);
        }
        return node == null ? null : node.key;
    }

    private BSTNode<E> successor(BSTNode<E> node) {
        // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (node.right != null) {
            return minimum(node.right);
        }

        // 如果x没有右孩子。则x有以下两种可能：
        // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"
        // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        BSTNode<E> y = node.parent;
        while (y != null && y.right == node) {
            node = y;
            y = y.parent;
        }
        return y;
    }

    /*
     * 找结点(x)的前驱结点。即，查找"二叉树中数据值小于该结点"的"最大结点"。
     */
    public BSTNode<E> predecessor(BSTNode<E> node) {
        // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (node.left != null) {
            return maximum(node.left);
        }

        // 如果x没有左孩子。则x有以下两种可能：
        // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
        BSTNode<E> y = node.parent;
        while (y != null && node == y.left) {
            node = y;
            y = y.parent;
        }

        return y;
    }

    private BSTNode<E> minimum(BSTNode<E> tree) {
        if (tree == null) {
            return null;
        }
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    private BSTNode<E> maximum(BSTNode<E> tree) {
        if (tree == null) {
            return null;
        }
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree;
    }

    private void preOrder(BSTNode<E> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    //前序遍历
    public void preOrder() {
        preOrder(mRoot);
    }

    private void inOrder(BSTNode<E> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    //中序遍历
    public void inOrder() {
        inOrder(mRoot);
    }

    private void postOrder(BSTNode<E> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    //后序遍历
    public void postOrder() {
        postOrder(mRoot);
    }

    private void insert(BSTree<E> bst, BSTNode<E> z) {
        int cmp;
        BSTNode<E> y = null;
        BSTNode<E> x = bst.mRoot;

        // 查找z的插入位置
        while (x != null) {
            y = x;
            cmp = z.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }

        z.parent = y;
        if (y == null)
            bst.mRoot = z;
        else {
            cmp = z.key.compareTo(y.key);
            if (cmp < 0)
                y.left = z;
            else
                y.right = z;
        }
    }

    private BSTNode<E> remove(BSTree<E> bst, BSTNode<E> z) {
        BSTNode<E> x = null;
        BSTNode<E> y = null;
        if (z.left == null || z.right == null) {
            y = z;
        } else {
            y = successor(z);
        }

        if (y.left != null) {
            x = y.left;
        } else {
            x = y.right;
        }

        if (x != null) {
            x.parent = y.parent;
        }

        if (y.parent == null) {
            bst.mRoot = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        if (y != z) {
            z.key = y.key;
        }
        return y;
    }

    /*
     * 打印"二叉查找树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(BSTNode<E> tree, E key, int direction) {

        if (tree != null) {

            if (direction == 0) {
                // tree是根节点
                System.out.printf("%2d is root\n", tree.key);
            } else {
                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");
            }

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (mRoot != null) {
            print(mRoot, mRoot.key, 0);
        }
    }
}
