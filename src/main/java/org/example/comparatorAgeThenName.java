package org.example;
import java.util.Comparator;
public class comparatorAgeThenName implements Comparator <Passenger>{
//public int compare(Passenger pass1, Passenger pass2){
////    return pass1.getAge().compareTo(pass2.getName());
//
//    int compAge = pass1.getAge().compareTo(pass2.getAge());
//    if(compAge== 0){ // if it equals to zero that means that they are the same :)
//        return pass1.getName().compareTo(pass2.getName());
//    }
//    return compAge;
//}
public int compare(Passenger pass1, Passenger pass2) {
    // First compare by age (int comparison)
    int compAge =  Integer.compare(pass1.getAge(), pass2.getAge());

    // If ages are the same, compare by name (String comparison)
    if (compAge == 0) {
        return pass1.getName().compareTo(pass2.getName());
    }

    // Otherwise return the age comparison
    return compAge;
}
}

