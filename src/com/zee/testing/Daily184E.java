package com.zee.testing;

import sun.awt.image.ImageWatched;

import java.util.function.Function;

// http://www.reddit.com/r/dailyprogrammer/comments/2j5929/10132014_challenge_184_easy_smart_stack_list/
public class Daily184E {
    public static void main(String[] args) {
        Daily184E d = new Daily184E();
        d.push(8); d.push(6); d.push(9); d.push(4); d.push(3); d.push(10);
        //d.pop(); d.pop();
        d.removeGreater(5);
        d.printStack();
        System.out.println();
        d.printOrdered();
    }

    private static class LinkedItem {
        private int item;
        private LinkedItem previousStackItem;

        // prev and next are tracked so that push and pop (mainly this) can be fast.
        private LinkedItem previousOrderedItem;
        private LinkedItem nextOrderedItem;

        public LinkedItem(int item) {
            this(item, null);
        }

        public LinkedItem(int item, LinkedItem previousStackItem) {
            this.item = item;
            this.previousStackItem = previousStackItem;
        }
    }

    private LinkedItem topStackItem;
    private LinkedItem smallestItem;

    public  Daily184E() {}

    public void push(int item) {
        this.topStackItem = new LinkedItem(item, this.topStackItem);

        // order the pushed item
        LinkedItem previous = null;
        LinkedItem current = this.smallestItem;
        // first, find the slot it belongs in
        while (current != null && current.item < item) {
            previous = current;
            current = current.nextOrderedItem;
        }

        this.topStackItem.previousOrderedItem = previous;
        this.topStackItem.nextOrderedItem = current;

        // fix links of the items that are before and after it.
        if (current != null) {
            current.previousOrderedItem = this.topStackItem;
        }

        if (previous != null) {
            previous.nextOrderedItem = this.topStackItem;
        }
        else {
            // new item is the smallest!
            this.smallestItem = this.topStackItem;
        }
    }

    public int pop() {
        // fix ascending order; the next smaller and next greater have to be linked with each
        // // other when the topmost item is removed.
        LinkedItem current = this.topStackItem;
        LinkedItem smaller = current.previousOrderedItem;
        if (smaller != null) {
            smaller.nextOrderedItem = current.nextOrderedItem;
        }

        LinkedItem greater = current.nextOrderedItem;
        if (greater != null) {
            greater.previousOrderedItem = smaller;
        }

        if (this.topStackItem == this.smallestItem) {
            this.smallestItem = greater;
        }

        // fix stack order
        this.topStackItem = current.previousStackItem;
        return current.item;
    }

    public void removeGreater(int item) {
        LinkedItem previous = null;
        LinkedItem current = this.topStackItem;
        while (current != null) {
            if (current.item > item) {
                if (previous != null) {
                    previous.previousStackItem = current.previousStackItem;
                }
                else {
                    this.topStackItem = current.previousStackItem;
                }
            }
            else {
                previous = current;
            }

            current = current.previousStackItem;
        }
    }

    public void printStack() {
        this.print(this.topStackItem, c -> c.previousStackItem);
    }

    public void printOrdered() {
        this.print(this.smallestItem, c -> c.nextOrderedItem);
    }

    // learning functional interfaces
    private void print(LinkedItem initial, Function<LinkedItem, LinkedItem> currentIterator) {
        LinkedItem current = initial;
        while (current != null) {
            System.out.format("%d ", current.item);
            current = currentIterator.apply(current);
        }
    }
}