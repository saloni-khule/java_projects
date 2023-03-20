package MemoryGame;

import byowTools.RandomUtils;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    /** The width of the window of this game. */
    private int width;
    /** The height of the window of this game. */
    private int height;
    /** The current round the user is on. */
    private int round;
    /** The Random object used to randomly generate Strings. */
    private Random rand;
    /** Whether or not the game is over. */
    private boolean gameOver;
    /** Whether or not it is the player's turn. Used in the last section of the
     * spec, 'Helpful UI'. */
    private boolean playerTurn;
    /** The characters we generate random Strings from. */
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    /** Encouraging phrases. Used in the last section of the spec, 'Helpful UI'. */
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        long seed = Long.parseLong(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String ans ="";
        int count = n;
        while(count>0){
            Random rnd = new Random();
            char ch = (char) ('a' + rnd.nextInt(26));

            ans+=ch;
            count--;
        }


        return ans;
    }

    public void drawFrame(String s) {
        /* Take the input string S and display it at the center of the screen,
        * with the pen settings given below. */
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.WHITE);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);

        StdDraw.text(this.width / 2, this.height / 2, s);

        //TODO: If the game is not over, display encouragement, and let the user know if they
        // should be typing their answer or watching for the next round.


        if (!gameOver) {
            Font fontSmall = new Font("Monaco", Font.BOLD, 20);
            StdDraw.setFont(fontSmall);
            StdDraw.line(0, height - 2, width, height - 2);
            StdDraw.text(5, height - 1, "Round " + round);
            int cnt = rand.nextInt(7);
            StdDraw.textRight(this.width, this.height - 1, ENCOURAGEMENT[cnt]);
            if (playerTurn) {
                StdDraw.textRight(this.width / 2, this.height - 1, "Type!");
            }
            if (!playerTurn) {
                StdDraw.textRight(this.width / 2, this.height - 1, "Watch!");
            }
        }
        StdDraw.show();

//        String j = "Round: "+this.round;
//        StdDraw.text(5 , this.height-1 , j);






//        while(! gameOver){
//            String x = "APP";
//            StdDraw.text(this.width/2 , this.height/2 , x);
//
//
//        }



    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        char[] l = letters.toCharArray();
       int  count =0;
       while(count< l.length){
//           char temp = l[count];

           drawFrame(String.valueOf(l[count]));
           StdDraw.pause(1000);
           drawFrame("");
           StdDraw.pause(500);



           count++;
       }




    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        int count =n;
        String ans = "";
    while (StdDraw.hasNextKeyTyped() && ans.length()<n ){

        ans+= StdDraw.nextKeyTyped();
        drawFrame(ans);
        StdDraw.pause(300);


    }
        StdDraw.pause(1000);
        return ans;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        this.gameOver = false;
        this.round =1;

        //TODO: Establish Engine loop
//        drawFrame("You should implement this game!");
//        StdDraw.pause(1000);



        while (!gameOver) {
            playerTurn = false;
            drawFrame("Round: " + this.round);
            StdDraw.pause(1000);
            String wrd = generateRandomString(this.round);
            flashSequence(wrd);
            playerTurn = true;
            drawFrame("");
            StdDraw.pause(1000);


            String text = solicitNCharsInput(this.round);

            if(! text.equals(wrd)){

                this.gameOver=true;


            }
            else if(text.equals(wrd)){

                this.round+=1;
            }


            StdDraw.text(this.width/4,this.height/4,"Round: "+ this.round);

            StdDraw.show();










//
//
//            while(playerTurn){
//                playerTurn=false;
//
//
//
//
//
//            this.drawFrame(text);
//                StdDraw.pause(3000);
//
//
//
//
//            }





        }

        this.drawFrame("Game Over! You made it to round: " + this.round);
    }

}
