package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;


import javax.xml.stream.util.StreamReaderDelegate;
import java.awt.*;
import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    public static   long SEED ;
    public static int load_posx;
    public static int load_posy;



    public WorldGen newWorld;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        boolean L = false;


        StdDraw.setCanvasSize(WIDTH *10, HEIGHT*10);
        Font font = new Font("Monaco", Font.BOLD, 50);
        Font font2 = new Font("Monaco", Font.PLAIN, 30);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH*10);
        StdDraw.setYscale(0, HEIGHT*10);
        StdDraw.clear(Color.BLACK);

        while(!StdDraw.hasNextKeyTyped()) {
            StdDraw.setFont(font);
            StdDraw.text(WIDTH * 10 / 2, (HEIGHT * 10) - 40, "CS61B : THE GAME");

            StdDraw.setFont(font2);
            StdDraw.text(WIDTH * 10 / 2, (HEIGHT * 10) - 100, "NEW GAME");
            StdDraw.text(WIDTH * 10 / 2, (HEIGHT * 10) - 140, "LOAD GAME");
            StdDraw.text(WIDTH * 10 / 2, (HEIGHT * 10) - 180, "QUIT");
        }
        String ans ="";
        StdDraw.clear(Color.BLACK);
        if (StdDraw.hasNextKeyTyped()) {
            if (StdDraw.nextKeyTyped() == 'n' || StdDraw.nextKeyTyped() == 'N') {


                // StdDraw.setFont(font2);
                StdDraw.text(WIDTH * 10 / 2, (HEIGHT * 10) - 100, "Please enter a seed");
                StdDraw.pause(100);

                StdDraw.pause(1500);
                while(StdDraw.hasNextKeyTyped()){

                    StdDraw.text(WIDTH * 10 / 2, (HEIGHT * 10) - 100, "Please enter a seed");

                    ans+=StdDraw.nextKeyTyped();
                    StdDraw.text((float) WIDTH * 10 / 2, (float) (HEIGHT * 10) -140, ans);

                    StdDraw.pause(500);
                    StdDraw.clear(Color.BLACK);






                }

                SEED = Long.parseLong(ans);



            }

            else if (StdDraw.nextKeyTyped() == 'l' || StdDraw.nextKeyTyped() == 'L') {
                L =true;

            }

            else if (StdDraw.nextKeyTyped() == 'q' || StdDraw.nextKeyTyped() == 'Q') {

               System.exit(0);

            }





        }





        StdDraw.show();
        StdDraw.pause(1000);
        //System.exit(0);





        newWorld = new WorldGen(SEED, WIDTH,HEIGHT);

        int x_pos;
        int y_pos;
        if(L){
             x_pos = load_posx;
             y_pos =load_posy;

        }

        else{

        WorldGen.Position start = newWorld.listOfRooms.get(0);
         x_pos = start.x+1;
         y_pos = start.y+1;}

       newWorld.world[x_pos][y_pos]= Tileset.AVATAR;
        ter.renderFrame(newWorld.world);






        while (true) {
            StdDraw.pause(1500);

            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toUpperCase(StdDraw.nextKeyTyped());

                if (c=='W'|| c=='w') {

                    if( newWorld.world[x_pos][y_pos+1]== Tileset.FLOOR){



                    newWorld.world[x_pos][y_pos+1]= Tileset.AVATAR;
                    newWorld.world[x_pos][y_pos]= Tileset.FLOOR;
                    y_pos+=1;
                        ter.renderFrame(newWorld.world);
                    }}

                    if (c=='A'|| c=='a'){
                        if( newWorld.world[x_pos-1][y_pos]== Tileset.FLOOR){



                            newWorld.world[x_pos-1][y_pos]= Tileset.AVATAR;
                            newWorld.world[x_pos][y_pos]= Tileset.FLOOR;
                            x_pos-=1;
                        }
                        ter.renderFrame(newWorld.world);

                }


                if (c=='S'|| c=='s'){
                    if( newWorld.world[x_pos][y_pos-1]== Tileset.FLOOR){



                        newWorld.world[x_pos][y_pos-1]= Tileset.AVATAR;
                        newWorld.world[x_pos][y_pos]= Tileset.FLOOR;
                        y_pos-=1;
                    }
                    ter.renderFrame(newWorld.world);

                }



                if (c=='D'|| c=='d'){
                    if( newWorld.world[x_pos+1][y_pos]== Tileset.FLOOR){



                        newWorld.world[x_pos+1][y_pos]= Tileset.AVATAR;
                        newWorld.world[x_pos][y_pos]= Tileset.FLOOR;
                        x_pos+=1;
                    }

                    ter.renderFrame(newWorld.world);

                }

                if(c == 'q'|| c=='Q' ){

                    load_posx = x_pos;
                    load_posy = y_pos;
                    System.exit(0);
                   // ter.renderFrame(newWorld.world);
                }




            }
        }



    }




//    public String solicitNCharsInput(int n) {
//        //TODO: Read n letters of player input
//        int count =n;
//        String ans = "";
//        while (StdDraw.hasNextKeyTyped() && ans.length()<=n ){
//
//            ans+= StdDraw.nextKeyTyped();
//            StdDraw.text(WIDTH * 10 / 2, (HEIGHT * 10) - 140, ans);
//            StdDraw.pause(200);
//
//
//        }
//        StdDraw.pause(1000);
//        return ans;
//    }



    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, running both of these:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

//        if(input.toCharArray()[0]!='N'){
//           new
//        }

        char[] temp = input.toCharArray();
        String nums ="";

        for(int i=1;i<temp.length-1; i++){
            nums+= temp[i];



        }

        long val = Long.parseLong(nums);


        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        System.out.println(finalWorldFrame);
//        WorldGen finalWorldFrame = new WorldGen(100, WIDTH,HEIGHT);
        return finalWorldFrame;
    }


}
