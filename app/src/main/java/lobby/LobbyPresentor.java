package lobby;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Activities.MainActivity;
import Client_Server_Communication.Poller;
import Models.Client;
import Models.Game;
import Services.GuiFacade;


/**
 * Created by krommend on 2/1/18.
 */

public class LobbyPresentor implements ILobbyPresentor, Observer {

    GuiFacade guiFacade = new GuiFacade();
    ArrayList<String> players = new ArrayList<String>();
    boolean gameStarted;
    String p[] = {"p1", "p2", "p3", "p4", "p5"};
    Context context;

    public LobbyPresentor(Context c)
    {
        context = c;
        Poller poller = new Poller();
        poller.runLobbyCommands();
        guiFacade.addObserver(this);
    }


    @Override
    public Game joinGame(Context context, Game currentGame, String name) {


        if (currentGame != null) {
            for (int i = 0; i < currentGame.getPlayers().size(); i++) {
                if (currentGame.getPlayers().get(i).equals(name)) {
                    Toast.makeText(context, "Cannot join same game twice", Toast.LENGTH_SHORT).show();
                    return currentGame;
                }
            }
        }
        else return currentGame;

        boolean vacant = false;
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
            return true;
        }
        else Toast.makeText(context, "game not started", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public Game createGame(Context context, ArrayList<String> players, String id, String username) {

        Game myGame = new Game(players, id);
        myGame.addPlayer(username);
        guiFacade.createGame(myGame);
        Toast.makeText(context, "game created", Toast.LENGTH_SHORT).show();
        return myGame;
    }

    @Override
    public void updateView() {

    }


    @Override
    public void update(Observable observable, Object result) {
        MainActivity lobbyFragment= (MainActivity)((Activity)context);
        if(result.equals("create"))
        {
            lobbyFragment.updateCreate(Client.getInstance().getActiveGame());

        }
        else if(result.equals("join"))
        {
            lobbyFragment.updateJoin(Client.getInstance().getActiveGame());
        }
        else if (result.equals("start"))
        {
            //start a game
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.openGame();
        }
        else
        {
            Toast.makeText(context, (CharSequence) result, Toast.LENGTH_SHORT).show();
        }
//        Result newResult = (Result)result;
        //      newResult.getErrorMsg();
    }
}

