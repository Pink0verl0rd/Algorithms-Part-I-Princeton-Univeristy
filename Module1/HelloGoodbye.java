package Module1;
/******************************************************************************
 *
 *  Prints out Hello and Goodbye using the 2 command line arguments that would be provided
 *
 ******************************************************************************/

public class HelloGoodbye {

    public static void main(String[] args) {

        System.out.println("Hello " + args[0] + " and " + args[1] + ".");

        System.out.println("Goodbye " + args[1] + " and " + args[0] + ".");
    }

}