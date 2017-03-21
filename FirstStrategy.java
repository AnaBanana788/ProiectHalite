package prjHalite;

import java.util.Arrays;

public class FirstStrategy  implements NextMove {

	@Override
	public Move nextMove(GameMap gameMap, Location location) {
		// TODO Auto-generated method stub
		
		//verific daca e vreo casuta libera in jurul pozitiei mele actuale pe care o pot cuceri
		 for (Direction direction : Arrays.asList(Direction.CARDINALS))
			 if( (gameMap.getSite(location, direction).owner != gameMap.getSite(location).owner) && (gameMap.getSite(location, direction).strength < gameMap.getSite(location).strength) )
			 {
			 	return new Move(location, direction);
			 }
		 
		 //daca nu avem ce sa cucerim in jurul nostru
		 //si nu am lasa destule resurse in urma pentru protectia site-ului, stam
		 if( gameMap.getSite(location).strength < 2*gameMap.getSite(location).production )
		 {
			 return new Move(location,Direction.STILL);
		 }
		 
		 //altfel, mergem sa ajutam in alta parte
		 //TO-DO: ne-ar trebui o strategie pentru mers in cazul asta(sa cautam borduri sau ceva)
		 return new Move(location, Direction.randomDirection());
		 
	}

}
