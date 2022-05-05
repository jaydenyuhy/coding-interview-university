package binarysearchtrees;

public interface ITree<E> {

    /**
     * insert value into tree
     * @param e
     */
    void insert(E e);

    /**
     * @return get count of values stored
     */
    int getNodeCount();

    /**
     * prints the values in the tree, from min to max
     */
    void printValues();

    void deleteTree();

    /**
     * @param e
     * @return returns true if given value exists in the tree
     */
    boolean isInTree(E e);

    /**
     * @return returns the height in nodes (single node's height is 1)
     */
    int getHeight();

    /**
     * @return returns the minimum value stored in the tree
     */
    E getMin();

    /**
     * @return returns the maximum value stored in the tree
     */
    E getMax();

    boolean isBinarySearchTree();

    void deleteValue(E e);

    /**
     * @return returns next-highest value in tree after given value, null if none
     */
    E getSuccessor(E e);

}
