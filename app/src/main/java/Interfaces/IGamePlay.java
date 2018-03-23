package Interfaces;

import Models.Request;

public interface IGamePlay {
    void setupGame(Request request);
    //adjusted to void - they don't need to return anything to the Client - they updateStats the models directly:
    void addGameHistory(Request request);
    void discardDestCards(Request request);
    void drawDestCards(Request request);
    void takeFaceUpCard(Request request);
    void drawTrainCard(Request request);
    void claimRoute(Request request);
    void endTurn(Request request);
    void shuffleFaceUp(Request request);
    //include the updateClient:
    void updateClient(Request request); //(String authToken);
}
