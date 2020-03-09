package com.chm.algorithms.bagstackqueue;

import java.util.Iterator;

/**
 * Author:meice Huang
 * Time: 2020/3/8 上午1:27
 */
public class Bag<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
    }

    private Node first;//链表的首节点

    public void add(Item item) {
        /**
         * 我的实现
         */
/*
        Node node = new Node();
        node.item = item;
        first.next = node;
*/
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {

        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
