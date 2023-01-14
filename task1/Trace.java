package ru.job4j.task;

public class Trace {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        printTrace(n, m);
    }

    public static void printTrace(int n, int m) {
        if (n < 1 || m < 1) {
            throw new IllegalArgumentException();
        }
        int nextVal = 1;
        do {
            System.out.print(nextVal);
            nextVal = 1 + (nextVal + m - 2) % n;
        } while (nextVal != 1);
    }
}