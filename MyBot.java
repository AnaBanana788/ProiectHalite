import java.util.ArrayList;
import java.util.List;
import java.lang.Math.*;

public class MyBot {
    public static void main(String[] args) throws java.io.IOException {

        final InitPackage iPackage = Networking.getInit();
        final int myID = iPackage.myID;
        final GameMap gameMap = iPackage.map;
		
		Networking.sendInit("RambotTheKiller");

		while(true) {
            List<Move> moves = new ArrayList<Move>();

            Networking.updateFrame(gameMap);

			final int l = gameMap.height;
			final int r = gameMap.width;
			
			
			
            for (int y = 0; y < l; y++) {
                for (int x = 0; x < r; x++) {
                    final Location location = gameMap.getLocation(x, y);
                    final Site site = location.getSite();
                    if(site.owner == myID) {
						
						
						
						
						final Location[] loc = new Location[4];
												
                        loc[0] = gameMap.getLocation(location, Direction.EAST);//est
						loc[1] = gameMap.getLocation(location, Direction.WEST);//vest
						loc[2] = gameMap.getLocation(location, Direction.SOUTH);//nord
						loc[3] = gameMap.getLocation(location, Direction.NORTH);//sud
						
												
						final Site[] cub = new Site[4];
						int ord[] = new int[4];
						int sw = 0, sw2, i, j, val_min = 0, val_min1 = 0, val_min2 = 0, max;	
						
						for(i = 0; i < 4; i++)
							cub[i] = loc[i].getSite();
						
						
						for(i = 0; i < 4; i++)
							if( cub[i].owner != myID )   // daca nu este site-ul meu atunci ii retin productia intr-un vector
							{
								ord[i] = (cub[i].strength);
								sw = 1;						// daca exista cel putin un site care nu e al meu atunci sw = 1
							}
							else						// daca nu e al meu ii dau valoare maxima ca sa nu il iau in calcul
								ord[i] = 257;
							
							if(sw == 0) 
							{								// toate site-urile din jur sunt ale mele
								
								if(site.strength > 15){
									
									sw2 =0;
									
									Location[] pos = new Location[4];
												
									pos[0] = gameMap.getLocation(location, Direction.EAST);//est
									pos[1] = gameMap.getLocation(location, Direction.WEST);//vest
									pos[2] = gameMap.getLocation(location, Direction.SOUTH);//nord
									pos[3] = gameMap.getLocation(location, Direction.NORTH);//sud
									
									
									
									Site[] sit = new Site[4];
									for(i = 0; i < 4; i++)
										cub[i] = loc[i].getSite();
									
									if(l > r)
										max = l;
									else
										max = r;
									j = 0;
									
									while( (sw2 == 0) && (j < max) )
									{
										
										sit[0] = pos[0].getSite();
										if(sit[0].owner != myID)
										{
											moves.add(new Move(location, Direction.EAST));
											sw2 = 1;
										}
										else
											pos[0] = gameMap.getLocation(pos[0], Direction.EAST);//est
										
																				
										sit[1] = pos[1].getSite();
										if(sit[1].owner != myID)
										{
											moves.add(new Move(location, Direction.WEST));
											sw2 = 1;
										}
										else
											pos[1] = gameMap.getLocation(pos[1], Direction.WEST);
										
										
										sit[2] = pos[2].getSite();
										if(sit[2].owner != myID)
										{
											moves.add(new Move(location, Direction.SOUTH));
											sw2 = 1;
										}
										else
											pos[2] = gameMap.getLocation(pos[2], Direction.SOUTH);
										
																				
										
										sit[3] = pos[3].getSite();
										if(sit[3].owner != myID)
										{
											moves.add(new Move(location, Direction.NORTH));
											sw2 = 1;
										}
										else
											pos[3] = gameMap.getLocation(pos[3], Direction.NORTH);
											
										j++;
										
									}
									
									if(j == max)
									moves.add(new Move(location, Direction.NORTH));
								
								}
									
								
							}
							else
							{
								if(ord[0] < ord[1])
									val_min1 = ord[0];
								else
									val_min1 = ord[1];
								
								if(ord[2] < ord[3])
									val_min2 = ord[2];
								else
									val_min2 = ord[3];
								
								if(val_min1 < val_min2)
									val_min = val_min1;
								else
									val_min = val_min2;
								
								
								if( val_min < site.strength)
								{
									if( val_min == cub[0].strength )
										moves.add(new Move(location, Direction.EAST));
									else
										if( val_min == cub[1].strength )
											moves.add(new Move(location, Direction.WEST));
										else
											if( val_min == cub[3].strength ) 
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
