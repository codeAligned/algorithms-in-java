package me.baymac;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Screen {

    private String screenName;
    private int totalRow;
    private int totalSeatsRow;
    private ArrayList<Integer> aisleSeats;
    private ArrayList<ArrayList<Boolean>> seats;

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalSeatsRow() {
        return totalSeatsRow;
    }

    public void setTotalSeatsRow(int totalSeatsRow) {
        this.totalSeatsRow = totalSeatsRow;
    }

    public ArrayList<Integer> getAisleSeats() {
        return aisleSeats;
    }

    public void setAisleSeats(ArrayList<Integer> aisleSeats) {
        this.aisleSeats = aisleSeats;
    }

    public static Screen getInstance() {
        return new Screen();
    }

    public void defaultAllocation() {
        seats = new ArrayList<>();
        ArrayList<Boolean> rowAllocation = new ArrayList<>();
        for(int i = 0; i < totalSeatsRow; i++) {
            rowAllocation.add(FALSE);
        }
        for(int i = 0; i < totalRow; i++) {
            seats.add(rowAllocation);
        }
    }

    public boolean allocatSeats(int rowNumber, ArrayList<Integer> seatsToReserve) {
        for(int i = 0; i < seatsToReserve.size(); i++) {
            int seat = seatsToReserve.get(0);
            if(seat > totalSeatsRow) {
                return false;
            }
            if(seats.get(rowNumber - 1).get(seat) != TRUE) {
                seats.get(rowNumber - 1).set(seat, TRUE);
            } else {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> getUnreservedSeats(int rowNumber) {
        ArrayList<Integer> unreservedSeats = new ArrayList<>();
        if(rowNumber > this.totalRow ) {
            return unreservedSeats;
        }
        ArrayList<Boolean> rowSeats = seats.get(rowNumber - 1);
        for(int i = 0; i < totalSeatsRow; i++) {
            if(rowSeats.get(i) == FALSE) {
                unreservedSeats.add(i + 1);
            }
        }
        return unreservedSeats;
    }
}
