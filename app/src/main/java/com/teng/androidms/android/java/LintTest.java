package com.teng.androidms.android.java;


import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class LintTest {

//    public static void main(String[] arg) {
//        /***********  链表反转  ************/
//        revertNodeTest();
//
//        /***********  链表是否成环  ************/
//        circleNodeTest();
//
//        /***********  递归  ************/
//        System.out.print("\n10层台阶的走法有：" + goRecursion(10) + "种");
//
//        /********** 9*9 **************/
//        print99();
//
//        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
//        int[][] temp = change(matrix);
//        for (int i = 0; i < temp.length; i++) {
//            for (int j = 0; j < temp[0].length; j++) {
//                System.out.print(temp[i][j] + "\t");
//            }
//            System.out.println();
//        }
//
//        System.out.print(getNumberPosition(123456, 5));
//
//    }

    public static void main(String[] arg) {
//        Solution2 solution = new Solution2();
//        List decodeString = solution.restoreIpAddresses("25525511135");


//        Solution solution1 = new Solution();
//        String s = solution1.decodeString("3[aaa]2[b4[c]]");
//        System.out.println(decodeString);
//        reverse(-123);
//        reverseBits(43261596);

        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        listNode1.next = listNode2;
        ListNode listNode4 = new ListNode(4);
        listNode2.next = listNode3;
        ListNode listNode5 = new ListNode(5);
        listNode3.next = listNode4;
        listNode4.next = listNode5;
//        ListNode listNode1 = new ListNode(1);

//        reorderList(listNode1);

//        findComplement(5); // 101   0101  0111

//        sortedArrayToBST(new int[]{-10,-3,0,5,9});

        TreeNode root = new TreeNode(1);

        TreeNode oneRight = new TreeNode(2);

        root.right = oneRight;
        root.left = new TreeNode(4);

//        TreeNode twoLeft = new TreeNode(3);
//        oneRight.left = twoLeft;

//        inorderTraversal(root);
        diameterOfBinaryTree(root);

    }


    static int res = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return res;
    }

    public static int depth(TreeNode root) {
        if (root == null) return 0;

        int leftDep = depth(root.left);
        int rightDep = depth(root.right);

        res = Math.max(res , leftDep + rightDep);

        return Math.max(leftDep, rightDep) + 1;
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int centerIndex = nums.length / 2;

        TreeNode root = new TreeNode(nums[centerIndex]);
        root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, centerIndex));
        root.right = sortedArrayToBST(Arrays.copyOfRange(nums, centerIndex + 1, nums.length));

        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        TreeNode cru = root;
        while(cru != null || !stack.isEmpty()) {
            while(cru != null) {
                stack.add(cru);
                cru = cru.left;
            }

            cru = stack.pop();

            list.add(cru.val);

            cru = cru.right;

        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        LinkedList<Integer> list = new LinkedList<>();
        if (root == null) {
            return list;
        }
        stack.add(root);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();

            list.addFirst(node.val);

            if (node.left != null) {
                stack.add(node.left);
            }

            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return list;
    }


    public static int findComplement(int num) {
        int tmp = num;
        int num2 = 1;
        while (tmp > 0) {
            num2 <<= 1;
            tmp >>= 1;
        }
        num2 -= 1;
        int i = num ^ num2;
        return i;

    }

    private static void circleNodeTest() {
        Node header = new Node(1);
        Node header1 = new Node(2);
        Node header2 = new Node(3);
        Node header3 = new Node(4);

        header.next = header1;
        header1.next = header2;
        header2.next = header3;
        header3.next = header1;
        int circleNumber = linkCircleNumber(header);
        System.out.print("\n链表是否成环：" + (circleNumber == 0 ? "否" : ("是, 环的长度是：" + circleNumber)));
    }

    private static void revertNodeTest() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Node a = revertWhileNode(node1);
        // Node a = revertNode(node1);
        System.out.print("\n链表反转的结果：");

        while (a != null) {
            System.out.print(a.value);
            a = a.next;
        }
    }

    /**
     * 链表反转 递归
     */
    private static Node revertNode(Node header) {
        if (header == null || header.next == null) {
            return header;
        }
        Node trail = revertNode(header.next);
        // 每次都反转header.next  所以需要给 header.next.next重新设值
        header.next.next = header;
        header.next = null;
        return trail;
    }

    /**
     * 链表反转 遍历
     */
    private static Node revertWhileNode(Node header) {
        Node prev = null;
        while (header != null) {
            // 先保存后面的数据  temp 2345    345
            Node tmp = header.next;
            // header  1 null      2  1 null
            header.next = prev;
            // prev 1 null    2 1 null
            prev = header;
            // 2345   345
            header = tmp;
        }
        return prev;
    }


    private static Node slowNode, twoNode;

    /**
     * 判断链表是否成环，并返回环的大小
     * 思路：两个人跑400米操场，A跑的快的是B的两倍速度， 第一次相遇在200米的时候，第二次相遇就是400的时候，这时候A跑了800米 B跑了400米
     * 所以第二次相遇的时候就是环的长度
     */
    private static int linkCircleNumber(Node header) {
        int visitCount = 0;
        int stepCount = 0;
        int firstVisitStep = 0;
        int secondVisitStep = 0;

        slowNode = header;
        twoNode = header;

        while (visitCount < 2) {
            if (!goOneStep() || !goTwoStep()) {
                // 只要能走完就说明没有成环
                break;
            }
            stepCount++;
            if (slowNode == twoNode) {
                // 相遇
                visitCount++;
                if (visitCount == 1) {
                    firstVisitStep = stepCount;
                } else if (visitCount == 2) {
                    secondVisitStep = stepCount;
                }
            }
        }
        return secondVisitStep - firstVisitStep;
    }

    private static boolean goOneStep() {
        if (slowNode == null || slowNode.next == null) {
            return false;
        }
        slowNode = slowNode.next;
        return true;
    }

    private static boolean goTwoStep() {
        if (twoNode == null || twoNode.next == null || twoNode.next.next == null) {
            return false;
        }
        twoNode = twoNode.next.next;
        return true;
    }

    /**
     * n层台阶，每次走一步或者两步，有多少种走法
     */
    private static int goRecursion(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        return goRecursion(n - 1) + goRecursion(n - 2);
    }

    /**
     * 判断一个数字是不是回文
     */
    private static void huiwenNumber(int num) {

    }

    /**
     * 99乘法表 一个遍历，一个变量
     */
    static void print99() {
        for (int a = 11; a <= 99; ) {
            System.out.print((a % 10) + "*" + (a / 10) + "=" + (a % 10) * (a / 10));
            if ((a % 10) >= (a / 10)) {
                a = (a / 10 + 1) * 10 + 1;
                System.out.print(a + "   \n");
            } else {
                a++;
                System.out.print("\t");
            }
        }
    }

    // 矩阵顺时针旋转180
    public static int[][] change(int[][] matrix) {
        int[][] temp = new int[matrix[0].length][matrix.length];
        int dst = matrix.length - 1;
        for (int i = 0; i < matrix.length; i++, dst--) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.println(temp[j][dst] + " -- " + matrix[i][j]);
                temp[j][dst] = matrix[i][j];
            }
        }
        return temp;
    }


//    boolean numberIsHuiWen(int number) {
//
//        for (int i = 0; i < ; i++) {
//
//        }
//
//    }


    static int getNumberPosition(int number, int pos) {
        double v = number / Math.pow(10, pos) % 10;
        return (int) v;
    }


    //
    static class Solution {
        public String decodeString(String s) {
            Stack<Integer> numStack = new Stack<Integer>();
            Stack<String> sbStack = new Stack<String>();
            StringBuilder sb = new StringBuilder();
            int mulit = 0;
            char[] charArray = s.toCharArray();

            for (int i = 0; i < charArray.length; i++) {
                char currentChar = charArray[i];
                if ('[' == currentChar) {
                    numStack.add(mulit);
                    sbStack.add(sb.toString());
                    mulit = 0;
                    sb = new StringBuilder();
                } else if (']' == currentChar) {
                    StringBuilder builder = new StringBuilder();
                    int num = numStack.pop();
                    for (int j = 0; j < num; j++) {
                        builder.append(sb.toString());
                    }
                    sb = new StringBuilder(sbStack.pop() + builder.toString());
                } else if (currentChar >= '0' && currentChar <= '9') {
                    mulit = mulit * 10 + Integer.parseInt(currentChar + "");
                } else {
                    sb.append(currentChar);
                }
            }
            return sb.toString();
        }
    }


    static class Solution1 {
        int[] index = new int[3];
        int n;
        List<String> ret;
        String s;

        public List<String> restoreIpAddresses(String s) {
            n = s.length();
            ret = new ArrayList<String>();
            this.s = s;
            if (n < 4 || n > 12) {
                return ret;
            }

            f(0, 3);
            return ret;
        }

        void f(int startIndex, int insertCount) {
            System.out.println("insertCount:" + insertCount);
            if (insertCount == 0) {// 结束，找到了最优解
                if (startIndex <= n - 1 && Long.parseLong(s.substring(startIndex)) < 256) {
                    StringBuilder sb = new StringBuilder();
                    String temp1 = s.substring(0, index[0]);
                    String temp2 = s.substring(index[0], index[1]);
                    String temp3 = s.substring(index[1], index[2]);
                    String temp4 = s.substring(index[2]);
                    if (temp4.length() > 1 && temp4.startsWith("0")) {
                        return;
                    }
                    sb.append(temp1).append(".").append(temp2).append(".").append(temp3).append(".").append(temp4);
                    ret.add(sb.toString());
                }
                return;
            }
            for (int i = startIndex + 1; i < startIndex + 4; i++) {
                if (i > n - 1) {
                    return;
                }
                String temp = s.substring(startIndex, i);
                if (temp.length() > 1 && temp.startsWith("0")) {
                    return;
                }
                if (Long.parseLong(temp) < 256) {
                    index[3 - insertCount] = i;
                    f(i, insertCount - 1);
                }
            }
            System.out.print("index: ");
            for (int i = 0; i < index.length; i++) {
                System.out.print(i + index[i]);
            }
            System.out.println();

        }
    }


    static class Solution2 {
        int n;
        String s;
        ArrayList<String> output = new ArrayList<>();
        LinkedList<String> segments = new LinkedList<>();

        public List<String> restoreIpAddresses(String s) {
            this.s = s;
            n = s.length();
            if (n < 4 || n > 12) {
                return output;
            }
            backTrack(-1, 3);
            return output;
        }

        // startIndex：从哪个位置开始找数字
        // leftNum: 还需要插入几个点，这个里一共需要插入三个点
        void backTrack(int startIndex, int leftNum) {
            // startIndex表示前一个放置的点的位置   end表示 当前可以放置的点的位置  前一个点 + 4   最多三个数
            int end = Math.min(startIndex + 4, n - 1);
            // 从0开始

            // 第一次放置的时候，可以放置 0 - 2, 最大为三位数  0、1、2 的时候分别开始向后递归
            for (int i = startIndex + 1; i < end; i++) {
                String segment = s.substring(startIndex + 1, i + 1);
                if (isVaild(segment)) {
                    segments.add(segment);
                    // 三个点插完就检测最后一个数， 最后一个数通过就保存
                    if (leftNum - 1 == 0) {
                        saveOutput(i);
                    } else {
                        backTrack(i, leftNum - 1);
                    }
                    // 不满足的情况下回溯
                    System.out.println(segments);
                    segments.removeLast();
                }
            }
        }

        void saveOutput(int index) {
            String segment = s.substring(index + 1, n);
            if (isVaild(segment)) {
                StringBuilder sb = new StringBuilder();
                segments.add(segment);
                output.add(sb.append(segments.get(0)).append(".").append(segments.get(1)).append(".").append(segments.get(2)).append(".").append(segments.get(3)).toString());
                System.out.println(segments);
                segments.removeLast();
            }
        }

        boolean isVaild(String segment) {
            if (segment == null || segment == "" || segment.length() > 3) {
                return false;
            }
            if ((segment.length() > 1 && segment.charAt(0) == '0') || Integer.parseInt(segment) > 255) {
                return false;
            }
            return true;
//            HashMap<String, String> hashMap = new HashMap<>();
//            hashMap.containsKey()
        }
    }

    public static int reverse(int x) {
        int y = 0;
        while (x != 0) {
            if (y > Integer.MAX_VALUE / 10 || y < Integer.MIN_VALUE / 10) {
                return 0;
            }
            y = y * 10 + x % 10;
            x = x / 10;
            System.out.println(y + " -------- " + x);
//            System.out.println(Integer.MIN_VALUE + " -------- " + Integer.MAX_VALUE);
        }
        return y;
    }

    public int bitwiseComplement(int n) {
        int res = 0;
        for (int i = 31; n > 0; n = n >>> 1, i--) {
            // if ((1 & n << 1) != 1) {
            res += (n & 0) << i;
            // res += 2 * res;
            // }
        }
        return res;
    }


    public static int reverseBits(int n) {
        int ans = 0;
        for (int bitsSize = 31; n != 0; n = n >>> 1, bitsSize--) {
//            System.out.println("n ==== " + n);
            ans += (n & 1) << bitsSize;
            System.out.println(((n & 1) << bitsSize) + " ----- " + (n & 1));
        }
        return ans;

    }

//    WeakReference weakReference = new WeakReference<Node>(new Node(1), new ReferenceQueue());


    public static void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }


        // ListNode last = null;
        // if(fast.next != null) {
        //     last = slow.next;
        //     last.next = null;
        // }

        ListNode next = slow.next;
        slow.next = null;

        ListNode prev = null;
        while (next != null) {
            ListNode temp = next.next;
            next.next = prev;
            prev = next;
            next = temp;
        }

        ListNode newPreHead = new ListNode(-1);
        ListNode newPre = newPreHead;
        while (prev != null && head != null) {
            newPre.next = head;
            head = head.next;
            newPre.next.next = prev;
            prev = prev.next;
            newPre = newPre.next.next;
        }

        System.out.println(newPreHead.next);
        // newPre.next = (prev == null ? head : prev);

        // newPre.next = last;

        // return newPreHead.next;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
