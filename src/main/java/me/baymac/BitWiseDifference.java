package me.baymac;

public class BitWiseDifference {

    int a, b;

    public BitWiseDifference(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void printBinaries() {
        System.out.println("a: "+Integer.toBinaryString(a));
        System.out.println("b: "+Integer.toBinaryString(b));
    }

    public int getDifference() {
        int differentBits = a ^ b;
        int count = 0;
        do {
            count++;
            differentBits /= 2;
        } while (differentBits != 0);
        return count;
    }

    public static void main(String [] args) {
        BitWiseDifference bitWiseDifference = new BitWiseDifference(100, 123);
        bitWiseDifference.printBinaries();
        System.out.println(bitWiseDifference.getDifference());
    }
}
