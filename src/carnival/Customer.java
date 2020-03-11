package carnival;

/******************************************************************************
 * Customer.java 
 * Programmer: @author jcboyd
 * 
 * This class is associated with Carnival.java
 *****************************************************************************/

import java.util.*; //ArrayList, ArrayDeque

public class Customer 
{
    private String name;
    private int tickets;
    private ArrayList<String> backpack = new ArrayList<>(); //keep track of prizes
    private String[] hands = new String[2]; //0=left, 1=right
    
    public Customer(int tickets)
    {
        this.tickets = tickets;
        hands[0] = "empty";
        hands[1] = "empty";
    } //end Game constructor
    
    public void setName(String name)
    {
        this.name = name;
    } //end setName
    
    public String getName()
    {
        return name;
    } //end getName
    
    public int getTickets()
    {
        return tickets;
    } //end getTickets
    
    public void addTickets(int tickets)
    {
        this.tickets += tickets;
    } //end addTickets
    
    public void spendTickets(int tickets)
    {
        this.tickets -= tickets;
    } //end spendTickets
    
    public boolean checkTickets(int ticketPrice)
    {
        return this.tickets >= ticketPrice;
    } //end checkTickets
    
    public void addPrize(String prize)
    {
        backpack.add(prize);
    } //end addPrize

    public String getPrizes()
    {
        String outputString = "";
        for (String obj : backpack)
        {
            outputString = outputString + obj + "\n";
        } //end for each
        return outputString;
    } //end getPrizes
    
    public boolean backpackEmpty()
    {
        return backpack.isEmpty();
    } //end backpackFull
    
    public void holdFood(String food)
    {
        if (hands[0].equals("empty"))
        {
            hands[0] = food;
        }
        else
        {
            hands[1] = food;
        } //end if
    } //end addFood
    
    public String getHand1()
    {
        return hands[0];   
    } //end getLeftHand
    
    public String getHand2()
    {
        return hands[1];   
    } //end getRightHand
    
    public void eatFood(int handNumber)
    {
        System.out.printf("Yummm! That %s was good!\n", hands[handNumber]);
        hands[handNumber] = "empty";
    } //end eatFood
    
    public boolean availableHand()
    {
        return hands[0].equals("empty") | hands[1].equals("empty");
    } //end availableHand

} //end class Customer
