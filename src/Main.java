import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the booking platform!");
        /*
        * 1st use case: Room Reservation request
        * user needs to be logged-in
        * user sets the parameters for reservation; roomId, startDate, endDate
        * */
        Reservation reservation = makeRoomReservationRequest();

        //create Room
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 14);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.YEAR, 2023);
        ArrayList<Date> availableDates = new ArrayList<>();
        Date firstDay = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        Date secondDay = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 16);
        Date thirdDay = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 17);
        Date fourthDay = calendar.getTime();
        availableDates.add(firstDay);
        availableDates.add(secondDay);
        availableDates.add(thirdDay);
        availableDates.add(fourthDay);

        ArrayList<Date> unavailableDates = new ArrayList<>();
        unavailableDates.add(firstDay);
        unavailableDates.add(secondDay);
        Room room = new Room(25, 100, availableDates, "Meeting");

        viewRoomAvailability();
        ReservationApproved(reservation);
        //SignUpEmployee();
        //SignUpAdmin();
        AdminChangeAvailability(room, unavailableDates);
    }

    public static Reservation makeRoomReservationRequest(){
        System.out.println("1st Use-case \"Make room reservation request started\"...");
        // following lines of test data can be substituted later with a file/ databank
        // mininum required: 1 Employee, 1 Administrator, 1 Room, 1 Reservation

        // create employee
        Employee testEmployee = new Employee("Maria", "Cole", "m.cole@csd.uoc.gr", "12345678", 0345645645);

        //create Room
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 14);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.YEAR, 2023);
        ArrayList<Date> availableDates = new ArrayList<>();
        Date firstDay = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        Date secondDay = calendar.getTime();
        availableDates.add(firstDay);
        availableDates.add(secondDay);
//        for(Date date: availableDates){
//            System.out.println(date.toString());
//        }
        Room testRoom = new Room(0, 20 , availableDates, "Conference");


        // 1. Use Case (we assume the employee knows the room ID)
        return testEmployee.reservationRequest(testRoom, availableDates);
    }

    public static void viewRoomAvailability(){
        // 2. Use Case
        System.out.println("2nd Use-case \"View room availability started\"...");
        // following lines of test data can be substituted later with a file/ databank
        // mininum required: 1 Employee, 1 Administrator, 2 Room, 1 Schedule

        // create employee
        Employee testEmployee = new Employee("Maria", "Cole", "m.cole@csd.uoc.gr", "12345678", 0345645645);

        // create Rooms
        ArrayList<Date> availableDates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 14);
        calendar.set(Calendar.MONTH, Calendar.MAY);
        calendar.set(Calendar.YEAR, 2023);
        Date firstDay = calendar.getTime();
        availableDates.add(firstDay);

        calendar.set(Calendar.DAY_OF_MONTH, 15);
        Date secondDay = calendar.getTime();
        availableDates.add(secondDay);

        calendar.set(Calendar.DAY_OF_MONTH, 16);
        Date thirdDay = calendar.getTime();
        availableDates.add(thirdDay);

        Room testRoom = new Room(0, 20 , availableDates, "Conference");
        Room testRoom2 = new Room(1, 40, availableDates, "small");


        // create Schedule
        Schedule schedule = new Schedule(new ArrayList<Room>());

        // create Admin
        Administrator admin = new Administrator("Athina ", "Bitou", "a.bitou@csd.uoc.gr", "12345678", 54743566, schedule);

        admin.addRoomToList(testRoom);
        admin.addRoomToList(testRoom2);

        admin.getSchedule().addRoomToSchedule(testRoom);
        admin.getSchedule().addRoomToSchedule(testRoom2);

        // User searches without filters
        List<Room> allRooms = testEmployee.searchRoom(schedule);
        for(Room room : allRooms){
            System.out.println("Room available! RoomID: "+ room.getRoomID());
        }
        System.out.println();

        // User searches with min. capacity
        List<Room> capacityRooms = testEmployee.searchRoom(30, schedule);
        if(capacityRooms.size()==0){
            System.out.println("No Rooms available. Search again!");
        }
        else {
            System.out.println("For the capacity you gave, following rooms are available:");
            for(Room room : capacityRooms){
                System.out.println("Room with capacity " + room.getCapacity() + " available! RoomID: "+ room.getRoomID());
            }
        }
        System.out.println();

        // User searches with dates
        List<Date> dates = new ArrayList<>();
        dates.add(firstDay);
        List<Room> datesRooms = testEmployee.searchRoom(dates, schedule);
        if(datesRooms.size() == 0){
            System.out.println("No Rooms available. Search again!");
        } else {
            for(Room room : datesRooms){
                System.out.println("Room with ID "+ room.getRoomID() +"  at Date(s) " + room.getAvailability() + " available!");
            }
        }
        System.out.println();

        // User searches with both filters (capacity and time)
        List<Room> filterRooms = testEmployee.searchRoom(dates, 49, schedule);
        if(filterRooms.size()==0){
            System.out.println("No Rooms available. Search again!");
        }
        else {
            System.out.println("For the filters given, the following rooms are available");
            for(Room room : filterRooms){
                System.out.println("Room with capacity " + room.getCapacity() + " available! RoomID: "+ room.getRoomID());
            }
        }



        //System.out.println(firstQuery);


    }


    public static void ReservationApproved(Reservation reservation){
        //3. Use case
        System.out.println("3rd Use-case \"Approve reservation request from administrator started\"...");
        //minimum required 1 admin, 1 reservation request

         // create Schedule
         Schedule schedule = new Schedule(new ArrayList<Room>());

        // create Admin
        Administrator admin = new Administrator("Athina ", "Bitou", "a.bitou@csd.uoc.gr", "12345678", 54743566, schedule);

        if(reservation.getRoom().isAvailable(reservation.getDates())==true){
            admin.confirmReservation("Your reservation is approved!", reservation);
            admin.updateAvailability(reservation.getRoom(), reservation.getDates());
        }else{
            System.out.println("Reservation denied");
            reservation.setStatus(Reservation.Status.DENIED);
        }
    }

    //Use case 4
    public static void SignUpEmployee(){
        //4. Use case
        System.out.println("4rd Use-case \"SignUp of Employee started\"...");
        String firstName;
        String lastName;
        String email;
        String password;
        int telephone;

        Scanner console = new Scanner(System.in);
        
        System.out.print("Enter first name: ");
        firstName = console.nextLine();

        System.out.print("Enter last name: ");
        lastName = console.nextLine();

        System.out.print("Enter email: ");
        email = console.nextLine();

        System.out.print("Enter password: ");
        password = console.nextLine();

        System.out.print("Enter telephone: ");
        telephone = console.nextInt();

        Employee employee = new Employee(firstName, lastName, email, password, telephone);

        System.out.print("Your Sign Up is completed! Your user ID is: "+ employee.getPersonID());

    }

   
    public static void SignUpAdmin(){

        //4. Use case
        System.out.println("4rd Use-case \"SignUp of Administrator started\"...");

        String firstName;
        String lastName;
        String email;
        String password;
        int telephone;

        Scanner console = new Scanner(System.in);
        
        System.out.print("Enter first name: ");
        firstName = console.nextLine();

        System.out.print("Enter last name: ");
        lastName = console.nextLine();

        System.out.print("Enter email: ");
        email = console.nextLine();

        System.out.print("Enter password: ");
        password = console.nextLine();

        System.out.print("Enter telephone: ");
        telephone = console.nextInt();

        Schedule schedule = new Schedule(new ArrayList<Room>());

        Administrator administrator = new Administrator(firstName, lastName, email, password, telephone, schedule);

        System.out.print("Your Sign Up is completed! Your user ID is: "+ administrator.getPersonID());

    }

    //Use case 5
    public static void AdminChangeAvailability(Room room, List<Date> dates){
        //5. Use case
        System.out.println("4rd Use-case \"Administrator changes availability\"...");

        room.RemoveAvailability(dates);

        System.out.print("Availability of room "+ room.getRoomID()+"changed!");
    }
}