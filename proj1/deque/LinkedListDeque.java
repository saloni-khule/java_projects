package deque;

import java.util.Iterator;
public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private  int sizeOfList;
    private class TNode<T> {
        private TNode  first;
        private TNode  rest;
        private T  item;

        private TNode(TNode   f, T  i, TNode  r) {
            this.first = f;
            this.rest = r;
            this.item = i;
        }


    }

    private TNode sentinel;


    public LinkedListDeque() {

        sentinel = new TNode(null, null, null);
        sentinel.rest = sentinel;
        sentinel.first = sentinel;
        sizeOfList = 0;


    }



    public void addFirst(T item) {

        TNode itemx = new TNode(null, item, null);

        if (sizeOfList == 0) {
            sentinel.first = itemx;
            sentinel.rest = itemx;
            itemx.rest = sentinel;
            itemx.first = sentinel;

        } else {

            sentinel.rest.first = itemx;


            itemx.rest = sentinel.rest;
            itemx.first = sentinel;
            sentinel.rest = itemx;


        }



        sizeOfList += 1;

    }







    public void addLast(T item) {
        TNode itemx = new TNode(null, item, null);

        if (sizeOfList == 0) {
            sentinel.first = itemx;
            sentinel.rest = itemx;
            itemx.rest = sentinel;
            itemx.first = sentinel;

        } else {

            sentinel.first.rest = itemx;
            itemx.first = sentinel.first;


            itemx.rest = sentinel;

            sentinel.first = itemx;


        }



        sizeOfList += 1;
    }




    public int size() {
        return sizeOfList;
    }


    public void printDeque() {
        TNode temp = sentinel.rest;
        int count = 0;
        while (count < sizeOfList || count == 0) {
            System.out.print(temp.item + " ");
            temp = temp.rest;
            count++;
        }
        System.out.println();


    }

    public T removeFirst() {

        T itemRem = (T) sentinel.rest.item;

        sentinel.rest = sentinel.rest.rest;
        sentinel.rest.first = sentinel;
        if (sizeOfList > 0) {
            sizeOfList -= 1;
        }


        return itemRem;

    }




    public T removeLast() {
        T mix = (T) sentinel.first.item;

        sentinel.first = sentinel.first.first;
        sentinel.first.rest = sentinel;
        if (sizeOfList > 0) {
            sizeOfList -= 1;
        }
        return mix;


    }







    public T get(int index) {


        int count = 0;
        TNode temp = sentinel.rest;
        T mix = null;
        while (count <= index) {

            mix = (T) temp.item;
            temp = temp.rest;
            count++;
        }
        return mix;
    }


    private T getRecursiveHelper(int index, TNode j) {
        if (index == 0) {
            return (T) j.rest.item;

        }
        return (T) getRecursiveHelper(index - 1, j.rest);

    }


    public T getRecursive(int index) {


        return getRecursiveHelper(index, sentinel);

    }


    public boolean equals(Object o) {

        if (o instanceof Deque Ob1) {


            if (sizeOfList != Ob1.size()) {
                return false;
            }



            int count = 0;


            while (count < sizeOfList) {
                if (!(get(count).equals(Ob1.get(count)))) {
                    return false;

                }
                count++;

            }

        } else if (!(o instanceof Deque Ob1)) {
            return false;
        }
        return true;


    }



    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizpos;
        public ArraySetIterator() {
            wizpos = 0;

        }
        public boolean hasNext() {

            return wizpos < sizeOfList;

        }
        private TNode<T> temp = sentinel;
        public T next() {
            temp = temp.rest;
            T returnItem = (T) temp.item;
            wizpos++;

            return returnItem;

        }
    }

}



