package org.example;
// CA1

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String fileName = "titanic-data-100.csv"; // file should be in the project folder (below pom.xml)

        ArrayList<Passenger> passengerList = new ArrayList<>();

        loadPassengerDataFromFile(passengerList, fileName);

      //  displayAllPassengers(passengerList);


        // Assignment: Implement and test the following methods.
        // See the description of each method in the CA1 Specification PDF file from Moodle

        // Display all the methods
        System.out.println();
        System.out.println("                 _____ _   _ _____   _____ ___ _____  _    _   _ ___ ____  \n" +
                "                |_   _| | | | ____| |_   _|_ _|_   _|/ \\  | \\ | |_ _/ ___| \n" +
                "                  | | | |_| |  _|     | |  | |  | | / _ \\ |  \\| || | |     \n" +
                "                  | | |  _  | |___    | |  | |  | |/ ___ \\| |\\  || | |___  \n" +
                "                  |_| |_| |_|_____|   |_| |___| |_/_/   \\_\\_| \\_|___\\____| ");
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
// print one
        String[] allpass = getPassengerName(passengerList);
        System.out.println(" display the passenger names " + Arrays.toString(allpass));
// print two
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        System.out.println("print out all the willians in the data base ");
        List<Passenger> allpass2 = getPassengersContainingName(passengerList, "william");
        System.out.println(allpass2.toString());
// print three
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        System.out.println("printing out passengers older than 37");
        List<Passenger> allpass3 = getPassengerslderThan(passengerList, 37);
        System.out.println(allpass3.toString());
// print four
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        System.out.println("counting passengers by gender ");
        int allpass4 = countPassengersByGender(passengerList, "female");
        System.out.println("there are " + allpass4 + " Females on this train");
// print five
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        System.out.println("the sum of the fair prices ");
        double allpass5 = sumFares(passengerList);
        System.out.printf("%.2f%n", allpass5);
// print six
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        System.out.println("the number of male survivors  ");
        List<String>  allpass6 = maleSurvivors(passengerList);
        System.out.println(allpass6.toString());
//        System.out.println(Arrays.toString(allpass6));
// print seven
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        System.out.println("ticket owner of of PC 17599 is ");
        Passenger allpass7 = ticketOwner(passengerList, "PC 17599");
        if (allpass7 == null) {
            System.out.println("this passenger is not found ");
        } else {
            System.out.println(allpass7.toString());
        }
// print eight
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        System.out.println("the average age is  ");
        int allpass8 = averageAge(passengerList);
        System.out.println( allpass8);
// print nine
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        System.out.println("passangers by list class");
        List<Passenger> allpass9 = getPassengersByTicketClass(passengerList, PassengerClass.THIRD);
        System.out.println(allpass9);
// print ten
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
//        System.out.println("sort passangers by passenger id ");
//        List<Passenger> allpass10 = sortPassengersByPassengerId(passengerList);
        System.out.println("Sorting passengers by ID:");
        sortPassengersByPassengerId(passengerList);
        displayAllPassengers(passengerList);
//print eleven
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        System.out.println("Sorting passengers by passenger Name ");
        sortPassengersByPassengerName(passengerList);
        displayAllPassengers(passengerList);
// print twelve
        System.out.println("--------------------------------------------------------------COMPARATOR SORTING---------------------------");
        Collections.sort(passengerList, new comparatorAgeThenName());
       // System.out.println(passengerList);
        displayAllPassengers(passengerList);
// print thirteen
        System.out.println("--------------------------------------------------------------COMPARATOR SORTING---------------------------");
        Collections.sort(passengerList, new comparatorGenderThenPassNum());
        displayAllPassengers(passengerList);
// print fourteen
        System.out.println("--------------------------------------------------------------COMPARATOR SORTING---------------------------");
        Collections.sort(passengerList, new comparatorFareThenSurvival());
        displayAllPassengers(passengerList);
// print fifteen
        System.out.println("--------------------------------------------------------------COMPARATOR SORTING---------------------------");
        Collections.sort(passengerList, new ComparatorPassThenTicketClass());
        displayAllPassengers(passengerList);
// print sixteen
        List<Passenger> pass16 = sortPassengersByAge(passengerList); // we need it returned as a list !! to display list you need to display them from a forloop
        for(Passenger pass : pass16){ // this is a forEach instead of an inhanced forLoop// the pass16 acts as my key in this case
            System.out.println(pass);
        }
// print seventeen
        System.out.println("--------------------------------------------------------------COMPARATOR SORTING---------------------------");
        List<Passenger> pass17 = sortPassengersByTicketNumberLambda(passengerList);
        for(Passenger pass : pass17){
            System.out.println(pass);
        }
// print eighteen
        System.out.println("--------------------------------------------------------------COMPARATOR SORTING---------------------------");
        System.out.println("question 18----------------------------------------------------------------------------------------------------");
        List<Passenger> pass18 = sortPassengersByTicketNumberStatic(passengerList);
        for(Passenger pass : pass18){
            System.out.println(pass);
        }
// print ninteen
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
       Collections.sort(passengerList, Passenger.TicketNumberComparator);
       Passenger pass19 = new Passenger("PC 17599", null);
       Passenger foundpass = findPassengerByTicketNumber(passengerList,pass19);
        System.out.println(pass19);
// print twenty
        System.out.println("--------------------------------------------------------------- printing -----------------------------------------------------------");
        Collections.sort(passengerList, Passenger.PassengerIdComparator);
        Passenger pass20 = new Passenger(null, "26");
        Passenger foundpass2 = findPassengerByPassengerId(passengerList,pass20);
        System.out.println(pass20);
        System.out.println("-----------------------------------------------------------------Finished---------------------------------------------------------------------");

    }

    /**
     * Populate an ArrayList of Passenger objects with data from a text file
     *
     * @param passengerList - an ArrayList to be filled with Passenger objects
     * @param fileName      - name of CSV text file containing passenger details
     */
    public static void loadPassengerDataFromFile(ArrayList<Passenger> passengerList, String fileName) {

        // Format of each row of data is:
        // Name,Age,Height(m),GPA  - these heading names are included as the first row in file
        // John Malone,20,1.78,3.55   for example

        // Use a Regular Expression to set both comma and newline as delimiters.
        //  sc.useDelimiter("[,\\r\\n]+");
        // Text files in windows have lines ending with "CR-LF" or "\r\n" sequences.
        // or may have only a newline - "\n"
        // Windows uses CRLF (\r\n, 0D 0A) line endings while Unix just uses LF (\n, 0A).
        //
        try (Scanner sc = new Scanner(new File(fileName))
                .useDelimiter("[,\\r\\n]+")) {

            // skip past the first line, as it has field names (not data)
            if (sc.hasNextLine())
                sc.nextLine();   // read the header line containing column titles, but don't use it

            // while there is a next token to read....
            System.out.println("Go...");

            while (sc.hasNext()) {
                String passengerId = sc.next();    // read passenger ID
                int survived = sc.nextInt();     // 0=false, 1=true
                int passengerClass = sc.nextInt();  // passenger class, 1=1st, 2=2nd or 3rd
                String name = sc.next();
                String gender = sc.next();
                int age = sc.nextInt();
                int siblingsAndSpouses = sc.nextInt();
                int parentsAndChildren = sc.nextInt();
                String ticketNumber = sc.next();
                double fare = sc.nextDouble();
                String cabin = sc.next();
                String embarkedAt = sc.next();

                System.out.println(passengerId + ", " + name);

                passengerList.add(
                        new Passenger(passengerId, survived, passengerClass,
                                name, gender, age, siblingsAndSpouses, parentsAndChildren, ticketNumber,
                                fare, cabin, embarkedAt)
                );
            }
        } catch (FileNotFoundException exception) {
            System.out.println("FileNotFoundException caught. The file " + fileName + " may not exist." + exception);
        }
    }

    public static void displayAllPassengers(ArrayList<Passenger> passengerList) {
        System.out.println("Displaying all passengers:");
        for (Passenger passenger : passengerList) {
            System.out.println(passenger);
        }
    }

    // question 1
    public static String[] getPassengerName(ArrayList<Passenger> passengers) {
        String[] passengername = new String[passengers.size()];
        for (int i = 0; i < passengers.size(); i++) {
            passengername[i] = passengers.get(i).getName();
        }
        return passengername;
    }
// SIDE NOTE
    // list size can be altered
    // stored in the order they were made in (start at 0)
    // you use . size()
    //
// question2
    public static List<Passenger> getPassengersContainingName(List<Passenger> passengers, String name) {
        List<Passenger> passengercontains = new ArrayList<>();// arraylist
        for (Passenger passenger1 : passengers) {
            if (passenger1.getName().toLowerCase().contains(name.toLowerCase())) {
                passengercontains.add(passenger1);
            }
        }
        return passengercontains;
    }

    // question3
    public static List<Passenger> getPassengerslderThan(List<Passenger> passengers, int age) {
        List<Passenger> passengerolder = new ArrayList<>();// arraylist
        for (Passenger passenger1 : passengers) {
            if (passenger1.getAge() >= age) {
                passengerolder.add(passenger1);
            }
        }
        return passengerolder;
    }

    // question 4
    // we have to use equalsignorcase becasue you are comparing two string to each other not two numbers
    public static int countPassengersByGender(List<Passenger> passengers, String gender) {
        int counter = 0;
        for (Passenger passenger1 : passengers) {
            if (passenger1.getGender().equalsIgnoreCase(gender)) {
                counter++;
            }
        }
        return counter;
    }

    // question 5
    // cant ask for an int while you have a double declared  :(
    public static Double sumFares(List<Passenger> passengers) {
        double total = 0.0;
        for (Passenger passenger1 : passengers) {
            total += passenger1.getFare();
        }
        return total;
    }

    // question 6 ask about this mess hehehehheh

    // previous attempt
//        for (Passenger passenger1 : passengers) {
//            if (passenger1.getSurvived() && passenger1.getGender().equalsIgnoreCase("male"))  {
//                passengersurvivorsmale.add(passenger1);
//            }
//        }

    public static List<String> maleSurvivors(List<Passenger> passengers) {
        List<String> passengersurvivorsmale = new ArrayList<>();
        for (Passenger passenger1 : passengers) {
            if (passenger1.getGender().equalsIgnoreCase("male") && passenger1.getSurvived() == 1) {
                passengersurvivorsmale.add(passenger1.getName());
            }
        }
        return passengersurvivorsmale;
    }

    // question 7
    public static Passenger ticketOwner(List<Passenger> passengers, String ticketnum) {
        // ArrayList  <Passenger> ticketowner = new ArrayList<>();

        for (Passenger passenger1 : passengers) {
            if (passenger1.getTicketNumber().equals(ticketnum)) {
//                ticketowner.add(passenger1);
                return passenger1;
            }

        }
        return null;
    }

    // question 8 get the average
    public static int averageAge(List<Passenger> passengers) {
        int total = 0;

        for (Passenger passenger1 : passengers) {
            total += passenger1.getAge();
        }
        return  total / passengers.size();
    }

    // question 9
    public static List<Passenger> getPassengersByTicketClass(List<Passenger> passengers, PassengerClass passengerClass) {
        List<Passenger> PassengersByTicketClass = new ArrayList<>();// arraylist
        for (Passenger passenger1 : passengers) {
            if (passenger1.getPassengerClass() == passengerClass) {
                PassengersByTicketClass.add(passenger1);
            }
        }
        return PassengersByTicketClass; // no brackets at the end
    }

    // question 10 for the sort you need your comparator
    public static void sortPassengersByPassengerId(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparing(Passenger::getPassengerId)); //
    }

    // question 11
    public static void sortPassengersByPassengerName(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparing(Passenger::getName));
    }

    // question 12 sort name within age
    public static void sortPassengersByAgeThenName(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparing(Passenger::getName).thenComparing(Passenger::getAge));
    }

    // question 13 sort the passenegers by gender then by passenger number
    public static void sortPassengersByGenderThenPassengerNumber(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparing(Passenger::getGender).thenComparing(Passenger::getPassengerId));

    }

    // question 14 sort the passengers by their ticket class
    public static void sortPassengersByFareThenSurvival(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparing(Passenger::getFare).thenComparing(Passenger::getSurvived));
    }

    // question 15 sort by ticket class !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static void sortPassengersByTicketClass(ArrayList<Passenger> passengers) {
        passengers.sort(Comparator.comparing(Passenger::getTicketNumber));
    }

    // question 16
    public static List<Passenger> sortPassengersByAge ( List<Passenger> passengers ) {

        passengers.sort( new Comparator<Passenger>() {
            @Override
            public int compare( Passenger p1 , Passenger p2 ) {
                return Integer.compare( p1.getAge() , p2.getAge() );
            }
        });
        return passengers;

    }

    // question 17
    public static List<Passenger> sortPassengersByTicketNumberLambda ( List<Passenger> passengers ) {

        passengers.sort( ( p1 , p2 ) -> p1.getTicketNumber().compareTo(p2.getTicketNumber() ) );
        return passengers;
}
// question 18  static comparator question
    // SC is a way to call the comparator , we want to use our comparator in multiple places resuse
public static List<Passenger> sortPassengersByTicketNumberStatic(List<Passenger>passengers){
        passengers.sort(Passenger.TicketNumberComparator);
        return passengers;
}


// question 19

    public static Passenger findPassengerByTicketNumber(List<Passenger> passengerList, Passenger targetPassenger) {
        Collections.sort(passengerList, Passenger.TicketNumberComparator);

        int index1 = Collections.binarySearch(passengerList, targetPassenger, Passenger.TicketNumberComparator);

        if (index1 >= 0) {
            return passengerList.get(index1);
        } else {
            return null;
        }
    }

    // question 20
    public static Passenger findPassengerByPassengerId(List<Passenger> passengerList, Passenger targetPassenger) {
        Collections.sort(passengerList, Passenger.PassengerIdComparator);

        int index2 = Collections.binarySearch(passengerList, targetPassenger, Passenger.PassengerIdComparator);

        if (index2 >= 0) {
            return passengerList.get(index2);
        } else {
            return null;
        }
    }


    // ---------------------------------FIRST ATTEMPT-----------------------------------------------
//    // question 19
//
//    public static int findPassengerByTicketNumber(ArrayList<Passenger> passengers, Passenger ticketNumber) {
//        List<Passenger> PassengersByTicketNumber = new ArrayList<>();
//        Collections.sort(PassengersByTicketNumber);// to sort the array we have
//
//        int low = 0;
//        int high = passengers.size() - 1;
//
//        while (low <= high) {
//            int middlePosition = (low + high) / 2;
//            Passenger middlePassenger = passengers.get(middlePosition);
////
////            String middleTicketNumber = middlePassenger.getTicketNumber();
//
//            int comparisonResult = ticketNumber.compareTo(middlePassenger.getTicketNumber());
//
//            if (comparisonResult == 0) {
//                return middlePosition;
//            }
//
//            if (comparisonResult < 0) {
//                high = middlePosition - 1;
//            } else {
//                low = middlePosition + 1;
//            }
//        }
//
//        return -1; // return -1 if passenger not found
//    }
//    // question 20
//    public static int findPassengerByPassengerId(ArrayList<Passenger> passengers,int passengerid){
//        List<Passenger> PassengersByPassengerid = new ArrayList<>();
//        Collections.sort(PassengersByPassengerid);// to sort the array we have
//        int low=0;
//        int high = PassengersByPassengerid.size() -1;
//
//        while(low <= high){
//            int middlePosition = (low + high)/2;
//            int middleNumber =PassengersByPassengerid(middlePosition);
//
//            if(passengerid == middleNumber){
//                return middlePosition;
//            }
//            if(passengerid < middleNumber){
//                high = middlePosition-1;
//            }else{
//                low = middlePosition +1;
//            }
//        }
//        return -1; // to say sorry we didnt find it
//    }
}