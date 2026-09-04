import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Hotel hotel = Hotel.loadHotel();

        System.out.println("==== GRANDSTAY HOTEL ====");

         while(true){
             System.out.println("1. Add Room");
             System.out.println("2. Search Available Rooms");
             System.out.println("3. Book Room");
             System.out.println("4. Cancel Reservation");
             System.out.println("5. Generate Bill");
             System.out.println("6. Exit");

             System.out.println("Enter your choice");
             int choice = scanner.nextInt();

             switch(choice){
                 case 1:
                     System.out.println("Enter Room Number :");
                     int roomNumber = scanner.nextInt();
                     System.out.println("Select Room Type");
                     System.out.println("1. STANDARD");
                     System.out.println("2. DELUXE");
                     System.out.println("3. SUITE");

                     System.out.println("Select Room Type :");
                     int typeChoice = scanner.nextInt();
                     RoomType roomType;

                     if(typeChoice == 1){
                         roomType = RoomType.STANDARD;
                     }
                     else if( typeChoice == 2){
                         roomType = RoomType.DELUXE;
                     }
                     else if( typeChoice == 3){
                         roomType = RoomType.SUITE;
                     } else{
                         System.out.println(" Invalid Choice .....");
                         break;
                     }

                     System.out.println("Enter Price Per Night");
                     double PricePerNight = scanner.nextDouble();

                     Room room = new Room(roomNumber , roomType , PricePerNight);
                     hotel.addRoom(room);

                     System.out.println("Room Added Succesfully .");
                      break;
                 case 2:
                     System.out.println("Select Room Type");
                     System.out.println("1. STANDARD");
                     System.out.println("2. DELUXE");
                     System.out.println("3. SUITE");

                     System.out.println("Select Room Type :");
                     int typeChoice1 = scanner.nextInt();
                     RoomType roomType1;

                     if(typeChoice1 == 1){
                         roomType1 = RoomType.STANDARD;
                     }
                     else if( typeChoice1 == 2){
                         roomType1 = RoomType.DELUXE;
                     }
                     else if( typeChoice1 == 3){
                         roomType1 = RoomType.SUITE;
                     } else {
                         System.out.println(" Invalid Choice .....");
                         break;
                     }
                         System.out.println("Enter Checkin Date (YYYY-MM-DD");
                         LocalDate checkIn = LocalDate.parse(scanner.next());
                         System.out.println("Enter CheckOut Date (YYYY-MM-DD");
                         LocalDate checkOut = LocalDate.parse(scanner.next());

                         ArrayList<Room> availableRooms = hotel.searchavalablerooms( roomType1,checkIn,checkOut);

                         if(availableRooms.isEmpty()){
                             System.out.println("No Rooms Available");
                         }else {
                             System.out.println("Available Rooms :");

                             for(Room room1 : availableRooms){
                                 System.out.println("Room :" +room1.getRoomNumber()
                                 +" | Type :"+ room1.getRoomType()
                                 + " | Price: " + room1.getPricePerNight());
                             }
                         }
                         break;
                 case 3:
                     System.out.println("Enter Room Number");
                     int roomNumber1 = scanner.nextInt();

                     Room room2 = hotel.findRoom(roomNumber1);
                     if( room2 == null){
                         System.out.println("Room Not Found ." );
                         break;
                     }

                     System.out.println("Enter Guest ID :");
                     int guestId = scanner.nextInt();

                     scanner.nextLine();

                     System.out.println("Enter Guest Name :");
                     String guestName = scanner.next();

                     Guest guest = new Guest(guestId , guestName);
                     System.out.println("Enter Checkin Date (YYYY-MM-DD)");
                     LocalDate checkIn1 = LocalDate.parse(scanner.next());
                     System.out.println("Enter CheckOut Date (YYYY-MM-DD)");
                     LocalDate checkOut1 = LocalDate.parse(scanner.next());

                     if(checkIn1.isBefore(checkOut1)) {
                         boolean booked = hotel.bookRoom(room2, guest, checkIn1, checkOut1);

                         if (booked) {
                             System.out.println("Room booked successfully");
                         } else {
                             System.out.println("Room is not available for these dates");
                         }
                     } else{
                         System.out.println("Check-out Date must be after Check-in date");
                     }

                     break;

                 case 4:
                     System.out.println("Enter Room Number ");
                     int roomNumber2 = scanner.nextInt();

                     Room room3 = hotel.findRoom(roomNumber2);
                     if( room3 == null){
                         System.out.println("Not Room Found ");
                         break;
                     }
                     System.out.println("Enter Guest ID :");
                     int guestid1 = scanner.nextInt();

                     scanner.nextLine();

                     System.out.println("Enter Guest Name :");
                     String guestName1 = scanner.next();

                     Guest guest1 = new Guest(guestid1,guestName1);

                     boolean cancelled = hotel.cancelReservation(room3 , guest1);
                     if(cancelled){
                         System.out.println("Reservation Cancelled Successfully ");
                     }else {
                           System.out.println("Reservtion Not Found");
                     }
                     break;
                 case 5:
                     System.out.println("Enter Room Number");
                     int roomNumber4 = scanner.nextInt();

                     System.out.println("Enter Guest ID");
                     int guestId2 = scanner.nextInt();

                     Reservation reservation = hotel.findReservation(guestId2 , roomNumber4);

                     if(reservation == null){
                         System.out.println("reservation Not Found");
                         break;
                     }
                     long nights = reservation.getnights();
                     double bill = hotel.generateBill(reservation);

                     System.out.println("\n==== GRANDSTAY BILL ====");
                     System.out.println("Guest ID : " + reservation.getGuest().getGuestId());
                     System.out.println("Guest Name : " +reservation.getGuest().getGuestName());
                     System.out.println("Room Number : " + reservation.getRoom().getRoomNumber());
                     System.out.println("Room Type : " + reservation.getRoom().getRoomType());
                     System.out.println("Price Per Nights : " + reservation.getRoom().getPricePerNight());
                     System.out.println("Nights : " + nights);
                     System.out.println("Total Bill : "+ bill);
                     System.out.println("==========================");
                     break;
                 case 6:
                     hotel.saveHotel();
                     System.out.println("GoodBye......");
                     scanner.close();
                     return;

                 default:
                     System.out.println("invalid Choice");

             }

         }
    }
}