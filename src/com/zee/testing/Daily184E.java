package com.zee.testing;

// http://www.reddit.com/r/dailyprogrammer/comments/2j5929/10132014_challenge_184_easy_smart_stack_list/
public class Daily184E {
    public static void main(String[] args) {
        Daily184E d = new Daily184E();
        d.push(8); d.push(6); d.push(9); d.push(4); d.push(3); d.push(10);
        //d.pop(); d.pop();
        d.removeGreater(7);
        d.printStack();
    }

    private static class LinkedItem {
        private int item;
        private LinkedItem previousStackItem;

        public LinkedItem(int item) {
            this(item, null);
        }

        public LinkedItem(int item, LinkedItem previousStackItem) {
            this.item = item;
            this.previousStackItem = previousStackItem;
        }
    }

    private LinkedItem topItem;

    public  Daily184E() {}

    public void push(int item) {
        this.topItem = new LinkedItem(item, this.topItem);
    }

    public int pop() {
        int item = this.topItem.item;
        this.topItem = this.topItem.previousStackItem;
        return item;
    }

    public void removeGreater(int item) {
        LinkedItem previous = null;
        LinkedItem current = this.topItem;
        while (current != null) {
            if (current.item > item) {
                if (previous == null) {
                    // topmost item needs removal.
                    this.topItem = current.previousStackItem;
                    previous = current;
                }
                else {
                    // unlinking current.
                    previous.previousStackItem = current.previousStackItem;
                }
            }
            else {
                previous = current;
            }
            current = current.previousStackItem;
        }
    }

    public void printStack() {
        LinkedItem current = this.topItem;
        while (current != null) {
            System.out.format("%d ", current.item);
            current = current.previousStackItem;
        }
    }
}