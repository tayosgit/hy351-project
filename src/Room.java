import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Room {
    private int roomID;
    private int capacity;
    private List<Date> availability;
    private String type;

    public Room(int roomID, int capacity, List<Date> availability, String type) {
        this.roomID = roomID;
        this.capacity = capacity;
        this.availability = availability;
        this.type = type;
    }

    public boolean isAvailable(List<Date> dates){
        boolean available = true;
        for (Date d : dates) {
            if (!availability.contains(d)) {
                available = false;
                break;
            }
        }
        return available;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRoomID() {
        return this.roomID;
    }

    public List<Date> getAvailability() {
        return availability;
    }
}
