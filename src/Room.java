import java.io.Serializable;

public class Room implements Serializable {
    private int roomNumber;
    private RoomType roomType;
    private double pricePerNight;
         public Room(int roomNumber , RoomType roomType , double pricePerNight){
             this.roomNumber = roomNumber;
             this.roomType = roomType ;
             this.pricePerNight = pricePerNight;
         }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}
