package Interfaces;

import Models.Request;
import Models.Result;

/**
 * Created by brianotte on 3/6/18.
 */

public interface IGamePlay {
    void setupGame(Request request);
    Result addGameHistory(Request request);
    Result discardDestCards(Request request);
}
