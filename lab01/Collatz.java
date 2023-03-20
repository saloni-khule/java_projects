/** Class that prints the Collatz sequence starting from a given number.
 *  @author YOUR NAME HERE
 */
public class Collatz {

    /** Returns the nextNumber in a Collatz sequence. */
    /** n is the given number which gets divided by 2 if it's even
     and multiplied by 3 and added to 1 if it's odd. */
    public static int nextNumber(int n) {
        // TODO: Fill in this method.


        if (n!=1 && n%2==0){
            return n/2;
        }
        else if(n!=1 && n%2==1){
            return (3*n)+1;
        }

        return 1;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");

        // Some starter code to test
        while (n != 1) {          
            n = nextNumber(n);          
            System.out.print(n + " ");
        }
        System.out.println();

    }
}

