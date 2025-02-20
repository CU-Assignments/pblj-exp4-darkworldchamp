//2.Develop a ticket booking system with synchronized threads to ensure no double booking of seats. Use thread priorities to simulate VIP bookings being processed first.
class TicketBookingSystem {
    private int availableSeats;

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    // Synchronized method to book a seat
    public synchronized boolean bookSeat(String customerType) {
        if (availableSeats > 0) {
            System.out.println(customerType + " booked a seat. Seats left: " + (availableSeats - 1));
            availableSeats--;
            return true;
        } else {
            System.out.println(customerType + " could not book a seat. No seats left.");
            return false;
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem bookingSystem;
    private String customerType;

    public BookingThread(TicketBookingSystem bookingSystem, String customerType, int priority) {
        this.bookingSystem = bookingSystem;
        this.customerType = customerType;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(customerType);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(5); // Assume 5 seats available

        // Create threads for VIP and regular customers
        Thread vip1 = new BookingThread(bookingSystem, "VIP Customer 1", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(bookingSystem, "VIP Customer 2", Thread.MAX_PRIORITY);
        Thread regular1 = new BookingThread(bookingSystem, "Regular Customer 1", Thread.NORM_PRIORITY);
        Thread regular2 = new BookingThread(bookingSystem, "Regular Customer 2", Thread.NORM_PRIORITY);
        Thread regular3 = new BookingThread(bookingSystem, "Regular Customer 3", Thread.NORM_PRIORITY);

        // Start the threads
        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();
        regular3.start();

        // Wait for all threads to finish
        try {
            vip1.join();
            vip2.join();
            regular1.join();
            regular2.join();
            regular3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
