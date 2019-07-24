package me.baymac;

import java.util.HashMap;

public class FiboMemoization {

    long n;

    HashMap<Long, Long> T;

    public long getN() {
        return n;
    }

    public FiboMemoization(long n) {
        this.n = n;
        T = new HashMap<>();
    }

    private long getFibonacciSeries() {
        return getFibonacciSeries(this.n);
    }

    public long getFibonacciSeries(long n) {
        if(!T.containsKey(n)) {
            if(n <= 1) {
                T.put(n, n);
            } else {
                T.put(n, getFibonacciSeries(n-1) + getFibonacciSeries(n-2));
            }
        }
        return T.get(n);
    }

    public long getFiboitr() {
        T.put(0L, 0L);
        T.put(1L, 1L);
        for(long i = 2; i <= n; i++) {
            T.put(i, T.get(i-1) + T.get(i-2));
        }
        return T.get(n);
    }

    public static void main(String[] args) {
        FiboMemoization fiboMemoization = new FiboMemoization(101);
        // starting time
        long start = System.currentTimeMillis();
        long x = fiboMemoization.getFibonacciSeries();
        // ending time
        long end = System.currentTimeMillis();
        System.out.println("x = " + x + " " + (end-start) + "ms");
        start = System.currentTimeMillis();
        long y = fiboMemoization.getFiboitr();
        // ending time
        end = System.currentTimeMillis();
        System.out.println("y = " + y + " " + (end-start) + "ms");
    }

}
