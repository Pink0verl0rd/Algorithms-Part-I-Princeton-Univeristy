/******************************************************************************
 *
 *  Prints out Hello and Goodbye using the 2 command line arguments that would be provided
 *
 ******************************************************************************/

public class HelloGoodbye {

    public static void main(String[] args) {

        
        // Combinded the names together as we use the twice anways 
        String combinedName = args[0] + " and " + args[1];

        System.out.println("Hello " + combinedName);

        System.out.println("Goodbye " + combinedName);
    }

}