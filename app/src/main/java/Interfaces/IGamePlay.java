package Interfaces;

import Models.Request;
import Models.Result;

public interface IGamePlay {
    void setupGame(Request request);
    Result addGameHistory(Request request);
    Result discardDestCards(Request request);
}
