package com.teng.androidms.android.java.dataStructure;

import java.util.HashMap;

/**
 * hashMap解读
 */
public class HashMapDecode {

    /**
     * 默认的初始化长度 为减小hashcode碰撞 和空间的浪费造成的更大的碰撞率 必须为2的n次
     * 这个长度不同的SDK版本不同，SDK25是4  、 SDK27 28是16
     * @see {why_hashMap_length_is_n_2_is_n_2.png}
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * 最大的容量
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     *  默认加载因子0.75   达到 length * DEFAULT_LOAD_FACTOR 长度时进行扩容
     *  0.75 是时间与空间的一个折中
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 链表长度大于 8 时，有可能会转化成树。需要与当前总的数量比较，大于这个长度才会变为树
     * 8是根据泊松分布来确定的，负载因子为0.75时，碰撞的概率几乎为0，
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * 小于6这个长度时，从树变成链表
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * 当链表长度为8时，需要判断容器内长度有没有达到64，来判断是否把链表变成树
     */
    static final int MIN_TREEIFY_CAPACITY = 64;


    // Node 用来表示链表 的数据结构

    // TreeNode 用来表示二叉树的数据结构  继承自Node

    /**
     * get方法
     */
    private Object get(int hashCode, String key) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        // 首先从table中获取获取第一个元素，判断是否是否相等，相等，直接返回第一个value

        // 不相等的话，如果还有下个节点 判断头结点，1、是链表，遍历寻找 2、是树， 从根节点开始找
        return new Object();
    }

    /**
     * put方法
     *
     * int hash = (key == null) ? 0 : sun.misc.Hashing.singleWordWangJenkinsHash(key);
     */
    private void put(Object key, Object value) {
        // 1、table为空或者长度为0   resize()
        // 2、根据 index = （长度-1) & hash  查看table对应位置是否为null, 为空时创建一个节点并赋值
        // 3、否则 当前节点已经有数据，发生了碰撞
        // 4、当前节点为树， 在树节点下继续加其他节点
        // 5、当前节点为链表，在链表后插入一个节点，然后判断是否达到 8
        // 6、达到8的话，继续判断当前table是否达到64，没有的话，resize()
        // 7、达到64的话，将链表转换为二叉树
    }

    /**
     * resize方法
     */
    private void resize() {
        // 1、先扩容
        // 2、创建新的table数组
        // 3、遍历每一个节点
        // 4、如果节点下没有next, 只有一个节点，直接赋值
        // 5、如果是二叉树，将二叉树拆解，重新赋值
        // 6、如果是链表，遍历重新赋值

    }

    private void remove() {
        // 1、先找到这个节点 tab[index = (n - 1) & hash]

        // 2、判断节点类型，根据不同的类型删除节点
    }


}
