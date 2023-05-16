import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BookingPlatform {
    private List<Room> roomList;

    public BookingPlatform(List<Room> roomList) {
        this.roomList = roomList;
    }

    // 1st Case: Search with both capacity and dates
    public List<Room> searchRoom(int capacity, List<Date> dates){
        List<Room> resultList = searchRoom(capacity).stream().filter(room->room.isAvailable(dates)).collect(Collectors.toList());
        return resultList;
    }

    // 2nd Case: Search with capacity
    public List<Room> searchRoom(int capacity){
        List<Room> resultList = roomList.stream().filter(room->room.getCapacity()>=capacity).collect(Collectors.toList());
        return resultList;
    }

    // 3rd Case: Search with dates
    public List<Room> searchRoom(List<Date> dates){
        List<Room> resultList = roomList.stream().filter(room->room.isAvailable(dates)).collect(Collectors.toList());
        return resultList;
    }

    // 4th Case: Search without any filter, i.e. show all rooms
    public List<Room> searchRoom(){
        return roomList;
    }


    // Update room schedule
    public void addRoomToSchedule(Room room){
        this.roomList.add(room);
    }

    public void deleteRoomFromSchedule(Room room){
        this.roomList.remove(room);
    }

    public Employee signUpEmployee(){
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

        System.out.print("Your Sign Up is completed! Your user ID is: "+ employee.getPersonID() + "\n");
        return employee;

    }
    public Administrator signUpAdmin(){
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


        Administrator administrator = new Administrator(firstName, lastName, email, password, telephone, this);

        System.out.print("Your Sign Up is completed! Your user ID is: "+ administrator.getPersonID() + "\n");

        return administrator;

    }
}
