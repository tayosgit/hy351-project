import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the booking platform!");
        /*
        * 1st use case: Room Reservation request
        * user needs to be logged-in
        * user sets the parameters for reservation; roomId, startDate, endDate
        * */

        List<Room> listOfRooms = parseRoomsFromInput();
        List<Administrator> listOfAdmins = parseAdminsFromInput();
        List<Employee> listOfEmployees = parseEmployeesFromInput();

        // 1st Use Case
        Reservation reservation = makeRoomReservationRequest(listOfRooms, listOfEmployees, listOfAdmins);

        // 2nd Use Case
        viewRoomAvailability(listOfRooms, listOfEmployees, listOfAdmins);

        // 3rd Use Case
        // will only run, when a reservation request was created
        if(reservation==null){
            System.out.println("Randomly generated Reservation was not valid. Try again by running main again.");
        }
        else{
            reservationApproved(reservation, listOfAdmins);
        }

        // 4th Use Case
        BookingPlatform schedule = new BookingPlatform(listOfRooms);
        System.out.println("Use-case 4.1: Sign-Up Employee");
        Employee employee = schedule.signUpEmployee();
        System.out.println("Use-case 4.2: Sign-Up Admin");
        Administrator admin = schedule.signUpAdmin();

        // 5th Use Case
        System.out.println("Use case 5: Admin updates availability for a room");
        deleteAvailability(listOfRooms, listOfEmployees, listOfAdmins);
    }


    public static Reservation makeRoomReservationRequest(List<Room> listOfRooms, List<Employee> listOfEmployees, List<Administrator> listOfAdmins){
        System.out.println("1st Use-case \"Make room reservation request started\"...");

        List<Date> dateRange = generateDatesInRange("29.04.2023", "03.05.2023");
        List<Date> randomDateList = new ArrayList<Date>();
        if (!dateRange.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Random random = new Random();
            Date randomDate = dateRange.get(random.nextInt(dateRange.size()));
            System.out.println("Random Date selected by Employee: " + dateFormat.format(randomDate));
            randomDateList.add(randomDate);
        }
        // 1. Use Case employee chooses a random room on a random date and issues a Reservation request
        Room randomRoom = listOfRooms.get(new Random().nextInt(listOfRooms.size()));
        System.out.println("Room selected by employee: " + randomRoom.getRoomID());
        return listOfEmployees.get(1).reservationRequest(randomRoom, randomDateList);
    }

    public static void viewRoomAvailability(List<Room> listOfRooms, List<Employee> listOfEmployees, List<Administrator> listOfAdmins){
        // 2. Use Case
        System.out.println("2nd Use-case \"View room availability started\"...");
        // following lines of test data can be substituted later with a file/ databank
        //select admin
        Administrator admin = listOfAdmins.get(new Random().nextInt(listOfAdmins.size()));
        // Get Schedule, which is managed by admin
        BookingPlatform schedule = admin.getSchedule();

        //add rooms to the admin and the schedule
        for(Room room: listOfRooms){
            admin.addRoomToList(room);
            admin.getSchedule().addRoomToSchedule(room);
        }
        Employee randomEmployee = listOfEmployees.get(new Random().nextInt(listOfEmployees.size()));
        // User searches without filters
        List<Room> allRooms = randomEmployee.searchRoom(schedule);
        for(Room room : allRooms){
            System.out.println("Room available! RoomID: "+ room.getRoomID());
        }
        System.out.println();
        // User searches with min. capacity
        List<Room> capacityRooms = randomEmployee.searchRoom(30, schedule);
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
        List<Date> randomDate = generateDatesInRange("22.04.2023", "29.04.2023");
        List<Date> dates = new ArrayList<>();
        dates.add(randomDate.get(new Random().nextInt(randomDate.size())));
        List<Room> datesRooms = randomEmployee.searchRoom(dates, schedule);
        if(datesRooms.size() == 0){
            System.out.println("No Rooms available. Search again!");
        } else {
            for(Room room : datesRooms){
                System.out.println("Room with ID "+ room.getRoomID() +"  at Date(s) " + room.getAvailability() + " available!");
            }
        }
        System.out.println();

        // User searches with both filters (capacity and time)
        List<Room> filterRooms = randomEmployee.searchRoom(dates, 49, schedule);
        if(filterRooms.size()==0){
            System.out.println("No Rooms available. Search again!");
        }
        else {
            System.out.println("For the filters given, the following rooms are available");
            for(Room room : filterRooms){
                System.out.println("Room with capacity " + room.getCapacity() + " available! RoomID: "+ room.getRoomID());
            }
        }
    }


    public static void reservationApproved(Reservation reservation, List<Administrator> listOfAdmins){
        //3. Use case
        System.out.println("3rd Use-case \"Approve reservation request from administrator started\"...");
        //minimum required 1 admin, 1 reservation request

        Administrator admin = listOfAdmins.get(new Random().nextInt(listOfAdmins.size()));
         // create Schedule
         BookingPlatform schedule = admin.getSchedule();

        if(reservation.getRoom().isAvailable(reservation.getDates())){
            admin.confirmReservation("Your reservation is approved!", reservation);
            admin.deleteAvailability(reservation.getRoom(), reservation.getDates());
        }else{
            System.out.println("Reservation denied");
            reservation.setStatus(Reservation.Status.DENIED);
        }
    }

    public static void deleteAvailability(List<Room> listOfRooms, List<Employee> listOfEmployees, List<Administrator> listOfAdmins){
        // randomDate.get(new Random().nextInt(randomDate.size()))
        Administrator admin = listOfAdmins.get(0);
        for(Room r : listOfRooms) {
            admin.addRoomToList(r);
        }
        Room randomRoom = listOfRooms.get(new Random().nextInt(listOfRooms.size()));
        List<Date> availabilityForRandomRoom = randomRoom.getAvailability();
        System.out.println("Initial availability:\n" + availabilityForRandomRoom);
        Date dateToRemove = availabilityForRandomRoom.get(new Random().nextInt(availabilityForRandomRoom.size()));
        List<Date> removeDates = new ArrayList<Date>();
        removeDates.add(dateToRemove);
        admin.deleteAvailability(randomRoom, removeDates);
        System.out.println("Availability after:\n" + randomRoom.getAvailability());

    }

    // Helper functions
    // Functions to parse Data from the text files
    public static List<Room> parseRoomsFromInput(){
        List<Room> parsedRooms = new ArrayList<Room>();
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            BufferedReader br = new BufferedReader(new FileReader(new File("./data/room.txt")));
            String line = br.readLine();
            while (line != null){
                String[] data = line.split(",");
                int roomId = Integer.parseInt(data[0]);
                int roomCapacity = Integer.parseInt(data[1]);
                String[] datesString = data[2].substring(1,data[2].length()-1).split("#");
                List<Date> dates = new ArrayList<Date>();
                for(String s : datesString){
                    Date date = dateFormat.parse(s);
                    dates.add(date);
                }
                String roomType = data[3];
                parsedRooms.add(new Room(roomId, roomCapacity, dates, roomType));
                line = br.readLine();
            }
        }catch (Exception fileNotFoundException){
            System.out.println(fileNotFoundException);
        }
        return parsedRooms;
    }

    public static List<Employee> parseEmployeesFromInput(){
        List<Employee> parsedEmployees = new ArrayList<Employee>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("./data/employee.txt")));
            String line = br.readLine();
            while (line != null){
                String[] data = line.split(",");
                String firstName = data[0];
                String lastName = data[1];
                String email = data[2];
                String password = data[3];
                int phone = Integer.parseInt(data[4]);
                parsedEmployees.add(new Employee(firstName, lastName, email, password, phone));
                line = br.readLine();
            }
        }catch (Exception fileNotFoundException){
            System.out.println(fileNotFoundException);
        }
        return parsedEmployees;
    }

    public static List<Administrator> parseAdminsFromInput(){
        List<Administrator> parsedAdministrator = new ArrayList<Administrator>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("./data/admin.txt")));
            String line = br.readLine();
            while (line != null){
                String[] data = line.split(",");
                String firstName = data[0];
                String lastName = data[1];
                String email = data[2];
                String password = data[3];
                int phone = Integer.parseInt(data[4]);
                parsedAdministrator.add(new Administrator(firstName, lastName, email, password, phone, new BookingPlatform(new ArrayList<Room>())));
                line = br.readLine();
            }
        }catch (Exception fileNotFoundException){
            System.out.println(fileNotFoundException);
        }
        return parsedAdministrator;
    }

    public static List<Date> generateDatesInRange(String firstDate, String lastDate) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<Date> dates = new ArrayList<>();

        try {
            Date startDate = dateFormat.parse(firstDate);
            Date endDate = dateFormat.parse(lastDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            while (!calendar.getTime().after(endDate)) {
                Date currentDate = calendar.getTime();
                dates.add(currentDate);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        return dates;
    }
}