package me.baymac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Theatre {

    private HashMap<String, Screen> screens = new HashMap<>();

    private boolean addScreen(String screenName, ArrayList<Integer> items) {
        Screen screen = Screen.getInstance();
        screen.setScreenName(screenName);
        screen.setTotalRow(items.get(0));
        screen.setTotalSeatsRow(items.get(1));
        screen.setAisleSeats(new ArrayList<>(items.subList(2, items.size())));
        if(screen.getAisleSeats().size() > screen.getTotalSeatsRow()) {
            return false;
        }
        screen.defaultAllocation();
        screens.put(screenName, screen);
        return true;
    }

    private boolean reserveSeat(String screenName, ArrayList<Integer> items) {
        if(screens.containsKey(screenName)) {
            int rowNumber = items.get(0);
            ArrayList<Integer> seatsToReserve = new ArrayList<>(items.subList(1, items.size()));
            return screens.get(screenName).allocatSeats(rowNumber, seatsToReserve);
        }
        return false;
    }

    private ArrayList<Integer> getUnreservedSeat(String screenName, ArrayList<Integer> items) {
        if (screens.containsKey(screenName)) {
            int rowNumber = items.get(0);
            return screens.get(screenName).getUnreservedSeats(rowNumber);
        }
        return new ArrayList<>();
    }

    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputs = scanner.nextLine().split("\\s+");
        Theatre theatre = new Theatre();
        String command = inputs[0];
        String screenName = inputs[1];
        ArrayList<Integer> items = new ArrayList<>();
        for(int i = 2; i < inputs.length; i++) {
            items.add(Integer.parseInt(inputs[i]));
        }
        switch(command) {
            case "add-screen":
                if(theatre.addScreen(screenName, items)) {
                    System.out.println("success");
                } else {
                    System.out.println("failure");
                }
                break;
            case "reserve-seat":
                if(theatre.reserveSeat(screenName, items)) {
                    System.out.println("success");
                } else {
                    System.out.println("failure");
                }
                break;
            case "get-unreserved-seats":
                ArrayList<Integer> unreservedSeats = theatre.getUnreservedSeat(screenName, items);
                unreservedSeats.forEach(unreservedSeat -> System.out.print(unreservedSeat + " "));
                break;
        }

    }
}
