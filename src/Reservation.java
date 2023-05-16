import javax.print.attribute.standard.OrientationRequested;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    private int userID;
    private Room room;
    private ArrayList<Date> dates;
    private Status status;
    enum Status {
        REQUESTED,
        DENIED,
        ACCEPTED
    };
    private static  int nextReservationNumber = 1;
    private int reservationNumber;


    public Reservation(int userID, Room room, ArrayList<Date> dates, Status status) {
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

    public ArrayList<Date> getDates(){
        return this.dates;
    }

    public void delete(int reservationNumber){

    }
}
