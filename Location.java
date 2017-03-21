package prjHalite;

public class Location {
	
	// Public for backward compatibility
    public final int x, y;
    private final Site site;

    public Location(int x, int y, Site site) 
    {
        this.x = x;
        this.y = y;
        this.site = site;
    }

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }

    public Site getSite() 
    {
        return site;
    }
    
    //functie de calculat distanta dintre locatii
    //probabil redundanta(vezi getDistance din GameMap.java) 
    public double distanceFromLocation(Location location)
    {
        return (double)(location.x - x) + (double)(location.y - y);
    }
    
    //functie pentru testare/debug
    @Override
    public String toString()
    {
    	return "Location x: " + x + " Location y: " + y + " " + site.toString();
    }

}