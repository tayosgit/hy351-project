import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The Employee class represents an Employee that can search for rooms on the Booking platform and send a reservation
 * request, extending the Person class
 */

public class Employee extends Person{


    /**
     * Constructs an Employee object with the given information.
     *
     * @param firstName The first name of the employee.
     * @param lastName  The last name of the employee.
     * @param email     The email address of the employee.
     * @param password  The password of the employee.
     * @param telephone The telephone number of the employee.
     */
    public Employee(String firstName, String lastName, String email, String password, int telephone) {
        super(firstName, lastName, email, password, telephone);

    }

    /**
     * Requests a reservation for a room based on the provided parameters.
     *
     * @param room  The room to be reserved.
     * @param dates The list of dates for the reservation.
     * @return The Reservation object if successful, null otherwise.
     */
    public Reservation reservationRequest(Room room, List<Date> dates){
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

    /**
     * Searches for available rooms based on the provided dates and capacity.
     * 1st Case: Search with both capacity and dates.
     *
     * @param date    The list of dates for the search.
     * @param capacity The capacity of the room.
     * @param schedule The booking platform to search in.
     * @return The list of available rooms.
     */
    public List<Room> searchRoom(List<Date> date, int capacity, BookingPlatform schedule){
        return schedule.searchRoom(capacity, date);
    }

    /**
     * Searches for available rooms based on the provided capacity.
     * 2nd Case: Search with capacity.
     *
     * @param capacity The capacity of the room.
     * @param schedule The booking platform to search in.
     * @return The list of available rooms.
     */
    public List<Room> searchRoom(int capacity, BookingPlatform schedule){
        return schedule.searchRoom(capacity);
    }

    /**
     * Searches for available rooms based on the provided dates.
     * 3rd Case: Search with dates.
     *
     * @param date    The list of dates for the search.
     * @param schedule The booking platform to search in.
     * @return The list of available rooms.
     */
    public List<Room> searchRoom(List<Date> date, BookingPlatform schedule){
        return schedule.searchRoom(date);
    }

    /**
     * Searches for all available rooms.
     * 4th Case: Search without any filter, i.e. show all rooms.
     *
     * @param schedule The booking platform to search in.
     * @return The list of available rooms.
     */
    public List<Room> searchRoom(BookingPlatform schedule){
        return schedule.searchRoom();
    }

    public void modifyReservation(int reservationNumber, ArrayList<Date> date, int duration){
        // Implement for Bonus
    }

    public void cancelReservation(int reservationNumber){
        // Implement for Bonus
    }

    public void makeReview(int RoomId, int reservationNumber, String review){
        // Implement for Bonus
    }
}
