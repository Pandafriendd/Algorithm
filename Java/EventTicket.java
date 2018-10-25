
import java.util.*;

public class EventTicket {
	
	public static class Ticket {
        int id;
        int x;
        int y;
        int price;
        
        public Ticket(int id, int x, int y, int price) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.price = price;
        }
    }
    
    public static void main(String args[] ) throws Exception {
        
        @SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
        
        int sizeOfWorld = Integer.parseInt(scan.nextLine());
        int numberOfEvents = Integer.parseInt(scan.nextLine());
        List<Ticket> ticketsList = new ArrayList<>();  // store all the events, I define the same event id but has different prices to be different event
        
        while(numberOfEvents > 0){
            String eventLine = scan.nextLine();
            // TODO: you will need to parse and store the events
            String[] event = eventLine.split(" ");
            int id = Integer.parseInt(event[0]);
            int eventX = Integer.parseInt(event[1]);
            int eventY = Integer.parseInt(event[2]);
            if (eventX < sizeOfWorld &&  eventY < sizeOfWorld && eventX >= 0 && eventY >= 0) {  // chekcing if the event is valid
                for (int i = 3; i < event.length; i++) {
                    ticketsList.add(new Ticket(id, eventX, eventY, Integer.parseInt(event[i])));
                }   
            }
            numberOfEvents--;
        }
        
        int numberOfBuyers = Integer.parseInt(scan.nextLine());
        while(numberOfBuyers > 0){
            String buyerLine = scan.nextLine();
            // TODO: you will need to parse and store the buyers
            String[] buyer = buyerLine.split(" ");
            int buyerX = Integer.parseInt(buyer[0]);
            int buyerY = Integer.parseInt(buyer[1]);
            
            Ticket t = getTicket(buyerX, buyerY, ticketsList, sizeOfWorld);
            if (t != null) {
                System.out.println(t.id + " " + t.price); 
            } 
            else {
                System.out.println("-1 0"); 
            }
            numberOfBuyers--;
        }

        // The solution to the first sample above would be to output the following to console:
        // (Obviously, your solution will need to figure out the output and not just hard code it)
        // System.out.println("2 50");
    }
                              
    public static Ticket getTicket(int buyerX, int buyerY, List<Ticket> ticketsList, int sizeOfWorld) {
        if (buyerX >= sizeOfWorld || buyerX < 0 || buyerY >= sizeOfWorld || buyerY < 0) {  // corner cases checking
            return null;
        }
        if (ticketsList.isEmpty()) {
            return null;
        }
        
        Ticket cur = null;
        int index = 0;
        for (int i = 0; i < ticketsList.size(); i++) {
            if (compareTowTickets(cur, ticketsList.get(i), buyerX, buyerY) > 0) {  // find the most priority ticket, if found, update index and cur
                cur = ticketsList.get(i);
                index = i;
            }
        }
        return ticketsList.remove(index);
    }
    
    // if t2 has more priority than t1, return positive, if equal, return 0; otehrwise return negative                        
    public static int compareTowTickets(Ticket t1, Ticket t2, int buyerX, int buyerY) {
        if (t1 == null) {
            return 1;
        }
        int mdis1 = calculateManhattanDistance(t1.x, t1.y, buyerX, buyerY);
        int mdis2 = calculateManhattanDistance(t2.x, t2.y, buyerX, buyerY);
        if (mdis1 == mdis2) { // first compare ManhattanDistance
            if (t1.price == t2.price) {  // then compare price
                return t1.id - t2.id;  // finally compare id
            } 
            else {
                return t1.price - t2.price;
            }
        } 
        else {
            return mdis1 - mdis2;
        }
    }
                              
    // The following method get the manhatten distance betwen two points (x1,y1) and (x2,y2)
    public static int calculateManhattanDistance(int x1, int y1, int x2, int y2)    {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
