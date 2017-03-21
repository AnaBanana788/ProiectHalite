package prjHalite;

import java.util.Arrays;

public class FirstStrategy  implements NextMove {

	@Override
	public Move nextMove(GameMap gameMap, Location location) {
		// TODO Auto-generated method stub
		 for (Direction direction : Arrays.asList(Direction.CARDINALS))
			 if( (gameMap.getSite(location, direction).owner != gameMap.getSite(location).owner) && (gameMap.getSite(location, direction).strength < gameMap.getSite(location).strength) )
			 {
			 	return new Move(location, direction);
			 }
		
		return null;
	}

}
