package PlusWorld;
import org.junit.Test;
import static org.junit.Assert.*;

import byowTools.TileEngine.TERenderer;
import byowTools.TileEngine.TETile;
import byowTools.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of plus shaped regions.
 */
public class PlusWorld {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 50;

    public static void addPlus(int s){
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }


        int p =WIDTH;
        int q = HEIGHT/2;


        for (int x = 0; x <  s*3; x += 1) {
            for (int y = q; y < q+s; y += 1) {
                world[x][y] = Tileset.FLOWER;
            }
        }




        for (int x = s; x < 2*s; x += 1) {
            for (int y = q-s; y < q+s*2; y += 1) {
                world[x][y] = Tileset.FLOWER;
            }
        }

        // draws the world to the screen
        ter.renderFrame(world);
    }


    public static void main(String[] args) {
      addPlus(10);

    }


}



