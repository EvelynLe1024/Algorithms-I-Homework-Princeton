/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node { // nested class to define nodes
        Item item;
        Node next, pre;
    }

    private Node first; // front of the line, most recently added
    private Node last; // end of the line, least recently added
    private int N;

    public Deque()                            // construct an empty deque
    {
        first = null;
        last = null;
        N = 0;
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return first == null;
    }

    public int size()                        // return the number of items on the deque
    {
        return N;
    }

    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null) throw new IllegalArgumentException("null argument");
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (oldfirst != null) oldfirst.pre = first;
        N++;
    }

    public void addLast(Item item)           // add the item to the end
    {
        if (item == null) throw new IllegalArgumentException("null argument");
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (oldlast != null) {
            oldlast.next = last;
            last.pre = oldlast;
        }
        N++;
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty()) throw new NoSuchElementException("empty list");
        Item item = first.item;

        first = first.next;
        if (first != null) first.pre = null;
        N--;
        if (isEmpty()) last = null;
        return item;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty()) throw new NoSuchElementException("empty list");
        Item item = last.item;
        Node oldlast = last;
        last = last.pre;
        if (last != null) last.next = null;
        return oldlast.item;
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


    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        {
            return new ListIterator();
        }
    }

    public static void main(String[] args) {
        Deque<String>

    }   // unit testing (optional)
}
