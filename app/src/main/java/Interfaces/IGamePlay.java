package Interfaces;

import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 3/6/18.
 */

public interface IGamePlay {
    void setupGame(Request request);
    //adjusted to void - they don't need to return anything to the Client - they update the models directly:
    void addGameHistory(Request request);
    void discardDestCards(Request request);
    //include the updateClient:
    void updateClient(Request request); //(String authToken);
}
