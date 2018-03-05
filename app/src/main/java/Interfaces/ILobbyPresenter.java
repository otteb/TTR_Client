package Interfaces;

import android.content.Context;

import Models.Gameplay.Game;

import java.util.ArrayList;

/**
 * Created by fjameson on 2/12/18.
 */

public interface ILobbyPresenter {
    public ArrayList<String> Players= new ArrayList<String>();
    boolean GameStarted = false;

    void joinGame(Context context, Game g, String name);
    boolean startGame(Context context, Game game);


    Game createGame(Context context, String id);

    void updateView();

}
