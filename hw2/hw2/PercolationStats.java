package hw2;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.ArrayList;

public class PercolationStats {
    private int Trials;
    private double[] data;

    public PercolationStats(int N, int T, PercolationFactory pf){
        if(T<=0 || N<=0){
            throw new IllegalArgumentException("T and N cannot be negative ");
        }
        Trials =T;

        data = new double[T];


        int count = Trials;
        int keepTrack = 0;

        while (count>0){
            Percolation newx = pf.make(N);
        while (!(newx.percolates())) {

            int x = StdRandom.uniform( N);
            int y = StdRandom.uniform( N);
            if(! newx.isOpen(x,y)){
                newx.open(x,y);
            }

        }
        data[count-1] = (double) (newx.numberOfOpenSites())/(N*N);

        count--;

        }
    }


    public double mean(){

//        double dataSum = 0;
//        for(Double x: data){
//            dataSum+=x;
//        }
//        return dataSum/T;
    return StdStats.mean(data);

    }


    public double stddev(){
//        double meanx = this.mean();
//        double temp =0;
//        for(Double x : data){
//
//            temp+= Math.pow(x-meanx, 2);
//
//
//        }
//        double stdSqrt = temp/(T-1);
//
//        return Math.pow(stdSqrt,0.5);
        return StdStats.stddev(data);


    }


    public double confidenceLow(){
        double meanx = mean();
        double stdDevx = stddev();
        double temp = meanx - ((1.96*stdDevx)/Math.pow(Trials,0.5));
        return temp;





    }

    public double confidenceHigh(){
        double meanx = this.mean();
        double stdDevx = this.stddev();
        double temp = meanx + ((1.96*stdDevx)/Math.pow(Trials,0.5));
        return temp;


    }






}
