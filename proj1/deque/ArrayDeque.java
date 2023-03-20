package deque;



import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] arrayx;

    private static final int RANN_T = 8;
    private static final int TWO_FIVE = 25;
    private static final int HUNDRED = 100;

    private static final int SMALLEST = 16;
    private static final int DIVIDER = 4;

    private int sizeOfList;
    private int nextFirst;
    private int nextLast;
    private int length;


    public ArrayDeque() {
        arrayx = (T[]) new Object[RANN_T];
        nextFirst = 0;
        nextLast = 1;
        length = arrayx.length;
        sizeOfList = 0;
    }

    private void resize(int capacity) {

        T[] a = (T[]) new Object[capacity];







        int count = 0;
        while (count < sizeOfList) {

            a[count] = this.get(count);
            count++;

        }





        arrayx = a;
        length = arrayx.length;

        nextFirst = length - 1;
        nextLast = sizeOfList;


    }





    public void addFirst(T item) {
        if (nextFirst == -1) {

            nextFirst = length - 1;
        }

        if (arrayx[nextFirst] != null) {


            resize(length * 2);


        }
        arrayx[nextFirst] = item;
        nextFirst--;
        sizeOfList++;


    }





    public void addLast(T item) {

        if (nextLast >= length) {

            nextLast = 0;

        }

        if (arrayx[nextLast] != null) {
            resize(length * 2);
            nextLast = sizeOfList;
        }

        arrayx[nextLast] = item;

        sizeOfList++;
        nextLast++;


    }



    public int size() {
        return sizeOfList;
    }



    public T removeFirst() {
        if (nextFirst >= length - 1) {
            nextFirst = -1;
        }
        T mix = arrayx[nextFirst + 1];
        arrayx[nextFirst + 1] = null;



        nextFirst++;

        if (sizeOfList > 0) {
            sizeOfList--;
        }
        if (sizeOfList * HUNDRED / length < TWO_FIVE && length > SMALLEST) {
            resize(length / DIVIDER);
        }
        return mix;


    }




    public T removeLast() {

        if (nextLast == 0 || nextLast >= length) {

            nextLast = length;

        }
        T mix = arrayx[nextLast - 1];
        arrayx[nextLast - 1] = null;
        nextLast--;
        if (sizeOfList > 0) {
            sizeOfList--;
        }
        if (sizeOfList * HUNDRED / length < TWO_FIVE && length > SMALLEST) {
            resize(length / DIVIDER);
        }
        return mix;
    }


    public T get(int index) {
        if (sizeOfList <= index || length <= index) {
            return null;
        } else if (nextFirst + index + 1 >= length) {
            int j = nextFirst + 1 - length;
            return arrayx[index + j];
        }


        return arrayx[nextFirst + index + 1];

    }

    public void printDeque() {


        int count = 0;
        while (count < sizeOfList) {
            System.out.print(get(count) + " ");
            count++;

        }


    }


    public boolean equals(Object o) {
        if (o instanceof Deque Ob1) {

            if (this.size() != Ob1.size()) {
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
        return new ArrayDeque.ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizpos;

        public ArraySetIterator() {
            wizpos = 0;

        }

        public boolean hasNext() {

            return wizpos < sizeOfList;

        }

        public T next() {


            T returnItem = get(wizpos);
            wizpos++;
            return returnItem;


        }
    }
}
