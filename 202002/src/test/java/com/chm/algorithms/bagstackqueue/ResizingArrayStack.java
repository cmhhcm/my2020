package com.chm.algorithms.bagstackqueue;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Author:meice Huang
 * Time: 2020/3/8 上午12:36
 * 下压(LIFO)栈(能够动态调整数组大小的实现)
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] a = (Item[]) new Object[1];//栈元素
    private int N = 0;

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    public void push(Item item) {
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }

    public Item pop() {
        Item item = a[N--];
        a[N] = null;//避免对象游离
        if (N > 0 && N == a.length / 4) {
            resize(a.length / 2);
        }
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    @Override
    public void forEach(Consumer<? super Item> action) {

    }

    @Override
    public Spliterator<Item> spliterator() {
        return null;
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            return a[i--];
        }

        ;

        public void remove() {
        }

    }

    /**
     * 总结：
     * 1、调整了下栈的大小
     */
}
