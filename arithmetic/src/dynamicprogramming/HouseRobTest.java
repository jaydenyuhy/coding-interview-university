package dynamicprogramming;

import bean.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class HouseRobTest {

    public static void main(String[] args) {

        //[4,1,null,2,null,3]
        //[2,1,3,null,4] = 6
    }

    Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }

        if(memo.containsKey(root)){
            return memo.get(root);
        }

        int doIt = root.val +
                (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right)) +
                (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));

        int unDoIt = rob(root.left) + rob(root.right);

        int res = Math.max(doIt, unDoIt);
        memo.put(root, res);
        return res;
    }

//    public int rob(TreeNode root) {
//        int[] rootStatus = dfs(root);
//        return Math.max(rootStatus[0], rootStatus[1]);
//    }
//
//    public int[] dfs(TreeNode node) {
//        if (node == null) {
//            return new int[]{0, 0};
//        }
//        int[] l = dfs(node.left);
//        int[] r = dfs(node.right);
//        int selected = node.val + l[1] + r[1];
//        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
//        return new int[]{selected, notSelected};
//    }
}
