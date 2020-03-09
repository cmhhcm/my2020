package com.chm.algorithms.bagstackqueue;

import java.util.Iterator;

/**
 * Author:meice Huang
 * Time: 2020/3/8 上午1:10
 * 算法1.3 先进先出队列
 */
public class Queue<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    private Node first;//指向最早添加的节点的连接
    private Node last;//指向最近添加的节点的连接
    private int N;//队列中元素数量

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void enqueue(Item item) {
        //向表尾添加元素
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        //从表头删除元素
        Item item = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        N--;
        return item;

    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    /**
     * 1、first = first.next 非常好，只用变更顺序
     */
}
