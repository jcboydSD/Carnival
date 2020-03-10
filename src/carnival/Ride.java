package carnival;

/******************************************************************************
 * Ride.java 
 * Programmer: @author jcboyd
 * 
 * This class is associated with Carnival.java
 *****************************************************************************/

public class Ride 
{
    private String name;
    private int ticketPrice;
    
    public Ride(String name, int ticketPrice)
    {
        this.name = name;
        this.ticketPrice = ticketPrice;
    } //end Ride constructor
    
    public String getName()
    {
        return name;
    } //end getName
    
    public int getTicketPrice()
    {
        return ticketPrice;
    } //end getTicketPrice
    
} //end class Ride
