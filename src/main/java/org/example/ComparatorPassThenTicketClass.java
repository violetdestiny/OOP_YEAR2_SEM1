package org.example;

import java.util.Comparator;

public class ComparatorPassThenTicketClass implements Comparator<Passenger> {
    public int compare(Passenger pass1, Passenger pass2) {

        return pass1.getPassengerId().compareTo(pass2.getPassengerId());
    }


}

