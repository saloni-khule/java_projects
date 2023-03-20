package deque;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest{

    @Test
    public void test1(){

        ArrayDeque<Integer> saloni = new ArrayDeque();
        for(int i =0; i<100; i++){
        saloni.addFirst(i);}

        for(int i =0; i<100; i++){

//            System.out.println(saloni.removeFirst());
            saloni.removeFirst();


        }
        for(int i =0; i<100; i++){
            saloni.addFirst(i);}





    }






}