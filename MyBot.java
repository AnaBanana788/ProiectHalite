import java.util.ArrayList;
import java.util.List;
import java.lang.Math.*;

public class MyBot {
    public static void main(String[] args) throws java.io.IOException {

        final InitPackage iPackage = Networking.getInit();
        final int myID = iPackage.myID;
        final GameMap gameMap = iPackage.map;

        Networking.sendInit("MyJavaBot");

        while(true) {
            List<Move> moves = new ArrayList<Move>();

            Networking.updateFrame(gameMap);

            for (int y = 0; y < gameMap.height; y++) {
                for (int x = 0; x < gameMap.width; x++) {
                    final Location location = gameMap.getLocation(x, y);
                    final Site site = location.getSite();
                    if(site.owner == myID) {
						
                        final Location[] loc = new Location[4];
						loc[0] = gameMap.getLocation(x+1, y);
						loc[1] = gameMap.getLocation(x-1, y);
						loc[2] = gameMap.getLocation(x, y+1);
						loc[3] = gameMap.getLocation(x, y-1);
						final Site[] cub = new Site[4];
						int ord[] = new int[4];
						int sw = 0, i, val_min;
						
						for(i = 0; i < 4; i++)
							cub[i] = loc[i].getSite();
						
						
						
						for(i = 0; i < 4; i++)
							if( cub[i].owner != myID )   // daca nu este site-ul meu atunci ii retin productia intr-un vector
							{
								ord[i] = cub[i].strength;
								sw = 1;						// daca exista cel putin un site care nu e al meu atunci sw = 1
							}
							else						// daca nu e al meu ii dau valoare maxima ca sa nu il iau in calcul
								ord[i] = 256;
							
							if( sw == 0)			// toate site-urile din jur sunt ale mele
								moves.add(new Move(location, Direction.NORTH));// o sa modific astfel incat sa se duca unde e nevoie de ajutor
							else
							{
								val_min = Math.min(	Math.min(	Math.min(	cub[0].strength, cub[1].strength	), cub[2].strength	), cub[3].strength	);
								if( val_min < site.strength)
								{
									if( val_min == cub.strength[0] )
										moves.add(new Move(location, Direction.WEST));
									else
										if( val_min == cub.strength[1] )
											moves.add(new Move(location, Direction.EAST));
										else
											if( val_min == cub.strength[2] )
												moves.add(new Move(location, Direction.NORTH));
											else
												moves.add(new Move(location, Direction.SOUTH));
								}
								else
									moves.add(new Move(location, Direction.STILL));
							}
                    }
                }
            }
            Networking.sendFrame(moves);
        }
    }
}
