package me.baymac;

import java.util.ArrayList;
import java.util.Scanner;

public class Tshaped {

    public static void main(String[] args) {
        int p[][] = new int[5][2];
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < 5; i++) {
            p[i][0] = scanner.nextInt();
            p[i][1] = scanner.nextInt();
        }

        Tshaped tshaped = new Tshaped(p);
        System.out.println(tshaped.isTshaped());
    }

    int points[][];

    Tshaped(int points[][]) {
        this.points = points;
    }

    public class myPoint {
        int a, b;
        myPoint(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

    public boolean isTshaped() {
        for(int i = 0; i < 5; i++) {
            boolean isT = isEdgeTpoint(points[i][0], points[i][1]) || isMidTpoint(points[i][0], points[i][1]) || isTriTpoint(points[i][0], points[i][1]);
            if(isT) {
                return true;
            }
        }
        return false;
    }

    private boolean isTriTpoint(int a, int b) {
        ArrayList<myPoint> adjacentPoints = searchAround(a, b);
        if(adjacentPoints.size() != 3) {
            return false;
        }
        int nonAlignedPointOnA = -1;
        int nonAlignedPointOnB = -1;
        if(isSameAxis(a, adjacentPoints.get(0).getA())) {
            if(isSameAxis(a, adjacentPoints.get(1).getA())) {
                nonAlignedPointOnA = 2;
            } else if(isSameAxis(a, adjacentPoints.get(2).getA())) {
                nonAlignedPointOnA = 1;
            } else {
                nonAlignedPointOnA = 0;
            }
        } else if(isSameAxis(b, adjacentPoints.get(0).getB())) {
            if(isSameAxis(b, adjacentPoints.get(1).getB())) {
                nonAlignedPointOnB = 2;
            } else if(isSameAxis(b, adjacentPoints.get(2).getB())) {
                nonAlignedPointOnB = 1;
            } else {
                nonAlignedPointOnB = 0;
            }
        }
        if(nonAlignedPointOnA != -1) { // search for the point next to non-aligned point
            return searchInPoints(2 * adjacentPoints.get(nonAlignedPointOnA).getA() - a,
                    adjacentPoints.get(nonAlignedPointOnA).getB());
        } else {
            return searchInPoints(adjacentPoints.get(nonAlignedPointOnB).getA(),
                    2 * adjacentPoints.get(nonAlignedPointOnB).getB() - b);
        }
    }

    private boolean isMidTpoint(int a, int b) {
        ArrayList<myPoint> adjacentPoints = searchAround(a, b);
        if(adjacentPoints.size() != 2) {
            return false;
        }
        return (isTriTpoint(adjacentPoints.get(0).getA(), adjacentPoints.get(0).getB()) &&
                searchAround(adjacentPoints.get(1).getA(), adjacentPoints.get(1).getB()).size() == 1)
                || (searchAround(adjacentPoints.get(0).getA(), adjacentPoints.get(0).getB()).size() == 1 &&
                    isTriTpoint(adjacentPoints.get(1).getA(), adjacentPoints.get(1).getB()));
    }

    private boolean isEdgeTpoint(int a, int b) {
        ArrayList<myPoint> adjacentPoints = searchAround(a, b);
        if(adjacentPoints.size() != 1) {
            return false;
        }
        return isTriTpoint(adjacentPoints.get(0).getA(), adjacentPoints.get(0).getB()) || isMidTpoint(adjacentPoints.get(0).getA(), adjacentPoints.get(0).getB());

    }

    private boolean isSameAxis(int i, int j) {
        if(Math.abs(i - j) == 1) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<myPoint> searchAround(int a, int b) {
        ArrayList<myPoint> myPoints = new ArrayList<>();
        if(a != 0 && searchInPoints(a-1, b)) {
            myPoints.add(new myPoint(a-1, b));
        }
        if(searchInPoints(a+1, b)) {
            myPoints.add(new myPoint(a+1, b));
        }
        if(searchInPoints(a, b+1)) {
            myPoints.add(new myPoint(a, b+1));
        }
        if(b != 0 && searchInPoints(a, b-1)) {
            myPoints.add(new myPoint(a, b-1));
        }
        return myPoints;
    }

    private boolean searchInPoints(int a, int b) {
        for(int i = 0; i < 5; i++) {
            if(points[i][0] == a && points[i][1] == b) {
                return true;
            }
        }
        return false;
    }
}
