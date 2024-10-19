package org.example;

import java.util.Comparator;

public class comparatorFareThenSurvival implements Comparator<Passenger> {
    // attempt 1
//    public int compare(Passenger pass1, Passenger pass2) {
//        // First compare by age (int comparison)
//      int compAge = Double.pass1.getFare() - pass2.getFare();
//
//        // If ages are the s  ame, compare by name (String comparison)
//        if (compAge == 0) {
//            return pass1.getSurvived().compareTo(pass2.getSurvived());
//        }
//
//        return compAge;
//    }
    // attemp2 it was not sorting my survived only the fare price so i had to chnage it
//    public int compare(Passenger pass1, Passenger pass2) {
//        double differenceInFare = pass1.getFare() - pass2.getFare();
//        // First compare by fare
//        if (differenceInFare > 0) {
//            return 1; // pass1 fare is greater than pass2 fare
//        } else if (differenceInFare < 0) {
//            return -1; // pass1 fare is less than pass2 fare
//        }
//// i am assuming that when we get back 1 that they survived
//
//        if (pass1.getSurvived() > pass2.getSurvived()) {
//            return 1; // pass1 survived, pass2 didnt
//        } else if (pass1.getSurvived() < pass2.getSurvived()) {
//            return -1;
//        }
//        return 0;
//    }
    public int compare(Passenger pass1, Passenger pass2) {
        // First compare by fare
        int fareComparison = Double.compare(pass1.getFare(), pass2.getFare());

        // If fare is the same, compare by survival (survived field: 1 for true, 0 for false)
        if (fareComparison == 0) {
            return Integer.compare(pass1.getSurvived(), pass2.getSurvived());
        }
        return fareComparison;
    }

}
