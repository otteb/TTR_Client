package Interfaces;

import android.content.Context;

import Models.Gameplay.Game;


public interface ILobbyPresenter {
    void joinGame(Context context, Game g, String name);
    void startGame(Context context, Game game);
    Game createGame(Context context, String id);
}
