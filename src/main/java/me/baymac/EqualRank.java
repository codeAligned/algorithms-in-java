package me.baymac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class EqualRank {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int j = scanner.nextInt();
        ArrayList<ArrayList<Integer>> m = new ArrayList<>();
        for(int a = 0; a < i; a++) {
            ArrayList<Integer> row = new ArrayList<>();
            for(int b = 0; b < j; b++) {
                row.add(scanner.nextInt());
            }
            m.add(row);
        }
        EqualRank equalRank = new EqualRank(i, j, m);
        m = equalRank.createRank();
        for(int a = 0; a < i; a++) {
            for(int b = 0; b < j; b++) {
                System.out.print(m.get(a).get(b) + " ");
            }
            System.out.println();
        }
    }

    ArrayList<ArrayList<Integer>> m;
    int i, j;

    public EqualRank(int i, int j, ArrayList<ArrayList<Integer>> m) {
        this.i = i;
        this.j = j;
        this.m = m;
    }


    public ArrayList<ArrayList<Integer>> createRank() {
        ArrayList<ArrayList<Integer>> rank = new ArrayList<>();
        ArrayList<Integer> flatArray = createFlatArray();
        HashMap<Integer, Integer> rankMap = createRankMap(flatArray);
        ArrayList rowRank = new ArrayList();
        for(int a = 0; a < i; a++) {
            for(int b = 0; b < j; b++) {
                m.get(a).set(b, rankMap.get(m.get(a).get(b)));
            }
        }
        return m;
    }

    private ArrayList<Integer> createFlatArray() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int a = 0; a < i; a++) {
            for(int b = 0; b < j; b++) {
                arrayList.add(m.get(a).get(b));
            }
        }
        return arrayList;
    }

    private HashMap<Integer, Integer> createRankMap(ArrayList<Integer> arrayList) {
        Collections.sort(arrayList);
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int ranks = 1;
        for(int a = 0; a < arrayList.size(); a++) {
            if(a != 0 && arrayList.get(a - 1).equals(arrayList.get(a))) {
                continue;
            } else {
                rankMap.put(arrayList.get(a), ranks);
                ranks++;
            }
        }
        return rankMap;
    }

}
