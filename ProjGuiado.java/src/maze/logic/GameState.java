package maze.logic;
import maze.logic.Playing;
import maze.logic.End;

public class GameState
{

	public static enum State
	{
		PLAYING,END
	}

	public static State state;
	public static Playing playing;
	public static End end;
	
	/*public static void update()
	{
		switch(state)
		{
		case PLAYING:
			if (playing == null)
				playing = new Playing();
			playing.update();
			break;
			
		case END:
			if(end == null)
				end= new End();
			end.update(lab l);
			break;
			
			
			
			
		}
	}*/
}
