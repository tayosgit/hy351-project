import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee extends Person{

    public Employee(String firstName, String lastName, String email, String password, int telephone) {
        super(firstName, lastName, email, password, telephone);
        
    }

    public Reservation reservationRequest(Room room, ArrayList<Date> dates){
        if(room.isAvailable(dates)){
            System.out.println("Room is available.");
            Reservation reservation = new Reservation(getPersonID(), room, dates, Reservation.Status.REQUESTED);
            System.out.println(("Successfully created a Reservation Request. The servation ID is" + reservation.getReservationNumber()));
            return reservation;
        } else {
            System.out.println("Failed. Room not available.");
            return null;
        }

    }

    // 1st Case: Search with both capacity and dates
    public List<Room> searchRoom(List<Date> date, int capacity, Schedule schedule){
        return schedule.searchRoom(capacity, date);
    }

    // 2nd Case: Search with capacity
    public List<Room> searchRoom(int capacity, Schedule schedule){
        return schedule.searchRoom(capacity);
    }

    // 3rd Case: Search with dates
    public List<Room> searchRoom(List<Date> date, Schedule schedule){
        return schedule.searchRoom(date);
    }

    // 4th Case: Search without any filter, i.e. show all rooms
    public List<Room> searchRoom(Schedule schedule){
        return schedule.searchRoom();
    }

    public void modifyReservation(int reservationNumber, ArrayList<Date> date, int duration){

    }

    public void cancelReservation(int reservationNumber){

    }

    public void makeReview(int RoomId, int reservationNumber, String review){

    }
}
