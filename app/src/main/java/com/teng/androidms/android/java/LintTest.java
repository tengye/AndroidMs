package com.teng.androidms.android.java;


public class LintTest {

    public static void main(String[] arg) {
        /***********  链表反转  ************/
        revertNodeTest();

        /***********  链表是否成环  ************/
        circleNodeTest();

        /***********  递归  ************/
        System.out.print("\n10层台阶的走法有：" + goRecursion(10) + "种");

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
        return goRecursion(n-1) + goRecursion(n-2);
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
        for(int a = 11; a <= 99; ){
            System.out.print((a % 10) + "*" + (a / 10) + "=" + (a % 10) * (a / 10));
            if((a % 10) >= (a / 10)){
                a = (a / 10 + 1) * 10 + 1;
                System.out.print(a + "   \n");
            } else{
                a++;
                System.out.print("\t");
            }
        }
    }

}
