package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        int ops = 1000;
        AList opsl = new AList();
        AList timeElpsed = new AList();
        Stopwatch sw = new Stopwatch();

        while (ops<128001){
            AList list = new AList();
            int m = 0;
            while(m<=ops){
                list.addLast(1);
                m++;
            }


//            list.addLast(4);
            double timeElapsed = sw.elapsedTime();


            timeElpsed.addLast(timeElapsed);

            opsl.addLast(ops);






            ops*=2;



        }
        printTimingTable(opsl, timeElpsed, opsl);








    }
}
