package prjHalite;

import java.util.Random;

public enum Direction {
    STILL, NORTH, EAST, SOUTH, WEST;

    public static final Direction[] DIRECTIONS = new Direction[]{STILL, NORTH, EAST, SOUTH, WEST};
    public static final Direction[] CARDINALS = new Direction[]{NORTH, EAST, SOUTH, WEST};
    
    //functie ce intoarce directia in functie de valoarea unui int dat
    //ca e mai rapid sa scrii un int decat o directie
    public static Direction outofInt(int dir)
    {
    	if(dir == 0)
    		return STILL;
    	
    	if(dir == 1)
    		return NORTH;
    	
    	if(dir == 2)
    		return EAST;
    	
    	if(dir == 3)
    		return SOUTH;
    	
    	if(dir == 4)
    		return WEST;
    		
    	return null;
    }

    //functie din starter_package
    //alege directia random
    public static Direction randomDirection() 
    {
        Direction[] values = values();
        return values[new Random().nextInt(values.length)];
    }
}
