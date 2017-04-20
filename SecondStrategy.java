public class SecondStrategy implements NextMove{

	@Override
	public Move nextMove(GameMap gameMap, Location location) {
		// TODO Auto-generated method stub
		
		final Site currSite = gameMap.getSite(location);
		Direction enemyDir = null;
		double bestHeuristic = -1.0;
		boolean isOnBorder = false;
		
		//Cautam cea mai buna directie in care sa mergem sa ocupam o "casuta" cu ajutorul euristicii
		for( Direction direction: Direction.CARDINALS) 
		{
			Site neighbour = gameMap.getSite(location, direction);
			
			if( neighbour.owner != currSite.owner )
			{
				isOnBorder = true;
				double currHeuristic = heuristics(gameMap, neighbour, gameMap.getLocation(location, direction), currSite.owner);
				if( currHeuristic > bestHeuristic )
				{
					enemyDir = direction;
					bestHeuristic = currHeuristic;
				}
			}
		}
		
		//Daca suntem la marginea taramlui nostru si putem cuceri ce e in directia data, cucerim	
		if( (isOnBorder) && (gameMap.getSite(location, enemyDir).strength < currSite.strength) )
		{
			return new Move(location, enemyDir);
		}
			
		//10-le e testat prin incercari, a nu se modifica
		if (currSite.strength < (10 * currSite.production)) 
		{
	        return new Move(location, Direction.STILL);
	    }
			
		//Daca suntem in interiorul taramului nostru, ne indreptam spre cel mai apropiat inamic
		if (!isOnBorder)
		{
			return new Move(location, findClosestEnemyDirection(gameMap, location));
		}
			
		return new Move(location, Direction.STILL);
	}
	
	//Euristica ceva mai avansata
	private double heuristics(GameMap gameMap, Site site, Location location, int myId)
	{
		//Daca casuta la care suntem e neutra, facem euristica stupida
		if (site.owner == 0 && site.strength > 0) 
		{
			return (site.production*1.0) / site.strength;
		}
		//Altfel, calculam cu cat trebuie sa atacam inamicii astfel incat sa facem "overkill"(parca??; ala de pierd si casutele adiacente viata)
		else 
		{
			double neededStrength = 0;
			for( Direction direction: Direction.CARDINALS) 
			{
				final Site neighbour = gameMap.getSite(location, direction);
				if (neighbour.owner != 0 && neighbour.owner != myId) 
					neededStrength += neighbour.strength;
			}
			return neededStrength;
		}
	}
	
	//Caut prima casuta care nu ne apartine in fiecare directie si o retin pe cea mai apropiata
	private Direction findClosestEnemyDirection(GameMap gameMap, Location location)
	{
		final Site currSite = gameMap.getSite(location);
		int minDistance = Math.min(gameMap.width, gameMap.height) / 2;
		Direction returnDirection = Direction.EAST;
		
		for( Direction direction: Direction.CARDINALS)
		{
			int distance = 0;
			Location currLoc = location;
			Site site = gameMap.getSite(currLoc, direction);
			
			//Stim sigur ca owner la currSite suntem noi(ca altfel nu intram pe cazul asta)
			while (site.owner == currSite.owner && distance < minDistance)
			{
				distance++;
				currLoc = gameMap.getLocation(currLoc, direction);
				site = gameMap.getSite(currLoc);
			}

			if (distance < minDistance)
			{
				returnDirection = direction;
				minDistance = distance;
			}
		}
		
		return returnDirection;
	}

}
