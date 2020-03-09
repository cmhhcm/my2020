package com.chm.algorithms.bagstackqueue;

import java.util.Iterator;

/**
 * Author:meice Huang
 * Time: 2020/3/8 上午12:53
 * 算法1.2 下压堆栈(链表实现)
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;//栈顶，最近添加的元素
    private int N;//元素数量

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    ;//或者N ==0

    public int size() {
        return N;
    }

    ;

    public void push(Item item) {
        //向栈顶添加元素
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        //从栈顶删除元素
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
