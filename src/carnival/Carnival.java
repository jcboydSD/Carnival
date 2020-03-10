package carnival;

/******************************************************************************
 * Carnival.java 
 * Programmer: @author jcboyd
 * Version: 1.0
 * Course: SDEV 2210
 *
 * This program simulates a carnival experience with games, rides, food, and
 * prizes using Arrays, ArrayLists, switch, for-each, and random numbers
 *****************************************************************************/

import java.util.*; //Scanner, ArrayList

public class Carnival 
{
    public static void main(String[] args) 
    {
        //declarations
        Scanner stdIn = new Scanner(System.in);
        Customer customer = new Customer(20); //set up customer with 20 tickets
        Game[] games = new Game[3]; //array for 3 games
        Ride[] rides = new Ride[2]; //array for 2 rides
        Food[] foods = new Food[4]; //array for 4 foods
        int activityAnswer;
        boolean playAgain = true;
        String prizeWon;
        //call methods to populate arrays with data
        gameSetup(games); 
        rideSetup(rides); 
        foodSetup(foods);
        //output
        System.out.println("Final Project by JC Boyd\n");
        System.out.print("Welcome to the Carnival!\n What is your name? ");
        customer.setName(stdIn.nextLine());
        do 
        {
            prizeWon = "none";
            activityAnswer = activityMenu(customer.getTickets());
            switch (activityAnswer)
            {
                case 1: case 2: case 3: //play games
                    //customer checks pocket for tickets
                    if (customer.checkTickets(games[activityAnswer - 1].getTicketPrice())) 
                    {
                        //customer pays tickets for selected game
                        customer.spendTickets(games[activityAnswer - 1].getTicketPrice());
                        //customer plays game and gets result
                        prizeWon = games[activityAnswer - 1].playGame(); //play
                    }
                    else
                    {
                        shortGameTickets();
                    }
                    break;
                case 4: //buy more tickets
                    System.out.print("How many tickets do you want to buy? ");
                    int numberTickets = stdIn.nextInt();
                    stdIn.nextLine(); //clear newline
                    customer.addTickets(numberTickets);
                    break;
                case 5: case 6: //go on rides
                    //customer checks pockets for tickets
                    if (customer.checkTickets(rides[activityAnswer - 5].getTicketPrice())) //check customer pocket for tickets
                    {
                        //customer pays tickets for selected ride
                        customer.spendTickets(rides[activityAnswer - 5].getTicketPrice());
                        //customer goes on ride
                        System.out.printf("Enjoy the %s ride!",   
                                rides[activityAnswer - 5].getName());
                    }
                    else
                    {
                        shortRideTickets();
                    }
                    break;
                case 7: //buy foods
                    // customer checks pockets for tickets and for available hand
                    if (customer.checkTickets(foods[foodChoice() - 1].getTicketPrice())
                            && customer.checkHands()) 
                    {
                        //customer pays tickets for selected food
                        customer.spendTickets(foods[foodChoice() - 1].getTicketPrice());
                        //customer takes food in hand
                        customer.holdFood(foods[foodChoice() - 1].getName());
                    }
                    else if (customer.checkTickets(foods[foodChoice() - 1].getTicketPrice()))
                    {
                        shortFoodTickets();
                    }
                    else
                    {
                        System.out.println("You don't have enough hands!");
                    }
                    break;
                case 8: //eat food
                    //customer checks for food in hands
                    if (customer.getLeftHand().equals("empty") && customer.getRightHand().equals("empty"))
                    {
                        System.out.println("You don't have any food to eat!");
                    }
                    else
                    {
                        System.out.printf("1. %8s 2. %s", 
                                customer.getLeftHand(), customer.getRightHand());
                        System.out.print("Which one do you want to consume? ");
                        customer.eatFood(stdIn.nextInt());
                    }
                    break;
                case 9: playAgain = false; break; //leave Carnival
                default: 
                    System.out.println("Please enter a valid option");
            } //end switch
            if (!prizeWon.equals("sorry, no prize") && !prizeWon.equals("none"))
            {
                customer.addPrize(prizeWon);
            }   
        } while (playAgain); //end do while
        
        //exit Carnival
        System.out.printf("All Done! Hope you had a good time, %s", 
                customer.getName());
        if (!customer.backpack.isEmpty())
        {
            System.out.println("You won these prizes:");
            for (String obj : customer.backpack)
            {
                System.out.println(obj);
            } //end for each
        } //end if
    } //end main
    
    //ADDITIONAL METHODS*******************************************************
    
    public static void gameSetup(Game[] games)
    {
        //Game[] games = new Game[3];
        games[0] = new Game("Water Shooter", 4, 
                "stuffed bear", "plastic bear", "bear key chain");
        games[1] = new Game("Balloon Dart Toss", 4, 
                "stuffed tiger", "plastic tiger", "tiger key chain");
        games[2] = new Game("Ring Toss", 4, 
                "stuffed pig", "plastic pig", "pig key chain");
    } //end gameSetup
    
    public static void rideSetup(Ride[] rides)
    {
        rides[0] = new Ride("Ferris Wheel", 6);
        rides[1] = new Ride("Carousel", 6);
    } //end rideSetup
    
    public static void foodSetup(Food[] foods)
    {
        foods[0] = new Food("drink", 3);
        foods[1] = new Food("hot dog", 5);
        foods[2] = new Food("popcorn", 5);
        foods[3] = new Food("cotton candy", 5);          
    } //end foodSetup
    
    public static int activityMenu(int tickets)
    {
        Scanner stdIn = new Scanner(System.in);
        System.out.printf("\nYou have %d tickets", tickets);
        System.out.println("1. Water Shooter\t5. Ferris Wheel");
        System.out.println("2. Balloon Dart Toss\t6. Carousel");
        System.out.println("3. Ring Toss\t7. Get Food");
        System.out.println("4. Add Tickets\t8. Eat Food");
        System.out.println("9. Exit Carnival");
        System.out.print("What do you want to do?");
        int activityAnswer = stdIn.nextInt();
        stdIn.nextLine(); //flush new line
        return activityAnswer;
    } //end activityMenu
    
    public static int foodChoice()
    {
        Scanner stdIn = new Scanner(System.in);
        System.out.println("Food choices");
        System.out.println("1. Drink\t3. Popcorn");
        System.out.println("2. Hot Dog\t4. Cotton Candy");
        System.out.print("Which one do you want? ");
        int foodAnswer = stdIn.nextInt();
        stdIn.nextLine(); //flush new line
        return foodAnswer;
    } //end foodMenu
    
    public static void shortRideTickets()
    {
        System.out.println("You don't have enough tickets for this game.");
    } //end shortRideTickets
    
    public static void shortGameTickets()
    {
        System.out.println("You don't have enough tickets for this ride.");
    } //end shortGameTickets
    
    public static void shortFoodTickets()
    {
        System.out.println("You don't have enough tickets for this food.");
    } //end shortFoodTickets
   
} //end class Carnival
