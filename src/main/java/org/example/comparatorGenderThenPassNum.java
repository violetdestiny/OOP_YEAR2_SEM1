package org.example;

import java.util.Comparator;

public class comparatorGenderThenPassNum implements Comparator<Passenger> {
    public int compare(Passenger pass1, Passenger pass2) {
//    return pass1.getAge().compareTo(pass2.getName());

        int compAge = pass1.getGender().compareToIgnoreCase(pass2.getGender());
        if (compAge == 0) { // if it equals to zero that means that they are the same :)
            return pass1.getPassengerId().compareTo(pass2.getPassengerId());
        }
        return compAge;
    }
}
