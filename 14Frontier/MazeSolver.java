public class MazeSolver{
    private Maze maze;
    private Frontier frontier;

    public MazeSolver(String mazeText){
	maze = new Maze(mazeText);
    }

    public boolean solve(){
	return solve(3);
    }

    public boolean solve(int mode){
	if(mode == 0){
	    frontier = new FrontierQueue();
	}else if(mode == 1){
	    frontier = new FrontierStack();
	}else if(mode == 2 || mode == 3){
	    frontier = new FrontierPriorityQueue();
	}else{
	    throw new IllegalArgumentException();
	}
	frontier.add(maze.getStart());
	while(frontier.hasNext()){
	    Location cur = frontier.next();
	    Location[] neighbors;
	    if(cur != maze.getStart()){
		maze.set(cur.getX(),cur.getY(),'.');
	    }
	    if(mode != 3){
		neighbors = maze.getNeighbors(cur,0);
	    }else{
		neighbors = maze.getNeighbors(cur,1);
	    }
	    for(int i=0; i<4; i++){
		if(neighbors[i] != null){
		    if(maze.get(neighbors[i].getX(),neighbors[i].getY()) == 'E'){
			while(neighbors[i].hasPrev()){
			    neighbors[i] = neighbors[i].getPrev();
			    maze.set(neighbors[i].getX(),neighbors[i].getY(),'@');
			}
			return true;
		    }else{
			frontier.add(neighbors[i]);
			maze.set(neighbors[i].getX(),neighbors[i].getY(),'?');
		    }
		}
	    }
	    System.out.println(maze.toStringColor());
	}
	return false;
    }
    public String toString(){
	return maze.toString();
    }
}
