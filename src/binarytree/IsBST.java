package binarytree;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 */
public class IsBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            val = v;
        }
    }

    public static class Info {
        boolean isBst;
        int min;
        int max;

        public Info(boolean isBst, int min, int max) {
            this.isBst = isBst;
            this.min = min;
            this.max = max;
        }
    }

    public boolean isValidBST(TreeNode root) {
        Info info = process(root);
        return info != null && info.isBst;
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);
        boolean isBst = true;
        int val = root.val;
        int min = val;
        int max = val;
        if (left != null) {
            if (!left.isBst || left.max >= val) {
                isBst = false;
            }
            min = Math.min(min, left.min);
            max = Math.max(max, left.max);
        }
        if (right != null) {
            if (!right.isBst || right.min <= val) {
                isBst = false;
            }
            min = Math.min(min, right.min);
            max = Math.max(max, right.max);
        }
        return new Info(isBst, min, max);
    }
}
