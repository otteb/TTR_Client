package lobby;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import activities.MainActivity;
import Models.Game;
import Services.GuiFacade;

import static Models.Client.getInstance;


/**
 * Created by krommend on 2/1/18.
 */

public class LobbyPresenter implements ILobbyPresenter, Observer {

    private GuiFacade guiFacade = new GuiFacade();
    ArrayList<String> players = new ArrayList<String>();
    boolean gameStarted;
    String p[] = {"p1", "p2", "p3", "p4", "p5"};
    private Context context;

    public LobbyPresenter(Context c)
    {
        context = c;
        guiFacade.addObserver(this);
    }

    @Override
    public Game joinGame(Context context, Game currentGame, String name)
    {
        if (currentGame != null) {
            for (int i = 0; i < currentGame.getPlayers().size(); i++) {
                if (currentGame.getPlayers().get(i).equals(name)) {
                    Toast.makeText(context, "Cannot join same game twice", Toast.LENGTH_SHORT).show();
                    return currentGame;
                }
            }
        }
        else return null;

//        boolean vacant = false;
        if(currentGame.getPlayers().size()<5)
        {
            guiFacade.joinGame(currentGame, name);
        }
        return currentGame;
    }


    @Override
    public boolean startGame(Context context, Game game) {
        if (game.getPlayers().size() > 1) {
            Toast.makeText(context, "game started", Toast.LENGTH_SHORT).show();
            guiFacade.startGame(game.getId());
            return true;
        }
        else Toast.makeText(context, "game not started", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public Game createGame(Context context, ArrayList<String> players, String id, String username) {
        //TODO: FIGURE OUT WHAT TO RETURN HERE
        //This currently returns a new game and does not check with the server
        //How can we get access to the proper game object here?
        Game myGame = new Game(players, id);
        myGame.addPlayer(username);
        guiFacade.createGame(id);
        Toast.makeText(context, "game created", Toast.LENGTH_SHORT).show();
        return myGame; //Client.getInstance().getActiveGame();
    }

    @Override
    public void updateView() {

    }


    @Override
    public void update(Observable observable, Object result) {
        if(result.equals("create"))
        {
            MainActivity lobbyFragment= (MainActivity)((Activity)context);
            result = "";
            lobbyFragment.updateCreate(getInstance().getActiveGame());

        }
        else if(result.equals("join"))
        {
            MainActivity lobbyFragment= (MainActivity)((Activity)context);
            result= "";
            lobbyFragment.updateJoin(getInstance().getActiveGame());
        }
        else if (result.equals("start"))
        {
            //start a game
            MainActivity mainActivity = (MainActivity) context;
            result = "";
            mainActivity.openGame();
        }
        else
        {
            Toast.makeText(context, (CharSequence) result, Toast.LENGTH_SHORT).show();
        }
        observable.hasChanged();
//        Result newResult = (Result)result;
        //      newResult.getErrorMsg();
    }

    public void addPlayer(String player)
    {

    }

}

