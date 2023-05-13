import java.util.Date;

public class Employee extends Person{

    public Employee(String firstName, String lastName, String email, String password, int telephone) {
        super(firstName, lastName, email, password, telephone);

    }

    public void reservationRequest(int roomId, Date date, int duration){

    }

    public void searchRoom(Date date, int capacity, String address){

    }

    public void modifyReservation(int reservationNumber, Date date, int duration){

    }

    public void CancelReservation(int reservationNumber){

    }

    public void MakeReview(int RoomId, int reservationNumber, String review){

    }
}
