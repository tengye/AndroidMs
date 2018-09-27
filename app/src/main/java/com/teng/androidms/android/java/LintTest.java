package com.teng.androidms.android.java;


public class LintTest {
    // 链表反转

    private Node revertNode(Node header){

        if (header == null || header.next ==null){
            return header;
        }

        Node trail = revertNode(header.next);
        trail.next.next = header;
        header.next = null;
        return trail;
    }

}
