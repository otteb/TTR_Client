package lobby;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

import Interfaces.ILobbyPresenter;
import Models.Client;
import Models.Request;
import activities.MainActivity;
import Models.Gameplay.Game;
import Services.GUI.GuiFacade;


public class LobbyPresenter implements ILobbyPresenter, Observer {

    private GuiFacade guiFacade = new GuiFacade();
    private Context context;
    private Request user = new Request();
    private MainActivity lobbyFragment;

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
                if (currentGame.isValidPlayer(name)) {
                    Toast.makeText(context, "Cannot join same game twice", Toast.LENGTH_SHORT).show();
                }
            }
            Client.getInstance().setAuthToken(user.getAuthToken());
            guiFacade.joinGame(currentGame, name);
        }
        else
        {
            Toast.makeText(context, "That Game is already full or active", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void startGame(Context context, Game game) {
        if (game.getPlayers().size() > 1 && game.isValidPlayer(Client.getInstance().getUserName()))
        {
            Toast.makeText(context, "Starting game", Toast.LENGTH_SHORT).show();
            guiFacade.startGame(game.getId());
        }
        else Toast.makeText(context, "You cannot start that game", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Game createGame(Context context, String id) {
        Game myGame = new Game(id);
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
            //lobbyFragment.updatePlayers();
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
        observable.hasChanged();
    }
}

