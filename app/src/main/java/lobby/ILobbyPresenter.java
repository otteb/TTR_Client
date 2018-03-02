package lobby;

import android.content.Context;

import Models.Game;

import java.util.ArrayList;

/**
 * Created by fjameson on 2/12/18.
 */

public interface ILobbyPresenter {
    public ArrayList<String> Players= new ArrayList<String>();
    boolean GameStarted = false;

    Game joinGame(Context context, Game g, String name);
    boolean startGame(Context context, Game game);


    Game createGame(Context context, ArrayList<String> players, String id, String username);

    void updateView();

}
