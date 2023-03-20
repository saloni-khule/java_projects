package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
      AListNoResizing listOne = new AListNoResizing();
      BuggyAList listTwo = new BuggyAList();
      listOne.addLast(4);
      listOne.addLast(5);
      listOne.addLast(6);
      listTwo.addLast(4);
      listTwo.addLast(5);
      listTwo.addLast(6);

      assertEquals(listOne.size(),listTwo.size());





      assertEquals(listOne.removeLast(),listTwo.removeLast());
      assertEquals(listOne.removeLast(),listTwo.removeLast());
      assertEquals(listOne.removeLast(),listTwo.removeLast());




    }



    @Test
  public void randomizedTest(){
      AListNoResizing<Integer> L = new AListNoResizing<>();
      BuggyAList<Integer> B = new BuggyAList<>();

      int N = 5000;
      for (int i = 0; i < N; i += 1) {
        int operationNumber = StdRandom.uniform(0, 4);
        if (operationNumber == 0) {
          // addLast
          int randVal = StdRandom.uniform(0, 100);
          L.addLast(randVal);
          B.addLast(randVal);

        } else if (operationNumber == 1) {
          // size
          int sizeL = L.size();
          int sizeB = B.size();


          assertEquals(L.size(),B.size());
        }

        else if (operationNumber ==2  && L.size()>0 && B.size()>0){
          L.removeLast();
          B.removeLast();

        }
        else if(operationNumber == 3 && L.size()>0 && B.size()>0){
          int lastL = L.getLast();
          int lastB = B.getLast();

          assertEquals(lastL,lastB);
        }
      }
    }
  @Test
  public void randomizedTest2(){
    BuggyAList<Integer> L = new BuggyAList<>();

    int N = 5000;
    for (int i = 0; i < N; i += 1) {
      int operationNumber = StdRandom.uniform(0, 4);
      if (operationNumber == 0) {
        // addLast
        int randVal = StdRandom.uniform(0, 100);
        L.addLast(randVal);

      } else if (operationNumber == 1) {
        // size
        int size = L.size();

      }

      else if (operationNumber ==2  && L.size()>0){
        L.removeLast();

      }
      else if(operationNumber == 3 && L.size()>0){
        int last = L.getLast();

      }
    }




  }

}
