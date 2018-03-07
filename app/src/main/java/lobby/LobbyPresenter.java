package lobby;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import Interfaces.ILobbyPresenter;
import Models.Client;
import Models.Request;
import activities.MainActivity;
import Models.Gameplay.Game;
import Services.GUI.GuiFacade;


/**
 * Created by krommend on 2/1/18.
 */

public class LobbyPresenter implements ILobbyPresenter, Observer {

    private GuiFacade guiFacade = new GuiFacade();
    ArrayList<String> players = new ArrayList<String>();
    boolean gameStarted;
    String p[] = {"p1", "p2", "p3", "p4", "p5"};
    private Context context;
    private Request user = new Request();
    MainActivity lobbyFragment;

    public LobbyPresenter(Context c)
    {
        context = c;
        guiFacade.addObserver(this);
    }

    @Override
    public void joinGame(Context context, Game currentGame, String name)
    {
        //seeing if the game exists and if number of players is less than 5 and that it has not been started
        if (currentGame != null && currentGame.isJoinable()) {
            //seeing if you are already in the game
            for (int i = 0; i < currentGame.getPlayers().size(); i++) {
                if (currentGame.getPlayers().get(i).equals(name)) {
                    Toast.makeText(context, "Cannot join same game twice", Toast.LENGTH_SHORT).show();
                }
            }
            Client.getInstance().setAuthToken(user.getAuthToken());
            guiFacade.joinGame(currentGame, name);
        }
        else
        {
            Toast.makeText(context, "The Game is already full or active", Toast.LENGTH_SHORT).show();
        }
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
    public Game createGame(Context context, String id) {
        //TODO: FIGURE OUT WHAT TO RETURN HERE
        //This currently returns a new game and does not check with the server
        //How can we get access to the proper game object here?
        Game myGame = new Game(id);
//        myGame.addPlayer(user.getUsername());
        Client.getInstance().setAuthToken(user.getAuthToken());
        guiFacade.createGame(id);
        if(Client.getInstance().getGameMap().containsKey(id))
        {
            System.out.println("Game was created!");
        }
        else
        {
            System.out.println("Game not created");
        }
        return myGame; //Client.getInstance().getGameById(id); //Client.getInstance().getActiveGame();
    }

    @Override
    public void updateView() {

    }

    public void setUser(String username, String password, String authToken)
    {
        user.setAuthToken(authToken);
        user.setPassword(password);
        user.setUsername(username);
    }


    @Override
    public void update(Observable observable, Object result) {
        if(result.equals("create"))
        {
            lobbyFragment= (MainActivity)((Activity)context);
            lobbyFragment.updateGamesList();
//            lobbyFragment.updatePlayers();
        }
        else if(result.equals("join"))
        {
            lobbyFragment= (MainActivity)((Activity)context);
            lobbyFragment.updatePlayers();
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
        observable.hasChanged();
    }
}

