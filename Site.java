package prjHalite;

public class Site {
	
	public int owner;
	public int strength;
	public final int production;
	
	public Site(int production) 
	{
        this.production = production;
    }
	
	//functie pentru testare/debug
	@Override
	public String toString()
	{
		return "Site owner: " + owner + " Site strength: " + strength + " Site production: " + production;
	}
	
}
