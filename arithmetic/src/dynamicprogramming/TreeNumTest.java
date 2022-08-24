package dynamicprogramming;

import bean.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TreeNumTest {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(5);
        TreeNode root2l = new TreeNode(4);
        TreeNode root2r = new TreeNode(8);
        root.left = root2l;
        root.right = root2r;
        TreeNode root31l = new TreeNode(11);
        TreeNode root32l = new TreeNode(13);
        TreeNode root32r = new TreeNode(4);
        root2l.left = root31l;
        root2l.right = null;

        root2r.left = root32l;
        root2r.right = root32r;

        root31l.left = new TreeNode(7);
        root31l.right = new TreeNode(2);
        root32r.left = new TreeNode(5);
        root32r.right = new TreeNode(1);


        System.out.println("result " + new TreeNumTest().pathSum(root, 22));
    }

    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return res;
    }
    void recur(TreeNode root, int tar) {
        if(root == null) return;
        path.add(root.val);
        tar -= root.val;
        if(tar == 0 && root.left == null && root.right == null)
            res.add(new LinkedList<>(path));
        recur(root.left, tar);
        recur(root.right, tar);
        path.removeLast();
    }



    TreeNode pre, head;
    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    void dfs(TreeNode cur) {
        if(cur == null) return;
        dfs(cur.left);
        if(pre != null) pre.right = cur;
        else head = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }
}
