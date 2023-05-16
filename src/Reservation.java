import java.util.Date;
import java.util.List;

public class Reservation {
    private int userID;
    private Room room;
    private List<Date> dates;
    private Status status;
    enum Status {
        REQUESTED,
        DENIED,
        ACCEPTED
    }
    private static  int nextReservationNumber = 1;
    private int reservationNumber;


    public Reservation(int userID, Room room, List<Date> dates, Status status) {
        this.userID = userID;
        this.room = room;
        this.dates = dates;
        this.status = status;
        this.reservationNumber = nextReservationNumber;
        nextReservationNumber++;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public Reservation getReservationDetails(int reservationNumber){
        return this;
    }

    public void setStatus(Status stat){
        this.status = stat;
    }

    public Room getRoom(){
        return this.room;
    }

    public List<Date> getDates(){
        return this.dates;
    }

    public void delete(int reservationNumber){

    }
}
