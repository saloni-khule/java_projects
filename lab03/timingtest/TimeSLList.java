package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE


        int ops = 1000;
        AList opsl = new AList();
        AList timeElpsed = new AList();
        AList M = new AList();


        while (ops<128001){
            SLList list = new SLList();
            int m = 0;
            while(m<=ops){
                list.addLast(1);
                m++;
            }
            int i =0;




            Stopwatch sw = new Stopwatch();
            while(i<10000){
            Object j = list.getLast();
            i++;
            }


//            list.addLast(4);

            double timeElapsed = sw.elapsedTime();


            timeElpsed.addLast(timeElapsed);

            opsl.addLast(ops);
            M.addLast(i);






            ops*=2;



        }
        printTimingTable(opsl, timeElpsed, M);



    }

}
