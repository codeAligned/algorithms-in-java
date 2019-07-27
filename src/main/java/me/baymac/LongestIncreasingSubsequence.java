package me.baymac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LongestIncreasingSubsequence {

    ArrayList<Integer> arrayList;
    HashMap<Integer, Integer> t;

    public LongestIncreasingSubsequence(ArrayList<Integer> arrayList) {
        this.arrayList = arrayList;
        this.t = new HashMap<>();
    }

    public int lis(int i) {
        if(!t.containsKey(i)) {
            t.put(i, 1);
            for(int j = 0; j < i; j++) {
                if(arrayList.get(j) < arrayList.get(i)) {
                    t.put(i, Math.max(t.get(i), lis(j) + 1));
                }
            }
        }
        return t.get(i);
    }

    public int lisItr() {
        for(int i = 0; i < arrayList.size(); i++) {
            t.put(i, 1);
            for(int j = 0; j < i; j++) {
                if(arrayList.get(j) < arrayList.get(i) && t.get(i) < t.get(j) + 1) {
                    t.put(i, t.get(j) + 1);
                }
            }
        }
        return Collections.max(t.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getValue();
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(7, 2, 1, 3 , 8 , 4 , 9 , 1 , 2 , 6 , 5 , 9 , 3));
        LongestIncreasingSubsequence longestIncreasingSubsequence = new LongestIncreasingSubsequence(a);
        ArrayList<Integer> lis = new ArrayList<>();
        for(int i = 0; i < a.size(); i++) {
            System.out.println(longestIncreasingSubsequence.lis(i));
            lis.add(longestIncreasingSubsequence.lis(i));
        }
        System.out.println(Collections.max(lis));
        System.out.println(longestIncreasingSubsequence.lisItr());
    }

}
