package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] gridx;
    private int[][] gridj;
    private boolean[][] gridb;
    private WeightedQuickUnionUF wf;
    private WeightedQuickUnionUF oneP;
    private int num;

    private int openSites = 0;


    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Number of rows or columns cannot be 0");
        }

        int count = 0;
        num = N;


        gridx = new int[N][N];

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                gridx[i][j] = count;
                count++;
            }
        }
        int count2 = 0;

        gridb = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                gridb[i][j] = false;
                count2++;
            }

        }
        gridj = gridx.clone();

        wf = new WeightedQuickUnionUF(N * N + 2);
        oneP = new WeightedQuickUnionUF(N * N + 2);
    }

    public void open(int row, int col) {

        if (isOpen(row, col)) {
            return;
        }

        if (row == 0) {
            wf.union(gridx[row][col], wf.find(num * num));
            oneP.union(gridx[row][col], oneP.find(num * num));
        }
        if (row == gridx.length - 1) {
            oneP.union(gridx[row][col], oneP.find(num * num + 1));
        }

        if (!isOpen(row, col)) {
            openSites++;
            gridb[row][col] = true;
        }

        if (row + 1 < gridb.length && gridb[row + 1][col]) {

            wf.union(gridx[row][col], gridx[row + 1][col]);
            oneP.union(gridx[row][col], gridx[row + 1][col]);
        }
        if (row - 1 > -1 && gridb[row - 1][col]) {
            wf.union(gridx[row][col], gridx[row - 1][col]);
            oneP.union(gridx[row][col], gridx[row - 1][col]);

        }
        if (col + 1 < gridb.length && gridb[row][col + 1]) {
            wf.union(gridx[row][col], gridx[row][col + 1]);
            oneP.union(gridx[row][col], gridx[row][col + 1]);

        }
        if (col - 1 > -1 && gridb[row][col - 1]) {
            wf.union(gridx[row][col], gridx[row][col - 1]);
            oneP.union(gridx[row][col], gridx[row][col - 1]);


        }



    }

    public boolean isOpen(int row, int col) {
        return gridb[row][col];
    }

    public boolean isFull(int row, int col) {

        if (wf.connected(gridx[row][col], wf.find(num * num))) {
            return true;
        }

        return false;
    }



    public int numberOfOpenSites() {


        return openSites;

    }



    public boolean percolates() {


        if (oneP.connected(oneP.find(num * num), oneP.find(num * num + 1))) {
            return true;
        }
        return false;

    }




}






