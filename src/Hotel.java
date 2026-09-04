import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Hotel implements Serializable {

    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

          public Hotel(){
              rooms = new ArrayList<>();
              reservations = new ArrayList<>();
          }

          public void addRoom(Room room){
              rooms.add(room);
          }

          public Room findRoom(int roomNumer){
               for( Room room : rooms){
                   if(room.getRoomNumber() == roomNumer){
                       return room;
                   }
               }
               return null;
          }

          public ArrayList<Room> searchavalablerooms(RoomType roomType , LocalDate checkin , LocalDate checkout){
              ArrayList<Room> availableRooms = new ArrayList<>();
              for( Room room : rooms){
                  if(room.getRoomType() == roomType){
                      boolean available = true;

                      for (Reservation reservation : reservations){
                          if(reservation.getRoom().getRoomNumber() == room.getRoomNumber()){
                              if(checkin.isBefore(reservation.getCheckOut()) &&
                               checkout.isAfter(reservation.getCheckin())){
                                  available = false;
                                  break;
                              }
                          }
                      }
                      if(available){
                          availableRooms.add(room);
                      }
                  }
              }
              return availableRooms;
          }

          public boolean bookRoom( Room room , Guest guest , LocalDate checkin , LocalDate checkout){
              ArrayList<Room> availableRooms = searchavalablerooms(room.getRoomType() , checkin , checkout);
              for( Room availableRoom : availableRooms){
                  if( availableRoom.getRoomNumber() == room.getRoomNumber()){
                      Reservation reservation = new Reservation(room , guest , checkin , checkout);

                      reservations.add(reservation);

                      return true;
                  }
              }
              return false;
          }

    public boolean cancelReservation(Room room, Guest guest) {

        Iterator<Reservation> iterator = reservations.iterator();

        while (iterator.hasNext()) {

            Reservation reservation = iterator.next();

            if (reservation.getGuest().getGuestId() == guest.getGuestId()
                    && reservation.getRoom().getRoomNumber() == room.getRoomNumber()) {

                iterator.remove();
                return true;
            }
        }

        return false;
    }

          public double generateBill(Reservation reservation){
              double pricePerNight = reservation.getRoom().getPricePerNight();
              long nights = reservation.getnights();
              return pricePerNight*nights;

          }

          public Reservation findReservation(int guestId , int roomNumber){
             for( Reservation reservation : reservations){
                 if(reservation.getGuest().getGuestId() == guestId
                    && reservation.getRoom().getRoomNumber() == roomNumber){
                     return reservation;
                 }
             }
             return null;
          }

    public void saveHotel() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("grandstay.dat"))) {

            out.writeObject(this);
            System.out.println("Hotel data saved.");

        } catch (IOException e) {
            System.out.println("Error saving hotel data.");
        }
    }

    public static Hotel loadHotel() {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("grandstay.dat"))) {

            return (Hotel) in.readObject();

        } catch (FileNotFoundException e) {
            return new Hotel();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading hotel data.");
            return new Hotel();
        }
    }

}

