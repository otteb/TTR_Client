package Interfaces;

import Models.Request;

public interface ILobby {
    //for joinGame, the client might want to include the ability to add other usernames to the game
    //the UML said + joinGame(String token, String gameId):boolean, but maybe we call token something else
    //so it makes more sense...

    void createGame(Request request); //(String authToken, String gameId);

    void joinGame(Request request); //(String authToken, String gameId);

    void leaveGame(Request request); //String authToken, String gameId);

    void startGame(Request request); //(String authToken, String gameId);

    void updateClient(Request request); //(String authToken);
}
