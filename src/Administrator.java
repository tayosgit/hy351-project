import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Administrator extends Person{
    private ArrayList<Room> roomList;
    private BookingPlatform schedule;
    // roomList as an optional attribute, therefore two constructors
    public Administrator(String firstName, String lastName, String email, String password, int telephone, List<Room> roomList, BookingPlatform schedule) {
        super(firstName, lastName, email, password, telephone);
        this.schedule = schedule;
    }
    public Administrator(String firstName, String lastName, String email, String password, int telephone, BookingPlatform schedule) {
        super(firstName, lastName, email, password, telephone);
        roomList = new ArrayList<>();
        this.schedule = schedule;
    }

    public void confirmReservation(String message, Reservation reservation){
        reservation.setStatus(Reservation.Status.ACCEPTED);
        System.out.println(message);

    }

    public void deleteAvailability(Room room, List<Date> dates){
        room.removeAvailability(dates);
    }

    public void updateRoomInfo(int roomID){
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public void addRoomToList(Room room) {
        roomList.add(room);
    }

    public BookingPlatform getSchedule(){
        return this.schedule;
    }
}
