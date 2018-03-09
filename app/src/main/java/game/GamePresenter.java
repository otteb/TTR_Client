package game;

import android.content.Context;
import android.widget.Toast;

import java.util.Observable;

import Models.Gameplay.Player;
import Models.Result;
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