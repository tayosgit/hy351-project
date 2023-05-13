import java.util.Date;

public class Room {
    private int roomID;
    private int capacity;
    private Date availability;
    private String type;

    public Room(int roomID, int capacity, Date availability, String type) {
        this.roomID = roomID;
        this.capacity = capacity;
        this.availability = availability;
        this.type = type;
    }

    public boolean isAvailable(){
        return true;
    }
}
