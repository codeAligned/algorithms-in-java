package me.baymac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BitWiseSubArray {

    ArrayList<Integer> a;
    BitWiseSubArray(ArrayList<Integer> a) {
        this.a = a;
    }


    public ArrayList<ArrayList<Integer>> findSubArrays() {
        ArrayList<ArrayList<Integer>> subArrays = new ArrayList<>();
        for(int i = 0; i < a.size(); i++) {
            int n = i;
            while(n < a.size()) {
                ArrayList<Integer> subArray = new ArrayList<>();
                for(int j = i; j <= n; j++) {
                    subArray.add(a.get(j));
                }
                subArrays.add(subArray);
                n++;
            }
        }
        return subArrays;
    }

    public long findSubArraySum()
    {
        long result = 0;
        for (int i = 0; i< a.size(); i++)
            result += (a.get(i) * (i+1) * (a.size()-i));
        return result ;
    }

    public int findBitWiseSum() {
        ArrayList<ArrayList<Integer>> subArrays = findSubArrays();
        int bitWiseSumOfSubArrays = 0;
        for(int i = 0; i < subArrays.size(); i++) {
            int bitWiseSumOfSubArray = 0;
            for(int j = 0; j < subArrays.get(i).size(); j++) {
                bitWiseSumOfSubArray |= subArrays.get(i).get(j);
            }
            bitWiseSumOfSubArrays += bitWiseSumOfSubArray;
        }
        return bitWiseSumOfSubArrays;
    }

    public int findBitWiseSumFast() {
        int max = Collections.max(a);

        int maxBit = (int) Math.ceil(Math.log(max) + 1);

        int len = a.size();
        int totalSubarrays = len * (len + 1) / 2;

        int s = 0;

        for(int i = 0; i < maxBit; i++) {
            int c1 = 0;
            // Store indices of the array with i-th bit not set
            ArrayList<Integer> indices = new ArrayList<>();

            for(int j = 0; j < len; j++) {
                int x = a.get(j) >> i;
                if(!(x%2 == 1)) {
                    indices.add(j);
                }
            }

            // Variable to store count of subarrays
            // whose bitwise OR will have i-th bit
            // not set
            int notSet = 0;
            int count = 1;
            for(int j = 1; j < indices.size(); j++) {
                if(a.get(j) - a.get(j - 1) == 1) {
                    count++;
                } else {
                    notSet += count * (count + 1) / 2;
                    count = 1;
                }
            }

            // For last element of indices
            notSet += count * (count + 1) / 2;

            // Variable to store count of subarrays
            // whose bitwise OR will have i-th bit set
            int set = totalSubarrays - notSet;
            s += set * Math.pow(2, i);
        }
        return s;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for(int i = 0; i < n; i++) {
            a.add(scanner.nextInt());
        }
        BitWiseSubArray bitWiseSubArray = new BitWiseSubArray(a);
        System.out.println(bitWiseSubArray.findBitWiseSumFast());
    }
}
