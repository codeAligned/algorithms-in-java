package me.baymac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LatestGuests {

    public static class Guest {
        String dir;

        public String getDir() {
            return dir;
        }

        public int getPos() {
            return pos;
        }

        int pos;

        public Guest(int pos, String dir) {
            this.dir = dir;
            this.pos = pos;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        ArrayList<Integer> n = new ArrayList<>();
        ArrayList<Integer> g = new ArrayList<>();
        ArrayList<Integer> m = new ArrayList<>();
        LatestGuests latestGuests = new LatestGuests();
        ArrayList<LinkedHashMap<Integer, Guest>> modLists = new ArrayList<>();
        for(int i = 0; i < t; i++) {
            n.add(scanner.nextInt());
            g.add(scanner.nextInt());
            m.add(scanner.nextInt());
            LinkedHashMap<Integer, Guest> mod = new LinkedHashMap<>();
            for(int j = 0; j < g.get(i); j++) {
                mod.put(j+1, new Guest(scanner.nextInt(), scanner.next()));
            }
            modLists.add(mod);
        }
        for(int i = 0; i < t; i++) {
            latestGuests.getLastGuests(n.get(i), g.get(i), m.get(i), modLists.get(i), i+1);
        }
    }

    private void getLastGuests(int n, int g, int m, LinkedHashMap<Integer, Guest> guests, int t) {
        HashMap<Integer, ArrayList<Integer>> consulates = new HashMap<>();
        for(int i = 0; i < n; i++) {
            consulates.put(i, new ArrayList<>());
        }
        for(Map.Entry<Integer, Guest> entry : guests.entrySet()) {
            consulates.get(entry.getValue().getPos()-1).add(entry.getKey());
        }
        HashMap<Integer, Integer> guestStatus = new HashMap<>();
        for(Map.Entry<Integer, Guest> entry : guests.entrySet()) {
            if(entry.getValue().getDir().equals("C")) {
                int pos = entry.getValue().getPos();
                if(pos == n) {
                    pos = 1;
                } else {
                    pos++;
                }
                guestStatus.put(entry.getKey(), pos);
            } else {
                int pos = entry.getValue().getPos();
                if(pos == 1) {
                    pos = n;
                } else {
                    pos--;
                }
                guestStatus.put(entry.getKey(), pos);
            }
        }
        for(int i = 0; i < m; i++) {
            for(Map.Entry<Integer, Integer> entry : guestStatus.entrySet()) {
                if(!consulates.get(entry.getValue()-1).isEmpty()) {
                    consulates.put(entry.getValue()-1, new ArrayList<>());
                }
            }
            for(Map.Entry<Integer, Guest> entry : guests.entrySet()) {
                int currentPosition = guestStatus.get(entry.getKey());
                if(entry.getValue().getDir().equals("C")) {
                    consulates.get(currentPosition-1).add(entry.getKey());
                    if(currentPosition == n) {
                        currentPosition = 1;
                    } else {
                        currentPosition++;
                    }
                    guestStatus.put(entry.getKey(), currentPosition);
                } else {
                    consulates.get(currentPosition-1).add(entry.getKey());
                    if(currentPosition == 1) {
                        currentPosition = n;
                    } else {
                        currentPosition--;
                    }
                    guestStatus.put(entry.getKey(), currentPosition);
                }
            }
        }
        for(int i = 0; i < g; i++) {
            guestStatus.put(i+1, 0);
        }
        for(Map.Entry<Integer, ArrayList<Integer>> entry : consulates.entrySet()) {
            ArrayList<Integer> guestRemebered = entry.getValue();
            guestRemebered.forEach(e -> guestStatus.put(e, guestStatus.get(e) + 1));
        }
        System.out.print("Case #" + t + ": ");
        for(int i = 0; i < g; i++) {
            System.out.print(guestStatus.get(i+1) + " ");
        }
        System.out.println();
    }
}
