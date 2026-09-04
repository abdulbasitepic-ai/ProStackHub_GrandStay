import java.time.LocalDate;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;

public class Reservation implements Serializable {
    private Room room;
    private Guest guest;
    private LocalDate checkin;
    private LocalDate checkOut;

         public Reservation(Room room , Guest guest , LocalDate checkin , LocalDate checkOut){
             this.room = room;
             this.guest = guest;
             this.checkin = checkin;
             this.checkOut = checkOut;
         }

    public Guest getGuest() {
        return guest;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public long getnights(){
             return ChronoUnit.DAYS.between(checkin , checkOut);
    }
}