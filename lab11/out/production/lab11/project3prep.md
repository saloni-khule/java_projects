# Project 3 Prep

**For tessellating pluses, one of the hardest parts is figuring out where to place each plus/how to easily place plus on screen in an algorithmic way.
If you did not implement tesselation, please try to come up with an algorithmic approach to place pluses on the screen. This means a strategy, pseudocode, or even actual code! 
Consider the `HexWorld` implementation provided near the end of the lab (within the lab video). Note that `HexWorld` was the lab assignment from a previous semester (in which students drew hexagons instead of pluses). 
How did your proposed implementation/algorithm differ from the given one (aside from obviously hexagons versus pluses) ? What lessons can be learned from it?**

Answer:to place pluses on the screen in an algorithmic wa:
Let's take the first plus added to be the  main plus.
I would then place the southwestern edge of one plus to the northeastern edge of the main plus.
I would then place the southeastern edge of one plus to the northwestern edge of the main plus.
And so on...
I could keep track of position using the width of the plus as a measure and then use recursion to implement tesselation.
In the given implementation, there is a lot of detail involved with positioning. My approach is similar to the given one
but not as well planned out. 
I learnt that hardcoding the positions of pluses would not be useful in the long run.
-----

**Can you think of an analogy between the process of tessellating pluses and randomly generating a world using rooms and hallways?
What is the plus and what is the tesselation on the Project 3 side?**

Answer:  Using tessellation, we can create hallways and rooms since we can use the tiles to make walls in certain patterns.
The plus is the tile and the tesselation is the building of hallways and rooms on the Project 3 side.

-----
**If you were to start working on world generation, what kind of method would you think of writing first? 
Think back to the lab and the process used to eventually get to tessellating pluses.**

Answer:  I would think of writing the tile method first. I would use something convenient like a rectangle as a tile.
I would also think of random generation of tiles at random locations.


-----
**What distinguishes a hallway from a room? How are they similar?**

Answer: Similarities:
their number and locations are random.
Rooms and hallways both must have walls that are visually distinct from floors.
 differences:
The width and height of rooms should be random but Hallways should have a width of 1 or 2 tiles and a random length.
Rooms need to accessed unlike hallways.
