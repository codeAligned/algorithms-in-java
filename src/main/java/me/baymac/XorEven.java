package me.baymac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class XorEven {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int n = 0 , q = 0;
        XorEven xorEven = new XorEven();
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<>();
        ArrayList<LinkedHashMap<Integer, Integer>> modLists = new ArrayList<>();
        for(int i = 0; i < t; i++) {
            n = scanner.nextInt();
            q = scanner.nextInt();
            ArrayList<Integer> arrayList = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                arrayList.add(scanner.nextInt());
            }
            LinkedHashMap<Integer, Integer> mod = new LinkedHashMap<>();
            for(int j = 0; j < q; j++) {
                mod.put(scanner.nextInt(), scanner.nextInt());
            }
            arrays.add(arrayList);
            modLists.add(mod);
        }
        for(int i = 0; i < t; i++) {
            xorEven.getLargestSI(arrays.get(i), modLists.get(i), n, q, i+1);
        }
    }

    private void getLargestSI(ArrayList<Integer> a, HashMap<Integer, Integer> m, int n, int q, int i) {
        ArrayList<Integer> mysubs = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : m.entrySet()) {
            a.set(entry.getKey(), entry.getValue());
            int k = findMaxSubInterval(a);
            mysubs.add(k);
        }
        System.out.print("Case #" + i + ": ");
        mysubs.forEach(s -> System.out.print(s +" "));
        System.out.println();
    }

    private Integer findMaxSubInterval(ArrayList<Integer> a) {
        int i = 0;
        for(; i < a.size(); i++) {
            int max = a.get(i);
            for(int j = i+1; j < a.size(); j++) {
                max ^= a.get(j);
            }
            if(counBits(max) % 2 == 0) {
                break;
            }
        }
        return a.size() - i;
    }

    private int counBits(int n) {
        int count = 0;
        while(n != 0)
        {
            n = n&(n-1);
            count++;
        }
        return count;
    }

}
