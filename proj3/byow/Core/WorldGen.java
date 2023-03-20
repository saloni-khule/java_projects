package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.*;

import static byow.Core.RandomUtils.uniform;

public class WorldGen {

    private    Random RANDOM ;
            //new Random(19);
    private  int WIDTH ;
    private   int HEIGHT ;
    public   TETile[][] world ;
            //= new TETile[WIDTH][HEIGHT];

    public ArrayList<Position> listOfRooms = new ArrayList<>();


    public WorldGen(long seed, int WIDTH, int HEIGHT){
        this.RANDOM =  new Random(seed);
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.world  = new TETile[WIDTH][HEIGHT];





        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // initialize tiles
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        // draws the world to the screen
        int random = RANDOM.nextInt(1000, 2000);
        for (int i = 0; i < random; i++) {
            makeRoom();
        }
        connectRooms();
        checkWorld();
        // createHorizontalHallway(5, 10, 20, 10);

        //connectRooms(listOfRooms.get(3), listOfRooms.get(4));
        ter.renderFrame(world);

    }


    public static void main(String[] args) {
//        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
//        TERenderer ter = new TERenderer();
//        ter.initialize(WIDTH, HEIGHT);
//        // initialize tiles
//        for (int x = 0; x < WIDTH; x += 1) {
//            for (int y = 0; y < HEIGHT; y += 1) {
//                world[x][y] = Tileset.NOTHING;
//            }
//        }
//        // draws the world to the screen
//        int random = RANDOM.nextInt(1000, 2000);
//        for (int i = 0; i < random; i++) {
//            makeRoom();
//        }
//        connectRooms();
//        checkWorld();
//        // createHorizontalHallway(5, 10, 20, 10);
//
//        //connectRooms(listOfRooms.get(3), listOfRooms.get(4));
//        ter.renderFrame(world);
        new WorldGen(101,60,30);

    }

    public  void makeRoom() {
        int x_crd = RANDOM.nextInt(2, WIDTH);
        int y_crd = RANDOM.nextInt(2, HEIGHT);
        int width = RANDOM.nextInt(5, 20);
        int height = RANDOM.nextInt(5, 15);

        // Should we call makeRoom() again if it we end up returning it?
        // We could keep track by having it reference another area
        if (!(outOfBounds(x_crd, y_crd, width, height) || overlap(x_crd, y_crd, width, height))) {
            Position position = new Position(x_crd, y_crd, width, height);
            listOfRooms.add(position);
            for (int x = x_crd; x <= x_crd + width; x += 1) {
                for (int y = y_crd; y <= y_crd + height; y += 1) {
                    if (x == x_crd || x == x_crd + width) {
                        world[x][y] = Tileset.WALL;
                    } else if (y == y_crd || y == y_crd + height) {
                        world[x][y] = Tileset.WALL;
                    } else {
                        world[x][y] = Tileset.FLOOR;
                    }
                }
            }
        }
    }

    public   boolean overlap(int xr, int yr, int wr, int hr) {
        boolean overLap = false;
        for (int x = xr - 2; x <= xr + wr + 2; x += 1) {
            for (int y = yr - 2; y <= yr + hr + 2; y += 1) {
                if (!world[x][y].equals(Tileset.NOTHING)) {
                    overLap = true;
                    break;
                }
            }
        }
        return overLap;
    }

    // Cannot be equal to width as the index of the array begins at zero
    public  boolean outOfBounds(int xr, int yr, int wr, int hr) {
        boolean outOfBoundsValue = false;
        if (xr + wr + 2 >= WIDTH || yr + hr + 2 >= HEIGHT) {
            outOfBoundsValue = true;
        }
        return outOfBoundsValue;
    }

    //    public static void makeHallways()
    public class Position implements Comparable {
        int x;
        int y;
        int width;
        int height;
        double distance;

        Position(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.distance = Double.POSITIVE_INFINITY;
        }

        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public   double distanceOfPosition(Position position1, Position position2) {
        int x1 = position1.x;
        int x2 = position2.x;
        int y1 = position1.y;
        int y2 = position2.y;
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    //   private class PositionComparator implements Comparator<Position> {
    //       public int compare(Position p1, Position p2) {
    //          if ()
//        }
//    }

    public   void changeDistance(int room1, double distance) {
        listOfRooms.get(room1).distance = distance;
    }

//    public static class  implements Comparable{
//
//        public int compareTo(Object o) {
//            return compare(this, o);
//        }
//
//        public static int compare(WorldGen.Position p1, WorldGen.Position p2) {
//            if (p1.distance < p2.distance) {
//                return -1;
//            }
//            else if (p1.distance > p2.distance) {
//                return 1;
//            }
//            return 0;
//        }
//
//    }

    public  void hallwayMaker() {
        PriorityQueue<Position> temp = new PriorityQueue<>();
        changeDistance(0, 0);
        temp.add(listOfRooms.get(0));
        listOfRooms.remove(0);
        for (Position i : listOfRooms) {
            temp.add(i);
        }
    }

    public   boolean isRoom(int x, int y) {
        for (Position i : listOfRooms) {
            int x_crd = i.x;
            int y_crd = i.y;
            int width = i.width;
            int height = i.height;
            if (x >= x_crd && x <= (x_crd + width)) {
                if (y >= y_crd && y <= (y_crd + height)) {
                    return true;
                }
            }
        }
        return false;
    }

    //checks if room has made any connections
    public   boolean isConnected(Position positionRoom) {
        int x_pos = positionRoom.x;
        int y_pos = positionRoom.y;
        int width = positionRoom.width;
        int height = positionRoom.height;
        for (int x = x_pos; x <= (x_pos + width); x++) {
            for (int y = y_pos; y <= (y_pos + height); y++) {
                if (x == x_pos && world[x][y] == Tileset.FLOOR) {
                    return true;
                }
                if (x == (x_pos + width) && world[x][y] == Tileset.FLOOR) {
                    return true;
                }
                if (y == y_pos && world[x][y] == Tileset.FLOOR) {
                    return true;
                }
                if (y == (y_pos + height) && world[x][y] == Tileset.FLOOR) {
                    return true;
                }
            }

        }
        return false;
    }


    //returns true if point is on a corner, so that we properly connect hallways
    public   boolean isBottomLeftCorner(int x, int y) {
        Position room = whichRoom(x, y);
        if (room == null) {
            return false;
        } else {
            int x_crd = room.x;
            int y_crd = room.y;
            if (x == x_crd && y == y_crd) {
                return true;
            }
        }
        return false;
    }

    public   boolean isBottomRightCorner(int x, int y) {
        Position room = whichRoom(x, y);
        if (room == null) {
            return false;
        } else {
            int x_crd = room.x;
            int y_crd = room.y;
            int width = room.width;
            if (x == (x_crd + width) && y == y_crd) {
                return true;
            }
        }
        return false;
    }

    public  boolean isTopLeftCorner(int x, int y) {
        Position room = whichRoom(x, y);
        if (room == null) {
            return false;
        } else {
            int x_crd = room.x;
            int y_crd = room.y;
            int height = room.height;
            if (x == x_crd && y == (y_crd + height)) {
                return true;
            }
        }
        return false;
    }

    //given a room, this finds the closest Room checks on the list that is NOT itself

    public  Position closestRoom(Position p) {
        Position bestRoom = null;
        for (Position i : listOfRooms) {
            if (distanceOfPosition(p, i) != 0) {
                bestRoom = i;
                break;
            }
        }
        double distance = distanceOfPosition(p, bestRoom);
        for (Position i : listOfRooms) {
            double possibleDistance = distanceOfPosition(p, i);
            if (possibleDistance != 0 && possibleDistance < distance) {
                distance = possibleDistance;
                bestRoom = i;
            }
        }
        return bestRoom;
    }

    // returns room if the coordinate is currently in a room or on the wall
    public   Position whichRoom(int x, int y) {
        for (Position i : listOfRooms) {
            int x_crd = i.x;
            int y_crd = i.y;
            int width = i.width;
            int height = i.height;
            if (x >= x_crd && x <= (x_crd + width)) {
                if (y >= y_crd && y <= (y_crd + height)) {
                    return i;
                }
            }
        }
        return null;
    }

    //creates hallways which can go through various rooms in its horizontal path -> y_final and y_start should equal each other
    //If statements make sure it can pass through a room and pass through a vertical hallway
    public  void createHorizontalHallway(int x_start, int y_start, int x_final, int y_final) {
        world[x_start][y_start] = Tileset.FLOOR;
        world[x_final][y_final] = Tileset.FLOOR;
        x_start = x_start + 1;
        while (x_start < x_final) {
            if (!isRoom(x_start, y_start)) {
                if (world[x_start][y_start] == Tileset.WALL || world[x_start][y_start] == Tileset.FLOOR) {
                    world[x_start][y_start] = Tileset.FLOOR;
                    x_start = x_start + 1;
//                    world[x_start][y_start] = Tileset.FLOOR;
//                    x_start = x_start + 1;
//                    world[x_start][y_start] = Tileset.FLOOR;
//                    x_start = x_start + 1;
                } else {
                    world[x_start][y_start] = Tileset.FLOOR;
                    world[x_start][y_start + 1] = Tileset.WALL;
                    world[x_start][y_start - 1] = Tileset.WALL;
                    x_start = x_start + 1;
                }
            } else {
//                if (isBottomLeftCorner(x_start, y_start)) {
//                    expandRoomHorizontal(x_start, y_start, "bottom");
//                }
//                if (isTopLeftCorner(x_start, y_start)) {
//                    expandRoomHorizontal(x_start, y_start, "top");
//                }
                while (isRoom(x_start, y_start)) {
                    //want to shift border wall down by 1
                    world[x_start][y_start] = Tileset.FLOOR;
                    x_start = x_start + 1;
                    if (x_final == x_start) {
                        return;
                    }
                }
            }
        }
    }

    //can pass through a horizontal hallway and can pass through a room going up
    public   void createVerticalHallway(int x_start, int y_start, int x_final, int y_final) {
        world[x_start][y_start] = Tileset.FLOOR;
        world[x_final][y_final] = Tileset.FLOOR;
        y_start = y_start + 1;
        while (y_start < y_final) {
            if (!isRoom(x_start, y_start)) {
                if (world[x_start][y_start] == Tileset.WALL) {
                    world[x_start][y_start] = Tileset.FLOOR;
                    y_start = y_start + 1;
                    world[x_start][y_start] = Tileset.FLOOR;
                    y_start = y_start + 1;
                    world[x_start][y_start] = Tileset.FLOOR;
                    y_start = y_start + 1;
                } else {
                    world[x_start][y_start] = Tileset.FLOOR;
                    world[x_start + 1][y_start] = Tileset.WALL;
                    world[x_start - 1][y_start] = Tileset.WALL;
                    y_start = y_start + 1;
                }
            } else {
//                if (isBottomLeftCorner(x_start, y_start)) {
//                    expandRoomVertical(x_start, y_start, "left");
//                }
//                if (isBottomRightCorner(x_start, y_start)) {
//                    expandRoomVertical(x_start, y_start, "right");
//                }
                while (isRoom(x_start, y_start)) {
                    world[x_start][y_start] = Tileset.FLOOR;
                    y_start = y_start + 1;
                    if (y_final == y_start) {
                        return;
                    }
                }
            }
        }
    }

    //String takes in a string which refers to whether a horizontal hallway was called beforehand or
    //a vertical hallway
    public   void drawCornerHallway(int x_crd, int y_crd, String verticalOrHorizontal, String lOrNoL) {
        if (isRoom(x_crd, y_crd)) {
            return;
        }
        if (verticalOrHorizontal.equals("vertical") && lOrNoL.equals("NoL")) {
            world[x_crd + 1][y_crd] = Tileset.WALL;
            world[x_crd][y_crd - 1] = Tileset.WALL;
            world[x_crd + 1][y_crd - 1] = Tileset.WALL;
        } else if (verticalOrHorizontal.equals("horizontal") && lOrNoL.equals("NoL")) {
            world[x_crd - 1][y_crd] = Tileset.WALL;
            world[x_crd][y_crd + 1] = Tileset.WALL;
            world[x_crd - 1][y_crd + 1] = Tileset.WALL;
        } else if (verticalOrHorizontal.equals("vertical") && lOrNoL.equals("L")) {
            world[x_crd + 1][y_crd] = Tileset.WALL;
            world[x_crd][y_crd + 1] = Tileset.WALL;
            world[x_crd + 1][y_crd + 1] = Tileset.WALL;

        } else if (verticalOrHorizontal.equals("horizontal") && lOrNoL.equals("L")) {
            world[x_crd - 1][y_crd] = Tileset.WALL;
            world[x_crd][y_crd - 1] = Tileset.WALL;
            world[x_crd - 1][y_crd - 1] = Tileset.WALL;
        }
    }

    public   void connectRooms(Position p1, Position p2) {
        int x1 = p1.x;
        int y1 = p1.y;
        int width1 = p1.width;
        int height1 = p1.height;
        int x2 = p2.x;
        int y2 = p2.y;
        int width2 = p2.width;
        int height2 = p2.height;
        Position eastRoom = null;
        Position westRoom = null;
        Position northRoom = null;
        Position southRoom = null;
        if (x1 < x2) {
            westRoom = p1;
            eastRoom = p2;
        }
        if (x1 > x2) {
            westRoom = p2;
            eastRoom = p1;
        }
        if (y1 < y2) {
            southRoom = p1;
            northRoom = p2;
        }
        if (y1 > y2) {
            northRoom = p1;
            southRoom = p2;
        }
        for (int i = y1 + 1; i < y1 + height1; i++) {
            for (int j = y2 + 1; j < y2 + height2; j++) {
                if (i == j) {
                    if (westRoom == p1) {
                        createHorizontalHallway(x1 + width1, i, x2, j);
                        return;
                    }
                    if (westRoom == p2) {
                        createHorizontalHallway(x2 + width2, i, x1, j);
                        return;
                    }
                }
            }
        }
        for (int i = x1 + 1; i < x1 + width1; i++) {
            for (int j = x2 + 1; j < x2 + width2; j++) {
                if (i == j) {
                    if (southRoom == p1) {
                        createVerticalHallway(i, y1 + height1, j, y2);
                        return;
                    }
                    if (southRoom == p2) {
                        createVerticalHallway(i, y2 + height2, j, y1);
                        return;
                    }
                }
            }
        }
        if (westRoom == p1 && southRoom == p1) {
            //x_crd has the - 1 because it doesnt touch corner
            int x_crd = x1 + width1 - 1;
            int y_crd = y2 + 1;
            createVerticalHallway(x_crd, y1 + height1, x_crd, y_crd);
            createHorizontalHallway(x_crd, y_crd, x2, y_crd);
            drawCornerHallway(x_crd, y_crd, "horizontal", "NoL");
            return;
        }
        if (eastRoom == p1 && southRoom == p1) {
            int x_crd = x1 + 1;
            int y_crd = y2 + 1;
            createVerticalHallway(x_crd, y1 + height1, x_crd, y_crd);
            createHorizontalHallway(x2 + width2, y_crd, x_crd, y_crd);
            drawCornerHallway(x_crd, y_crd, "vertical", "L");
            return;
        }
        if (westRoom == p1 && northRoom == p1) {
            int x_crd = x2 + 1;
            int y_crd = y1 + 1;
            createVerticalHallway(x_crd, y2 + height2, x_crd, y_crd);
            createHorizontalHallway(x1 + width1, y_crd, x_crd, y_crd);
            drawCornerHallway(x_crd, y_crd, "vertical", "L");
            return;
        }
        if (eastRoom == p1 && northRoom == p1) {
            int x_crd = x2 + width2 - 1;
            int y_crd = y1 + 1;
            createVerticalHallway(x_crd, y2 + height2, x_crd, y_crd);
            createHorizontalHallway(x_crd, y_crd, x1, y_crd);
            drawCornerHallway(x_crd, y_crd, "horizontal", "NoL");
        }

    }

    public  void expandRoomHorizontal(int x, int y, String bottomOrTop) {
        if (bottomOrTop.equals("bottom")) {
            Position p = whichRoom(x, y);
            int initialy = p.y;
            int initialx = p.x;
            int width = p.width;
//            boolean erase = true;
//            for (int j = initialx; j <= (initialx + width) ; j++) {
//                if (world[j][initialy - 1] == Tileset.NOTHING || world[j][initialy - 1] == Tileset.NOTHING) {
//                    erase = false;
//                }
//            }
            //will not expand because createHorizontalHallway will just override
//            if (erase == true) {
//                return;
//            }

            for (int i = initialx; i <= (initialx + width); i++) {
                //if it shares room with hallway then I remove the floor when trying to expand
                if (world[i][initialy] == Tileset.FLOOR) {
                    world[i][initialy - 1] = Tileset.FLOOR;
                } else {
                    world[i][initialy - 1] = Tileset.WALL;
                }
            }
            p.y = p.y - 1;
            p.height = p.height + 1;
            return;
        }
        if (bottomOrTop.equals("top")) {
            Position p = whichRoom(x, y);
            int initialy = p.y;
            int initialx = p.x;
            int width = p.width;
            int height = p.height;
            //          boolean erase = true;
            //will not expand because createHorizontalHallway will just override

            for (int i = initialx; i <= (initialx + width); i++) {
                //if it shares room with hallway then I remove the floor when trying to expand

                if (world[i][initialy + height] == Tileset.FLOOR) {
                    world[i][initialy + height + 1] = Tileset.FLOOR;
                } else {
                    world[i][initialy + height + 1] = Tileset.WALL;
                }
            }
            p.height = p.height + 1;
        }
    }

    public  void expandRoomVertical(int x, int y, String leftOrRight) {
        if (leftOrRight.equals("left")) {
            Position p = whichRoom(x, y);
            int initialy = p.y;
            int initialx = p.x;
            int width = p.width;
            int height = p.width;
            for (int i = initialy; i <= (initialy + height); i++) {
                //if it shares room with hallway then I remove the floor when trying to expand
                if (world[initialx][i] == Tileset.FLOOR) {
                    world[initialx - 1][i] = Tileset.FLOOR;
                } else {
                    world[initialx - 1][i] = Tileset.WALL;
                }
            }
            p.x = p.x - 1;
            p.width = p.width + 1;
            return;
        }
        if (leftOrRight.equals("right")) {
            Position p = whichRoom(x, y);
            int initialy = p.y;
            int initialx = p.x;
            int width = p.width;
            int height = p.height;
            //          boolean erase = true;
            //will not expand because createHorizontalHallway will just override
            for (int i = initialy; i <= (initialy + height); i++) {
                //if it shares room with hallway then I remove the floor when trying to expand

                if (world[initialx + width][i] == Tileset.FLOOR) {
                    world[initialx + width + 1][i] = Tileset.FLOOR;
                } else {
                    world[initialx + width + 1][i] = Tileset.WALL;
                }
            }
            p.width = p.width + 1;
        }

    }


    public  void connectRooms() {
        ArrayList<Position> copyOfListOfRooms = new ArrayList<>();
        copyOfListOfRooms = (ArrayList<Position>) listOfRooms.clone();
        Position zero = new Position(0, 0, 1, 1);
        ArrayList<Double> distance = new ArrayList<>();
        for (Position i : copyOfListOfRooms) {
            distance.add(distanceOfPosition(zero, i));
        }
        ArrayList<Double> distanceCloneI = (ArrayList<Double>) distance.clone();
        Collections.sort(distanceCloneI);
        Double closest = distanceCloneI.get(0);
        int posI = distance.indexOf(closest);
        Position source = copyOfListOfRooms.remove(posI);
        while (!copyOfListOfRooms.isEmpty()) {
            ArrayList<Double> distances = new ArrayList<>();
            for (Position i : copyOfListOfRooms) {
                distances.add(distanceOfPosition(source, i));
            }
            ArrayList<Double> distanceClone = (ArrayList<Double>) distances.clone();
            Collections.sort(distanceClone);
            Double closestX = distanceClone.get(0);
            int pos = distances.indexOf(closestX);
            Position closestRoom = copyOfListOfRooms.get(pos);
            connectRooms(source, closestRoom);
            source = copyOfListOfRooms.remove(pos);
        }
    }

    public  void checkWorld() {
        for (int x = 1; x < WIDTH - 1; x++) {
            for (int y = 1; y < HEIGHT - 1; y++) {
                if (world[x][y] == Tileset.WALL && (world[x][y + 1] == Tileset.FLOOR || world[x][y - 1] == Tileset.FLOOR)) {
                    if (world[x + 1][y] == Tileset.NOTHING) {
                        world[x + 1][y] = Tileset.WALL;
                    }
                    if (world[x - 1][y] == Tileset.NOTHING) {
                        world[x - 1][y] = Tileset.WALL;
                        checkWorld();
                    }
                }
                if (world[x][y] == Tileset.WALL && (world[x + 1][y] == Tileset.FLOOR || world[x - 1][y] == Tileset.FLOOR)) {
                    if (world[x][y + 1] == Tileset.NOTHING) {
                        world[x][y + 1] = Tileset.WALL;
                    }
                    if (world[x][y - 1] == Tileset.NOTHING) {
                        world[x][y - 1] = Tileset.WALL;
                        checkWorld();
                    }

                }

            }
        }
    }
}
