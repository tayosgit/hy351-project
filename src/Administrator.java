import java.util.Date;

public class Administrator extends Person{

    public Administrator(String firstName, String lastName, String email, String password, int telephone) {
        super(firstName, lastName, email, password, telephone);
    }

    public void confirmReservation(String message, int reservationNumber){

    }

    public void updateAvailability(Date startDate, Date endDate, String Room){

    }

    public void updateRoomInfo(int roomID){

    }
}
