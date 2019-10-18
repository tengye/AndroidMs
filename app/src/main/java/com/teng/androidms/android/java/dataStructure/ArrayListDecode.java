package com.teng.androidms.android.java.dataStructure;

public class ArrayListDecode {
/**
    简单的区别：
            1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。 （LinkedList是双向链表，有next也有previous）
            2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
            3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。

    深度的区别：
            1．对ArrayList和LinkedList而言，在列表末尾增加一个元素所花的开销都是固定的。对ArrayList而言，主要是在内部数组中增加一项，指向所添加的元素，偶尔可能会导致对数组重新进行分配；而对LinkedList而言，这个开销是统一的，分配一个内部Entry对象。
            2．在ArrayList的中间插入或删除一个元素意味着这个列表中剩余的元素都会被移动；而在LinkedList的中间插入或删除一个元素的开销是固定的。
            3．LinkedList不支持高效的随机元素访问。
            4．ArrayList的空间浪费主要体现在在list列表的结尾预留一定的容量空间，而LinkedList的空间花费则体现在它的每一个元素都需要消耗相当的空间
    遍历方式区别：
        get、foreach、迭代器

        ArrayList用get是最快的， LinkedList用迭代器会好一些主要是ArrayList 实现了RandromAccess标记接口
 **/


    /**
     * 默认长度10
     */
    private static final int DEFAULT_CAPACITY = 10;



}
