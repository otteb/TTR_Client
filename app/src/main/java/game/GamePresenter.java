package game;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;

import Models.Client;
import Models.Gameplay.ActiveGame;
import Models.Gameplay.Player;
import Models.Gameplay.Route;
import Models.Request;
import Models.Result;
import Services.Commands.GamePlayServices;
import activities.MainActivity;

/**
 * Created by fjameson on 2/28/18.
 */

public class GamePresenter {
    Context context;
    MainActivity mainActivity;
    Player player;
    public GamePresenter(Context c) {
        context=c;
        //guiFacade.addObserver(this);
    }
    public Result switchToLobby(Context c)
    {
        return null;
    }

    public void switchToStats(Context c)
    {
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToStats();
    }

    public void switchToCards(Context c, Boolean destinationCardSetup)
    {
        player = new Player();
        player.setName("kip");
        context=c;
        mainActivity = (MainActivity) context;
        mainActivity.switchToCards(player.getName(), destinationCardSetup);
    }


    public Result claimRoute(Context c)
    {
        //draw line -- new color for that player

        //increment routes
        Route rt = new Route();
        ActiveGame.getInstance().getMyPlayer().getClaimedRoutes().add(rt);

        //update player points
        ActiveGame.getInstance().getMyPlayer().addPoints(7);

        //update trains left
        ActiveGame.getInstance().getMyPlayer().decNumTrains(4);

        //add game history
        Request fakeReq = new Request();
//        fakeReq.setGameId(ActiveGame.getInstance().getId());
        String action = Client.getInstance().getUserName() + " claimed a route.";
        fakeReq.setAction(action);
        GamePlayServices.getInstance().addGameHistory(fakeReq);

        //increment turn
        ActiveGame.getInstance().incTurn();
        String username = ActiveGame.getInstance().getActivePlayer().getName();
        Toast.makeText(c, "It\'s " + username + "\'s turn!", Toast.LENGTH_SHORT).show();
        return null;
    }



//    //TODO change the update for the GamePresenter
//    @Override
//    public void update(Observable o, Object authToken) {
//
//        if (!authToken.equals("create") && !authToken.equals("join") && !authToken.equals("start")) {
//            if (user != null) {
//                if (authToken.equals("ERROR: Invalid Registration") || authToken.equals("ERROR: Incorrect username/password combination")) {
//                    Toast.makeText(context, (CharSequence) authToken, Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    user.setAuthToken((String) authToken);
//                    mainActivity = (MainActivity) context;
//                    mainActivity.switchToLobby(user);
//                    user = null;
//                }
//            }
//        }
//    }

}