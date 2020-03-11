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

import java.util.*; //Scanner

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
        int foodAnswer;
        int handNumber;
        boolean playAgain = true;
        String prizeWon;
        //call methods to populate arrays with data
        gameSetup(games); 
        rideSetup(rides); 
        foodSetup(foods);
        //output
        System.out.println("Final Project by JC Boyd\n");
        System.out.print("Welcome to the Carnival!\nWhat is your name? ");
        customer.setName(stdIn.nextLine());
        //do while game loop
        do 
        {
            prizeWon = "none"; //reset value each loop
            //display number of tickets, menu, and get user activity choice
            activityAnswer = activityChoice(customer.getTickets());
            //select activities based on user choice
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
                    else //when not enough tickets for game
                    {
                        System.out.println("You don't have enough tickets for this game.");
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
                    if (customer.checkTickets(rides[activityAnswer - 5].getTicketPrice())) 
                    {
                        //customer pays tickets for selected ride
                        customer.spendTickets(rides[activityAnswer - 5].getTicketPrice());
                        //customer goes on ride
                        System.out.printf("Enjoy the %s ride!\n",   
                                rides[activityAnswer - 5].getName());
                    }
                    else //when not enough tickets for ride
                    {
                        System.out.println("You don't have enough tickets for this ride.");
                    }
                    break;
                case 7: //buy foods
                    // customer checks pockets for tickets and checks for available hand
                    foodAnswer = foodChoice();
                    if (customer.checkTickets(foods[foodAnswer].getTicketPrice())
                            && customer.availableHand()) 
                    { //when enough tickets and available hand
                        //customer pays tickets for selected food
                        customer.spendTickets(foods[foodAnswer].getTicketPrice());
                        //customer takes food in hand
                        customer.holdFood(foods[foodAnswer].getName());
                        System.out.printf("Enjoy your %s!\n", foods[foodAnswer].getName());
                    }
                    else if (customer.checkTickets(foods[foodAnswer].getTicketPrice()))
                    { //when enough tickets, but no available hand
                        System.out.println("You don't have enough hands!");
                    }
                    else
                    { //when not enough tickets (available hand is irrelevant)
                        System.out.println("You don't have enough tickets for this food.");
                    }
                    break;
                case 8: //eat food
                    //customer checks for food currently in hands
                    int lowerBound = 1, upperBound = 2;
                    if (customer.getLeftHand().equals("empty") 
                            && customer.getRightHand().equals("empty"))
                    {
                        System.out.println("You don't have any food to eat!");
                    }
                    else 
                    {    
                        if (customer.getLeftHand().equals("empty"))
                        {
                            System.out.printf("2. %-12s\n", customer.getRightHand());
                            lowerBound = 2; //adjust for input validation 
                        }
                        else if (customer.getRightHand().equals("empty"))
                        {
                            System.out.printf("1. %-12s\n", customer.getLeftHand());
                            upperBound = 1; //adjust for input validation
                        }
                        else
                        {
                            System.out.printf("1. %-12s 2. %-12s\n", 
                                customer.getLeftHand(), customer.getRightHand());
                        } //end if
                        do
                        {
                            System.out.print("Which one do you want to consume? ");
                            handNumber = stdIn.nextInt();
                            //validate entry, repeat if invalid
                            if (handNumber < lowerBound | handNumber > upperBound)
                            {
                                System.out.println("Please enter a valid option");
                            } //end if
                        } while (handNumber < lowerBound | handNumber > upperBound);
                        //eat food in selected hand
                        customer.eatFood(handNumber - 1);
                    } //end if
                    break;
                case 9: //leave Carnival 
                    playAgain = false;
                    break; 
                default: 
                    System.out.println("Please enter a valid option");
            } //end switch
            //add prize to backpack if prize exists
            if (!prizeWon.equals("sorry, no prize") && !prizeWon.equals("none"))
            {
                customer.addPrize(prizeWon);
            }   
        } while (playAgain); //end do while game loop
        
        //exit Carnival
        System.out.printf("\nAll Done! Hope you had a great time, %s\n", 
                customer.getName());
        if (!customer.backpackEmpty())
        {
            System.out.println("You won these prizes:");
            System.out.println(customer.getPrizes());
        } //end if
    } //end main
    
    //additional methods ******************************************************
    
    public static void gameSetup(Game[] games)
    {
        games[0] = new Game("water shooter", 4, 
                "stuffed bear", "plastic bear", "bear key chain");
        games[1] = new Game("balloon dart Toss", 4, 
                "stuffed tiger", "plastic tiger", "tiger key chain");
        games[2] = new Game("ring toss", 4, 
                "stuffed pig", "plastic pig", "pig key chain");
    } //end gameSetup
    
    public static void rideSetup(Ride[] rides)
    {
        rides[0] = new Ride("ferris wheel", 6);
        rides[1] = new Ride("carousel", 6);
    } //end rideSetup
    
    public static void foodSetup(Food[] foods)
    {
        foods[0] = new Food("drink", 3);
        foods[1] = new Food("hot dog", 5);
        foods[2] = new Food("popcorn", 5);
        foods[3] = new Food("cotton candy", 5);          
    } //end foodSetup
    
    public static int activityChoice(int tickets)
    {
        Scanner stdIn = new Scanner(System.in);
        System.out.printf("\nYou have %d tickets\n", tickets);
        System.out.printf("%-25s%s\n", "1. Water Shooter", "5. Ferris Wheel");
        System.out.printf("%-25s%s\n", "2. Balloon Dart Toss", "6. Carousel");
        System.out.printf("%-25s%s\n", "3. Ring Toss", "7. Get Food");
        System.out.printf("%-25s%s\n", "4. Add Tickets", "8. Eat Food");
        System.out.printf("%-25s\n", "9. Exit Carnival");
        System.out.print("What do you want to do? ");
        int activityAnswer = stdIn.nextInt();
        stdIn.nextLine(); //flush new line
        return activityAnswer;
    } //end activityChoice
    
    public static int foodChoice()
    {
        Scanner stdIn = new Scanner(System.in);
        int choice;
        System.out.println("Food choices");
        System.out.println("1. Drink\t3. Popcorn");
        System.out.println("2. Hot Dog\t4. Cotton Candy");
        do 
        {
            System.out.print("Which one do you want? ");
            choice = stdIn.nextInt();
            stdIn.nextLine(); //flush new line
        } while (choice < 1 && choice > 4); //check for valid input
        return choice - 1;
    } //end foodChoice
    
} //end class Carnival
