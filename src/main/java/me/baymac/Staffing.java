package me.baymac;

import java.util.HashSet;
import java.util.Scanner;

public class Staffing {

    int staffs;
    char compatibility[][];

    public Staffing(int staffs, char[][] compatibility) {
        this.staffs = staffs;
        this.compatibility = compatibility;
    }

    public int numberOfTeams() {
        int teams = 0;
        HashSet<Integer> teamsFormed = new HashSet<>();
        for(int i = 0; i < staffs; i++) {
            if(teamsFormed.contains(i)) {
                continue;
            }
            int j = 0;
            boolean flag = false;
            while(j < staffs) {
                if(j != i && !teamsFormed.contains(j)) {
                    if(compatibility[i][j] == 'C') {
                        teamsFormed.add(i);
                        teamsFormed.add(j);
                        teams++;
                        flag = true;
                        break;
                    }
                }
                j++;
            }
            if(!flag && compatibility[i][i] == 'C') {
                teamsFormed.add(i);
                teams++;
            }
        }
        return teams;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for(int i = 0; i < n; i++) {
            int s = scanner.nextInt();
            char[][] c = new char[s][s];
            for(int j = 0; j < s; j++) {
                String input = scanner.next();
                for(int k = 0; k < s; k++) {
                    c[j][k] = input.charAt(0);
                }
            }
            Staffing staffing = new Staffing(s, c);
            System.out.println(staffing.numberOfTeams());
        }
    }
}
