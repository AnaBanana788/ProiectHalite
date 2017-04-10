public interface NextMove{
	
	//interfata ca sa imi fie usor sa modific strategiiile de deplasare
	//adica ca sa nu trebuiasca sa modific in MyBot.java si sa sterg ce am scris
	//(pentru cazul in care vreo strategie anterioara se dovedeste mai rapida sau mai folositoare)
    public Move nextMove(final GameMap gameMap, final Location location);
}