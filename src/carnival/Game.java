package carnival;

/******************************************************************************
 * Game.java 
 * Programmer: @author jcboyd
 * 
 * This class is associated with Carnival.java
 *****************************************************************************/

public class Game 
{
    private String name;
    private int ticketPrice;
    private String prize1, prize2, prize3;
    
    public Game(String name, int ticketPrice, String prize1, String prize2,
            String prize3)
    {
        this.name = name;
        this.ticketPrice = ticketPrice;
        this.prize1 = prize1;
        this.prize2 = prize2;
        this.prize3 = prize3;
    } //end Game constructor
    
    public String getName()
    {
        return name;
    } //end getName
    
    public int getTicketPrice()
    {
        return ticketPrice;
    } //end getTicketPrice
    
    public String getPrize1()
    {
        return prize1;
    } //end getPrize1
    
    public String getPrize2()
    {
        return prize2;
    } //end getPrize2
    
    public String getPrize3()
    {
        return prize3;
    } //end getPrize3
    
    public void playGame()
    {
        int result = (int)(Math.random() * 4 + 1);
        String prize;
        switch (result)
        {
            case 1: prize = getPrize1(); break;
            case 2: prize = getPrize2(); break;
            case 3: prize = getPrize3(); break;
            default: prize = "sorry, no prize"; break;
        }
        System.out.printf("Playing %s, and the result is: %s\n", name, prize);
        //XXXXXXXXXput prize in customer backpackXXXXXXXXX
    } //end playGame

} //end class Game
