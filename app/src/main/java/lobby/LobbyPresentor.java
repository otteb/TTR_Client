package lobby;

import android.content.Context;
import android.widget.Toast;


import java.util.ArrayList;

import Models.Game;
import Services.GuiFacade;


/**
 * Created by krommend on 2/1/18.
 */

public class LobbyPresentor implements ILobbyPresentor {

    GuiFacade guiFacade = new GuiFacade();
    ArrayList<String> players = new ArrayList<String>();
    boolean gameStarted;
    String p[] = {"p1", "p2", "p3", "p4", "p5"};


    @Override
    public Game joinGame(Context context, Game currentGame, String name) {

        boolean vacant = false;
        if(currentGame.getPlayers().size()<5)
        {
         currentGame = new Game();
                 guiFacade.joinGame(currentGame, name);
        }
       /* for (int i = 0; i < players.length; i++) {
            if (players[i].getText().equals("")) {
                players[i].setText(name);
                players[i].setVisibility(View.VISIBLE);
                currentGame.addPlayer(name);
                Toast.makeText(context, "game joined", Toast.LENGTH_SHORT).show();
                vacant = true;
                break;
            }

        }*/

        //if (!vacant) Toast.makeText(context, "game not joined", Toast.LENGTH_SHORT).show();
        //if (currentGame.getPlayers().size() > 1) {
         //   currentGame.setStartable(true);
       // }

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
    public Game createGame(Context context, ArrayList<String> players, String id) {

        Game myGame = new Game(players, id);
        myGame.addPlayer(id);
        guiFacade.createGame(myGame);
        Toast.makeText(context, "game created", Toast.LENGTH_SHORT).show();
        return myGame;
    }

    @Override
    public void updateView() {

    }


}

