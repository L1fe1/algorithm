package binarytree;

public class CompleteTreeNodeNumber {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            val = v;
        }
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算树高
        TreeNode cur = root;
        int height = 0;
        while (cur != null) {
            cur = cur.left;
            height ++;
        }
        return process(root, 1, height);
    }

    /**
     * 计算节点数
     * @param root 根节点
     * @param level 当前层
     * @param height 整棵树的树高
     * @return 节点数
     */
    public static int process(TreeNode root, int level, int height) {
        // 只有 1 个节点
        if (level == height) {
            return 1;
        }
        // 统计右树左边界能到达的高度
        TreeNode cur = root.right;
        int rightHeight = level;
        while (cur != null) {
            cur = cur.left;
            rightHeight ++;
        }
        if (height == rightHeight) {
            // 右树高度达到最后一层，那么左树必是满二叉树
            return (1 << (height - level)) + process(root.right, level + 1, height);
        } else {
            // 右树高度没有达到最后一层，那么右树必是满二叉树
            return (1 << (height - level - 1)) + process(root.left, level + 1, height);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        System.out.println(countNodes(root));;
    }
}
