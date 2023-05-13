import java.util.Date;

public class Reservation {
    private int userID;
    private int roomID;
    private Date startDate;
    private Date endDate;
    private String status;
    private int reservationNumber;


    public Reservation(int userID, int roomID, Date startDate, Date endDate, String status, int reservationNumber) {
        this.userID = userID;
        this.roomID = roomID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.reservationNumber = reservationNumber;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public Reservation getReservationDetails(int reservationNumber){
        return this;
    }

    public void delete(int reservationNumber){

    }
}
