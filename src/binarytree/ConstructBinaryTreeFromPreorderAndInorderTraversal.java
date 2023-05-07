package binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数数组 preorder 和 inorder，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int v) {
            val = v;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        // 构建中序遍历中树的节点值与索引的映射
        Map<Integer, Integer> valueMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            valueMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, len - 1, inorder, 0, len - 1, valueMap);
    }

    public TreeNode buildTree(int[] preorder, int pl, int pr,
                              int[] inorder, int il, int ir,
                              Map<Integer, Integer> valueMap) {
        // 构建根节点
        TreeNode root = new TreeNode(preorder[pl]);
        // 计算左右子树大小
        int rootIndex = valueMap.get(root.val);
        int leftTreeSize = rootIndex - il;
        int rightTreeSize = ir - rootIndex;
        // 构建左子树
        if (leftTreeSize > 0) {
            root.left = buildTree(preorder, pl + 1, pl + leftTreeSize,
                    inorder, il, il + leftTreeSize - 1, valueMap);
        }
        // 构建右子树
        if (rightTreeSize > 0) {
            root.right = buildTree(preorder, pr - rightTreeSize + 1, pr,
                    inorder, ir - rightTreeSize + 1, ir, valueMap);
        }
        return root;
    }
}
