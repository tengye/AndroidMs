package com.teng.androidms.android.java;

public class TwoTreeTest extends SortTest {

    // 二叉树的深度
    // 二叉树K层节点
    // 二叉树三种遍历方法


    private int deepNode(Node node) {
        int deep = 0;
        if (node != null) {
            int left = deepNode(node.left);
            int right = deepNode(node.right);
            deep = left > right ? left + 1 : right + 1;
        }
        return deep;
    }

    private int kNode(Node node, int k) {
        if (node == null || k < 0) {
            return 0;
        } else if (k == 0) {
            return 1;
        } else {
            int left = kNode(node.left, k - 1);
            int right = kNode(node.right, k - 1);
            return left + right;
        }
    }


}
