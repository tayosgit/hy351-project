import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Schedule {
    private List<Room> roomList;

    public Schedule(List<Room> roomList) {
        this.roomList = roomList;
    }

    // 1st Case: Search with both capacity and dates
    public List<Room> searchRoom(int capacity, List<Date> dates){
        List<Room> resultList = searchRoom(capacity).stream().filter(room->room.isAvailable(dates)).collect(Collectors.toList());
        return resultList;
    }

    // 2nd Case: Search with capacity
    public List<Room> searchRoom(int capacity){
        List<Room> resultList = roomList.stream().filter(room->room.getCapacity()>=capacity).collect(Collectors.toList());
        return resultList;
    }

    // 3rd Case: Search with dates
    public List<Room> searchRoom(List<Date> dates){
        List<Room> resultList = roomList.stream().filter(room->room.isAvailable(dates)).collect(Collectors.toList());
        return resultList;
    }

    // 4th Case: Search without any filter, i.e. show all rooms
    public List<Room> searchRoom(){
        return roomList;
    }


    // Update room schedule
    public void addRoomToSchedule(Room room){
        this.roomList.add(room);
    }

    public void deleteRoomFromSchedule(Room room){
        this.roomList.remove(room);
    }

}
