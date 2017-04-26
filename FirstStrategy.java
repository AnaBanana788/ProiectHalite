public class FirstStrategy  implements NextMove {

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
				if( heuristics(neighbour) > bestHeuristic )
				{
					enemyDir = direction;
					bestHeuristic = heuristics(neighbour);
				}
			}
		}
		
		//Daca suntem la marginea taramlui nostru si putem cuceri ce e in directia data, cucerim
		if( (isOnBorder) && (gameMap.getSite(location, enemyDir).strength < currSite.strength) )
		{
			return new Move(location, enemyDir);
		}
		
        //Nu putem lasa o patratica cu productie mare fara aparare
        //Nu putem astepta ca o patratica cu productie mica sa ajunga la o limita de strength
		//5-le e testat si prin incercari, a nu se modifica
		if (currSite.strength < (5 * currSite.production))
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
	
	//Euristica stupida
	private double heuristics(Site site)
	{
		if (site.production > 0)
			return (site.production*1.0)/site.strength;
		return (site.production*1.0);
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
